package by.kozlov.utils;

import by.kozlov.spring.entity.*;
import jakarta.persistence.EntityManager;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@UtilityClass
public class TestDataImporter {
    public void importData(EntityManager entityManager) {

        Brigade brigade7 = saveBrigade(entityManager,"Brigade 7");
        Brigade brigade8 = saveBrigade(entityManager,"Brigade 8");
        Brigade brigade9 = saveBrigade(entityManager,"Brigade 9");

        Worker pavel = saveWorker(entityManager,"Pavel","Ivanov","extruder operator",brigade7,"braga@tut.by");
        Worker ivan = saveWorker(entityManager,"Ivan","Petrov","extruder operator",brigade7,"guga@tut.by");
        Worker petr = saveWorker(entityManager,"Petr","Grib","extruder foreman",brigade8,"riga@tut.by");
        Worker sergey = saveWorker(entityManager,"Sergey","Rak","extruder operator",brigade8,"biba@tut.by");
        Worker vitold = saveWorker(entityManager,"Vitold","Himmler","extruder operator",brigade9,"boba@tut.by");
        Worker mike = saveWorker(entityManager,"Mike","Miller","extruder operator",brigade9,"viva@tut.by");

        Material material1 = saveMaterial(entityManager,"K30","Gloom");
        Material material2 = saveMaterial(entityManager,"K40","Big gloom");
        Material material3 = saveMaterial(entityManager,"K50","Very big gloom");

        Set set1 = saveSet(entityManager,"5440",12,10.1);
        Set set2 = saveSet(entityManager,"6501",11,11.2);
        Set set3 = saveSet(entityManager,"4371",8,8.5);
        Set set4 = saveSet(entityManager,"54327",7,2.34);

        saveProduction(entityManager,pavel,set1,112,LocalDate.of(2023,Month.FEBRUARY,11));
        saveProduction(entityManager,ivan,set1,11,LocalDate.of(2023,Month.MARCH,1));
        saveProduction(entityManager,petr,set2,44,LocalDate.of(2023,Month.FEBRUARY,9));
        saveProduction(entityManager,sergey,set2,144,LocalDate.of(2023,Month.APRIL,11));
        saveProduction(entityManager,vitold,set3,52,LocalDate.of(2023,Month.MAY,23));
        saveProduction(entityManager,mike,set3,22,LocalDate.of(2023,Month.NOVEMBER,1));
        saveProduction(entityManager,pavel,set4,88,LocalDate.of(2023,Month.OCTOBER,13));

        saveRequirement(entityManager,set1,material1,7.8,123);
        saveRequirement(entityManager,set1,material2,6.8,123);
        saveRequirement(entityManager,set2,material2,5.8,400);
        saveRequirement(entityManager,set3,material3,3.8,10);
        saveRequirement(entityManager,set4,material3,4.8,245);
        saveRequirement(entityManager,set2,material2,2.8,328);

        saveMaterialsProduction(entityManager,material2,brigade7,124.2,LocalDate.of(2023,Month.MAY,14));
        saveMaterialsProduction(entityManager,material2,brigade7,600.0,LocalDate.of(2023,Month.APRIL,24));
        saveMaterialsProduction(entityManager,material3,brigade8,150.5,LocalDate.of(2023,Month.SEPTEMBER,23));
        saveMaterialsProduction(entityManager,material3,brigade8,230.0,LocalDate.of(2023,Month.JANUARY,11));
        saveMaterialsProduction(entityManager,material2,brigade9,11.3,LocalDate.of(2023,Month.JULY,4));
        saveMaterialsProduction(entityManager,material2,brigade9,12.0,LocalDate.of(2023,Month.MARCH,8));
        saveMaterialsProduction(entityManager,material3,brigade9,560.4,LocalDate.of(2023,Month.MAY,9));

    }

    private Brigade saveBrigade(EntityManager entityManager,String nameOfBrigade) {
        Brigade brigade =  Brigade.builder()
                .nameOfBrigade(nameOfBrigade)
                .build();
        entityManager.merge(brigade);

        return brigade;
    }

    private Worker saveWorker(EntityManager entityManager,
                              String name,
                              String surname,
                              String speciality,
                              Brigade brigade,
                              String email) {
        Worker worker = Worker.builder()
                .nameOfWorker(name)
                .surnameOfWorker(surname)
                .speciality(speciality)
                .brigade(brigade)
                .email(email)
                .build();

        entityManager.merge(worker);
        return worker;
    }

    private Material saveMaterial(EntityManager entityManager, String name, String description){

        Material material = Material.builder()
                .nameOfMaterial(name)
                .description(description)
                .build();
        entityManager.merge(material);
        return material;
    }

    private Set saveSet(EntityManager entityManager,String name,Integer numParts,Double rate){

        var set = Set.builder()
                .nameOfSet(name)
                .numberOfPartsIncluded(numParts)
                .rateOfSet(rate)
                .build();
        entityManager.merge(set);
        return set;
    }

    private void saveProduction(EntityManager entityManager,
                                      Worker worker,
                                      Set set,
                                      Integer madeSets,
                                      LocalDate dateOfProduction){

        var production = Production.builder()
                .madeSets(madeSets)
                .dateOfProduction(dateOfProduction)
                .build();
        production.setWorker(worker);
        production.setSet(set);
        entityManager.merge(production);
    }

    private void saveRequirement(EntityManager entityManager,
                                 Set set,
                                 Material material,
                                 Double unitCost,
                                 Integer totalSets) {
        var requirement = Requirement.builder()
                .set(set)
                .material(material)
                .unitCost(unitCost)
                .totalSets(totalSets)
                .build();
        entityManager.merge(requirement);
    }

    private void saveMaterialsProduction(EntityManager entityManager,
                                                    Material material,
                                                    Brigade brigade,
                                                    Double quantity,
                                                    LocalDate dateOfProduction) {

        var materialsProduction = MaterialsProduction.builder()
                .material(material)
                .brigade(brigade)
                .quantity(quantity)
                .dateOfProduction(dateOfProduction)
                .build();

        entityManager.merge(materialsProduction);
    }

}

package by.kozlov.utils;

import by.kozlov.hibernate.starter.entity.*;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.time.LocalDate;
import java.time.Month;

@UtilityClass
public class TestDataImporter {

    public void importData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        Brigade brigade7 = saveBrigade(session,"Brigade 7");
        Brigade brigade8 = saveBrigade(session,"Brigade 8");
        Brigade brigade9 = saveBrigade(session,"Brigade 9");

        Worker pavel = saveWorker(session,"Pavel","Ivanov","extruder operator",brigade7,"braga@tut.by");
        Worker ivan = saveWorker(session,"Ivan","Petrov","extruder operator",brigade7,"guga@tut.by");
        Worker petr = saveWorker(session,"Petr","Grib","extruder foreman",brigade8,"riga@tut.by");
        Worker sergey = saveWorker(session,"Sergey","Rak","extruder operator",brigade8,"biba@tut.by");
        Worker vitold = saveWorker(session,"Vitold","Himmler","extruder operator",brigade9,"boba@tut.by");
        Worker mike = saveWorker(session,"Mike","Miller","extruder operator",brigade9,"viva@tut.by");

        Material material1 = saveMaterial(session,"K30","Gloom");
        Material material2 = saveMaterial(session,"K40","Big gloom");
        Material material3 = saveMaterial(session,"K50","Very big gloom");

        Set set1 = saveSet(session,"5440",12,10.1);
        Set set2 = saveSet(session,"6501",11,11.2);
        Set set3 = saveSet(session,"4371",8,8.5);
        Set set4 = saveSet(session,"54327",7,2.34);

        saveProduction(session,pavel,set1,112,LocalDate.of(2023,Month.FEBRUARY,11));
        saveProduction(session,ivan,set1,11,LocalDate.of(2023,Month.MARCH,1));
        saveProduction(session,petr,set2,44,LocalDate.of(2023,Month.FEBRUARY,9));
        saveProduction(session,sergey,set2,144,LocalDate.of(2023,Month.APRIL,11));
        saveProduction(session,vitold,set3,52,LocalDate.of(2023,Month.MAY,23));
        saveProduction(session,mike,set3,22,LocalDate.of(2023,Month.NOVEMBER,1));
        saveProduction(session,pavel,set4,88,LocalDate.of(2023,Month.OCTOBER,13));

        saveRequirement(session,set1,material1,7.8,123);
        saveRequirement(session,set1,material2,6.8,123);
        saveRequirement(session,set2,material1,5.8,400);
        saveRequirement(session,set3,material3,3.8,10);
        saveRequirement(session,set4,material3,4.8,245);
        saveRequirement(session,set2,material2,2.8,400);

        saveMaterialsProduction(session,material1,brigade7,124.2,LocalDate.of(2023,Month.MAY,14));
        saveMaterialsProduction(session,material2,brigade7,600.0,LocalDate.of(2023,Month.APRIL,24));
        saveMaterialsProduction(session,material1,brigade8,150.5,LocalDate.of(2023,Month.SEPTEMBER,23));
        saveMaterialsProduction(session,material3,brigade8,230.0,LocalDate.of(2023,Month.JANUARY,11));
        saveMaterialsProduction(session,material1,brigade9,11.3,LocalDate.of(2023,Month.JULY,4));
        saveMaterialsProduction(session,material2,brigade9,12.0,LocalDate.of(2023,Month.MARCH,8));
        saveMaterialsProduction(session,material3,brigade9,560.4,LocalDate.of(2023,Month.MAY,9));

    }

    private Brigade saveBrigade(Session session,String nameOfBrigade) {
        Brigade brigade =  Brigade.builder()
                .nameOfBrigade(nameOfBrigade)
                .build();
        session.save(brigade);

        return brigade;
    }

    private Worker saveWorker(Session session,
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

        session.save(worker);
        return worker;
    }

    private Material saveMaterial(Session session, String name, String description){

        Material material = Material.builder()
                .nameOfMaterial(name)
                .description(description)
                .build();
        session.save(material);
        return material;
    }

    private Set saveSet(Session session,String name,Integer numParts,Double rate){

        var set = Set.builder()
                .nameOfSet(name)
                .numberOfPartsIncluded(numParts)
                .rateOfSet(rate)
                .build();
        session.save(set);
        return set;
    }

    private void saveProduction(Session session,
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
        session.save(production);
    }

    private void saveRequirement(Session session,
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
        session.save(requirement);
    }

    private void saveMaterialsProduction(Session session,
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

        session.save(materialsProduction);
    }

}

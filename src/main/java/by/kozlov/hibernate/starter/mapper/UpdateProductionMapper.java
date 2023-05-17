package by.kozlov.hibernate.starter.mapper;

import by.kozlov.hibernate.starter.dao.SetDao;
import by.kozlov.hibernate.starter.dao.SetRepository;
import by.kozlov.hibernate.starter.dao.WorkerDao;
import by.kozlov.hibernate.starter.dao.WorkerRepository;
import by.kozlov.hibernate.starter.dto.UpdateProductionDto;
import by.kozlov.hibernate.starter.entity.Production;
import by.kozlov.hibernate.starter.utils.HibernateUtil;
import by.kozlov.hibernate.starter.utils.LocalDateFormatter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
public class UpdateProductionMapper implements Mapper<UpdateProductionDto, Production> {

    private final SetRepository setRepository;
    private final WorkerRepository workerRepository;

    @Override
    public Production mapFrom(UpdateProductionDto object) {

            return Production.builder()
                    .id(Integer.parseInt(object.getId()))
                    .worker(workerRepository.findById(Integer.parseInt(object.getWorker())).orElseThrow())
                    .set(setRepository.findById(Integer.parseInt(object.getSet())).orElseThrow())
                    .madeSets(Integer.parseInt(object.getMadeSets()))
                    .dateOfProduction(LocalDateFormatter.format(object.getDateOfProduction()))
                    .build();

    }
}

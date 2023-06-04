package by.kozlov.spring.service;

import by.kozlov.spring.dto.SetDto;
import by.kozlov.spring.dto.WorkersSetsDto;
import by.kozlov.spring.mapper.SetMapper;
import by.kozlov.spring.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class SetService {
    private final SetMapper setMapper;
    private final SetRepository setRepository;

    @Autowired
    public SetService(SetRepository setRepository,SetMapper setMapper) {
        this.setRepository = setRepository;
        this.setMapper = setMapper;
    }

    public List<SetDto> findAll() {
        return setRepository.findAll().stream()
                .map(setMapper::mapFrom).collect(Collectors.toList());
    }

    public Optional<SetDto> findById(Integer id) {
        return setRepository.findById(id)
                .map(setMapper::mapFrom);
    }

    public List<SetDto> findAllFreeSets(List<WorkersSetsDto> workersSetsDtoList) {

        List<SetDto> allList = this.findAll();
        List<SetDto> resultList = new ArrayList<>();

        if (workersSetsDtoList.isEmpty()) {
            resultList = allList;
        } else {
            for (SetDto set: allList) {

                boolean flag = false;

                for(WorkersSetsDto workersSet: workersSetsDtoList) {
                    if (set.getNameOfSet().equals(workersSet.getSet().getNameOfSet())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    resultList.add(set);
                }
            }
        }
        return resultList;
    }
}

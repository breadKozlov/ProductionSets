package by.kozlov.spring.service;

import by.kozlov.spring.dto.SetReadDto;
import by.kozlov.spring.database.repository.SetRepository;
import by.kozlov.spring.dto.WorkersSetsReadDto;
import by.kozlov.spring.mapper.SetReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SetService {
    private final SetReadMapper setReadMapper;
    private final SetRepository setRepository;

    public List<SetReadDto> findAll() {
        return setRepository.findAll().stream()
                .map(setReadMapper::map).toList();
    }

    public Optional<SetReadDto> findById(Integer id) {
        return setRepository.findById(id)
                .map(setReadMapper::map);
    }

    public List<SetReadDto> findAllFreeSets(List<WorkersSetsReadDto> workersSetsDtoList) {

        List<SetReadDto> allList = this.findAll();
        List<SetReadDto> resultList = new ArrayList<>();

        if (workersSetsDtoList.isEmpty()) {
            resultList = allList;
        } else {
            for (SetReadDto set: allList) {

                boolean flag = false;

                for(WorkersSetsReadDto workersSet: workersSetsDtoList) {
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

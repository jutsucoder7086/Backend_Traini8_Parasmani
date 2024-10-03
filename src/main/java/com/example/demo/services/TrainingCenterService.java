package com.example.demo.services;
import com.example.demo.entities.TrainingCenter;
import com.example.demo.repositories.TrainingCenterRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingCenterService {

    private final TrainingCenterRepository repository;

    public TrainingCenterService(TrainingCenterRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TrainingCenter saveTrainingCenter(TrainingCenter trainingCenter) {
        return repository.save(trainingCenter);
    }


    public List<TrainingCenter> fetchTrainingCenters() {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdOn");
        return repository.findAll(sort);
    }
}


package com.example.demo.services;

import com.example.demo.customExceptions.CustomBadRequestException;
import com.example.demo.entities.TrainingCenter;
import com.example.demo.repositories.TrainingCenterRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
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


    public List<TrainingCenter> fetchTrainingCenters(
            Collection<String> cities,
            String createdOnStart,
            String createdOnEnd
    ) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
        if (createdOnStart == null) {
            createdOnStart = this.repository.findShortestDate();
        }
        if (createdOnEnd == null) {
            createdOnEnd = this.repository.findLastDate();
        }
        if (LocalDate.parse(createdOnEnd).isBefore(LocalDate.parse(createdOnStart))) {
            throw new CustomBadRequestException("End date should always be greater than or equal to start date");
        }
        if (cities == null) {
            cities = this.repository.findDistinctCities();
        }
        return this.repository.findByAddress_CityInAndCreatedOnBetween(cities, createdOnStart, createdOnEnd);
    }
}


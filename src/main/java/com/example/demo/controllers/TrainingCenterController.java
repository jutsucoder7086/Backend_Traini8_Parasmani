package com.example.demo.controllers;
import com.example.demo.customExceptions.CustomBadRequestException;
import com.example.demo.customExceptions.CustomDuplicateEntryException;
import com.example.demo.customExceptions.CustomInternalServerErrorException;
import com.example.demo.entities.TrainingCenter;
import com.example.demo.services.TrainingCenterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/training-centers")
@CrossOrigin(origins = "*")
public class TrainingCenterController {

    Logger logger = LoggerFactory.getLogger(TrainingCenterController.class);

    private final TrainingCenterService trainingCenterService;

    public TrainingCenterController(TrainingCenterService trainingCenterService) {
        this.trainingCenterService = trainingCenterService;
    }

    @PostMapping
    public ResponseEntity<TrainingCenter> createTrainingCenter(@Valid @RequestBody TrainingCenter trainingCenter) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(this.trainingCenterService.saveTrainingCenter(trainingCenter));
        }catch (DataIntegrityViolationException e){
            logger.error("DataIntegrityViolationException in createTrainingCenter() :: {}",e.getMessage());
            if(e.getMessage().contains("Unique index")){
                throw new CustomDuplicateEntryException("Data already present with given center code :"+trainingCenter.getCenterCode());
            }
            throw new CustomDuplicateEntryException("DataIntegrityViolationException Exception");
        }catch (Exception e){
            logger.error("Exception in createTrainingCenter() :: {}",e.getMessage());
            throw new CustomInternalServerErrorException("Something went wrong");
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainingCenter>> fetchTrainingCenters(
            @RequestParam(required = false) Collection<String> cities,
            @RequestParam(required = false) String createdOnStart,
            @RequestParam(required = false) String createdOnEnd
    ){
        try{
            return ResponseEntity.ok(this.trainingCenterService.fetchTrainingCenters(cities,createdOnStart,createdOnEnd));
        }catch(CustomBadRequestException e){
            logger.error("CustomBadRequestException in fetchTrainingCenters() :: {}",e.getMessage());
            throw new CustomBadRequestException(e.getMessage());
        }catch(DateTimeParseException e){
            logger.error("DateTimeParseException in fetchTrainingCenters() :: {}",e.getMessage());
            throw new CustomBadRequestException("Date should be in 'yyyy-mm-dd' format");
        }catch(Exception e){
            logger.error("Exception in fetchTrainingCenters() :: {}",e.getMessage());
            e.printStackTrace();
            throw new CustomInternalServerErrorException("Something went wrong");
        }
    }
}
package com.example.demo.repositories;

import com.example.demo.entities.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {


    @Query(value = "SELECT * FROM training_center WHERE city in(:cities) and CAST(created_on AS DATE) between :createdOnStart and :createdOnEnd order by created_on desc",nativeQuery = true)
    List<TrainingCenter> findByAddress_CityInAndCreatedOnBetween(Collection<String> cities, String createdOnStart, String createdOnEnd);

    @Query(value = "select distinct city from training_center",nativeQuery = true)
    Collection<String> findDistinctCities();

    @Query(value = "select CAST(created_on AS DATE) from training_center order by created_on asc limit 1",nativeQuery = true)
    String findShortestDate();

    @Query(value = "select CAST(created_on AS DATE) from training_center order by created_on desc limit 1",nativeQuery = true)
    String findLastDate();
}
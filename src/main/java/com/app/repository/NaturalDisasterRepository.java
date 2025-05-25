package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.NaturalDisaster;

import java.util.List;

public interface NaturalDisasterRepository extends JpaRepository<NaturalDisaster, Long> {

    List<NaturalDisaster> findByCountryAndDisasterType(String country, String disasterType);

    List<NaturalDisaster> findByCountryAndDisasterTypeAndYearBetween(String country, String disasterType, int from, int to);
}



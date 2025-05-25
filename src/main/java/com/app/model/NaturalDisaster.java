package com.app.model;

import jakarta.persistence.*;

@Entity
public class NaturalDisaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String iso2;

    private String iso3;

    private String disasterType;

    private Integer year;

    private Integer incidents;

    public NaturalDisaster() {}

    public NaturalDisaster(String country, String iso2, String iso3, String disasterType, Integer year, Integer incidents) {
        this.country = country;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.disasterType = disasterType;
        this.year = year;
        this.incidents = incidents;
    }

	public Long getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public String getIso2() {
		return iso2;
	}

	public String getIso3() {
		return iso3;
	}

	public String getDisasterType() {
		return disasterType;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getIncidents() {
		return incidents;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public void setDisasterType(String disasterType) {
		this.disasterType = disasterType;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setIncidents(Integer incidents) {
		this.incidents = incidents;
	}

}


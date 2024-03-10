package com.covid19.model;

public class AggregateUIModel {
	
	private String provinceState;
	private String countryRegion;
	private Double lat;
	private Double lng;
	
	private Integer conformed;
	private Integer dead;
	private Integer recovered;
	
	private float recoveredPercent;
	private float deadPercent;
	
	private String recoveredDisplayString;
	private String deadDisplayString;
	
	public String getRecoveredDisplayString() {
		return recoveredDisplayString;
	}
	public void setRecoveredDisplayString(String recoveredDisplayString) {
		this.recoveredDisplayString = recoveredDisplayString;
	}
	public String getDeadDisplayString() {
		return deadDisplayString;
	}
	public void setDeadDisplayString(String deadDisplayString) {
		this.deadDisplayString = deadDisplayString;
	}
	public float getRecoveredPercent() {
		return recoveredPercent;
	}
	public void setRecoveredPercent(float recoveredPercent) {
		this.recoveredPercent = recoveredPercent;
	}
	public float getDeadPercent() {
		return deadPercent;
	}
	public void setDeadPercent(float deadPercent) {
		this.deadPercent = deadPercent;
	}
	public String getProvinceState() {
		return provinceState;
	}
	public void setProvinceState(String provinceState) {
		this.provinceState = provinceState;
	}
	public String getCountryRegion() {
		return countryRegion;
	}
	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Integer getConformed() {
		return conformed;
	}
	public void setConformed(Integer conformed) {
		this.conformed = conformed;
		calcRecoverd();
	}
	public Integer getDead() {
		return dead;
	}
	public void setDead(Integer dead) {
		this.dead = dead;
		calcDead();
	}
	public Integer getRecovered() {
		return recovered;
	}
	public void setRecovered(Integer recovered) {
		this.recovered = recovered;
	}
	
	void calcRecoverd(){
		this.recoveredPercent = ((this.recovered*100)/this.conformed);
		this.recoveredDisplayString = " "+recovered+ " -> "+recoveredPercent+ " %";
	}
	void calcDead(){
		this.deadPercent = ((this.dead*100)/this.conformed);
		this.deadDisplayString = " "+dead+ " -> "+deadPercent+ " %";
	}
}

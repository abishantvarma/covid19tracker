package com.covid19.model;

import java.util.ArrayList;

public class AggregateDataModel {
	
	private ArrayList<Covid19DataVO> conformedList;
	private ArrayList<Covid19DataVO> deadList;
	private ArrayList<Covid19DataVO> recoverdList;
	
	private Integer conformed;
	private Integer dead;
	private Integer recoverd;
	
	
	public ArrayList<Covid19DataVO> getConformedList() {
		return conformedList;
	}
	public void setConformedList(ArrayList<Covid19DataVO> conformedList) {
		this.conformedList = conformedList;
	}
	public ArrayList<Covid19DataVO> getDeadList() {
		return deadList;
	}
	public void setDeadList(ArrayList<Covid19DataVO> deadList) {
		this.deadList = deadList;
	}
	public ArrayList<Covid19DataVO> getRecoverdList() {
		return recoverdList;
	}
	public void setRecoverdList(ArrayList<Covid19DataVO> recoverdList) {
		this.recoverdList = recoverdList;
	}
}

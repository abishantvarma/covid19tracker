package com.covid19.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.model.AggregateUIModel;
import com.covid19.services.Covid19Service;

@RestController
@RequestMapping("/rest")
public class Covid19RestController {
	
	@Autowired
	Covid19Service service;
	
	@GetMapping("")
	public ArrayList<AggregateUIModel> getCovidDataAsJSON() {
		try {
			return service.getMainData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
}

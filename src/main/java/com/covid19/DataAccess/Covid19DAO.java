package com.covid19.DataAccess;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.covid.httpConstants.CovidConstants;
import com.covid19.model.Covid19DataVO;

public class Covid19DAO {	
	public HttpResponse<String> fetchConformedData() {				
		try {			
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CovidConstants.COVID_DATA_URL_CONFORMED)).build();
			HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return httpResponse; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public HttpResponse<String> fetchDeadData() {				
		try {			
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CovidConstants.COVID_DATA_URL_DEAD)).build();
			HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return httpResponse; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public HttpResponse<String> fetchRecoverdData() {				
		try {			
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(CovidConstants.COVID_DATA_URL_RECOVERD)).build();
			HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return httpResponse; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}
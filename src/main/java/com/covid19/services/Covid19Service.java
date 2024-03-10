package com.covid19.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.covid19.DataAccess.Covid19DAO;
import com.covid19.model.AggregateDataModel;
import com.covid19.model.AggregateUIModel;
import com.covid19.model.Covid19DataVO;

@Service
public class Covid19Service {
	
	private ArrayList<AggregateUIModel> mainData = new ArrayList<AggregateUIModel>();
	private AggregateDataModel aggregateMainData = new AggregateDataModel();
	
	
	public AggregateDataModel getAggregateMainData() {
		return aggregateMainData;
	}

	public void setAggregateMainData(AggregateDataModel aggregateMainData) {
		this.aggregateMainData = aggregateMainData;
	}

	public ArrayList<AggregateUIModel> getMainData() {
		return mainData;
	}

	public void setMainData(ArrayList<AggregateUIModel> mainData) {
		this.mainData = mainData;
	}

	@PostConstruct
	@Scheduled(cron = "* 1 * * * *")
	public void fetchData() {
		Covid19DAO dao = new Covid19DAO();
		try {
			ArrayList<Covid19DataVO> data = new ArrayList<Covid19DataVO>();
			processData(dao.fetchConformedData(), "conf");
			processData(dao.fetchDeadData(), "dead");
			processData(dao.fetchRecoverdData(), "recov");
			ArrayList<AggregateUIModel> uiData = buildUIModel(getAggregateMainData());
			//setMainData(uiData);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void processData(HttpResponse<String> httpResponse, String dataType) throws IOException{
		StringReader csvReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> conformedRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		ArrayList<Covid19DataVO> dataList = new ArrayList<Covid19DataVO>();
		for(CSVRecord record: conformedRecords) {
			Covid19DataVO vo = new Covid19DataVO();
			vo.setProvinceState(record.get("Province/State"));
			vo.setCountryRegion(record.get("Country/Region"));
			vo.setLat(Double.parseDouble(record.get("Lat")));
			vo.setLat(Double.parseDouble(record.get("Long")));
			vo.setLatestConformedCases(Integer.parseInt(     record.get(   record.size() - 1  )     ));
			dataList.add(vo);
		}
		
		if("conf".equalsIgnoreCase(dataType)) {
			aggregateMainData.setConformedList(dataList);
		}else if ("dead".equalsIgnoreCase(dataType)) {
			aggregateMainData.setDeadList(dataList);
		}else if ("recov".equalsIgnoreCase(dataType)) {
			aggregateMainData.setRecoverdList(dataList);
		}
	}
	
	public ArrayList<AggregateUIModel> buildUIModel(AggregateDataModel adm) {
		ArrayList<AggregateUIModel> uiModelList = new ArrayList<AggregateUIModel>();
		
		for (Covid19DataVO d : adm.getRecoverdList()) {
			for (Covid19DataVO e : adm.getConformedList()) {
				for (Covid19DataVO f : adm.getDeadList()) {
					if (
							d.getCountryRegion().equalsIgnoreCase(e.getCountryRegion()) && d.getProvinceState().equalsIgnoreCase(e.getProvinceState()) &&
							d.getCountryRegion().equalsIgnoreCase(f.getCountryRegion()) && d.getProvinceState().equalsIgnoreCase(f.getProvinceState())
							
							) {
						AggregateUIModel uiModel = new AggregateUIModel();
						/*
						 * System.out.println(d.getCountryRegion() + " || " + d.getProvinceState() +
						 * "|| Recoverd " + d.getLatestConformedCases() + " Conformed " +
						 * e.getLatestConformedCases() + " Dead " + f.getLatestConformedCases());
						 */
						 
						uiModel.setCountryRegion(d.getCountryRegion());
						uiModel.setProvinceState(d.getProvinceState());
						uiModel.setRecovered(d.getLatestConformedCases());
						uiModel.setConformed(e.getLatestConformedCases());
						uiModel.setDead(f.getLatestConformedCases());
						this.mainData.add(uiModel);
					}
				}
			}
		}
		return uiModelList;
	}
	
	public Integer getTotalConformed(ArrayList<AggregateUIModel> mainData) {
		Integer total = mainData.stream().mapToInt(stat -> stat.getConformed()).sum();
		return total;
	}
}

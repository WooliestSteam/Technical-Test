package com.example.demo.scheduledtasks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Scanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class WeatherTask {
	
	@Scheduled(fixedRate = 5000)
	public void getWeatherFromExternalApi() throws MalformedURLException, IOException {
		
		String jsonReponseAsString = "";
		
		// 560743 IN THE URL IS A LOCATION ID WHICH MAPS TO DUBLIN
		URL externalWeatherApi = new URL("https://www.metaweather.com/api/location/560743/");
		HttpURLConnection httpUrlconnection = (HttpURLConnection) externalWeatherApi.openConnection();
		httpUrlconnection.setRequestMethod("GET");
		
		int responseCode = httpUrlconnection.getResponseCode();
		
		if(responseCode == 200) {
			
			Scanner scanner = new Scanner(externalWeatherApi.openStream());
			while(scanner.hasNext()) {
				jsonReponseAsString += scanner.nextLine();
			}
			
			scanner.close();
			
			JsonObject jsonObj = JsonParser.parseString(jsonReponseAsString).getAsJsonObject();
			JsonArray jsonArray = (JsonArray) jsonObj.get("consolidated_weather");
			
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonObject weatherObj = (JsonObject) jsonArray.get(i);
				System.out.print("Day: " + weatherObj.get("applicable_date").toString());
				System.out.println(" Weather: " + weatherObj.get("weather_state_name").toString());
			}
		}
	}
}

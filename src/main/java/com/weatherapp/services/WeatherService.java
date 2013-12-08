package com.weatherapp.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weatherapp.pojo.Location;

/**
 * @author Sapan.Saxena
 * This class provides services available for the Weather Services to work
 */
@Service
public class WeatherService {
	@Autowired
	RestTemplate restTemplate;
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	public static final String SUCCESS = "success";
	public static final String ZIPCODE_ERROR = "zipcode_error";
	public static final String NETWORK_ERROR = "network_error";
	
	private  @Value("#{settings['restcall.APIKEY']}") String apikey;
	
	/**
	 * This method is used to get weather info for a particular valid zip code
	 * @param location-location object which is populated
	 * @return response code
	 */
	public String getWeatherInfo(Location location){
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("zipcode", location.getZipcode()+".json");
		try{
		String jsonObject = restTemplate.getForObject(
		  "http://api.wunderground.com/api/"+apikey+"/conditions/q/{zipcode}", 
		  String.class, vars);
		JsonParser jsonParser = new JsonParser();
		JsonObject compositeJsonObj = ((JsonObject) jsonParser.parse(jsonObject));
		JsonObject currObservation = compositeJsonObj.get("current_observation").getAsJsonObject();
		JsonObject observationLocation = currObservation.get("observation_location").getAsJsonObject();
		
		location.setName(observationLocation.get("city").getAsString());
		location.setState(observationLocation.get("state").getAsString());
		location.setTemperature(currObservation.get("temp_f").getAsString() + " F");
		}catch(NullPointerException ne){
			
			return ZIPCODE_ERROR;
		}catch(Exception e){
			return NETWORK_ERROR;
		}
		return SUCCESS;
	}
}

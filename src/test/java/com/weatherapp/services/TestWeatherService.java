package com.weatherapp.services;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import com.weatherapp.pojo.Location;

public class TestWeatherService {
	
@Test
public void testGetWeatherInfo(){
	String apikey = "ed044d75b91fb500";
	WeatherService weatherService = getServiceUnderTest();
	RestTemplate restTemplate = weatherService.getRestTemplate();
	Location location = new Location();
	location.setZipcode("222222");
	ArgumentCaptor<String> searchCriteriaArgumentCaptor = ArgumentCaptor.forClass(String.class);

	String url = "http://api.wunderground.com/api/"+apikey+"/conditions/q/{zipcode}";
	Map<String, String> vars = new HashMap<String, String>();
	vars.put("zipcode", "222222.json");

	when(restTemplate.getForObject(
			   Matchers.anyString(), Matchers.eq(String.class), Matchers.anyMap())
			 ).thenReturn("{'current_observation': {'observation_location': {'city':'San Francisco','state':'CA'},'temp_f':'34'}, 'temp_f': '34'}");
	weatherService.getWeatherInfo(location);

	Assert.assertNotNull(location);
	Assert.assertTrue("34 F".equals(location.getTemperature()));
	Assert.assertTrue("CA".equals(location.getState()));
	Assert.assertTrue("San Francisco".equals(location.getName()));
}
private WeatherService getServiceUnderTest(){
	WeatherService weatherService = new WeatherService();
	RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
	weatherService.setRestTemplate(restTemplate);	
	 return weatherService;
}
}

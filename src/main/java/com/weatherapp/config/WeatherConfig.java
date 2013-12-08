package com.weatherapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.weatherapp.pojo.Location;
import com.weatherapp.services.WeatherService;
import com.weatherapp.validator.LocationValidator;

/**
 * Provides configuration objects for the entire app
 * @author Sapan.Saxena
 *
 */
@Configuration
public class WeatherConfig {
	    @Bean
	    public Location location() {
	        return new Location();
	    }
	    
	    @Bean
	    public WeatherService weatherService() {
	        return new WeatherService();
	    }
	    @Bean
	    public RestTemplate restTemplate(){
	    	 return new RestTemplate();
	    }
	    @Bean
	    public LocationValidator locationValidator(){
	    	return new LocationValidator();
	    }
	    
	}


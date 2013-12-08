package com.weatherapp.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.weatherapp.pojo.Location;
import com.weatherapp.services.WeatherService;
import com.weatherapp.validator.LocationValidator;


public class TestBaseController {

	

	 
	@Test
	public void testWelcome(){
		BaseController classUnderTest = getClassUnderTest();
		ModelMap modelMap = new ModelMap();
		ModelAndView modelAndView = classUnderTest.welcome(modelMap);
		Assert.assertNotNull(modelAndView);
		Assert.assertTrue("index".equals(modelAndView.getViewName()));
		Assert.assertNotNull(modelAndView.getModel());
	}
	
	@Test
	public void testGetWeather(){
		BaseController classUnderTest = getClassUnderTest();
		ModelMap modelMap = new ModelMap();
		Location location = Mockito.mock(Location.class);
		Object obj = new Object();
		Errors errors = Mockito.mock(Errors.class);
		LocationValidator locationValidator = classUnderTest.getLocationValidator();
		BindingResult bindingResult = Mockito.mock(BindingResult.class); 
		WeatherService weatherService = classUnderTest.getWeatherService();
		Mockito.when(weatherService.getWeatherInfo(Mockito.eq(location))).thenReturn("SUCCESS");
		Mockito.doNothing().when(locationValidator).validate(obj, errors);
		ModelAndView viewName = classUnderTest.getWeather(location, bindingResult, modelMap);
		Assert.assertNotNull(viewName);
		Assert.assertTrue("weather".equals(viewName.getViewName()));
	}
	@Test
	public void testGetWeatherWithError(){
		BaseController classUnderTest = getClassUnderTest();
		ModelMap modelMap = new ModelMap();
		Location location = Mockito.mock(Location.class);
		Object obj = new Object();
		Errors errors = Mockito.mock(Errors.class);
		LocationValidator locationValidator = classUnderTest.getLocationValidator();
		BindingResult bindingResult = Mockito.mock(BindingResult.class); 
		WeatherService weatherService = classUnderTest.getWeatherService();
		Mockito.when(weatherService.getWeatherInfo(Mockito.eq(location))).thenReturn("zipcode_error");
		Mockito.doNothing().when(locationValidator).validate(obj, errors);
		ModelAndView viewName = classUnderTest.getWeather(location, bindingResult, modelMap);
		Assert.assertNotNull(viewName);
		Assert.assertTrue("index".equals(viewName.getViewName()));
	}
	
	private BaseController getClassUnderTest(){
		BaseController classUnderTest;
		WeatherService mockWeatherService = Mockito.mock(WeatherService.class);
		Location mockLocation = Mockito.mock(Location.class);
		LocationValidator mockLocationValidator = Mockito.mock(LocationValidator.class);
		 classUnderTest = new BaseController();
		 classUnderTest.setLocation(mockLocation);
		 classUnderTest.setWeatherService(mockWeatherService);
		 classUnderTest.setLocationValidator(mockLocationValidator);
		 return classUnderTest;
	}
	

}
package com.weatherapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.bean.BeanValidator;
import org.springmodules.validation.bean.conf.loader.annotation.AnnotationBeanValidationConfigurationLoader;

import com.weatherapp.pojo.Location;
import com.weatherapp.services.WeatherService;
import com.weatherapp.validator.LocationValidator;

@Controller
@RequestMapping("/")
public class BaseController {

	@Autowired
	Location location;
	
	@Autowired
	WeatherService weatherService;
	
	@Autowired
	LocationValidator locationValidator;
	
	private BeanValidator beanValidator = new BeanValidator(new AnnotationBeanValidationConfigurationLoader());

	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public LocationValidator getLocationValidator() {
		return locationValidator;
	}

	public void setLocationValidator(LocationValidator locationValidator) {
		this.locationValidator = locationValidator;
	}

	/**
	 * The entry point for the application
	 * @param model ModelMap
	 * @return ModelAndView object
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model) {
		ModelAndView modelView = new ModelAndView("index");
		modelView.getModelMap().put("location", location);
		return modelView;

	}

	/**
	 * Get Weather details of a particular zipcode
	 * @param location Location object
	 * @param bindingResult Erros object
	 * @param model ModelMap object
	 * @return ModelAndView object
	 */
	@RequestMapping(value = "/getWeather", method = RequestMethod.POST)
	public ModelAndView getWeather(@ModelAttribute("location") Location location ,BindingResult bindingResult, ModelMap model) {
		beanValidator.validate(location, bindingResult);
		locationValidator.validate(location, bindingResult);
		if(bindingResult.hasErrors()){
			return returnWIthError(model, bindingResult);
		}
		String responseCode = weatherService.getWeatherInfo(location);
		if(WeatherService.ZIPCODE_ERROR.equals(responseCode)){
			bindingResult.rejectValue("zipcode", "LocationValidator.zipcode.notFound", "Zipcode Not Found");
			return returnWIthError(model, bindingResult);
		}else if(WeatherService.NETWORK_ERROR.equals(responseCode)){
			bindingResult.rejectValue("zipcode", "LocationValidator.zipcode.networkError", "Network Error");
			return returnWIthError(model, bindingResult);

		}
		model.addAttribute("location", location);
		return new ModelAndView("weather", model);

	}
	private ModelAndView returnWIthError(ModelMap model, BindingResult bindingResult){
		ModelAndView modelView = new ModelAndView("index");
		modelView.addAllObjects(model);
		modelView.addObject(bindingResult);
		modelView.addObject("location", location);
		return modelView;
	}

}
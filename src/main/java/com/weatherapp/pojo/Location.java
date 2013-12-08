package com.weatherapp.pojo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;


public class Location {

	@NotBlank(message="Zip code can not be empty")
	@NotNull(message="Zip code can not be empty")
	
	private String zipcode;
	
	private String name;
	private String state;
	private String temperature;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
	
}

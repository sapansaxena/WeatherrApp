package com.weatherapp.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.Errors;

import com.weatherapp.pojo.Location;

public class TestLocationValidator {
	@Test
	public void testValidate(){
		LocationValidator locationValidator = getValidatorUnderTest();
		Location location = new Location();
		Errors errors = Mockito.mock(Errors.class);
		locationValidator.validate(location, errors);
		Assert.assertNull(location.getZipcode());
	}
	private LocationValidator getValidatorUnderTest(){
		
		LocationValidator validatorUnderTest = new LocationValidator();

		 return validatorUnderTest;
	}
}

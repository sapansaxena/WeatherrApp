package com.weatherapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.weatherapp.pojo.Location;

/**
 * This class is validator for the zipcode for which weather is queried
 * @author Sapan.Saxena
 *
 */
public class LocationValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Location location = (Location) obj;
	    String zipcode = location.getZipcode();
	    validateZipcode(zipcode, errors);
		
	}
	 /**
	  * Validates a zipcode
	 * @param postCode-postal code in String
	 * @param errors- Bindingresult object
	 */
	private void validateZipcode(String postCode, Errors errors) {

		    if (!isValidZipcode(postCode) ) {
		      errors.rejectValue("zipcode", "LocationValidator.zipcode.notValid",
		          "Not a Valid Zip Code");
		    }
		  }

		  private boolean isValidZipcode(String str) {
			  if(str == null){
				  return false;
			  }
			  if(str.isEmpty()){
				  return true; 
			  }
			  
			  if(str.length()!=5){
				  return false;
			  }
			  if(str.charAt(0) == '0'){
				  return false;
			  }
			  String zipCodePattern = "[0-9]+";
			    return str.matches(zipCodePattern);
		  }



}

package com.vijayganduri.zippr.instamap.rest;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.vijayganduri.zippr.instamap.AppConstants;
import com.vijayganduri.zippr.instamap.beans.PhotoResponse;

public abstract class AbstractRestService {

	protected static final String rootUrl = AppConstants.END_POINT;
	protected static RestTemplate restTemplate;
	protected String apiKey = AppConstants.INSTAGRAM_APPLICATION_KEY;

	private static ObjectMapper objectMapper;
	
	static{
		//Can do heavy customization here later on depending on other server calls and their response
		objectMapper = new ObjectMapper();
		objectMapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
		MappingJacksonHttpMessageConverter convertor = new MappingJacksonHttpMessageConverter();
        convertor.setObjectMapper(objectMapper);
        
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(convertor);
	}
		
	public abstract PhotoResponse getPopularPhotos();
	
	
}
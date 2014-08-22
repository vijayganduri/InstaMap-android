package com.vijayganduri.zippr.instamap.rest;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.vijayganduri.zippr.instamap.beans.PhotoResponse;

public class InstagramRestService extends AbstractRestService{
	
	@Override
	public PhotoResponse getPopularPhotos(){
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("apiKey", apiKey);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        return restTemplate.exchange(rootUrl.concat("media/popular?client_id={apiKey}"), 
        		HttpMethod.GET, requestEntity, PhotoResponse.class, urlVariables).getBody();
	}
	
}

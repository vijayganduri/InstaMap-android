package com.vijayganduri.zippr.instamap.beans;

import java.util.List;

/**
 * 
 * @author Vijay Ganduri
 *
 */
public class PhotoResponse{

	private List<Data> data;

	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "PhotoResponse [data=" + data + "]";
	}
	
}
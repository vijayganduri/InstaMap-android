package com.vijayganduri.zippr.instamap.beans;

import java.util.List;


/**
 * 
 * @author Vijay Ganduri
 *
 */
public class Data{

	private String id;
	private Images images;
	private Location location;
	private Caption caption;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Images getImages() {
		return images;
	}
	public void setImages(Images images) {
		this.images = images;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Caption getCaption() {
		return caption;
	}
	public void setCaption(Caption caption) {
		this.caption = caption;
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", images=" + images + ", location="
				+ location + ", caption=" + caption + "]";
	}

}
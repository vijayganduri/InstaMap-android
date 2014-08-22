package com.vijayganduri.zippr.instamap.beans;

import java.io.Serializable;



/**
 * 
 * @author Vijay Ganduri
 *
 */
public class Caption implements Serializable{

	private String text;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Caption [text=" + text + "]";
	}

}
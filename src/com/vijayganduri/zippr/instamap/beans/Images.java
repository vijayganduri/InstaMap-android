package com.vijayganduri.zippr.instamap.beans;



/**
 * 
 * @author Vijay Ganduri
 *
 */
public class Images{

	private LowResolution low_resolution;
	private Thumbnail thumbnail;
	private StandardResolution standard_resolution;
	
	public LowResolution getLow_resolution() {
		return low_resolution;
	}
	public void setLow_resolution(LowResolution low_resolution) {
		this.low_resolution = low_resolution;
	}
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	public StandardResolution getStandard_resolution() {
		return standard_resolution;
	}
	public void setStandard_resolution(StandardResolution standard_resolution) {
		this.standard_resolution = standard_resolution;
	}
	@Override
	public String toString() {
		return "Images [low_resolution=" + low_resolution + ", thumbnail="
				+ thumbnail + ", standard_resolution=" + standard_resolution
				+ "]";
	}
}
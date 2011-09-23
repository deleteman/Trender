package com.mooveitlabs.trender.twitter;

import com.google.gson.annotations.SerializedName;

public class Trend implements java.io.Serializable{

	@SerializedName ("name")
	public String name;
	
	@SerializedName ("url")
	public String url;
	
	public String toString() {
		return name;
	}
	
}

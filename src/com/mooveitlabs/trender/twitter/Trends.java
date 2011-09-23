package com.mooveitlabs.trender.twitter;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Trends {
	@SerializedName ("trends")
	public List<Trend> trends;
	@SerializedName ("as_of")
	public String as_of; 
	

}

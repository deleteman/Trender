package com.mooveitlabs.trender.twitter;

import com.google.gson.annotations.SerializedName;

public class TwitterSearchResult implements java.io.Serializable{

	@SerializedName ("from_user")
	public String username;
	
	public String text;
	
	@SerializedName ("profile_image_url")
	public String avatar_url;
	
	public String toString() {
		return text;
	}
	
	
}

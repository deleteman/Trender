package com.mooveitlabs.trender.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;


public class TwitterCOM {

	private static String TRENDING_URL = "http://api.twitter.com/1/trends.json";
	private static String SEARCH_URL = "http://search.twitter.com/search.json?q=";
	
	
	public TwitterSearchResults search(String q, int rpp) {
		String jSonResult = queryRESTurl(TwitterCOM.SEARCH_URL + URLEncoder.encode(q) + "&rpp=" + rpp);
		
		Gson gsonObj = new Gson();
		TwitterSearchResults results = gsonObj.fromJson(jSonResult, TwitterSearchResults.class);
		return results;
	}
	
	public List<Trend> getTrends() {
		 String result = queryRESTurl(TwitterCOM.TRENDING_URL);

	        try{
	        		Gson gsonObject = new Gson();
	        		Trends trends = gsonObject.fromJson(result, Trends.class);
	        		Log.e("GSON", "Resultado de la url: " + result);
	        		//Trends t = trends.get(0);
	        		if(trends.trends.size() == 0) {
	        			throw new Exception("NO trending topics found");
	        		}
	        		return trends.trends;	        		
	                
	        }
	        catch (Exception e) {
	        		
	                Log.e("JSON", "There was an error parsing the JSON", e);
	        }
	        return null;

	}
	
	public String queryRESTurl(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;
        
        try {
                response = httpclient.execute(httpget);
               
              //  Log.i(TAG, "Status:[" + response.getStatusLine().toString() + "]");
                HttpEntity entity = response.getEntity();
                
                if (entity != null) {
                        
                        InputStream instream = entity.getContent();
                        String result = RestClient.convertStreamToString(instream);
                //        Log.i(TAG, "Result of converstion: [" + result + "]");
                        
                        instream.close();
                        return result;
                }
        } catch (ClientProtocolException e) {
                Log.e("REST", "There was a protocol based error", e);
        } catch (IOException e) {
                Log.e("REST", "There was an IO Stream related error", e);
        }
        
        return null;
}
}

package com.shivdev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;


import org.json.JSONObject;

public class ParserExercise {
	 public String getId(String url)
	{
		int i ;
		String output ="";
		try {
			URL wikiApi = new URL (url);
			HttpURLConnection req = (HttpURLConnection)wikiApi.openConnection();
			BufferedReader in = new BufferedReader (new InputStreamReader(req.getInputStream()));
			
			
			StringBuffer response  = new StringBuffer();
			 while(( i=in.read())!=-1){  
		          response.append((char)i);  
		          }
			// System.out.println(response.toString());
			 JSONObject obj   = new JSONObject (response.toString());
			 JSONObject obj2  = obj.getJSONObject("query").getJSONObject("pages").getJSONObject("1808130");
			 
			 output = String.valueOf(obj2.getInt("pageid"));
			 
			 JSONObject arr  = obj.getJSONObject("query").getJSONObject("pages").getJSONObject("1808130").getJSONArray("revisions").getJSONObject(0);
			
			 
			 String links = arr.getString("*").substring(arr.getString("*").lastIndexOf("==See also==")+13,arr.getString("*").indexOf("==References==")-2);
			 links=links.replace(" ","_").replace("[","").replace("]","").replace("*","");
			 String linkOut[] = links.split("\\n");
			 for (int j =0 ; j<linkOut.length;j++) {
				 System.out.println( "https://en.wikipedia.org/"+ linkOut[j]);
				 System.out.println();
			 }
			 
			 in.close();

		}
		catch(MalformedURLException ex)
		{
			throw new IllegalArgumentException("unknown protocol: htts");
		}
		catch(UnknownHostException ex) {
			throw new IllegalArgumentException("en.wikipeda.org");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	 
//	 public static void main(String [] args) {
//		 getId("htts://en.wikipeda.org/w/api.php?format=json&action=query&titles=SMALL&prop=revisions&rvprop=content");
//	 }
	 
	
	
	

}

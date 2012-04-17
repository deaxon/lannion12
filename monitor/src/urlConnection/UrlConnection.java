package urlConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.*;

public class UrlConnection {
	
	public UrlConnection(){
		
	}
	
	public boolean urlConnect(String url){
		
		java.net.URLConnection urlConnection;
		boolean connexionState = false;
		try
		{
			
			URL myURL = new URL(url);
			urlConnection = myURL.openConnection(); // open URL (HTTP query)
			urlConnection.connect();
			
			BufferedReader in = new BufferedReader
					(new java.io.InputStreamReader(urlConnection.getInputStream()));
						
		    String inputLine;
		    while ((inputLine = in.readLine()) != null) 
		    	System.out.println(inputLine);
		    in.close();
			System.out.println("Connection réussie.");
			connexionState = true;
			
		}catch(java.net.MalformedURLException ex){
			
			System.out.println(ex);
			
		}catch(java.io.IOException e){
			
			System.out.println(e);
			
		}
		
		return connexionState;
	}
}
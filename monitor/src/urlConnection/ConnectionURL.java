package urlConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.*;

public class ConnectionURL {

	public ConnectionURL() {
	}

	public boolean urlConnect(String url) {
		java.net.URLConnection urlConnection;
		boolean connexionState = false;
		try {
			URL myURL = new URL(url);
			urlConnection = myURL.openConnection(); // open URL (HTTP query)
			urlConnection.connect();
			System.out.println("Connection réussie.");
			connexionState = true;
		} catch (java.net.MalformedURLException ex) {
			System.out.println(ex);
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
		return connexionState;
	}

	public String getTag(String url, String tag) {
		java.net.URLConnection urlConnection;
		String tagContent = null;
		try {
			URL myURL = new URL(url);
			urlConnection = myURL.openConnection(); // open URL (HTTP query)
			urlConnection.connect();
			BufferedReader in = new BufferedReader(
					new java.io.InputStreamReader(
							urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				inputLine = inputLine.replaceAll(" ", "");
				Pattern p = Pattern.compile("<" + tag + ">(.*)</" + tag + ">");
				java.util.regex.Matcher m = p.matcher(inputLine);
				if (m.matches()) {
					tagContent = m.group(1);
				}
			}
			in.close();
		} catch (java.net.MalformedURLException ex) {
			System.out.println(ex);
		} catch (java.io.IOException e) {
			System.out.println(e);
		}
		return tagContent;
	}

	public int getStatus(String url) {
		java.net.URLConnection urlConnection;
		Object[] obj = null;
		int status = 0 ;
		try {
			URL myURL = new URL(url);
			urlConnection = myURL.openConnection();
			Map<String, List<String>> map = urlConnection.getHeaderFields();
			Set<Entry<String, List<String>>> set = map.entrySet();
			obj = set.toArray();
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		String  condition = obj[9].toString();
		String status_temp = "";
		for(int i = 0;i<condition.length();i++){
			String s = new Character(condition.charAt(i)).toString();
			if(s.equals("2") || s.equals("0")){
				status_temp += condition.charAt(i);
			}
		}
		status = Integer.parseInt(status_temp);
		return status;
	}
}
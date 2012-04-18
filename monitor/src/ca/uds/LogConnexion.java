/**
 * 
 */
package ca.uds;

import java.io.IOException;
import java.io.OutputStream;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import urlConnection.UrlConnection;

/**
 * @author Charlotte
 * 
 */
public class LogConnexion {
	// HttpClient client = new HttpClient();
	UrlConnection myURLConnection = new UrlConnection();
	URL url = null;

	public boolean sendLog(String url, String login, String passwd) {
		boolean b = false;
		boolean connState = myURLConnection.urlConnect(url);
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.addParameter("ndeveloper[cip]", login);
		method.addParameter("ndeveloper[password]", passwd);
		BufferedReader br = null;
		try {
			// int returnCode = client.executeMethod(method);
			// System.out.println("La reponse de executeMethod est : " +
			// returnCode);
			br = new BufferedReader(new InputStreamReader(
					method.getResponseBodyAsStream()));
			String readLine;

			while (((readLine = br.readLine()) != null)) {
				System.out.println(readLine);
			}
			b = true;
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			method.releaseConnection();
			if (br != null) {
				try {
					br.close();

				} catch (Exception e) {
				}
			}

		}

		// if (connState == true) {
		// try {
		// URL u = new URL(url);
		// String charset = "UTF-8";
		// URLConnection conn = u.openConnection();
		// conn.setDoOutput(true);
		//
		//
		//
		// b = true;
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		return b;
	}
}
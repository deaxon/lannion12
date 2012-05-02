package connexion;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import urlConnection.ConnectionURL;

public class LogConnexion {

	ConnectionURL myURLConnection = new ConnectionURL();

	public boolean sendLogConnexion(String url, String login, String passwd) {
		boolean b = false;
		boolean connState = myURLConnection.urlConnect(url);

		if (connState == true) {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);
			Header header = new Header("User-Agent", "Mozilla/3.5.1");
			method.setRequestHeader(header);
			method.addParameter("session[email]", login);
			method.addParameter("session[password]", passwd);
			GetMethod method_get = null;
			try {
				int statusCode = client.executeMethod(method);
				if (statusCode != HttpStatus.SC_OK) {
					while (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
						String redirectLocation = "";
						Header locationHeader = method
								.getResponseHeader("location");
						if (locationHeader != null) {
							redirectLocation = locationHeader.getValue();
							method_get = new GetMethod(redirectLocation);
							statusCode = client.executeMethod(method_get);
							System.out.println("Page actuelle : " + method_get.getURI());
							if (statusCode == HttpStatus.SC_OK) {
								b = true;
							}
						}
					}
				}else{
					b = true;
				}
				System.out.println("-------------------------------------------------------------");
				byte[] responseBody = method_get.getResponseBody();
				System.out.println(new String(responseBody));
			} catch (HttpException e) {
				System.err.println("Fatal protocol violation: "
						+ e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Fatal transport error: " + e.getMessage());
				e.printStackTrace();
			} finally {
				method.releaseConnection();
			}
		}
		return b;
	}
}
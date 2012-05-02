package work;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


public class TestLog {

	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient();
		Header header = new Header("User-Agent", "Mozilla/3.5.1");
		GetMethod method_get = null;
		PostMethod method = new PostMethod("http://0.0.0.0:3000/sessions");
		method.setRequestHeader(header);
		method.addParameter("session[email]", "bob@bob.com");
		method.addParameter("session[password]", "socrate");
		try {
			int statusCode = client.executeMethod(method);
			System.out.println(statusCode);
			System.out.println(method.getURI());
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
				while (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
					String redirectLocation = "";
					Header locationHeader = method
							.getResponseHeader("location");
					if (locationHeader != null) {
						redirectLocation = locationHeader.getValue();
					}
					System.out.println(redirectLocation);
					//method = new PostMethod(redirectLocation);
					//method.setRequestHeader(header);
					method_get = new GetMethod(redirectLocation);
					statusCode = client.executeMethod(method_get);
					System.out.println("Status code = " + statusCode);
					System.out.println(method_get.getURI());
				}
			}
			System.out
					.println("-------------------------------------------------------------");

			byte[] responseBody = method_get.getResponseBody();

			System.out.println(new String(responseBody));
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}

	}

}

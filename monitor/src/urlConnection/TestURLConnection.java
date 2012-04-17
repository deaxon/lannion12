package urlConnection;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestURLConnection {

	@Test
	public void test() {
		
		UrlConnection myURLConnection = new UrlConnection();
		
		assertTrue(myURLConnection.urlConnect("http://gogle.fr"));
		
	}

}

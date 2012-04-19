package urlConnection;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestURLConnection {

	String url = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
	String tag = "title";
	UrlConnection myURLConnection = new UrlConnection();

	@Test
	public void testURLConnection() {
		assertTrue(myURLConnection.urlConnect(url));
	}

	@Test
	public void testGetTag() {
		String tagContent = myURLConnection.getTag(url, tag);
		String newTagContent = "";
		int i = 0;
		while (tagContent.charAt(i) != ':') {
			newTagContent += tagContent.charAt(i);
			i++;
		}
		
		assertEquals(newTagContent, "SiteScrum");
	}

	@Test
	public void testGetHTTPResponseHeader(){
		
		int status = myURLConnection.getStatus(url);

		assert status == 200;
	}
}

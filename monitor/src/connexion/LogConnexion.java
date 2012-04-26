package connexion;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import urlConnection.ConnectionURL;

public class LogConnexion {

	ConnectionURL myURLConnection = new ConnectionURL();

	public boolean sendLogConnexion(String url, String login, String passwd) {
		boolean b = false;
		boolean connState = myURLConnection.urlConnect(url);
		String tagContent = "";
		String balise = "form";
		String attribut = "action";

		if (connState == true) {
			HttpURLConnection connection = null;
			try {
				String donnees = URLEncoder.encode("ndeveloper[cip]", "UTF-8") + "="
						+ URLEncoder.encode(login, "UTF-8");
				donnees += "&" + URLEncoder.encode("ndeveloper[password]", "UTF-8") + "="
						+ URLEncoder.encode(passwd, "UTF-8");

				// Create connection
				URL u = new URL(url);
				connection = (HttpURLConnection) u.openConnection();

				String myCookie = "username=" + login;

				connection.setRequestProperty("Cookie", myCookie);

				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);

				connection.connect();

				System.out.println("\n\n");

				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				writer.write(donnees);
				writer.flush();

				// Get Response
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
				String line;
				StringBuffer response = new StringBuffer();
				int cpt = 0;
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');

					line = line.replaceAll(" ", "");
					Pattern p = Pattern.compile("[\t\n\r\f]*<" + balise
							+ attribut + "=[\"|'](.*)[\"|']>");
					java.util.regex.Matcher m = p.matcher(line);
					if (m.matches()) {
						tagContent = m.group(1);
						cpt++;
						if (cpt == 2) {
							break;
						}
					}
				}
				rd.close();
				String tc = "";
				for (int i = 0; i < tagContent.length(); i++) {
					if (tagContent.charAt(i) == '"') {
						break;
					}
					tc += tagContent.charAt(i);
				}
				System.out.println("tc : " + tc);
				myURLConnection.urlConnect(tc);

				String t = myURLConnection.getTag(tc, "title");
				System.out.println("titre : " + t);
				URL u2 = new URL(tc);
				connection = (HttpURLConnection) u2.openConnection();

				InputStream is2 = connection.getInputStream();
				BufferedReader rd2 = new BufferedReader(new InputStreamReader(
						is2));
				String line2;
				// int cpt2 = 0;
				while ((line2 = rd2.readLine()) != null) {
					System.out.println(line2);
					// cpt2 ++;
				}
				
				if(t == "Projectlannion12Team:lannion12"){
					b = true;
				}
								
				rd2.close();
			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				if (connection != null) {
					connection.disconnect();
				}
			}

		}
		return b;
	}
}

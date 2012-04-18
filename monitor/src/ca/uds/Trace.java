package ca.uds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.StringTokenizer;

import urlConnection.UrlConnection;

public class Trace {

	public static int cptMinute;
	File FICHIER = new File("application" + ".log");

	
	public boolean createLog(String nameSite) {
		boolean result = false;
		boolean fonctionCharlotte = true;
		Calendar Today = Calendar.getInstance();
		UrlConnection urlconnect = new UrlConnection();
		File FICHIER = new File("application" + ".log");
		try {
			FICHIER.createNewFile();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(FICHIER, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter output = new BufferedWriter(fw);
		try {
			if ((urlconnect.urlConnect(nameSite) == true)
					&& (fonctionCharlotte == true)) {
				output.write("\n" + "Application OK : " + " "
						+ Today.get(Calendar.DAY_OF_MONTH) + "/"
						+ String.valueOf(Today.get(Calendar.MONTH) + 1) + "/"
						+ String.valueOf(Today.get(Calendar.YEAR)) + " "
						+ String.valueOf(Today.get(Calendar.HOUR_OF_DAY)) + ":"
						+ String.valueOf(Today.get(Calendar.MINUTE)) + ":"
						+ String.valueOf(Today.get(Calendar.SECOND)) +" " + cptMinute);
				System.out.println("File created");
				result = true;
			} else if (urlconnect.urlConnect(nameSite) == false) {
				if (fonctionCharlotte == false) {
					output.write("\n"
							+ "Application non OK : Access failed and Connection failed");
					System.out.println("File created");
					result = true;
				} else {
					output.write("\n" + "Application non OK : Access failed");
					System.out.println("File created");
					result = true;
				}
			} else {
				output.write("\n" + "Application non OK : Connection failed");
				System.out.println("File created");
				result = true;
			}
			output.flush();
			output.close();
		} catch (IOException ioe) {
			System.out.print("Erreur : File not created");
			result = false;
			ioe.printStackTrace();
		}
		return result;
	}

	public int countLineFile(){
		InputStream ips = null;
		int cpt = 0;
		try {
			ips = new FileInputStream("application.log");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			try {
				while ((ligne = br.readLine()) != null) {
					cpt ++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cpt;
	}
	
	public String[] replaceCaractFileInTab(){
		String ligne = null;
		InputStream ips = null;
		String[] tabC = new String[9];
		try {
			ips = new FileInputStream("application.log");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		StringTokenizer st = null;
		try {
			while ((ligne = br.readLine()) != null) {
				ligne = ligne.replaceAll("/", " ");
				ligne = ligne.replaceAll(":", " ");
				st = new StringTokenizer(ligne);
			}
			int i=0;
			while (st.hasMoreTokens()){
				tabC[i] = st.nextToken();
	 			i++;	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tabC;
	}
	
	public void IncrementLog() {
		InputStream ips = null;
		Calendar Today = Calendar.getInstance();
		int j=1;
		int cpt = countLineFile();
		try {
			ips = new FileInputStream("application.log");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String[] tabC = replaceCaractFileInTab();
			String[] tab = new String[cpt];
			if (Integer.parseInt(tabC[6]) != Today.get(Calendar.MINUTE)){
				if (tabC[1].equals("OK")){
					int number = Integer.parseInt(tabC[8]);
					number++;
				}
			}
			FICHIER.delete();
			File FICHIER = new File("application" + ".log");
			BufferedWriter output = new BufferedWriter(new FileWriter(FICHIER));
			String modifiedLine = null;
			for(int l=0;l<tabC.length; l++){
				modifiedLine += " " + tabC[l];
			}
			for (int k=0; k<tab.length; k++){
				
				output.write(tab[k]);
			}
			
			System.out.println(cptMinute);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
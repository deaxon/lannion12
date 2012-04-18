package trace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
			e.printStackTrace();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(FICHIER, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter output = new BufferedWriter(fw);
		try {
			if ((urlconnect.urlConnect(nameSite) == true)
					&& (fonctionCharlotte == true)) {
				output.write("Application OK : " + " "
						+ Today.get(Calendar.DAY_OF_MONTH) + "/"
						+ String.valueOf(Today.get(Calendar.MONTH) + 1) + "/"
						+ String.valueOf(Today.get(Calendar.YEAR)) + " "
						+ String.valueOf(Today.get(Calendar.HOUR_OF_DAY)) + ":"
						+ String.valueOf(Today.get(Calendar.MINUTE)) + ":"
						+ String.valueOf(Today.get(Calendar.SECOND)) + " "
						+ cptMinute + "\n");
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

	public int countLineFile() {
		InputStream ips = null;
		int cpt = 0;
		String ligne;
		try {
			ips = new FileInputStream("application.log");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			try {
				while ((ligne = br.readLine()) != null) {
					cpt++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cpt;
	}

	public String[] replaceCaractFileInTab(String line) {
		String[] tabC = new String[9];
		StringTokenizer st = null;
		line = line.replaceAll("/", " ");
		line = line.replaceAll(":", " ");
		st = new StringTokenizer(line);
		int i = 0;
		while (st.hasMoreTokens()) {
			tabC[i] = st.nextToken();
			i++;
		}
		return tabC;
	}

	public boolean eraseFile(File file) throws Exception {
		if (!file.exists()) {
			throw new Exception("le fichier est introuvable !");
		}
		if (!file.canWrite()) {
			throw new Exception("Droit insuffisant pour accéder au fichier");
		}
		return file.delete();
	}

	public String[] changeNumberCounter(String[] tab) {
		Calendar Today = Calendar.getInstance();
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		cpt--;
		String[] beforeLastLine = this.replaceCaractFileInTab(tab[cpt]);
		int number = Integer.parseInt(beforeLastLine[8]);
		if (Integer.parseInt(beforeLastLine[6]) != Today.get(Calendar.MINUTE)) {
			if (beforeLastLine[1].equals("OK") && lastLine[1].equals("OK")) {
				number++;
				lastLine[8] = "" + number;
			} else {
				number = 0;
				lastLine[8] = "" + number;
			}
		} else {
			lastLine[8] = "" + number;
		}
		return lastLine;
	}

	public String[] changeNumberCounter1Line(String[] tab) {
		Calendar Today = Calendar.getInstance();
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		int number = Integer.parseInt(lastLine[8]);
		if (Integer.parseInt(lastLine[6]) != Today.get(Calendar.MINUTE)) {
			if (lastLine[1].equals("OK")) {
				number++;
				lastLine[8] = "" + number;
			} else {
				number = 0;
				lastLine[8] = "" + number;
			}
		} else {
			lastLine[8] = "" + number;
		}
		return lastLine;
	}

	public void incrementLog() {
		InputStream ips = null;
		int cpt = countLineFile();
		try {
			ips = new FileInputStream("application.log");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String[] lastLine;
			String[] tab = new String[cpt];
			int j = 0;
			while ((ligne = br.readLine()) != null) {
				tab[j] = ligne;
				j++;
			}
			if (cpt != 1) {
				lastLine = changeNumberCounter(tab);
			} else {
				lastLine = changeNumberCounter1Line(tab);
			}

			br.close();
			try {
				this.eraseFile(FICHIER);
			} catch (Exception e) {
				e.printStackTrace();
			}

			File FICHIER = new File("application" + ".log");
			PrintWriter output = new PrintWriter(new BufferedWriter(
					new FileWriter(FICHIER)));
			String modifiedLine = "";
			for (int l = 0; l < lastLine.length; l++) {
				modifiedLine += lastLine[l] + " ";
			}
			cpt++;
			tab[cpt] = modifiedLine;
			for (int k = 0; k < tab.length; k++) {
				output.println(tab[k]);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
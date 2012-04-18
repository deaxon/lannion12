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
	boolean fonctionCharlotte = true;
	String nameSite;
	UrlConnection urlconnect = new UrlConnection();

	public boolean createLog(String nameSite) {
		boolean result = false;

		Calendar Today = Calendar.getInstance();

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
					output.write("Application non OK : Access failed and Connection failed "
							+ cptMinute + "\n");
					System.out.println("File created");
					result = true;
				} else {
					output.write("Application non OK : Access failed "
							+ cptMinute + "\n");
					System.out.println("File created");
					result = true;
				}
			} else {
				output.write("Application non OK : Connection failed "
						+ cptMinute + "\n");
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
		StringTokenizer st = null;
		line = line.replaceAll("/", " ");
		line = line.replaceAll(":", " ");
		st = new StringTokenizer(line);
		String[] tabC = new String[st.countTokens()];
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
		if (beforeLastLine[1].equals("OK") && lastLine[1].equals("OK")) {
			if (Integer.parseInt(beforeLastLine[8]) != Today
					.get(Calendar.MINUTE)) {
				int number = Integer.parseInt(beforeLastLine[8]);
				number++;
				lastLine[8] = "" + number;
			}
		} else if (beforeLastLine[1].equals("OK") && lastLine[1].equals("non")) {
			if ((urlconnect.urlConnect(nameSite) == false)
					&& (fonctionCharlotte == false)) {
				int number = Integer.parseInt(lastLine[8]);
				number = 0;
				lastLine[8] = "" + number;
			} else {
				int number = Integer.parseInt(beforeLastLine[5]);
				number = 0;
				lastLine[5] = "" + number;
			}
		} else if (beforeLastLine[1].equals("non") && lastLine[1].equals("non")) {
			if ((urlconnect.urlConnect(nameSite) == false)
					&& (fonctionCharlotte == false)) {
				int number = Integer.parseInt(lastLine[8]);
				number++;
				lastLine[8] = "" + number;
			} else {
				int number = Integer.parseInt(beforeLastLine[5]);
				number++;
				lastLine[5] = "" + number;
			}
		} else {
			int number = Integer.parseInt(beforeLastLine[5]);
			number = 0;
			lastLine[8] = "" + number;
		}
		return lastLine;
	}

	public String[] changeNumberCounter1Line(String[] tab) {
		Calendar Today = Calendar.getInstance();
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		if (lastLine[1].equals("OK")) {
			int number = Integer.parseInt(lastLine[8]);
			number++;
			lastLine[8] = "" + number;
		} else {
			if ((urlconnect.urlConnect(nameSite) == false)
					&& (fonctionCharlotte == false)) {
				int number = Integer.parseInt(lastLine[8]);
				number++;
				lastLine[8] = "" + number;
			} else {
				int number = Integer.parseInt(lastLine[5]);
				number++;
				lastLine[5] = "" + number;
			}

		}
		return lastLine;
	}

	public String[] copyFileTab() {
		InputStream ips = null;
		String ligne;
		int cpt = countLineFile();
		String[] tab = new String[cpt];
		try {
			ips = new FileInputStream("application.log");
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			int j = 0;
			while ((ligne = br.readLine()) != null) {
				tab[j] = ligne;
				j++;
			}
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tab;
	}

	public void incrementLog() {
		int cpt = countLineFile();
		String[] lastLine;
		String[] tabCopyFile = copyFileTab();
		if (cpt != 1) {
			lastLine = changeNumberCounter(tabCopyFile);
		} else {
			lastLine = changeNumberCounter1Line(tabCopyFile);
		}
		PrintWriter output = null;
		try {
			this.eraseFile(FICHIER);
			output = new PrintWriter(
					new BufferedWriter(new FileWriter(FICHIER)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String modifiedLine = "";
		for (int l = 0; l < lastLine.length; l++) {
			modifiedLine += lastLine[l] + " ";
		}
		cpt--;
		tabCopyFile[cpt] = modifiedLine;
		for (int k = 0; k < tabCopyFile.length; k++) {
			output.println(tabCopyFile[k]);
		}
		output.close();
	}
}
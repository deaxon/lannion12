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
	File FICHIER;
	boolean fonctionCharlotte;
	String nameSite;
	boolean fonctionArthur;

	public Trace(File FICHIER, boolean fonctionCharlotte, String nameSite) {
		this.FICHIER = FICHIER;
		this.fonctionCharlotte = fonctionCharlotte;
		this.nameSite = nameSite;
		UrlConnection urlconnect = new UrlConnection();
		this.fonctionArthur = urlconnect.urlConnect(nameSite);
	}

	public boolean createLog() {
		boolean result = false;
		Calendar Today = Calendar.getInstance();
		FileWriter fw = null;
		BufferedWriter output = null;
		try {
			FICHIER.createNewFile();
			fw = new FileWriter(FICHIER, true);
			output = new BufferedWriter(fw);
			if ((fonctionArthur == true) && (fonctionCharlotte == true)) {
				output.write("Application OK : " + " "
						+ Today.get(Calendar.DAY_OF_MONTH) + "/"
						+ String.valueOf(Today.get(Calendar.MONTH) + 1) + "/"
						+ String.valueOf(Today.get(Calendar.YEAR)) + " "
						+ String.valueOf(Today.get(Calendar.HOUR_OF_DAY)) + ":"
						+ String.valueOf(Today.get(Calendar.MINUTE)) + ":"
						+ String.valueOf(Today.get(Calendar.SECOND)) + " "
						+ cptMinute + "\n");
				result = true;
			} else if (fonctionArthur == false) {
				if (fonctionCharlotte == false) {
					output.write("Application non OK : Access failed and Connection failed "
							+ cptMinute + "\n");
					result = true;
				} else {
					output.write("Application non OK : Access failed "
							+ cptMinute + "\n");
					result = true;
				}
			} else {
				output.write("Application non OK : Connection failed "
						+ cptMinute + "\n");
				result = true;
			}
			output.flush();
			output.close();
		} catch (IOException ioe) {
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
			while ((ligne = br.readLine()) != null) {
				cpt++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	public String[] connexionAccessFailed(String[] tab) {
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		cpt--;
		String[] beforeLastLine = this.replaceCaractFileInTab(tab[cpt]);
		if (beforeLastLine[1].equals("OK")) {
			int number = 0;
			lastLine[8] = "" + number;
		} else {
			int number = Integer.parseInt(beforeLastLine[8]);
			number++;
			lastLine[8] = "" + number;
		}
		return lastLine;
	}

	public String[] connexionFailed(String[] tab) {
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		cpt--;
		String[] beforeLastLine = this.replaceCaractFileInTab(tab[cpt]);
		int taille = beforeLastLine.length;
		taille--;
		if (beforeLastLine[1].equals("OK")) {
			int number = 0;
			lastLine[5] = "" + number;
		} else {
			int number = Integer.parseInt(beforeLastLine[taille]);
			number++;
			lastLine[5] = "" + number;
		}
		return lastLine;
	}

	public String[] accessFailed(String[] tab) {
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		cpt--;
		String[] beforeLastLine = this.replaceCaractFileInTab(tab[cpt]);
		int taille = beforeLastLine.length;
		taille--;
		if (beforeLastLine[1].equals("OK")) {
			int number = 0;
			lastLine[5] = "" + number;
		} else {
			int number = Integer.parseInt(beforeLastLine[taille]);
			number++;
			lastLine[5] = "" + number;
		}
		return lastLine;
	}

	public String[] accessConnexionSuccess(String[] tab) {
		Calendar Today = Calendar.getInstance();
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		cpt--;
		String[] beforeLastLine = this.replaceCaractFileInTab(tab[cpt]);
		int taille = beforeLastLine.length;
		taille--;
		if (beforeLastLine[1].equals("OK")) {
			if (Integer.parseInt(beforeLastLine[6]) != Today
					.get(Calendar.MINUTE)) {
				int number = Integer.parseInt(beforeLastLine[taille]);
				number++;
				lastLine[8] = "" + number;
			} else {
				lastLine = null;
			}
		} else {
			int number = 0;
			lastLine[8] = "" + number;
		}
		return lastLine;
	}

	public String[] changeNumberCounter(String[] tab) {
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
		if ((fonctionArthur == false) && (fonctionCharlotte == false)) {
			lastLine = connexionAccessFailed(tab);
		} else if (fonctionCharlotte == false) {
			lastLine = connexionFailed(tab);
		} else if (fonctionArthur == false) {
			lastLine = accessFailed(tab);
		} else {
			lastLine = accessConnexionSuccess(tab);
		}
		return lastLine;
	}

	public String[] changeFormat1Line(String[] tab) {
		int cpt = tab.length;
		cpt--;
		String[] lastLine = this.replaceCaractFileInTab(tab[cpt]);
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
			lastLine = changeFormat1Line(tabCopyFile);
		}
		PrintWriter output = null;
		try {
			this.eraseFile(FICHIER);
			output = new PrintWriter(
					new BufferedWriter(new FileWriter(FICHIER)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lastLine != null) {
			String modifiedLine = "";
			for (int l = 0; l < lastLine.length; l++) {
				modifiedLine += lastLine[l] + " ";
			}
			cpt--;
			tabCopyFile[cpt] = modifiedLine;

			for (int k = 0; k < tabCopyFile.length; k++) {
				output.println(tabCopyFile[k]);
			}
		} else {
			for (int k = 0; k < (tabCopyFile.length) - 1; k++) {
				output.println(tabCopyFile[k]);
			}
		}
		output.close();
	}
}
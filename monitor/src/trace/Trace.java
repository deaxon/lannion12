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

public class Trace {

	public static int cptMinute;
	File _file;
	boolean _loginConnect;
	boolean _urlConnect;

	public Trace(File file, boolean loginConnect, boolean urlConnect) {
		this._file = file;
		this._loginConnect = loginConnect;
		this._urlConnect = urlConnect;
	}

	public boolean createLog() {
		boolean result = false;
		Calendar Today = Calendar.getInstance();
		FileWriter fw = null;
		BufferedWriter output = null;
		try {
			_file.createNewFile();
			fw = new FileWriter(_file, true);
			output = new BufferedWriter(fw);
			if ((_urlConnect == true) && (_loginConnect == true)) {
				output.write("Application OK : "
						+ " "
						+ formatDateTime(Today.get(Calendar.DAY_OF_MONTH))
						+ "/"
						+ formatDateTime(Integer.parseInt(String.valueOf(Today
								.get(Calendar.MONTH) + 1)))
						+ "/"
						+ formatDateTime(Integer.parseInt(String.valueOf(Today
								.get(Calendar.YEAR))))
						+ " "
						+ formatDateTime(Integer.parseInt(String.valueOf(Today
								.get(Calendar.HOUR_OF_DAY))))
						+ ":"
						+ formatDateTime(Integer.parseInt(String.valueOf(Today
								.get(Calendar.MINUTE))))
						+ ":"
						+ formatDateTime(Integer.parseInt(String.valueOf(Today
								.get(Calendar.SECOND)))) + " " + cptMinute
						+ "\n");
				result = true;
			} else if (_urlConnect == false) {
				if (_loginConnect == false) {
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
			this.incrementLog();
		} catch (IOException ioe) {
			result = false;
			ioe.printStackTrace();
		}
		return result;
	}

	public String formatDateTime(int data) {
		String newData = "" + data;
		if (data < 10) {
			newData = "0" + data;
		}
		return newData;
	}

	public int countLineFile() {
		InputStream ips = null;
		int cpt = 0;
		
		try {
			ips = new FileInputStream(_file);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line="";
			while ((line = br.readLine()) != null) {
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
		int length = beforeLastLine.length;
		length--;
		if (beforeLastLine[1].equals("OK")) {
			int number = 0;
			lastLine[8] = "" + number;
		} else {
			int number = Integer.parseInt(beforeLastLine[length]);
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
		if ((_urlConnect == false) && (_loginConnect == false)) {
			lastLine = connexionAccessFailed(tab);
		} else if (_loginConnect == false) {
			lastLine = connexionFailed(tab);
		} else if (_urlConnect == false) {
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
			ips = new FileInputStream(_file);
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

	public String replaceFormat(String line) {
		StringBuffer sb = new StringBuffer(line);
		for (int j = 0; j < line.length(); j++) {
			if (line.charAt(12) != 'n') {
				if (j > 16 && j < 24) {
					if (line.charAt(j) == ' ') {
						sb.setCharAt(j, '/');
					}
				} else if (j >= 26 && j < 33) {
					if (line.charAt(j) == ' ') {
						sb.setCharAt(j, ':');
					}
				}
			}
		}
		line = sb.toString();
		return line;
	}

	public void incrementLog() {
		int cpt = countLineFile();
		String[] lastLine;
		String[] tabCopyFile = copyFileTab();
		System.out.println("cpt : "+cpt);
		if (cpt != 1) {
			lastLine = changeNumberCounter(tabCopyFile);
		} else {
			lastLine = changeFormat1Line(tabCopyFile);
		}
		PrintWriter output = null;
		try {
			this.eraseFile(_file);
			output = new PrintWriter(
					new BufferedWriter(new FileWriter(_file)));
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
			for (int i = 0; i < tabCopyFile.length; i++) {
				String line = tabCopyFile[i];
				line = replaceFormat(line);
				tabCopyFile[i] = line;
			}
			for (int k = 0; k < tabCopyFile.length; k++) {
				output.println(tabCopyFile[k]);
			}
		} else {
			for (int i = 0; i < tabCopyFile.length; i++) {
				String line = tabCopyFile[i];
				line = replaceFormat(line);
				tabCopyFile[i] = line;
			}
			for (int k = 0; k < (tabCopyFile.length) - 1; k++) {
				output.println(tabCopyFile[k]);
			}
		}
		output.close();
	}
}
package SlangWordDictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class SlangDictionaryApp {
	private TreeMap<String, String> slangWords;
	private int size;
	LinkedHashMap<String, String> historySearch;
	private ArrayList<String> answers;

	public SlangDictionaryApp() {
		slangWords = new TreeMap<String, String>();
		historySearch = new LinkedHashMap<String, String>();
		size = 0;
		loadData("slang.txt");
		answers = new ArrayList<String>();

	}
	
	public TreeMap<String, String> getSlangWords() {
		return slangWords;
	}

	public void setSlangWords(TreeMap<String, String> slangWords) {
		this.slangWords = slangWords;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public LinkedHashMap<String, String> getHistorySearch() {
		return historySearch;
	}

	public void setHistorySearch(LinkedHashMap<String, String> historySearch) {
		this.historySearch = historySearch;
	}

	public boolean checkSlangWordExist(String word) {
		if (slangWords.containsKey(word)) {
			return true;
		} else {
			return false;
		}
	}

	public void add(String word, String definition) {
		slangWords.put(word, definition);
		size++;
	}
	
	public void loadData(String fileName) {
		File file = new File(fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			String line = br.readLine();

			while (line != null) {
				String[] slangWordTemp = line.split("`");
				if (slangWordTemp.length != 1) {
					if (!checkSlangWordExist(slangWordTemp[0])) {
						add(slangWordTemp[0], slangWordTemp[1]);
					}
				}
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.print("File not found");
		} catch (IOException e) {
			System.out.print("Problem reading the file " + file.getName());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("unable to close file " + file.getName());
			} catch (NullPointerException ex) {
				System.out.println("file was probably never opened " + ex);
			}
		}

	}
	
	public void output() {

		for (Map.Entry<String, String> m : slangWords.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
		System.out.println(size);
	}
}

package SlangWordDictionary;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class SlangWordList {
	private TreeMap<String, String> slangWords;
	private int size;
	LinkedHashMap<String, String> historySearch;
	private ArrayList<String> answers;
	int correctAnswer;
	String question;

	public SlangWordList() {
		slangWords = new TreeMap<String, String>();
		historySearch = new LinkedHashMap<String, String>();
		size = 0;
		correctAnswer = 0;
		question = "";
		answers = new ArrayList<String>();
		//		saveFile("slang_original.txt");
		loadData("slang.txt");
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

	public String getDefinition(String word) {
		return slangWords.get(word);
	}

	public void loadData(String fileName) {
		File file = new File(fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			String line = br.readLine();

			while (line != null) {
				String[] slangWordTemp = line.split("`");
				if (slangWordTemp.length == 2 ) {
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

	
	public void saveFile(String fileName) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			for (Map.Entry<String, String> m : slangWords.entrySet()) {
				out.write(m.getKey() + "`" + m.getValue());
				out.newLine();
			}

			out.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}


	public String[][] findDefinition(String word) {
		ArrayList<String> listMeaning = new ArrayList();
		if (slangWords.get(word) != null) {
			historySearch.put(word, slangWords.get(word));
			listMeaning.add(word);
		} else {
			for (Map.Entry<String, String> m : slangWords.entrySet()) {
				if ((m.getKey()).toLowerCase().contains(word.toLowerCase())) {
					listMeaning.add(m.getKey());
					historySearch.put(m.getKey(), m.getValue());
				}
			}
		}
		
		int size = listMeaning.size();
		String s[][] = new String[size][2];
		for (int i = 0; i < size; i++) {
			s[i][0] = listMeaning.get(i);
			s[i][1] = slangWords.get(listMeaning.get(i));
		}
		return s;
	}

	public String[][] findSlangWords(String word) {
		ArrayList<String> listMeaning = new ArrayList();
		for (Map.Entry<String, String> m : slangWords.entrySet()) {
			if ((m.getValue()).toLowerCase().contains(word.toLowerCase())) {
				listMeaning.add(m.getKey());
				historySearch.put(m.getKey(), m.getValue());
			}
		}
		int size = listMeaning.size();
		String s[][] = new String[size][2];
		for (int i = 0; i < size; i++) {
			s[i][0] = listMeaning.get(i);
			s[i][1] = slangWords.get(listMeaning.get(i));
		}
		return s;
	}

	String[][] ViewSearchHistory() {
		ArrayList<String> listMeaning = new ArrayList();
		int size = historySearch.size();
		String s[][] = new String[size][2];

		if (size == 0) {
			return s;
		} else {
			Set<String> keySet = historySearch.keySet();
			for (String key : keySet) {
				listMeaning.add(key);
			}
		}
		for (int i = 0; i < size; i++) {
			s[i][0] = listMeaning.get(i);
			s[i][1] = slangWords.get(listMeaning.get(i));
		}
		return s;
	}

	public void AddNewWord(int type, String word, String definition) {
		if (type == 0) {
			add(word, definition);
		} 
		if (type == 1){
			definition = slangWords.get(word) + "| " + definition;
			add(word, definition);
		}
		saveFile("slang.txt");
	}

	public void editSlangWord(String word, String definition) {
		slangWords.put(word, definition);
		saveFile("slang.txt");
	}

	public void remove(String word) {
		slangWords.remove(word);
		size--;
	}
	
	public void deleteSlangWord(boolean option, String word) {
		if (option) {
			remove(word);
			saveFile("slang.txt");
		}
	}

	public void RestoreBackup(boolean option) {
		if (option) {
			slangWords.clear();
			loadData("slang_original.txt");
			size = slangWords.size();
		}

	}

	public String randomDefinition() {
		int randomIndex = (int) (Math.random() * size);
		Set<Map.Entry<String, String>> entries = slangWords.entrySet();
		List<Map.Entry<String, String>> listEntries = new ArrayList<Map.Entry<String, String>>(entries);

		return listEntries.get(randomIndex).getValue();
	}

	public String randomWSlangWord() {
		int randomIndex = (int) (Math.random() * size);

		Set<Map.Entry<String, String>> entries = slangWords.entrySet();
		List<Map.Entry<String, String>> listEntries = new ArrayList<Map.Entry<String, String>>(entries);

		return listEntries.get(randomIndex).getKey();
	}

	public String[] RandomWord() {
		String[] radomWord = new String [2];
		String word = randomWSlangWord();
		String definition = slangWords.get(word);

		radomWord[0] = word;
		radomWord[1] = definition;
		return radomWord;
	}

	public String[][] slangWordList() {
		ArrayList<String> listMeaning = new ArrayList();
		for (Map.Entry<String, String> m : slangWords.entrySet()) {
			listMeaning.add(m.getKey());
		}
		int size = listMeaning.size();
		String s[][] = new String[size][2];
		for (int i = 0; i < size; i++) {
			s[i][0] = listMeaning.get(i);
			s[i][1] = slangWords.get(listMeaning.get(i));
		}
		return s;
	}

	public boolean isNumeric(String string) {

		String regex = "[0-9]+[\\.]?[0-9]*";
		return Pattern.matches(regex, string);
	}

	public String getQuestion() {
		return question;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public String[] GuessDefinition() {
		// int correctAnswer = 0;
		String[] answerList = new String[4];
		String word = randomWSlangWord();
		String definition = slangWords.get(word);
		
		question = word;
		answers.add(definition);

		for (int i = 1; i < 4; i++) {
			String temp = randomDefinition();
			if (!temp.equals(definition)) {
				answers.add(temp);
			}
		}
		Collections.shuffle(answers);
		for (int i = 0; i < 4; i++) {
			if (answers.get(i).equals(definition)) {
				correctAnswer = i;
			}
		}
		for(int i = 0; i < answers.size(); i++) {
			answerList[i] = answers.get(i);
		}
		answers.removeAll(answers);

		return answerList;
	}
	
	public String[] GuessSlangWord() {
		String[] answerList = new String[4];
		String word = randomWSlangWord();
		String definition = slangWords.get(word);

		question = definition;
		answers.add(word);

		for (int i = 1; i < 4; i++) {
			String temp = randomWSlangWord();
			if (!temp.equals(word)) {
				answers.add(temp);
			}
		}
		Collections.shuffle(answers);
		for (int i = 0; i < 4; i++) {
			if (answers.get(i).equals(word)) {
				correctAnswer = i;
			}
		}
		for(int i = 0; i < answers.size(); i++) {
			answerList[i] = answers.get(i);
		}
		answers.removeAll(answers);

		return answerList;
	}
}

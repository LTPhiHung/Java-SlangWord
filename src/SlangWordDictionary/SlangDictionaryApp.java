package SlangWordDictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

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
	
	public String inputSlangWord() {
		String word = "";
		do {
			BufferedReader bReader;
			try {
				bReader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
				word = bReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (word.equals("")) {
				System.out.print("Enter agian! Your input: ");
			}
		} while (word.equals(""));
		return word;

	}
	
	public void findDefinition() {
		System.out.println("Search a slang word");
		System.out.print("Enter a word: ");
		String word = inputSlangWord();
		System.out.printf("%-20s %-20s\n", "Slang Word" , "Defination");
		if (slangWords.get(word) != null) {
			historySearch.put(word, slangWords.get(word));
			System.out.printf("%-20s %-20s\n", word , slangWords.get(word));
		} else {
			boolean checkFind = false;
			for (Map.Entry<String, String> m : slangWords.entrySet()) {
				if ((m.getKey()).toLowerCase().contains(word.toLowerCase())) {
					checkFind = true;
					System.out.printf("%-20s %-20s\n",m.getKey(), m.getValue());
					historySearch.put(m.getKey(), m.getValue());
				}
			}
			if (!checkFind) {
				System.out.println("Not found!");
			}
		}

	}
	
	public void findSlangWords() {
		System.out.println("Enter the definition you want to search: ");
		System.out.print("Enter a definition: ");
		String word = inputSlangWord();
		System.out.printf("%-20s %-20s\n", "Slang Word" , "Defination");
		boolean checkFind = false;
		for (Map.Entry<String, String> m : slangWords.entrySet()) {
			if ((m.getValue()).toLowerCase().contains(word.toLowerCase())) {
				checkFind = true;
				System.out.printf("%-20s %-20s\n", m.getKey(), m.getValue());
				historySearch.put(m.getKey(), m.getValue());
			}
		}
		if (!checkFind) {
			System.out.println("Not found!");
		}
	}
	
	public void ViewSearchHistory() {
		if (historySearch.size() == 0) {
			System.out.println("No search history!");
		} else {
			System.out.println("Search history:");
			int i = 1;
			System.out.printf("%-20s %-20s %-20s\n", "STT", "Slang Word" , "Defination");
			Set<String> keySet = historySearch.keySet();
			for (String key : keySet) {
				System.out.printf("%-20s %-20s %-20s\n",i + ". ",  key , historySearch.get(key));
				i++;
			}
		}
	}
	
	public void AddNewWord() {
		System.out.print("Enter the slang word: ");
		String word = inputSlangWord();
		System.out.print("Enter the definition: ");
		String definition = inputSlangWord();
		String type;
		System.out.println("Choose type of edit: ");
		System.out.println("1. overwrite");
		System.out.println("2. duplicate");
		System.out.print("Your choice: ");
		if (checkSlangWordExist(word)) {
			do {

				type = inputSlangWord();
				if (type.equals("1") || type.equals("2")) {
					break;
				}
			} while (true);

			System.out.print("Old definition: " + slangWords.get(word) + "\n");
			System.out.print("Enter the new definition: ");
			if (type.equals("1")) {
				add(word, definition);
			} else {
				definition = slangWords.get(word) + "| " + definition;
				add(word, definition);
			}
		} else {
			add(word, definition);
		}
		System.out.println("Add new word successfully!");
	}
	
	public void EditSlangWord() {
		System.out.print("Enter the word you want to edit: ");
		String word = inputSlangWord();
		if (checkSlangWordExist(word)) {
			System.out.print("Old definition: " + slangWords.get(word) + "\n");
			System.out.print("Enter the new definition: ");
			String definition = inputSlangWord();
			slangWords.put(word, definition);
			System.out.println("Edit successfully!");
		} else {
			System.out.println("Word not found!");
		}
		System.out.println("Press enter to continue...");
	}
	
	public void remove(String word) {
		slangWords.remove(word);
		size--;
	}

	public void deleteSlangWord() {
		System.out.print("Enter the word you want to delete: ");
		String word = inputSlangWord();
		if (checkSlangWordExist(word)) {
			System.out.println("Slang word: " + word + " , definition: " + slangWords.get(word));
			System.out.println("Are you sure you want to delete this word? (Y/N)");
			System.out.println("Enter Y if you want to delete this word, and N if you don't want to delete this word");
			System.out.print("Enter your choice: ");
			String choice = inputSlangWord();
			if (choice.equalsIgnoreCase("Y")) {
				System.out.println("Delete successfully!");
				remove(word);
			} else {
				System.out.println("Delete unsuccessfully!");
			}
		} else {
			System.out.println("Word not found!");
		}
	}
	
	public void RestoreBackup() {
		System.out.println("Are you sure you want to restore backup? (Y/N)");
		System.out.print("Enter your choice: ");
		String choice = inputSlangWord();
		if (choice.equals("Y") || choice.equals("y")) {
			slangWords.clear();
			loadData("slang_original.txt");
			size = slangWords.size();
			System.out.println("Backup restored!");

		} else {
			System.out.println("Backup not restored!");
		}
	}
	
	public String randomWSlangWord() {
		int randomIndex = (int) (Math.random() * size);

		Set<Map.Entry<String, String>> entries = slangWords.entrySet();
		List<Map.Entry<String, String>> listEntries = new ArrayList<Map.Entry<String, String>>(entries);

		return listEntries.get(randomIndex).getKey();
	}
	
	public String randomdefinition() {
		int randomIndex = (int) (Math.random() * size);
		Set<Map.Entry<String, String>> entries = slangWords.entrySet();
		List<Map.Entry<String, String>> listEntries = new ArrayList<Map.Entry<String, String>>(entries);

		return listEntries.get(randomIndex).getValue();
	}
	
	public void RandomWord() {
		String word = randomWSlangWord();
		System.out.println("Slang word: " + word + ", definition: " + slangWords.get(word));
	}
	

	public boolean isNumeric(String string) {

		String regex = "[0-9]+[\\.]?[0-9]*";
		return Pattern.matches(regex, string);
	}

	public void Quiz(int correctAnswer) {
		boolean checkAnwer = false;
		for (int i = 1; i <= answers.size(); i++) {
			System.out.println(i + ": " + answers.get(i - 1));
		}
		int userAnswer = 0;

		do {
			if (userAnswer < 0 || userAnswer >= answers.size()) {

				System.out.println("Please enter a valid answer!");
			}
			System.out.print("Enter your answer: ");
			String line = inputSlangWord();
			if (isNumeric(line)) {
				userAnswer = Integer.parseInt(line) - 1;
			} else {
				userAnswer = -1;
			}

		} while (userAnswer < 0 || userAnswer >= answers.size());

		if (userAnswer == -1) {
			System.out.println("You haven't answered yet!");
		} else if (userAnswer == correctAnswer) {
			System.out.println("=====>Correct Answer!");
			checkAnwer = true;
		} else {
			System.out.println("=====>Wrong Answer!");
		}

		if (!checkAnwer) {
			System.out.println("*The correct answer is: " + answers.get(correctAnswer));
		}
		answers.removeAll(answers);
	}

	public void GuessDefinition() {
		int correctAnswer = 0;
		String word = randomWSlangWord();
		String difination = slangWords.get(word);

		System.out.println("Guess the definition of: " + word);
		answers.add(difination);

		for (int i = 1; i < 4; i++) {
			String temp = randomdefinition();
			if (!temp.equals(difination)) {
				answers.add(temp);
			}
		}
		Collections.shuffle(answers);
		for (int i = 0; i < 4; i++) {
			if (answers.get(i).equals(difination)) {
				correctAnswer = i;
			}
		}
		Quiz(correctAnswer);
	}
	
	public void GuessSlangWord() {
		int correctAnswer = 0;
		String word = randomWSlangWord();
		String difination = slangWords.get(word);
		System.out.println("Guess the slang word of: " + difination);
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
		Quiz(correctAnswer);
	}

	
	public void output() {

		for (Map.Entry<String, String> m : slangWords.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
		System.out.println(size);
	}
}

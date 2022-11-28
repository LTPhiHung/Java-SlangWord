package SlangWordDictionary;

import java.io.BufferedReader;
import java.util.*;

public class Main {
	public static void showMenu() {
		 
	     System.out.println("-----------menu------------");
	     System.out.println("\t---Welcome to Slang Word List Application---");
         System.out.println("1. Search by slang word\t\t\t6. Delete slang word");
         System.out.println("2. Search by definition\t\t\t7. Restore backup");
         System.out.println("3. View search history\t\t\t8. On this day slang word");
         System.out.println("4. Add new slang word\t\t\t9. Quiz: guess the definition");
         System.out.println("5. Edit slang word\t\t\t10. Random game: guess the slang word");
         System.out.println("0. Exit");
	     System.out.println("---------------------------");
	     System.out.print("Please choose: ");
	 }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean exit= true;
		Scanner scan = new Scanner(System.in);
		
		SlangDictionaryApp slangWords = new SlangDictionaryApp();

		do {
			showMenu();
			System.out.print(">>Mời chọn: ");
			int chon = Integer.parseInt(scan.nextLine());
			switch (chon) {
			case 0:
				exit = false;
				break;
			case 1:
				slangWords.findDefinition();
				break;
			case 2:
				slangWords.findSlangWords();
				break;
			case 3:
				slangWords.ViewSearchHistory();
				break;
			case 4:				
				slangWords.AddNewWord();
				break;
			case 5:
				slangWords.EditSlangWord();
				break;
			case 6:
				slangWords.deleteSlangWord();
				break;
			case 7:
				slangWords.RestoreBackup();
				break;
			case 8:
				slangWords.RandomWord();
				break;
			case 9:
				slangWords.GuessDefinition();
				break;
			case 10:
				slangWords.GuessSlangWord();
				break;
	        default:
	            System.out.println("invalid! please choose action in below menu:");
	            break;
			}

		} while (exit);
	}

}

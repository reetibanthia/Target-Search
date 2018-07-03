import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DocumentSearch {
	static String term;
	static int search;
	static ArrayList<FileRelevency> docs = new ArrayList<FileRelevency>();

	public static void main(String[] args) {
		DocumentSearch d = new DocumentSearch();
		d.populateDocs();
		d.prompt();
	}

	public void populateDocs() {
		File folder = new File("./");
		File[] listOfFiles = folder.listFiles();
		System.out.println("Searching from the following files: ");
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].toString().contains(".txt")) {
				docs.add(new FileRelevency(listOfFiles[i]));
				System.out.println("File " + listOfFiles[i].getName());
			} 
		}
	}

	@SuppressWarnings("resource")
	public void prompt() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the search term: ");
		term = " "+scan.nextLine().toLowerCase();
		if(term.lastIndexOf(' ') != term.length()-1) {
			term+=" ";
		}
		int search;
		do {
			System.out.print(
					"\nSearch Method? \n1) String Match\n2) Regular Expression\n3) Indexed. \nEnter 1,2, or 3:  ");
			while (!scan.hasNextInt()) {
				System.out.print("\nPlease enter a valid search method choice: ");
				scan.next();
			}
			search = scan.nextInt();
		} while (search < 1 && search > 3);

		switch (search) {
		case 1:
			stringMatch();
			break;
		case 2:
			regEx();
			break;
		case 3:
			indexed();
			break;
		}

	}

	public void stringMatch() {
		Scanner s=new Scanner(System.in);
		String temp="hello";
		for(FileRelevency fr: docs) {
			try {
				s =  new Scanner(fr.getFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while(s.hasNextLine()) {
				temp = " "+ s.nextLine().toLowerCase().replaceAll("\\p{Punct}", "");
				while(temp.indexOf(term) != -1) {
					fr.addCount();
					temp = temp.substring(temp.indexOf(term)+term.length()-1);
					System.out.println(temp);
				}
			}
		}
		for(FileRelevency fr: docs) {
			System.out.println(fr.getFile().getName() + "  "+ fr.getCount());
		}
		
	}

	public void regEx() {

	}

	public void indexed() {

	}
	
	public void printSorted() {
		
	}

}

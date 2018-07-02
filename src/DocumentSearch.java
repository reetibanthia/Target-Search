import java.io.File;
import java.util.*;

public class DocumentSearch {
	static String term;
	static int search;
	static ArrayList<File> docs = new ArrayList<File>();

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
				docs.add(listOfFiles[i]);
				System.out.println("File " + listOfFiles[i].getName());
			} 
		}
	}

	@SuppressWarnings("resource")
	public void prompt() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the search term: ");
		term = scan.next();
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

	}

	public void regEx() {

	}

	public void indexed() {

	}

}

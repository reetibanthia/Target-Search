import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentSearch {
	static String term;
	static int search;
	static ArrayList<FileRelevency> docs = new ArrayList<FileRelevency>();
	Scanner s = new Scanner(System.in);
	Long startTime = (long) 0;
	Long endTime = (long) 0;

	public static void main(String[] args) {
		DocumentSearch d = new DocumentSearch();
		d.populateDocs();
		d.prompt();
		d.printSorted();
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
		s = new Scanner(System.in);
		System.out.print("Enter the search term: ");
		term = " " + s.nextLine().toLowerCase();
		if (term.lastIndexOf(' ') != term.length() - 1) {
			term += " ";
		}
		int search;
		do {
			System.out.print(
					"\nSearch Method? \n1) String Match\n2) Regular Expression\n3) Indexed. \nEnter 1,2, or 3:  ");
			while (!s.hasNextInt()) {
				System.out.print("\nPlease enter a valid search method choice: ");
				s.next();
			}
			search = s.nextInt();
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
		startTime = System.currentTimeMillis();
		s = new Scanner(System.in);
		String temp = "";
		for (FileRelevency fr : docs) {
			try {
				s = new Scanner(fr.getFile());
				while (s.hasNextLine()) {
					temp = " " + s.nextLine().toLowerCase().replaceAll("\\p{Punct}", " ");
					while (temp.indexOf(term) != -1) {
						fr.addCount();
						temp = temp.substring(temp.indexOf(term) + term.length() - 1);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
	}

	public void regEx() {
		startTime = System.currentTimeMillis();
		s = new Scanner(System.in);
		String temp = "";
		Pattern p = Pattern.compile(term.trim());
		for (FileRelevency fr : docs) {
			try {
				s = new Scanner(fr.getFile());
				if (s.hasNextLine()) {
					temp = s.useDelimiter("\\Z").next().toLowerCase();
					Matcher m = p.matcher(temp);
					int start = 0;
					try {
						while (m.find(start)) {
							fr.addCount();
							start = m.start() + 1;
						}
					} catch (Exception e) {
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		endTime = System.currentTimeMillis();
	}

	public void indexed() {
		startTime = System.currentTimeMillis();
		for (FileRelevency fr : docs) {
			fr.createIndex();
			fr.getInstances(term.toLowerCase());
		}
		endTime = System.currentTimeMillis();
	}

	public class CustomComparator implements Comparator<FileRelevency> {
		@Override
		public int compare(FileRelevency o1, FileRelevency o2) {
			return o2.getCount() - o1.getCount();
		}
	}

	public void printSorted() {
		System.out.println();
		Collections.sort(docs, new CustomComparator());
		for (FileRelevency fr : docs) {
			System.out.format("%-40s%-40d\n", fr.getName(), fr.getCount());
		}
		System.out.format("Elapsed Time: %10d", endTime - startTime);
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileRelevency {
	File file;
	Integer count;
	Map<String, Integer> index = new HashMap<String, Integer>();

	public FileRelevency(File inputFile) {
		this.file = inputFile;
		this.count = 0;
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return file.getName();
	}

	public int getCount() {
		return count;
	}

	public void setCount(Integer i) {
		count = i;
	}

	public void addCount() {
		count++;
	}

	public void getInstances(String term) {
		try {
			Integer val = this.index.get(term.trim());
			setCount(val);
		} catch (Exception e) {
			count = 0;
		}

	}

	public void createIndex() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		String temp = new String();
		Integer num = 0;
		try {
			s = new Scanner(getFile());
			while (s.hasNext()) {
				temp = s.next().toLowerCase().replaceAll("\\p{Punct}", "");
				if (index.get(temp) != null) {
					num = index.get(temp);
					this.index.put(temp, num + 1);
				} else {
					this.index.put(temp, 1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

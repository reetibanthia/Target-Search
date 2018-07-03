import java.io.File;
import java.util.Comparator;

public class FileRelevency {
	File file;
	int count;
	
	public FileRelevency(File inputFile) {
		this.file = inputFile;
		this.count=0;
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
	
	public void addCount() {
		count++;
	}
	
}

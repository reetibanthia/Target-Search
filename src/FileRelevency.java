import java.io.File;

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
	
	public int getCount() {
		return count;
	}
	
	public void addCount() {
		count++;
	}

}

package generation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileIterator {

	BufferedReader file;

	public FileIterator(String file) {
		try {
			this.file = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File " + file + " not found");
			e.printStackTrace();
		}
	}

	public String nextLine() {
		String re = null;
		try {
			re = file.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	public void close() {
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

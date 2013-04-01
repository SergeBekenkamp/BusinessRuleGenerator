package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SQLFileOutput implements IOutput {
	
	StringBuilder sb = new StringBuilder();

	@Override
	public void addString(String output) {
		System.out.println(output);
		sb.append(output + "\n");
	}

	@Override
	public boolean saveOutput(String fileName) {
		File file = new File(fileName);
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".txt"));
		writer.write(sb.toString());
		writer.close();
		} catch (IOException e){
			System.out.println("Something went wrong with saving the output");
			System.out.println(e);
			return false;
		}
		sb = new StringBuilder();
		return true;
	}
	
}
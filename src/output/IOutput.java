package output;

import java.util.HashMap;

public interface IOutput {
	
	public void setCode(HashMap<String, String> code);
	public String doOutput(String s);

}

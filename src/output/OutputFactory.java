package output;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class OutputFactory {

	public OutputFactory() {

	}

	public IOutput createOutput(String outputType) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c = Class.forName("output." + outputType);
		return (IOutput) c.newInstance();
	}

	public List<String> getOutputTypes() throws IOException {
		return getPackageContent("output");
	}

	private List<String> getPackageContent(String packageName) throws IOException {
		ArrayList<String> classes = new ArrayList<String>();
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			File dir = new File(url.getFile());
			for (File f : dir.listFiles()) {
				String s = f.getName().replaceAll(".class", "");
				if (!(s.equals("IOutput") || s.equals("OutputFactory"))) {
					classes.add(s);
				}
			}
		}
		return classes;
	}

}

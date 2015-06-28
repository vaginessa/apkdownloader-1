package ooO0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AcountRead {
	public StringBuilder readpack() throws IOException {
		File sourceFile = new File("update.txt");
		FileReader frFileReader = new FileReader(sourceFile);
		BufferedReader bfrBufferedReader = new BufferedReader(frFileReader);
		String buf = null;
		// int i = 0;
		StringBuilder sbBuilder = new StringBuilder();
		// String[] pack = new [100];
		while ((buf = bfrBufferedReader.readLine()) != null) {
			// type type = (type) bfrBufferedReader.readLine().nextElement();
			sbBuilder.append(buf + " ");
		}
		frFileReader.close();
		return sbBuilder;

	}
}

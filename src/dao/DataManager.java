package dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 *
 * @author Litovchenko Vlad
 */
public class DataManager {	

	public static void saveTextData(List<?> lst, String filePath) throws IOException {
		try (BufferedWriter bW = Files.newBufferedWriter(Paths.get(filePath),
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			for (Object obj : lst) {
				bW.write(obj.toString());
				bW.newLine();
			}
		}
	}

	public static List<String> readTextData(String filePath) throws IOException {				
		return Files.readAllLines(Paths.get(filePath));
	}
}
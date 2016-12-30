package jks.csv;

import static java.util.stream.Collectors.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CsvScaffolding {
	
	public enum LineMode {
		ALL, SKIP_FIRST;
	}
	
	public <T> List<T> parseString(String csv, Function<String, T> lineParser,
			LineMode lineMode) {
		String[] lines = csv.split("\n");

		int startIdx = lineMode == LineMode.SKIP_FIRST ? 1 : 0;
		int endIdx = lines.length;
		
		return Arrays.stream(lines, startIdx, endIdx)
		             .map(lineParser::apply)
		             .collect(toList());
	}
	
	public <T> List<T> parseFile(Path path, Function<String, T> lineParser,
			LineMode lineMode) {
		try {
			String fileContents = TextFileReader.read(path);
			return parseString(fileContents, lineParser, lineMode);
			
		} catch (IOException ex) {
			throw new RuntimeException("Error while parsing input stream: " + ex);
		}
	}
	
	public <T> List<T> parseStream(InputStream inStream, 
			Function<String, T> lineParser, LineMode lineMode) {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(inStream))) {
			
			if (lineMode == LineMode.SKIP_FIRST) {
				reader.readLine();
			}
			
			return reader.lines()
                         .map(lineParser::apply)
                         .collect(toList());
		} catch (IOException ex) {
			throw new RuntimeException("Error while parsing input stream: " + ex);
		}
	}
	
}

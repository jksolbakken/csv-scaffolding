package jks.csv;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileReader {
	
	public static String read(Path path) throws IOException {
		return Files.readAllLines(path)
                    .stream()
                    .collect(joining("\n"));
	}

}

package jks.csv;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import jks.csv.TextFileReader;

public class TextFileReaderTest {
	
	private static final String EXPECTED  = "This,is,line,1\nThis,is,line,2";

	@Test
	public void matchingFileContent() throws Exception {
		URL url = this.getClass().getResource("/testfile.csv");
		Path path = Paths.get(url.toURI());
		String fileContents = TextFileReader.read(path);
		assertThat(fileContents, is(EXPECTED));
	}

}

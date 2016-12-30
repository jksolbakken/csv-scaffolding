package jks.csv;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import jks.csv.CsvScaffolding;
import jks.csv.TextFileReader;
import jks.csv.CsvScaffolding.LineMode;

public class CsvScaffoldingTest {
	private static final String FIRST_LINE = "This,is,line,1";
	private static final String SECOND_LINE = "This,is,line,2";

	@Test
	public void allLinesFromStream() throws Exception {
		InputStream inStream = this.getClass().getResourceAsStream("/testfile.csv");
		CsvScaffolding scaffolding = new CsvScaffolding();
		List<String> parsed = 
				scaffolding.parseStream(inStream, str -> str, LineMode.ALL);
		inStream.close();
		List<String> expected = Arrays.asList(FIRST_LINE, SECOND_LINE);
		assertThat(parsed, is(expected));
	}
	
	@Test
	public void allLinesFromString() throws Exception {
		URI uri = this.getClass().getResource("/testfile.csv").toURI();
		Path path = Paths.get(uri);
		String lines = TextFileReader.read(path);
		CsvScaffolding scaffolding = new CsvScaffolding();
		List<String> parsed = 
				scaffolding.parseString(lines, str -> str, LineMode.ALL);
		List<String> expected = Arrays.asList(FIRST_LINE, SECOND_LINE);
		assertThat(parsed, is(expected));
	}
	
	@Test
	public void skipFirstLineInStream() throws Exception {
		InputStream inStream = this.getClass().getResourceAsStream("/testfile.csv");
		CsvScaffolding scaffolding = new CsvScaffolding();
		List<String> parsed = 
				scaffolding.parseStream(inStream, str -> str, LineMode.SKIP_FIRST);
		inStream.close();
		List<String> expected = Arrays.asList(SECOND_LINE);
		assertThat(parsed, is(expected));
	}
	
	@Test
	public void skipFirstLineInString() throws Exception {
		URI uri = this.getClass().getResource("/testfile.csv").toURI();
		Path path = Paths.get(uri);
		String lines = TextFileReader.read(path);
		CsvScaffolding scaffolding = new CsvScaffolding();
		List<String> parsed = 
				scaffolding.parseString(lines, str -> str, LineMode.SKIP_FIRST);
		List<String> expected = Arrays.asList(SECOND_LINE);
		assertThat(parsed, is(expected));
	}
	
}

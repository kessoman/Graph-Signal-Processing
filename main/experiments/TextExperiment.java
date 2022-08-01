package experiments;
import core.*;
import loaded.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class TextExperiment {

	public static void main(String[] args) {
		Path p = Paths.get("pagetest.csv");
		Node sourceNode = new LoadedNode("Q");
		Node destinationNode = new LoadedNode("R");
		String s = System.lineSeparator() + sourceNode.toString() + " , " + destinationNode.toString();
		try {
		    Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}

}

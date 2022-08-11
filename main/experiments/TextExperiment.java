package experiments;
import core.*;
import disc.PartialDiscGraph;
import loaded.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class TextExperiment {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\kesso\\Documents\\Edges\\");
		PartialDiscGraph graph = new PartialDiscGraph(file);
		File infile = new File("pagetest.csv");
		long tic = System.currentTimeMillis();
		Scanner scanner = null ;
		try {
			 scanner = new Scanner(infile);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			while (scanner.hasNextLine()) {
				String[] links = scanner.nextLine().split(",");
				links[1] = links[1].strip();
				links[0] = links[0].strip();
				Node node1 = new LoadedNode(links[0]);
				Node node2 = new LoadedNode(links[1]);
				graph.addEdge(node1, node2);
			}
		long toc = System.currentTimeMillis();
		System.out.println("PartialDiscGraph" + (toc - tic)/1000);
		graph.clearGraphHistory();
	//} catch (FileNotFoundException e) {
		//e.printStackTrace();
	
	//}

}

}
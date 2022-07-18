package experiments;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.naming.spi.DirStateFactory.Result;

import org.neo4j.cypher.*;
import org.neo4j.cypher.internal.ExecutionEngine;
import org.neo4j.cypher.internal.javacompat.ExecutionResult;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
public class Neo4jMain {
	private static final String DB_PATH = "C:...../default.graphdb";
	public static void main(String[] args) {
		File file = new File("links_all.csv");
		GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
		GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
				  new File("links_all.csv"));
		graphDb.
		try {
			Scanner scanner = new Scanner(file);
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(file);

		Transaction tx1 = db.beginTx();
	    try{

	      ExecutionEngine engine = new ExecutionEngine(db);
	      ExecutionResult result = engine.execute("LOAD CSV WITH HEADERS FROM "C:/..../Mock_data.csv" AS csvLine ");

	        tx1.success();
	    } finally {
	        tx1.close();

	    }
	    db.shutdown();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	}
}	

package org.structr.test.lucene.npe;

import java.io.File;
import java.util.Date;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 */
public class Main {

	public static void main(final String[] args) {

		try {
		
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(new File("/tmp/graph.db." + new Date().getTime()));

		Transaction tx = db.beginTx();

		db.index().forNodes("TestIndex").add(db.createNode(), "test", new Test());

		tx.success();
		tx.close();
		
		db.shutdown();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println("The Neo4j version is NOT affected by the issue described in https://github.com/neo4j/neo4j/issues/6685.");
		System.out.println("----------------------------------------------------------------------------------------------------------------");

		System.exit(0);
		
		} catch (Throwable t) {
		
			t.printStackTrace();
			
			System.out.println("----------------------------------------------------------------------------------------------------------------");
			System.out.println("ERROR: The Neo4j version seems to be affected by the issue described in https://github.com/neo4j/neo4j/issues/6685.");
			System.out.println("----------------------------------------------------------------------------------------------------------------");
	
			System.exit(1);
			
		}

	}

	static class Test {

		@Override
		public String toString() {
			return null;
		}

	}

}

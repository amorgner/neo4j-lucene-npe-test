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

		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(new File("/tmp/graph.db." + new Date().getTime()));

		Transaction tx = db.beginTx();

		db.index().forNodes("TestIndex").add(db.createNode(), "test", new Test());

		tx.success();
		tx.close();

	}

	static class Test {

		@Override
		public String toString() {
			return null;
		}

	}

}

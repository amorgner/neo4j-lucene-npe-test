# neo4j-lucene-npe-test

Simple test project which creates a broken and unrecoverable Neo4j database.

## Usage

Set the Neo4j version to be tested in `pom.xml` and run 

    mvn clean install exec:java -Dexec.mainClass="org.structr.test.lucene.npe.Main"

## Result

If you see the following result, the version is affected.

	[WARNING] 
	java.lang.reflect.InvocationTargetException
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.codehaus.mojo.exec.ExecJavaMojo$1.run(ExecJavaMojo.java:293)
		at java.lang.Thread.run(Thread.java:745)
	Caused by: org.neo4j.graphdb.TransactionFailureException: Transaction was marked as successful, but unable to commit transaction so rolled back.
		at org.neo4j.kernel.TopLevelTransaction.close(TopLevelTransaction.java:121)
		at org.structr.test.lucene.npe.Main.main(Main.java:27)
		... 6 more
	Caused by: org.neo4j.kernel.api.exceptions.TransactionFailureException: Could not apply the transaction to the store after written to log
		at org.neo4j.kernel.impl.api.TransactionRepresentationCommitProcess.applyToStore(TransactionRepresentationCommitProcess.java:105)
		at org.neo4j.kernel.impl.api.TransactionRepresentationCommitProcess.commit(TransactionRepresentationCommitProcess.java:58)
		at org.neo4j.kernel.impl.api.KernelTransactionImplementation.commit(KernelTransactionImplementation.java:565)
		at org.neo4j.kernel.impl.api.KernelTransactionImplementation.close(KernelTransactionImplementation.java:458)
		at org.neo4j.kernel.TopLevelTransaction.close(TopLevelTransaction.java:97)
		... 7 more
	Caused by: java.lang.NullPointerException
		at org.neo4j.index.impl.lucene.IndexType.instantiateField(IndexType.java:318)
		at org.neo4j.index.impl.lucene.IndexType$1.addToDocument(IndexType.java:63)
		at org.neo4j.index.impl.lucene.LuceneCommandApplier.visitIndexAddNodeCommand(LuceneCommandApplier.java:65)
		at org.neo4j.kernel.impl.api.LegacyIndexApplier.visitIndexAddNodeCommand(LegacyIndexApplier.java:137)
		at org.neo4j.kernel.impl.api.CommandApplierFacade.visitIndexAddNodeCommand(CommandApplierFacade.java:232)
		at org.neo4j.kernel.impl.index.IndexCommand$AddNodeCommand.handle(IndexCommand.java:150)
		at org.neo4j.kernel.impl.api.CommandApplierFacade.visit(CommandApplierFacade.java:82)
		at org.neo4j.kernel.impl.api.CommandApplierFacade.visit(CommandApplierFacade.java:45)
		at org.neo4j.kernel.impl.transaction.log.PhysicalTransactionRepresentation.accept(PhysicalTransactionRepresentation.java:69)
		at org.neo4j.kernel.impl.api.TransactionRepresentationStoreApplier.apply(TransactionRepresentationStoreApplier.java:111)
		at org.neo4j.kernel.impl.api.TransactionRepresentationCommitProcess.applyToStore(TransactionRepresentationCommitProcess.java:100)
		... 11 more


When trying to start the database with Neo4j server, you'll see the following in `messages.log`:

	2016-03-12 13:04:58.138+0000 INFO  [o.n.k.a.i.i.LuceneLabelScanStore] No lucene scan store index found, this might just be first use. Preparing to rebuild.
	2016-03-12 13:04:58.213+0000 INFO  [o.n.k.NeoStoreDataSource] Recovery required from position LogPosition{logVersion=0, byteOffset=16}
	2016-03-12 13:04:58.252+0000 ERROR [o.n.k.KernelHealth] setting TM not OK. Kernel has encountered some problem, please perform necessary action (tx recovery/restart)
	java.lang.NullPointerException
		at org.neo4j.index.impl.lucene.IndexType.instantiateField(IndexType.java:318)
		at org.neo4j.index.impl.lucene.IndexType$1.addToDocument(IndexType.java:63)
		at org.neo4j.index.impl.lucene.LuceneCommandApplier.visitIndexAddNodeCommand(LuceneCommandApplier.java:65)
		at org.neo4j.kernel.impl.api.LegacyIndexApplier.visitIndexAddNodeCommand(LegacyIndexApplier.java:137)
		at org.neo4j.kernel.impl.api.CommandApplierFacade.visitIndexAddNodeCommand(CommandApplierFacade.java:232)
		at org.neo4j.kernel.impl.index.IndexCommand$AddNodeCommand.handle(IndexCommand.java:150)
		at org.neo4j.kernel.impl.api.CommandApplierFacade.visit(CommandApplierFacade.java:82)
		at org.neo4j.kernel.impl.api.CommandApplierFacade.visit(CommandApplierFacade.java:45)
		at org.neo4j.kernel.impl.transaction.log.PhysicalTransactionRepresentation.accept(PhysicalTransactionRepresentation.java:69)
		at org.neo4j.kernel.impl.api.TransactionRepresentationStoreApplier.apply(TransactionRepresentationStoreApplier.java:111)
		at org.neo4j.kernel.impl.transaction.state.RecoveryVisitor.visit(RecoveryVisitor.java:73)
		at org.neo4j.kernel.impl.transaction.state.RecoveryVisitor.visit(RecoveryVisitor.java:37)
		at org.neo4j.kernel.impl.transaction.log.LogFileRecoverer.visit(LogFileRecoverer.java:71)
		at org.neo4j.kernel.impl.transaction.log.LogFileRecoverer.visit(LogFileRecoverer.java:33)
		at org.neo4j.kernel.recovery.Recovery.init(Recovery.java:86)
		at org.neo4j.kernel.lifecycle.LifeSupport$LifecycleInstance.init(LifeSupport.java:424)
		at org.neo4j.kernel.lifecycle.LifeSupport.init(LifeSupport.java:66)
		at org.neo4j.kernel.lifecycle.LifeSupport.start(LifeSupport.java:102)
		at org.neo4j.kernel.NeoStoreDataSource.start(NeoStoreDataSource.java:600)
		at org.neo4j.kernel.lifecycle.LifeSupport$LifecycleInstance.start(LifeSupport.java:452)
		at org.neo4j.kernel.lifecycle.LifeSupport.start(LifeSupport.java:111)
		at org.neo4j.kernel.impl.transaction.state.DataSourceManager.start(DataSourceManager.java:112)
		at org.neo4j.kernel.lifecycle.LifeSupport$LifecycleInstance.start(LifeSupport.java:452)
		at org.neo4j.kernel.lifecycle.LifeSupport.start(LifeSupport.java:111)
		at org.neo4j.kernel.impl.factory.GraphDatabaseFacadeFactory.newFacade(GraphDatabaseFacadeFactory.java:139)
		at org.neo4j.kernel.impl.enterprise.EnterpriseFacadeFactory.newFacade(EnterpriseFacadeFactory.java:40)
		at org.neo4j.graphdb.EnterpriseGraphDatabase.<init>(EnterpriseGraphDatabase.java:57)
		at org.neo4j.server.enterprise.EnterpriseNeoServer$2.newGraphDatabase(EnterpriseNeoServer.java:67)
		at org.neo4j.server.database.LifecycleManagingDatabase.start(LifecycleManagingDatabase.java:95)
		at org.neo4j.kernel.lifecycle.LifeSupport$LifecycleInstance.start(LifeSupport.java:452)
		at org.neo4j.kernel.lifecycle.LifeSupport.start(LifeSupport.java:111)
		at org.neo4j.server.AbstractNeoServer.start(AbstractNeoServer.java:194)
		at org.neo4j.server.Bootstrapper.start(Bootstrapper.java:97)
		at org.neo4j.server.CommunityBootstrapper.start(CommunityBootstrapper.java:48)
		at org.neo4j.server.enterprise.EnterpriseBootstrapper.main(EnterpriseBootstrapper.java:32)
	2016-03-12 13:04:58.327+0000 WARN  [o.n.k.NeoStoreDataSource] Exception occurred while starting the datasource. Attempting to close things down.
	2016-03-12 13:04:58.338+0000 INFO  [o.n.k.i.s.c.CountsTracker] About to rotate counts store at transaction 3 to [/tmp/graph.db.1457787795863/neostore.counts.db.b], from [/tmp/graph.db.1457787795863/neostore.counts.db.a].
	2016-03-12 13:04:58.342+0000 INFO  [o.n.k.i.s.c.CountsTracker] Successfully rotated counts store at transaction 3 to [/tmp/graph.db.1457787795863/neostore.counts.db.b], from [/tmp/graph.db.1457787795863/neostore.counts.db.a].
	2016-03-12 13:04:58.352+0000 INFO  [o.n.k.i.DiagnosticsManager] --- STOPPING diagnostics START ---
	2016-03-12 13:04:58.352+0000 INFO  [o.n.k.i.DiagnosticsManager] --- STOPPING diagnostics END ---
	2016-03-12 13:04:58.352+0000 INFO  [o.n.g.EnterpriseGraphDatabase] Shutdown started

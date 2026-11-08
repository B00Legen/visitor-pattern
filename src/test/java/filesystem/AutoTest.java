package filesystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutoTest {
	
	@Test
	void testDoubleDispatch() {
		TestVisitor visitor = new TestVisitor();
		FileNode file = new FileNode("example.txt", 10);
		DirectoryNode dir = new DirectoryNode("docs");
		
		file.accept(visitor);
		assertTrue(visitor.fileVisit);
		assertFalse(visitor.dirVisit);
		
		visitor.fileVisit = false;
		
		dir.accept(visitor);
		assertTrue(visitor.dirVisit);
		assertFalse(visitor.fileVisit);
	}
	
	@Test
	void testTotalSizeNested() {
		DirectoryNode root = new DirectoryNode("Root");
		DirectoryNode folder = new DirectoryNode("Folder");
		DirectoryNode nested = new DirectoryNode("Nested");
		TotalSizeVisitor visitor = new TotalSizeVisitor();
		
		root.addChild(new FileNode("a.txt", 100));
		root.addChild(folder);
		assertEquals(100, visitor.getSize(root));
		
		folder.addChild(new FileNode("b.txt", 200));
		folder.addChild(nested);
		assertEquals(300, visitor.getSize(root));
		assertEquals(200, visitor.getSize(folder));
		
		nested.addChild(new FileNode("c.txt", 300));
		assertEquals(600, visitor.getSize(root));
		assertEquals(500, visitor.getSize(folder));
		assertEquals(300, visitor.getSize(nested));
	}
	
	@Test
	void testSearchCombinedFilters() {
		DirectoryNode root = new DirectoryNode("Root");
		SearchVisitor visitor = new SearchVisitor();
		
		root.addChild(new FileNode("analysis.txt", 100));
		root.addChild(new FileNode("bridges.txt", 200));
		root.addChild(new FileNode("cats.mp4", 300));
		
		Predicate<String> nameReq = name -> name.equals("cats.mp4");
		assertEquals("Root/cats.mp4", visitor.find(folder, nameReq, 0));
		
		nameReq = name -> name.endsWith(".txt");
		assertEquals("Root/analysis.txt", visitor.find(root, nameReq, 0));
		assertEquals("Root/bridges.txt", visitor.find(root, nameReq, 200));
	}
	
	@Test
	void testSearchRelativePaths() {
		DirectoryNode root = new DirectoryNode("Root");
		DirectoryNode folder = new DirectoryNode("Folder");
		DirectoryNode nested = new DirectoryNode("Nested");
		SearchVisitor visitor = new SearchVisitor();
		
		root.addChild(new FileNode("analysis.txt", 100));
		root.addChild(folder);
		
		folder.addChild(new FileNode("bridges.txt", 200));
		folder.addChild(nested);
		
		nested.addChild(new FileNode("cats.mp4", 300));
		
		Predicate<String> nameReq = name -> name.equals("cats.mp4");
		assertEquals("Folder/Nested/cats.mp4", visitor.find(folder, nameReq, 0));
		
		nameReq = name -> name.endsWith(".txt");
		assertEquals("Root/analysis.txt", visitor.find(root, nameReq, 0));
		assertEquals("Root/Folder/bridges.txt", visitor.find(root, nameReq, 200));
	}
	
	@Test
	void testSearchDeterministicOrder() {
		DirectoryNode root = new DirectoryNode("Root");
		DirectoryNode folder = new DirectoryNode("Folder");
		DirectoryNode nested = new DirectoryNode("Nested");
		SearchVisitor visitor = new SearchVisitor();
		
		root.addChild(folder);
		folder.addChild(nested);
		nested.addChild(new FileNode("cats.mp4", 300));
		
		Predicate<String> nameReq = name -> name.equals("cats.mp4");
		assertEquals("Folder/Nested/cats.mp4", visitor.find(folder, nameReq, 0));
		assertEquals("Folder/Nested/cats.mp4", visitor.find(folder, nameReq, 0));
		assertEquals("Root/Folder/Nested/cats.mp4", visitor.find(root, nameReq, 0));
	}
	
	@Test
	void testStatisticsNested() {
		
	}
	
	@Test
	void testEmptyStructures() {
		DirectoryNode root = new DirectoryNode("Root");
		TotalSizeVisitor vSize = new TotalSizeVisitor();
		SearchVisitor vSearch = new SearchVisitor();
		
		assertEquals(0, vSize.getSize(root));
		
		Predicate<String> nameReq = name -> name.equals("cats.mp4");
		assertEquals("", vSearch.find(root, nameReq, 0));
	}
	
	@Test
	void testSearchNoMatches() {
		DirectoryNode root = new DirectoryNode("Root");
		SearchVisitor visitor = new SearchVisitor();
		
		root.addChild(new FileNode("cats.mp4", 100));
		
		Predicate<String> nameReq = name -> name.equals("cats.mp4");
		assertEquals("", visitor.find(root, nameReq, 200));
	}
	
	@Test
	void testVisitorRepeatedExecution() {
		DirectoryNode root = new DirectoryNode("Root");
		DirectoryNode folder = new DirectoryNode("Folder");
		DirectoryNode nested = new DirectoryNode("Nested");
		TotalSizeVisitor vSize = new TotalSizeVisitor();
		SearchVisitor vSearch = new SearchVisitor();
		
		root.addChild(new FileNode("analysis.txt", 100));
		root.addChild(folder);
		
		folder.addChild(new FileNode("bridges.txt", 200));
		folder.addChild(nested);
		
		nested.addChild(new FileNode("cats.mp4", 300));
		
		assertEquals(500, vSize(folder));
		assertEquals(500, vSize(folder));
		
		Predicate<String> nameReq = name -> name.equals("cats.mp4");
		assertEquals("Folder/Nested/cats.mp4", vSearch.find(folder, nameReq, 0));
		assertEquals("Folder/Nested/cats.mp4", vSearch.find(folder, nameReq, 0));
	}
	
	@Test
	void testInvalidMutations() {
		
	}
}

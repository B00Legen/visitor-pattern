package filesystem;

import filesystem.models.*;
import filesystem.visitors.*;
import java.util.function.Predicate;

public class Main {
	public static void main (String[] args) {
		DirectoryNode root = new DirectoryNode("root");
		DirectoryNode source = new DirectoryNode("src");
		DirectoryNode resources = new DirectoryNode("res");
		
		TotalSizeVisitor visitorSize = new TotalSizeVisitor();
		SearchVisitor visitorSearch = new SearchVisitor();
		StatisticsVisitor visitorStats = new StatisticsVisitor();
		
		root.addChild(source);
		root.addChild(resources);
		
		source.addChild(new FileNode("Main.java", 1200));
		source.addChild(new FileNode("Tests.java", 400));
		
		resources.addChild(new FileNode("logo.png", 2600));
		resources.addChild(new FileNode("readme.md", 100));
		
		System.out.println("Total size of a project: " + visitorSize.getSize(root) + "B");
		System.out.println("Total size of resources folder: " + visitorSize.getSize(resources) + "B");
		
		System.out.print("Location of all .java files in project: ");
		System.out.println(visitorSearch.find(root, (str -> str.endsWith(".java")), 0));
		System.out.print("Location of .java files with >1000B in source: ");
		System.out.println(visitorSearch.find(source, (str -> str.endsWith(".java")), 1000));
		
		System.out.println("Statistics of a project: " + visitorStats.getStats(root));
		System.out.println("Statistics of resources folder: " + visitorStats.getStats(resources));
	}
}

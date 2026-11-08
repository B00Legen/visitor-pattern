package filesystem.visitors;

import filesystem.models.*;
import java.util.List;
import java.util.ArrayList;

public class StatisticsVisitor implements Visitor {
	private int fileCount, dirCount, maxDepth, curDepth;
	
	@Override
	public void visit(FileNode e) {
		fileCount++;
		maxDepth = Math.max(curDepth, maxDepth);
	}
	@Override
	public void visit(DirectoryNode e) {
		dirCount++;
		maxDepth = Math.max(curDepth, maxDepth);
		
		curDepth++;
		
		List<Element> child = e.getChildren();
		for (Element cur : child) {
			cur.accept(this);
		}
		
		curDepth--;
	}
	
	public Stats getStats(Element e) {
		fileCount = 0;
		dirCount = 0;
		curDepth = 0;
		maxDepth = 0;
		
		e.accept(this);
		
		return new Stats(fileCount, dirCount, maxDepth);
	}
}

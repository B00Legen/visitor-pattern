package filesystem.visitors;

import filesystem.models.*;
import java.util.List;
import java.util.ArrayList;

public class StatisticsVisitor implements Visitor {
	private Stats stat;
	private int currentDepth;
	
	@Override
	public void visit(FileNode file) {
		stat.raiseFileCount();
		stat.setMaxDepth(currentDepth);
	}
	@Override
	public void visit(DirectoryNode directory) {
		stat.raiseDirectoryCount();
		stat.setMaxDepth(currentDepth);
		
		currentDepth++;
		
		List<Element> child = directory.getChildren();
		for (Element current : child) {
			current.accept(this);
		}
		
		currentDepth--;
	}
	
	public Stats getStats(Element element) {
		stat = new Stats();
		currentDepth = 0;
		
		element.accept(this);
		
		return stat;
	}
}

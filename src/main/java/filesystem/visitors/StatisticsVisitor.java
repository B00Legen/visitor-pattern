package filesystem.visitors;

import filesystem.models.*;
import java.util.List;
import java.util.ArrayList;

public class StatisticsVisitor implements Visitor {
	private Stats stat;
	private int curDepth;
	
	@Override
	public void visit(FileNode e) {
		stat.raiseFileCount();
		stat.setMaxDepth(curDepth);
	}
	@Override
	public void visit(DirectoryNode e) {
		stat.raiseDirCount();
		stat.setMaxDepth(curDepth);
		
		curDepth++;
		
		List<Element> child = e.getChildren();
		for (Element cur : child) {
			cur.accept(this);
		}
		
		curDepth--;
	}
	
	public Stats getStats(Element e) {
		stat = new Stats();
		curDepth = 0;
		
		e.accept(this);
		
		return stat;
	}
}

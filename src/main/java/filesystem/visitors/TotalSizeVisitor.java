package filesystem.visitors;

import filesystem.models.*;
import java.util.List;
import java.util.ArrayList;

public class TotalSizeVisitor implements Visitor {
	private int size = 0;
	
	@Override
	public void visit(FileNode e) {
		size += e.getSize();
	}
	@Override
	public void visit(DirectoryNode e) {
		List<Element> child = e.getChildren();
		for (Element cur : child) {
			cur.accept(this);
		}
	}
	
	public int getSize(Element e) {
		size = 0;
		e.accept(this);
		return size;
	}
}

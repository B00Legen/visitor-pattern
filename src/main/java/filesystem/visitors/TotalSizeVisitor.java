package filesystem.visitors;

import filesystem.models.*;
import java.util.List;
import java.util.ArrayList;

public class TotalSizeVisitor implements Visitor {
	private int size = 0;
	
	@Override
	public void visit(FileNode file) {
		size += file.getSize();
	}
	@Override
	public void visit(DirectoryNode directory) {
		List<Element> child = directory.getChildren();
		for (Element current : child) {
			current.accept(this);
		}
	}
	
	public int getSize(Element element) {
		size = 0;
		element.accept(this);
		return size;
	}
}

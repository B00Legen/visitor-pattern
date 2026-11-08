package filesystem.visitors;

import filesystem.models.*;

public class TestVisitor implements Visitor {
	public boolean fileVisit = false, dirVisit = false;
	
	@Override
	public void visit(FileNode e) {
		fileVisit = true;
	}
	@Override
	public void visit(DirectoryNode e) {
		dirVisit = true;
	}
}

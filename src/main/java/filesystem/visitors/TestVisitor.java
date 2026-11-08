package filesystem.visitors;

import filesystem.models.*;

public class TestVisitor implements Visitor {
	public boolean fileVisit = false, directoryVisit = false;
	
	@Override
	public void visit(FileNode file) {
		fileVisit = true;
	}
	@Override
	public void visit(DirectoryNode directory) {
		directoryVisit = true;
	}
}

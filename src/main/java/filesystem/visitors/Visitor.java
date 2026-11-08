package filesystem.visitors;

import filesystem.models.*;

public interface Visitor {
	void visit(FileNode e);
	void visit(DirectoryNode e);
}

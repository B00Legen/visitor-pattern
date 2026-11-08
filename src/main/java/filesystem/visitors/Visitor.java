package filesystem.visitors;

import filesystem.models.*;

public interface Visitor {
	void visit(FileNode file);
	void visit(DirectoryNode directory);
}

package filesystem.models;

import filesystem.visitors.Visitor;

public abstract class BaseElement implements Element{
	protected String name;
	protected DirectoryNode parent;
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public DirectoryNode getParent() {
		return parent;
	}
	@Override
	public void setParent(DirectoryNode parent) {
		this.parent = parent;
	}
}

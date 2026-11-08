package filesystem.models;

import filesystem.visitors.Visitor;

public class FileNode extends BaseElement {
	private int size;
	
	public FileNode(String name, int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Negative size");
		}
		
		this.name = name;
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}

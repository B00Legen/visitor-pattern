package filesystem.models;

import filesystem.visitors.Visitor;
import java.util.List;
import java.util.ArrayList;

public class DirectoryNode implements Element{
	private String name;
	private List<Element> children = new ArrayList<>();
	
	public DirectoryNode(String name) {
		this.name = name;
	}
	
	public void addChild(Element el) {
		children.add(el);
	}
	public List<Element> getChildren() {
		return children;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	@Override
	public String getName() {
		return name;
	}
}

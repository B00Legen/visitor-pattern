package filesystem.models;

import filesystem.visitors.Visitor;
import java.util.List;
import java.util.ArrayList;

public class DirectoryNode extends BaseElement{
	private List<Element> children = new ArrayList<>();
	
	public DirectoryNode(String name) {
		this.name = name;
	}
	
	public void addChild(Element element) {
		for (Element current : children) {
			if (current.getName().equals(element.getName())) {
				throw new IllegalArgumentException("Duplicate sibling name");
			}
		}
		
		if (element.getParent() != null) {
			throw new IllegalArgumentException("Element already has a parent");
		}
		
		DirectoryNode current = this;
		while (current != null) {
			if (current == element) {
				throw new IllegalArgumentException("Cycle detected");
			}
			
			current = current.getParent();
		}
		
		children.add(element);
		element.setParent(this);
	}
	public void removeChild(Element element) {
		if (children.remove(element)) {
			element.setParent(null);
		}
	}
	public List<Element> getChildren() {
		return children;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

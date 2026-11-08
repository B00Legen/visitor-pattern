package filesystem.models;

import filesystem.visitors.Visitor;
import java.util.List;
import java.util.ArrayList;

public class DirectoryNode extends BaseElement{
	private List<Element> children = new ArrayList<>();
	
	public DirectoryNode(String name) {
		this.name = name;
	}
	
	public void addChild(Element el) {
		for (Element cur : children) {
			if (cur.getName().equals(el.getName())) {
				throw new IllegalArgumentException("Duplicate sibling name");
			}
		}
		
		if (el.getParent() != null) {
			throw new IllegalArgumentException("Element already has a parent");
		}
		
		DirectoryNode cur = this;
		while (cur != null) {
			if (cur == el) {
				throw new IllegalArgumentException("Cycle detected");
			}
			
			cur = cur.getParent();
		}
		
		children.add(el);
		el.setParent(this);
	}
	public void removeChild(Element el) {
		if (children.remove(el)) {
			el.setParent(null);
		}
	}
	public List<Element> getChildren() {
		return children;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}

package filesystem.visitors;

import filesystem.models.*;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;

public class SearchVisitor implements Visitor {
	private Predicate<String> nameReq;
	private Predicate<Integer> sizeReq;
	private String curDir;
	private List<String> res = new ArrayList<>();
	
	@Override
	public void visit(FileNode e) {
		String name = e.getName();
		int size = e.getSize();
		
		if (nameReq.test(name) && sizeReq.test(size)) {
			res.add(curDir);
		}
	}
	@Override
	public void visit(DirectoryNode e) {
		List<Element> child = e.getChildren();
		for (Element cur : child) {
			curDir += "/" + cur.getName();
			cur.accept(this);
			curDir = curDir.replace("/" + cur.getName(), "");
		}
	}
	
	public List<String> find(Element e, Predicate<String> name, int minSize) {
		nameReq = name;
		sizeReq = (n -> n >= minSize);
		curDir = "";
		res.clear();
		
		e.accept(this);
		
		return res;
	}
}

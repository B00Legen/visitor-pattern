package filesystem.visitors;

import filesystem.models.*;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;

public class SearchVisitor implements Visitor {
	private Predicate<String> nameRequirements;
	private Predicate<Integer> sizeRequirements;
	private String currentDirectory;
	private List<String> result = new ArrayList<>();
	
	@Override
	public void visit(FileNode element) {
		String name = element.getName();
		int size = element.getSize();
		
		if (nameRequirements.test(name) && sizeRequirements.test(size)) {
			result.add(currentDirectory);
		}
	}
	@Override
	public void visit(DirectoryNode directory) {
		List<Element> child = directory.getChildren();
		for (Element current : child) {
			currentDirectory += "/" + current.getName();
			current.accept(this);
			currentDirectory = currentDirectory.replace("/" + current.getName(), "");
		}
	}
	
	public List<String> find(Element element, Predicate<String> nameRequirements, int minimumSize) {
		this.nameRequirements = nameRequirements;
		sizeRequirements = (n -> n >= minimumSize);
		currentDirectory = "";
		result.clear();
		
		element.accept(this);
		
		result.sort();
		return result;
	}
}

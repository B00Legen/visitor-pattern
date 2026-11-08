import java.util.Arrays;

public class DirectoryNode implements Element{
	private String name;
	private Element[] children = new Element[1];
	private int buffer = 1, size = 0;
	
	public DirectoryNode(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void addChild(Element el) {
		if (size == buffer) {
			buffer *= 2;
			children = Arrays.copyOf(content, buffer);
		}
		children[size] = el;
		size++;
	}
	public void removeChild() {
		if (size > 0) {
			size--;
			children[size] = null;
		}
	}
	public Element[] getChildren() {
		return children;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this)
	}
}

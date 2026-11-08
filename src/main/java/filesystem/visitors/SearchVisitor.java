public class SearchVisitor implements Visitor {
	private Predicate<String> nameReq;
	private Predicate<long> sizeReq;
	private String res, curDir;
	
	@Override
	public void visit(FileNode e) {
		String name = e.getName();
		int size = e.getSize();
		
		if (nameReq.test(name) && sizeReq.test(size)) {
			res = curDir;
		}
	}
	@Override
	public void visit(DirectoryNode e) {
		Element[] child = e.getChildren();
		for (Element cur : child) {
			curDir += "/" + e.getName();
			cur.accept(this);
			curDir.replace("/" + e.getName(), "");
		}
	}
	
	public String find(Element e, Predicate<String> name, long minSize) {
		nameReq = name;
		sizeReq = size -> size >= minSize;
		res = "";
		curDir = e.getName();
		
		e.accept(this);
		
		return res;
	}
}

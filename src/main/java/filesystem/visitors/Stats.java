package filesystem.visitors;

public class Stats {
	private int fileCount;
	private int directoryCount;
	private int maxDepth;

	public Stats(int fileCount, int directoryCount, int maxDepth) {
		this.fileCount = fileCount;
		this.directoryCount = directoryCount;
		this.maxDepth = maxDepth;
	}

	public int getFileCount() {
		return fileCount;
	}
	public void raiseFileCount() {
		fileCount++;
	}

	public int getDirectoryCount() {
		return directoryCount;
	}
	public void raiseDirectoryCount() {
		directoryCount++;
	}

	public int getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(int depth) {
		maxDepth = Math.max(depth, maxDepth);
	}
}

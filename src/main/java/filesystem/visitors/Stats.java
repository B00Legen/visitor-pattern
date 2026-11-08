package filesystem.visitors;

public class Stats {
	private final int fileCount;
	private final int dirCount;
	private final int maxDepth;

	public Stats(int fileCount, int dirCount, int maxDepth) {
		this.fileCount = fileCount;
		this.dirCount = dirCount;
		this.maxDepth = maxDepth;
	}

	public int getFileCount() {
		return fileCount;
	}
	public void raiseFileCount() {
		fileCount++;
	}

	public int getDirCount() {
		return dirCount;
	}
	public void raiseDirCount() {
		dirCount++;
	}

	public int getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(int depth) {
		maxDepth = Math.max(depth, maxDepth);
	}
}

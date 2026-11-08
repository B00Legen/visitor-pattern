package filesystem.models;

import filesystem.visitors.Visitor;

public interface Element {
	void accept(Visitor v);
	String getName();
}

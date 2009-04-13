package ui.nodes;

import java.util.List;
import java.util.ArrayList;

public class Group implements ITreeNode {
	private String name = null;
	private List<ITreeNode> children = null;
	private ITreeNode parent = null;
	
	public Group(String name) {
		this.name = name;
		this.children = new ArrayList<ITreeNode>();
		this.parent = null;
	}

	public Group(String name, List<ITreeNode> children) {
		this.name = name;
		this.children = children;
		this.parent = null;
		for (int i = 0; i < this.children.size(); i++) {
			if (this.children.get(i) instanceof Group) {
				((Group)this.children.get(i)).setParent(this);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public List<ITreeNode> getChildren() {
		return children;
	}
	
	public ITreeNode getParent() {
		return parent;
	}
	
	public void setParent(ITreeNode parent) {
		this.parent = parent;
	}
}

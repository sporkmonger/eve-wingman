package ui.nodes;

import java.util.List;

/**
 * @author Bob Aman
 *
 */
public interface ITreeNode {
	public String getName();
	public List<ITreeNode> getChildren();
	public ITreeNode getParent();
}

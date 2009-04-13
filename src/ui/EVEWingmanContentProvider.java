package ui;

import java.util.List;
import java.util.ArrayList;

import ui.nodes.Group;
import ui.nodes.ITreeNode;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * @author Bob Aman
 *
 */
public class EVEWingmanContentProvider implements ITreeContentProvider {
	private static ITreeNode[] EMPTY_ARRAY = new ITreeNode[0];
	protected TreeViewer viewer;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		System.out.println(parentElement.toString());
		if (parentElement.equals("root")) {
			List<ITreeNode> logGroups = new ArrayList<ITreeNode>();
			logGroups.add(new Group("Archive"));
			logGroups.add(new Group("This Month"));

			List<ITreeNode> baseGroups = new ArrayList<ITreeNode>();
			baseGroups.add(new Group("Characters"));
			baseGroups.add(new Group("Fits"));
			baseGroups.add(new Group("Item Database"));
			baseGroups.add(new Group("Logs", logGroups));
			return baseGroups.toArray();
		} else if (parentElement instanceof ITreeNode) {
			System.out.println("getting children");
			return ((ITreeNode)parentElement).getChildren().toArray();
		} else {
			return EMPTY_ARRAY;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof ITreeNode) {
			return ((ITreeNode)element).getParent();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object parentElement) {
		if (parentElement instanceof ITreeNode) {
			return ((ITreeNode)parentElement).getChildren().size() > 0;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
	}

	/**
	 * Notifies this content provider that the given viewer's input
	 * has been switched to a different element.
	 * 
	 * A typical use for this method is registering the content provider as a listener
	 * to changes on the new input (using model-specific means), and deregistering the viewer 
	 * from the old input. In response to these change notifications, the content provider
	 * propagates the changes to the viewer.
	 * 
	 * @param viewer the viewer
	 * @param oldInput the old input element, or <code>null</code> if the viewer
	 *   did not previously have an input
	 * @param newInput the new input element, or <code>null</code> if the viewer
	 *   does not have an input
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer)viewer;
	}
}

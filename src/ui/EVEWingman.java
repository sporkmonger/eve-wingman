package ui;

import org.eclipse.jface.window.ApplicationWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;

import net.miginfocom.swt.MigLayout;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import ui.nodes.*;

/**
 * Main class for the EVE Wingman application.
 * 
 * @author Bob Aman
 */
public class EVEWingman extends ApplicationWindow {
	private Composite mainPanel = null;
	
	public EVEWingman() {
		super(null);
	}

	public void run() {
	    // Don't return from open() until window closes
	    setBlockOnOpen(true);
	    // Open the main window
	    open();
	    // Dispose the display
	    Display.getCurrent().dispose();
	}
	
	/**
	 * Creates the main window's contents
	 * 
	 * @param parent
	 *            the main window
	 * @return Control
	 */
	protected Control createContents(Composite parent) {
		parent.setLayout(new MigLayout("fill"));

		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		sashForm.setLayoutData("grow");

	    // Create the tree viewer to display the file tree
	    final TreeViewer tv = new TreeViewer(sashForm);
	    tv.setAutoExpandLevel(2);
	    tv.setContentProvider(new EVEWingmanContentProvider());
	    tv.setLabelProvider(new EVEWingmanLabelProvider());
	    tv.setInput("root"); // pass a non-null that will be ignored

	    mainPanel = new Composite(sashForm, SWT.BORDER);

	    int[] weights = {25, 75};
	    sashForm.setWeights(weights);
	    
	    tv.addSelectionChangedListener(new ISelectionChangedListener()
	    {
	    	public void selectionChanged(SelectionChangedEvent event)
	    	{
	    		if (event.getSelection() instanceof TreeSelection) {
	    			Object selectedObject = ((TreeSelection)event.getSelection()).getFirstElement();
	    			if (selectedObject instanceof ITreeNode) {
	    				System.out.println(((ITreeNode)selectedObject).getName());
	    			}
	    		}
	    	}
	    });

	    return parent;
	}
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EVEWingman().run();
	}

	/**
	 * Configures the shell
	 * 
	 * @param shell
	 *            the shell
	 */
	protected void configureShell(Shell shell) {
		// Set the title bar text and the size
		shell.setText("EVE Wingman");
		Image applicationIcon = null;
		String osName = System.getProperty("os.name", "");
		if (osName.equals("Windows XP") || osName.equals("Windows Vista") || osName.equals("Windows 7")) {
			applicationIcon = new Image(Display.getCurrent(), "icons/wingman.ico");
		} else if (osName.equals("Mac OS X")) {
			applicationIcon = new Image(Display.getCurrent(), "icons/wingman.icns");		
		} else {
			applicationIcon = new Image(Display.getCurrent(), "icons/wingman.png");		
		}
		shell.setImage(applicationIcon);
		shell.setSize(800, 600);
		shell.setMaximized(false);
	}
}

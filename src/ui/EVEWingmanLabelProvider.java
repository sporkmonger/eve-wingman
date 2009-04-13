package ui;

import java.net.URL;
import java.net.MalformedURLException;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import ui.nodes.*;

/**
 * @author Bob Aman
 */
public class EVEWingmanLabelProvider extends LabelProvider {
	private static ImageRegistry imageRegistry = null;

	public static URL newURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Malformed URL: " + url, e);
		}
	}
	
	public static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
			imageRegistry.put("group:characters", ImageDescriptor.createFromURL(newURL("file:icons/characters.png")));
			imageRegistry.put("item:character", ImageDescriptor.createFromURL(newURL("file:icons/character.png")));
			imageRegistry.put("group:fits", ImageDescriptor.createFromURL(newURL("file:icons/fits.png")));
			imageRegistry.put("item:fit", ImageDescriptor.createFromURL(newURL("file:icons/fit.png")));
			imageRegistry.put("group:item_database", ImageDescriptor.createFromURL(newURL("file:icons/item_database.png")));
			imageRegistry.put("group:logs", ImageDescriptor.createFromURL(newURL("file:icons/logs.png")));
			imageRegistry.put("group:archive", ImageDescriptor.createFromURL(newURL("file:icons/archive.png")));
			imageRegistry.put("group:this_month", ImageDescriptor.createFromURL(newURL("file:icons/this_month.png")));
			imageRegistry.put("item:log", ImageDescriptor.createFromURL(newURL("file:icons/log.png")));
		}
		return imageRegistry;
	}
	
	/*
	 * @see ILabelProvider#getImage(Object)
	 */
	public Image getImage(Object element) {
		ImageDescriptor descriptor = null;
		if (element instanceof Group && ((Group)element).getName().equals("Characters")) {
			descriptor = getImageRegistry().getDescriptor("group:characters");
		} else if (element instanceof Group && ((Group)element).getName().equals("Fits")) {
			descriptor = getImageRegistry().getDescriptor("group:fits");
		} else if (element instanceof Group && ((Group)element).getName().equals("Item Database")) {
			descriptor = getImageRegistry().getDescriptor("group:item_database");
		} else if (element instanceof Group && ((Group)element).getName().equals("Logs")) {
			descriptor = getImageRegistry().getDescriptor("group:logs");
		} else if (element instanceof Group && ((Group)element).getName().equals("Archive")) {
			descriptor = getImageRegistry().getDescriptor("group:archive");
		} else if (element instanceof Group && ((Group)element).getName().equals("This Month")) {
			descriptor = getImageRegistry().getDescriptor("group:this_month");
		} else {
			descriptor = ImageDescriptor.getMissingImageDescriptor();
		}
		return descriptor.createImage();
	}
	
	/*
	 * @see ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		if (element instanceof ITreeNode) {
			return ((ITreeNode)element).getName();
		} else {
			return "<unknown element>";
		}
	}
}

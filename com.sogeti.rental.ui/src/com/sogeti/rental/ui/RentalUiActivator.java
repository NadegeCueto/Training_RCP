package com.sogeti.rental.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.sogeti.rental.ui.palette.Palette;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUiActivator extends AbstractUIPlugin implements RentalConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sogeti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUiActivator plugin;
	
	// define the palette manager
	private static Map<String, Palette> paletteManager = new HashMap<>();
	
	/**
	 * The constructor
	 */
	public RentalUiActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		readViewExtensions();
		readPalette();
	}

	private void readPalette() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("com.sogeti.rental.ui.palette");
		for (int i = 0; i < elements.length; i++)
		{
			
			if ( elements[i].getName().equals("palette"))
			{
				Palette p = new Palette();
				p.setId(elements[i].getAttribute("id"));
				p.setName(elements[i].getAttribute("name"));
				try {
					p.setProvider((IColorProvider)elements[i].createExecutableExtension("paletteClass"));
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paletteManager.put(p.getId(), p) ;
				System.out.println("Ajout de palette: " + p.getId());
			}
		}
		
	}

	private void readViewExtensions() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.ui.views");
		for (int i = 0; i < elements.length; i++)
		{
			if ( elements[i].getName().equals("view"))
			System.out.println("Plugin : " + elements[i].getNamespaceIdentifier() + "\tVue : " + elements[i].getAttribute("name"));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUiActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		
		reg.put(IMG_CUSTOMER,  ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL,  ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_OBJECT,  ImageDescriptor.createFromURL(b.getEntry(IMG_OBJECT)));
		reg.put(IMG_AGENCY,  ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
	}

	public Map<String, Palette> getPaletteManager() {
		return this.paletteManager;
	}
}

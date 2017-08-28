package com.sogeti.rental.ui.cmd;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.sogeti.rental.ui.cmd.messages"; //$NON-NLS-1$
	public static String Activator_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

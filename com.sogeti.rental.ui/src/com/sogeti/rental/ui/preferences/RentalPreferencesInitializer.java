package com.sogeti.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.sogeti.rental.ui.RentalUiActivator;

public class RentalPreferencesInitializer extends AbstractPreferenceInitializer {
	
	public static final int DEFAULT_COLOR_CUSTOMER = SWT.COLOR_BLUE;
	public static final int DEFAULT_COLOR_RENTAL = SWT.COLOR_MAGENTA;
	public static final int DEFAULT_COLOR_OBJECTS = SWT.COLOR_GREEN;
	
	public RentalPreferencesInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store =  RentalUiActivator.getDefault().getPreferenceStore();
		store.setDefault(RentalPreferencesPage.PREF_CUSTOMER_COLOR_FIELD, StringConverter.asString(Display.getCurrent().getSystemColor(DEFAULT_COLOR_CUSTOMER).getRGB()));
		store.setDefault(RentalPreferencesPage.PREF_RENTAL_COLOR_FIELD, "0,20,30");
		store.setDefault(RentalPreferencesPage.PREF_OBJECTS_COLOR_FIELD, StringConverter.asString(Display.getCurrent().getSystemColor(DEFAULT_COLOR_OBJECTS).getRGB()));
		store.setDefault(PalettePreferencesPage.PREF_PALETTE, "com.sogeti.rental.ui.palettedefault");
	}

}

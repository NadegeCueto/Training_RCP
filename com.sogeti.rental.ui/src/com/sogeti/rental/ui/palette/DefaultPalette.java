package com.sogeti.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalPreferencesPage;
import com.sogeti.rental.ui.RentalUiActivator;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
			
		if (element instanceof Customer)
			return getAColor(RentalUiActivator.getDefault().getPreferenceStore().getString(RentalPreferencesPage.PREF_CUSTOMER_COLOR_FIELD));
		if (element instanceof Rental)
			return getAColor(RentalUiActivator.getDefault().getPreferenceStore().getString(RentalPreferencesPage.PREF_RENTAL_COLOR_FIELD));
		if (element instanceof RentalObject)
			return getAColor(RentalUiActivator.getDefault().getPreferenceStore().getString(RentalPreferencesPage.PREF_OBJECTS_COLOR_FIELD));
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Color getAColor(String rgbKey)
	{
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbKey);
		if (col == null)
		{
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
		
	}


}

package com.sogeti.rental.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.RentalUiActivator;
import com.sogeti.rental.ui.palette.Palette;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;

public class PalettePreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String PREF_PALETTE = "pref.palette";

	/**
	 * @wbp.parser.constructor
	 */
	public PalettePreferencesPage() {
		super(GRID);
		setPreferenceStore(RentalUiActivator.getDefault().getPreferenceStore());
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		Map <String, Palette> palettes = RentalUiActivator.getDefault().getPaletteManager();
		
		String[][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (Palette p : palettes.values())
		{
			comboValues[i][0] = p.getName();
			comboValues[i][1] = p.getId();
			i++;
		}
		
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette:", comboValues, getFieldEditorParent()));
	}

}

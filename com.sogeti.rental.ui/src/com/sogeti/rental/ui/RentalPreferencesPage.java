package com.sogeti.rental.ui;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RentalPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	
	public static final String PREF_OBJECTS_COLOR_FIELD = "PREF_OBJECTS_COLOR_FIELD";
	public static final String PREF_RENTAL_COLOR_FIELD = "PREF_RENTAL_COLOR_FIELD";
	public static final String PREF_CUSTOMER_COLOR_FIELD = "PREF_CUSTOMER_COLOR_FIELD";
	
	private ColorFieldEditor color_customers;
	private ColorFieldEditor color_rental;
	private ColorFieldEditor color_objects;

	public RentalPreferencesPage() {
		super(GRID);
		setPreferenceStore(RentalUiActivator.getDefault().getPreferenceStore());
	}


	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		color_customers = new ColorFieldEditor(PREF_CUSTOMER_COLOR_FIELD, "Customer", getFieldEditorParent());
		color_rental = new ColorFieldEditor(PREF_RENTAL_COLOR_FIELD, "Rental", getFieldEditorParent());
		color_objects = new ColorFieldEditor(PREF_OBJECTS_COLOR_FIELD, "Objects", getFieldEditorParent());
		
		addField(color_customers);
		addField(color_rental);
		addField(color_objects);
	}

}

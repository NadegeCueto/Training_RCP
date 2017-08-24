package com.sogeti.rental.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalView extends ViewPart {

	private Label rentedObjectLabel;
	Label rentedToLabel;
	Label rentedToNameLabel;
	Group locationDatesGroup;
	Label fromLabel;
	Label fromDateLabel;
	Label toLabel;
	Label toDateLabel;

	public RentalView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1,false));
		
		Group infoGroup = new Group (parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout (2, false));
		
		rentedObjectLabel = new Label (infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		rentedToLabel = new Label(infoGroup, SWT.NONE);
		rentedToLabel.setText("Lou\u00E9 \u00E0 :");
		
		rentedToNameLabel = new Label(infoGroup, SWT.NONE);
		rentedToNameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		locationDatesGroup = new Group(parent, SWT.NONE);
		locationDatesGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		locationDatesGroup.setText("Dates de location");
		locationDatesGroup.setLayout(new GridLayout(2, false));
		
		fromLabel = new Label(locationDatesGroup, SWT.NONE);
		fromLabel.setText("du :");
		
		fromDateLabel = new Label(locationDatesGroup, SWT.NONE);
		fromDateLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		toLabel = new Label(locationDatesGroup, SWT.NONE);
		toLabel.setText("au :");
		
		toDateLabel = new Label(locationDatesGroup, SWT.NONE);
		toDateLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	private void setRental (Rental rental)
	{
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		rentedToNameLabel.setText(rental.getCustomer().getFirstName() + " " + rental.getCustomer().getLastName());
		fromDateLabel.setText(rental.getStartDate().toString());
		toDateLabel.setText(rental.getEndDate().toString());
	}
}

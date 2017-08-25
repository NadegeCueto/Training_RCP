package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection)
			return ((Collection)inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if (parentElement instanceof RentalAgency)
			return ((RentalAgency)parentElement).getCustomers().toArray();
		return null;
	}

	@Override
	public Object getParent(Object element) {
			return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (getChildren(element) != null)
			return (getChildren(element).length > 0);
		return false;
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency)element).getName();
		else if (element instanceof Customer)
			return ((Customer)element).getFirstName() + " " + ((Customer)element).getLastName();
		return super.getText(element);
	}

}
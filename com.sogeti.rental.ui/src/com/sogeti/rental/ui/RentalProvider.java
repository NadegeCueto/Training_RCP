package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalConstants {
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection)
			return ((Collection)inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if (parentElement instanceof RentalAgency)
			return (new Node[]{new Node("Customers", (RentalAgency)parentElement), new Node("Locations", (RentalAgency)parentElement), new Node("Objets � louer", (RentalAgency)parentElement)});
		if (parentElement instanceof Node)
			return ((Node)parentElement).getChildren();
		return null;
	}

	@Override
	public Object getParent(Object element) {
			return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof RentalAgency || element instanceof Node);
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency)element).getName();
		if (element instanceof Customer)
			return ((Customer)element).getFirstName() + " " + ((Customer)element).getLastName();
		if (element instanceof Node)
			return ((Node)element).toString();
		if (element instanceof RentalObject)
			return ((RentalObject)element).getName();
		return super.getText(element);
	}
	
	private class Node
	{
		String label;
		RentalAgency agency;
		
		public String toString() {
			return label;
		}

		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}

		Object[] getChildren()
		{
			if (label.equals("Customers"))
				return agency.getCustomers().toArray();
			if (label.equals("Locations"))
				return agency.getRentals().toArray();
			if (label.equals("Objets � louer"))
				return agency.getObjectsToRent().toArray();
			return null;
		}
	}

	@Override
	public Color getForeground(Object element) {
			
		if (element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_MAGENTA);
		if (element instanceof Rental)
			return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
		if (element instanceof RentalObject)
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImage(Object element) {
		ImageRegistry reg = RentalUiActivator.getDefault().getImageRegistry();
		
		if (element instanceof Customer)
			return reg.get(IMG_CUSTOMER);
		if (element instanceof Rental)
			return reg.get(IMG_RENTAL);
		if (element instanceof RentalObject)
			return reg.get(IMG_OBJECT);
		if (element instanceof RentalAgency)
			return reg.get(IMG_AGENCY);
		return null;
	}
}

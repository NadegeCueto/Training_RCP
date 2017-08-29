package com.sogeti.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
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
import com.sogeti.rental.ui.RentalConstants;
import com.sogeti.rental.ui.RentalUiActivator;
import com.sogeti.rental.ui.palette.Palette;
import com.sogeti.rental.ui.preferences.PalettePreferencesPage;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalConstants {

	public RentalProvider() {
		super();
		initPalette();
	}

	private Palette palette;

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection)
			return ((Collection) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency)
			return (new Node[] { new Node("Customers", (RentalAgency) parentElement),
					new Node("Locations", (RentalAgency) parentElement),
					new Node("Objets à louer", (RentalAgency) parentElement) });
		if (parentElement instanceof Node)
			return ((Node) parentElement).getChildren();
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
			return ((RentalAgency) element).getName();
		if (element instanceof Customer)
			return ((Customer) element).getFirstName() + " " + ((Customer) element).getLastName();
		if (element instanceof Node)
			return ((Node) element).toString();
		if (element instanceof RentalObject)
			return ((RentalObject) element).getName();
		return super.getText(element);
	}

	private class Node {
		String label;
		RentalAgency agency;

		public String toString() {
			return label;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}

		Object[] getChildren() {
			if (label.equals("Customers"))
				return agency.getCustomers().toArray();
			if (label.equals("Locations"))
				return agency.getRentals().toArray();
			if (label.equals("Objets à louer"))
				return agency.getObjectsToRent().toArray();
			return null;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
	}

	@Override
	public Color getForeground(Object element) {

		return palette.getProvider().getForeground(element);
	}

	@Override
	public Color getBackground(Object element) {
		return palette.getProvider().getBackground(element);
	}

	public void initPalette() {
		palette = RentalUiActivator.getDefault().getPaletteManager().get(
				RentalUiActivator.getDefault().getPreferenceStore().getString(PalettePreferencesPage.PREF_PALETTE));
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

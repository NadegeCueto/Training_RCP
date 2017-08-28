package com.sogeti.rental.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class CustomerView extends ViewPart implements ISelectionListener{

	protected Label customerLabel;
	
	public CustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		customerLabel = new Label(parent, SWT.NONE);
		customerLabel.setText("");

	}
	
	private void setCustomer (Customer customer)
	{
		if (customer != null)
			customerLabel.setText(customer.getDisplayName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty())
			return;
		if (selection instanceof IStructuredSelection)
		{
			Object selected = ((IStructuredSelection)selection).getFirstElement();
			
			Customer c = Platform.getAdapterManager().getAdapter(selected, Customer.class);
			setCustomer(c);
		}
		
	}

	
	public void init (IViewSite site) throws PartInitException
	{
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	public void dispose()
	{
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
}

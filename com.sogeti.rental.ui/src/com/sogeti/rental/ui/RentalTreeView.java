package com.sogeti.rental.ui;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalTreeView extends ViewPart {

	public RentalTreeView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		ArrayList<RentalAgency> agencies = new ArrayList();
		agencies.add(RentalCoreActivator.getAgency());
		TreeViewer treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(new RentalProvider());
		treeViewer.setLabelProvider(new RentalProvider());
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

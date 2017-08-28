package com.sogeti.rental.ui;

import java.util.ArrayList;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalTreeView extends ViewPart implements IPropertyChangeListener{

	protected TreeViewer treeViewer;
	
	public RentalTreeView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		ArrayList<RentalAgency> agencies = new ArrayList();
		agencies.add(RentalCoreActivator.getAgency());
		treeViewer = new TreeViewer(parent);
		treeViewer.setContentProvider(new RentalProvider());
		treeViewer.setLabelProvider(new RentalProvider());
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		getSite().setSelectionProvider(treeViewer);
		
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, treeViewer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void init (IViewSite site) throws PartInitException
	{
		super.init(site);
		RentalUiActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	public void dispose()
	{
		RentalUiActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		treeViewer.refresh();
	}

	
}

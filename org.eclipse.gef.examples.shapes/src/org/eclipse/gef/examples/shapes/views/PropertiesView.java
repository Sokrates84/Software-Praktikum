package org.eclipse.gef.examples.shapes.views;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class PropertiesView extends ViewPart
		implements ITabbedPropertySheetPageContributor {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.gef.examples.shapes.views.PropertiesView";

	private List parameters;

	private TableViewer tableViewer;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		// createParameters();
		Composite composite = new Composite(parent, SWT.None);
		TableColumnLayout layout = new TableColumnLayout();
		composite.setLayout(layout);
		tableViewer = createParameterTable(composite, layout);
		// tableViewer.setInput(parameters.toArray());

		tableViewer.setLabelProvider(new ViewLabelProvider());

		getSite().setSelectionProvider(new ISelectionProvider() {
			public void setSelection(ISelection selection) {
			}

			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
			}

			public ISelection getSelection() {
				return new StructuredSelection(parameters);
			}

			public void addSelectionChangedListener(
					ISelectionChangedListener listener) {
			}
		});
	}

	/**
	*
	*/
	private void createParameters() {
		parameters = new ArrayList<Parameter>(50);
		for (int i = 0; i < 50; ++i) {
			parameters.add(new Object());
		}
	}

	class ViewLabelProvider extends LabelProvider
			implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * @param composite
	 * @param layout
	 */
	public static TableViewer createParameterTable(Composite composite,
			TableColumnLayout layout) {
		Table table = new Table(composite, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn keyColumn = new TableColumn(table, SWT.LEFT);
		keyColumn.setText("Key");
		layout.setColumnData(keyColumn, new ColumnWeightData(25, true));
		TableColumn valueColumn = new TableColumn(table, SWT.LEFT);
		valueColumn.setText("Value");
		layout.setColumnData(valueColumn, new ColumnWeightData(75, true));
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);
		// tableViewer.setLabelProvider(new ParameterLabelProvider());
		// tableViewer.setContentProvider(new ParameterContentProvider());
		// viewer.setContentProvider(ArrayContentProvider.getInstance());
		return tableViewer;
	}

	/**
	 * The constructor.
	 */
	public PropertiesView() {
	}
	//
	// /**
	// * This is a callback that will allow us to create the viewer and
	// initialize
	// * it.
	// */
	// public void createPartControl(Composite parent) {
	// viewer = new TableViewer(parent,
	// SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	//
	// viewer.setContentProvider(ArrayContentProvider.getInstance());
	// viewer.setInput(new String[] { "One", "Two", "Three" });
	// viewer.setLabelProvider(new ViewLabelProvider());
	//
	// // Create the help context id for the viewer's control
	// PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
	// "org.eclipse.gef.examples.shapes.viewer");
	// getSite().setSelectionProvider(viewer);
	// makeActions();
	// hookContextMenu();
	// hookDoubleClickAction();
	// contributeToActionBars();
	//
	// }
	//
	// private void hookContextMenu() {
	// MenuManager menuMgr = new MenuManager("#PopupMenu");
	// menuMgr.setRemoveAllWhenShown(true);
	// menuMgr.addMenuListener(new IMenuListener() {
	// public void menuAboutToShow(IMenuManager manager) {
	// PropertiesView.this.fillContextMenu(manager);
	// }
	// });
	// Menu menu = menuMgr.createContextMenu(viewer.getControl());
	// viewer.getControl().setMenu(menu);
	// getSite().registerContextMenu(menuMgr, viewer);
	// }
	//
	// private void contributeToActionBars() {
	// IActionBars bars = getViewSite().getActionBars();
	// fillLocalPullDown(bars.getMenuManager());
	// fillLocalToolBar(bars.getToolBarManager());
	//
	// }
	//
	// private void fillLocalPullDown(IMenuManager manager) {
	// manager.add(action1);
	// manager.add(new Separator());
	// manager.add(action2);
	// }
	//
	// private void fillContextMenu(IMenuManager manager) {
	// manager.add(action1);
	// manager.add(action2);
	// // Other plug-ins can contribute there actions here
	// manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	// }
	//
	// private void fillLocalToolBar(IToolBarManager manager) {
	// manager.add(action1);
	// manager.add(action2);
	// }
	//
	// private void makeActions() {
	// action1 = new Action() {
	// public void run() {
	// showMessage("Action 1 executed");
	// }
	// };
	// action1.setText("Action 1");
	// action1.setToolTipText("Action 1 tooltip");
	// action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
	// .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	//
	// action2 = new Action() {
	// public void run() {
	// showMessage("Action 2 executed");
	// }
	// };
	// action2.setText("Action 2");
	// action2.setToolTipText("Action 2 tooltip");
	// action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
	// .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	// doubleClickAction = new Action() {
	// public void run() {
	// ISelection selection = viewer.getSelection();
	// Object obj = ((IStructuredSelection) selection)
	// .getFirstElement();
	// // showMessage("Double-click detected on " + obj.toString());
	// Display display = new Display();
	// Shell shell = new Shell(display);
	//
	// FileDialog d = new FileDialog(shell, SWT.OPEN);
	// d.open();
	// }
	// };
	// }
	//
	// private void hookDoubleClickAction() {
	// viewer.addDoubleClickListener(new IDoubleClickListener() {
	// public void doubleClick(DoubleClickEvent event) {
	// doubleClickAction.run();
	// }
	// });
	// }
	//
	// private void showMessage(String message) {
	// MessageDialog.openInformation(viewer.getControl().getShell(),
	// "Properties View", message);
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

	@Override
	public String getContributorId() {
		return ID;
	}

	/*
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		Object result = null;
		if (adapter == IPropertySheetPage.class) {
			result = new TabbedPropertySheetPage(this);
		} else {
			result = super.getAdapter(adapter);
		}
		return result;
	}
}

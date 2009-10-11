package com.mdss.pos.config.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import com.mdss.pos.PosException;
import com.mdss.pos.ui.dialog.POSDialog;
import com.mdss.pos.ui.dialog.POSMessageDialog;

public class ConfigurationDialog extends POSDialog implements ChangeListener, ActionListener {
	private static final String OK = "Ok";
	private static final String CANCEL = "Cancel";
	private JTabbedPane tabbedPane = new JTabbedPane();
	private List<ConfigurationView> views = new ArrayList<ConfigurationView>();
	
	
	public ConfigurationDialog(Frame parent) {
		super(parent, false);
		
		setLayout(new MigLayout("","","[fill,grow][]"));
		
		add(tabbedPane , "grow");
		
		RestaurantConfigurationView restaurantConfigurationView = new RestaurantConfigurationView();
		addView(restaurantConfigurationView);
		
		PrintConfigurationView printConfigurationView = new PrintConfigurationView();
		addView(printConfigurationView);
		
		DrawerPullConfigurationView drawerPullConfigurationView = new DrawerPullConfigurationView();
		addView(drawerPullConfigurationView);
		
		tabbedPane.addChangeListener(this);
		
		JPanel bottomPanel = new JPanel(new MigLayout("fillx,ins 0"));
		bottomPanel.add(new JSeparator(), "span, grow");
		
		JButton btnOk = new JButton(CANCEL);
		btnOk.addActionListener(this);
		bottomPanel.add(btnOk, "dock east, gaptop 5");
		JButton btnCancel = new JButton(OK);
		btnCancel.addActionListener(this);
		bottomPanel.add(btnCancel, "dock east, gapright 5, gaptop 5");
		
		add(bottomPanel, "newline,growx, gaptop 10");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void addView(ConfigurationView view) {
		tabbedPane.addTab(view.getName(), view);
		views.add(view);
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		
		if(b) {
			stateChanged(null);
		}
	}

	public void stateChanged(ChangeEvent e) {
		ConfigurationView view = (ConfigurationView) tabbedPane.getSelectedComponent();
		if(!view.isInitialized()) {
			try {
				view.initialize();
			} catch (Exception e1) {
				POSMessageDialog.showError(this, POSMessageDialog.ERROR_MESSAGE, e1);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(OK.equalsIgnoreCase(e.getActionCommand())) {
			try {
				for (ConfigurationView view : views) {
					if(view.isInitialized())
						view.save();
				}
				setCanceled(false);
				dispose();
			} catch (PosException x) {
				POSMessageDialog.showError(this, x.getMessage());
			} catch (Exception x) {
				POSMessageDialog.showError(this, POSMessageDialog.ERROR_MESSAGE, x);
			}
		}
		else if(CANCEL.equalsIgnoreCase(e.getActionCommand())) {
			setCanceled(true);
			dispose();
		}
	}

}

package com.mdss.pos.ui.report.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.mdss.pos.bo.ui.BackOfficeWindow;
import com.mdss.pos.main.Application;
import com.mdss.pos.ui.report.ServerProductivityReportView;

public class ServerProductivityReportAction extends AbstractAction {

	public ServerProductivityReportAction() {
		super("Server Productivity Report");
	}

	public ServerProductivityReportAction(String name) {
		super(name);
	}

	public ServerProductivityReportAction(String name, Icon icon) {
		super(name, icon);
	}

	public void actionPerformed(ActionEvent e) {
		BackOfficeWindow window = Application.getInstance().getBackOfficeWindow();
		JTabbedPane tabbedPane = window.getTabbedPane();
		
		ServerProductivityReportView reportView = null;
		int index = tabbedPane.indexOfTab("Server Productivity Report");
		if (index == -1) {
			reportView = new ServerProductivityReportView();
			tabbedPane.addTab("Server Productivity Report", reportView);
		}
		else {
			reportView = (ServerProductivityReportView) tabbedPane.getComponentAt(index);
		}
		tabbedPane.setSelectedComponent(reportView);
	}

}

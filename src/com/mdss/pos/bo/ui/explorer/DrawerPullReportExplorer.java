package com.mdss.pos.bo.ui.explorer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;

import com.mdss.pos.model.DrawerPullReport;
import com.mdss.pos.model.dao.DrawerPullReportDAO;
import com.mdss.pos.model.util.DateUtil;
import com.mdss.pos.swing.TransparentPanel;
import com.mdss.pos.ui.PosTableRenderer;
import com.mdss.pos.ui.dialog.POSMessageDialog;

public class DrawerPullReportExplorer extends TransparentPanel {
	private JXDatePicker fromDatePicker = new JXDatePicker();
	private JXDatePicker toDatePicker = new JXDatePicker();
	private JButton btnGo = new JButton("GO");
	private JButton btnEditActualAmount = new JButton("Edit Actual Amount");
	
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd MMM, yyyy hh:mm a");

	private JTable table;

	public DrawerPullReportExplorer() {
		super(new BorderLayout());

		JPanel topPanel = new JPanel(new MigLayout());

		topPanel.add(new JLabel("From:"), "grow");
		topPanel.add(fromDatePicker, "wrap");
		topPanel.add(new JLabel("To:"), "grow");
		topPanel.add(toDatePicker, "wrap");
		topPanel.add(btnGo, "skip 1, al right");
		add(topPanel, BorderLayout.NORTH);

		add(new JScrollPane(table = new JTable(new DrawerPullExplorerTableModel(null))));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new PosTableRenderer());
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(btnEditActualAmount);
		add(bottomPanel, BorderLayout.SOUTH);

		btnGo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					viewReport();
				} catch (Exception e1) {
					POSMessageDialog.showError(DrawerPullReportExplorer.this, POSMessageDialog.ERROR_MESSAGE, e1);
				}
			}

		});
		btnEditActualAmount.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = table.getSelectedRow();
					if(selectedRow < 0) {
						JOptionPane.showMessageDialog(DrawerPullReportExplorer.this, "Please select a drawer pull to edit.", "MDS-POS", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					String amountString = JOptionPane.showInputDialog(DrawerPullReportExplorer.this, "Please enter the actual amount:");
					if(amountString == null) {
						return;
					}
					double amount = 0;
					try {
						amount = Double.parseDouble(amountString);
					}catch(Exception x) {
						JOptionPane.showMessageDialog(DrawerPullReportExplorer.this, "The amount you entered is not valid", "MDS-POS", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					DrawerPullExplorerTableModel model = (DrawerPullExplorerTableModel) table.getModel();
					DrawerPullReport report = (DrawerPullReport) model.getRowData(selectedRow);
					report.setCashToDeposit(amount);
					
					DrawerPullReportDAO dao = new DrawerPullReportDAO();
					dao.saveOrUpdate(report);
					model.updateItem(selectedRow);
				} catch (Exception e1) {
					POSMessageDialog.showError(DrawerPullReportExplorer.this, POSMessageDialog.ERROR_MESSAGE, e1);
				}
			}
			
		});
	}

	private void viewReport() {
		try {
			Date fromDate = fromDatePicker.getDate();
			Date toDate = toDatePicker.getDate();

			fromDate = DateUtil.startOfDay(fromDate);
			toDate = DateUtil.endOfDay(toDate);

			List<DrawerPullReport> list = new DrawerPullReportDAO().findReports(fromDate, toDate);
			table.setModel(new DrawerPullExplorerTableModel(list));
		} catch (Exception e) {
			POSMessageDialog.showError(this, POSMessageDialog.ERROR_MESSAGE, e);
		}
	}

	class DrawerPullExplorerTableModel extends ListTableModel {
		String[] columnNames = { "ID", "TIME", "DRAWER PULL AMOUNT", "ACTUAL AMOUNT" };
		
		
		DrawerPullExplorerTableModel(List<DrawerPullReport> list) {
			setRows(list);
			setColumnNames(columnNames);
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			DrawerPullReport report = (DrawerPullReport) rows.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return report.getId().toString();

			case 1:
				return dateTimeFormatter.format(report.getReportTime());

			case 2:
				return report.getDrawerAccountable();

			case 3:
				return report.getCashToDeposit();

			}
			return null;
		}
	}
}

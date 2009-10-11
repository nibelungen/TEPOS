package com.mdss.pos.ui.dialog;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

import com.mdss.pos.model.CookingInstruction;
import com.mdss.pos.model.dao.CookingInstructionDAO;
import com.mdss.pos.swing.PosButton;

public class SelectCookongInstructionDialog extends POSDialog implements ActionListener {
	private CookingInstruction cookingInstruction;
	
	private JComboBox cbCookingInstructions;
	private PosButton btnNew;
	private PosButton btnOk;
	private PosButton btnCancel;

	public SelectCookongInstructionDialog() throws HeadlessException {
		super();
	}

	public SelectCookongInstructionDialog(Dialog owner, boolean modal) {
		super(owner, modal);
	}

	public SelectCookongInstructionDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public SelectCookongInstructionDialog(Frame owner, boolean modal, boolean unDecorated) throws HeadlessException {
		super(owner, modal, unDecorated);
	}

	public SelectCookongInstructionDialog(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
	}
	
	@Override
	protected void initUI() {
		setLayout(new MigLayout());
		
		CookingInstructionDAO dao = new CookingInstructionDAO();
		List<CookingInstruction> cookingInstructions = dao.findAll();
		DefaultComboBoxModel cbModel = new DefaultComboBoxModel(cookingInstructions.toArray());
		
		cbCookingInstructions = new JComboBox(cbModel);
		cbCookingInstructions.setFont(cbCookingInstructions.getFont().deriveFont(16));
		btnNew = new PosButton("NEW");
		btnOk = new PosButton("OK");
		btnCancel = new PosButton("CANCEL");
		add(cbCookingInstructions, "wrap, span, grow, h 30");
		add(new JSeparator(), "wrap, span, grow");
		add(btnNew, "al right,width 120, height 30");
		add(btnOk, "al right,width 120, height 30");
		add(btnCancel, "width 120, height 30");
		
		btnNew.addActionListener(this);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	private void doOk() {
		cookingInstruction = (CookingInstruction) cbCookingInstructions.getSelectedItem();
		setCanceled(false);
		dispose();
	}

	private void doCancel() {
		setCanceled(true);
		dispose();
	}
	
	private void doCreateNew() {
		NewCookongInstructionDialog dialog = new NewCookongInstructionDialog(this, true);
		dialog.pack();
		dialog.open();
		
		if(!dialog.isCanceled()) {
			cookingInstruction = dialog.getCookingInstruction();
			DefaultComboBoxModel model = (DefaultComboBoxModel) cbCookingInstructions.getModel();
			model.addElement(cookingInstruction);
			model.setSelectedItem(cookingInstruction);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if("OK".equalsIgnoreCase(actionCommand)) {
			doOk();
		}
		else if("CANCEL".equalsIgnoreCase(actionCommand)) {
			doCancel();
		}
		else if("NEW".equalsIgnoreCase(actionCommand)) {
			doCreateNew();
		}
	}

	public CookingInstruction getCookingInstruction() {
		return cookingInstruction;
	}

	public CookingInstruction getSelectedCookingInstruction() {
		return cookingInstruction;
	}

}

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.FlowLayout;

import mutableRequisiteP.mutableRequisite;
import mutableRequisiteP.schoolYearSerializable;

public class RequisiteLinker extends JFrame {

	private static final long serialVersionUID = -4843017666403330164L;
	private ArrayList<mutableRequisite> allreqs;
	private java.awt.List allreqlist;
	private JPanel rightLayout;
	private JPanel leftLayout;
	private JPanel bottomLayout;
	private JButton addLink;
	private JButton complete;
	private Checkbox[] checkboxes;
	private JLabel[] labels;
	private schoolYearSerializable sr;

	public RequisiteLinker(ArrayList<mutableRequisite> allreqss, schoolYearSerializable sr) {
		this.sr = sr;
		setLayout(new BorderLayout());
		allreqs = allreqss;
		rightLayout = new JPanel();
		leftLayout = new JPanel();
		leftLayout.setLayout(new FlowLayout());
		bottomLayout = new JPanel();
		rightLayout.setLayout(new GridLayout(1,1));

		checkboxes = new Checkbox[allreqs.size()];
		labels = new JLabel[allreqs.size()];
		for (int i = 0; i < allreqs.size(); i++) {
			checkboxes[i] = new Checkbox();
			labels[i] = new JLabel("" + i + ") " + allreqs.get(i).name);
			leftLayout.add(checkboxes[i]);
			leftLayout.add(labels[i]);
		}

		allreqlist = new List();
		updateAllReqList();
		allreqlist.addItemListener(listListener());
		rightLayout.add(allreqlist);
		add(leftLayout, BorderLayout.CENTER);
		add(rightLayout, BorderLayout.LINE_END);

		addLink = new JButton("Set Links");
		addLink.addActionListener(addLinkListener());
		complete = new JButton("Complete");
		complete.addActionListener(addCompleteListener());
		bottomLayout.add(addLink);
		bottomLayout.add(complete);
		add(bottomLayout, BorderLayout.PAGE_END);
		
		setTitle("Link Requisites");
		pack();
		setSize(800, 500);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

	}

	private String promptForFile() {
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("Requisite.rql"));
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
	}

	private void updateAllReqList() {
		allreqlist.removeAll();

		for (int i = 0; i < allreqs.size(); i++) {
			allreqlist.add("" + i + ") " + allreqs.get(i).name);
		}

	}

	private ItemListener listListener() {
		return new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				int offset = allreqlist.getSelectedIndex();
				for (int i = 0; i < checkboxes.length; i++) {
					checkboxes[i].setEnabled(i != offset);
					checkboxes[i].setState(false);
				}
			}
		};
	}

	private void serializeRequisites() {
		try {
			String directory = promptForFile();
			if (directory == null || directory.isEmpty())
				return;
			FileOutputStream fileOutStream = new FileOutputStream(directory);
			ObjectOutputStream out = new ObjectOutputStream(fileOutStream);
			mutableRequisite[] reqArray = new mutableRequisite[allreqs.size()];
			reqArray = allreqs.toArray(reqArray);
			sr.requisites = reqArray;
			out.writeObject(sr);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ActionListener addCompleteListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serializeRequisites();
			}
		};
	}

	private ActionListener addLinkListener() {
		return new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LinkedList<Integer> prereqs = new LinkedList<Integer>();
				for (int i = 0; i < checkboxes.length; i++)
					if (checkboxes[i].getState())
						prereqs.add(i);

				int[] indexes = allreqlist.getSelectedIndexes();
				if (indexes.length == 0) {
					// TODO: give warning about no selection
					return;
				}
				if (allreqs.get(indexes[0]).prereqlists == null) { // when there
																	// is no
																	// existing
																	// pre-req
																	// list.
					int[][] prereqlistlist = new int[1][];
					int[] prereqlist = new int[prereqs.size()];
					for (int i = 0; i < prereqlist.length; i++)
						prereqlist[i] = prereqs.get(i);
					prereqlistlist[0] = prereqlist;
					allreqs.get(indexes[0]).prereqlists = prereqlistlist;
					return;
				} // when there IS an existing pre-reqlistlist, make it one
					// larger, and add the new list to the end.
				int[][] prereqlistlist = new int[allreqs.get(indexes[0]).prereqlists.length + 1][];
				for (int i = 0; i < prereqlistlist.length - 1; i++)
					prereqlistlist[i] = allreqs.get(indexes[0]).prereqlists[i];
				int[] prereqlist = new int[prereqs.size()];
				for (int i = 0; i < prereqlist.length; i++)
					prereqlist[i] = prereqs.get(i);
				prereqlistlist[prereqlistlist.length - 1] = prereqlist;
				allreqs.get(indexes[0]).prereqlists = prereqlistlist;
			}
		};
	}

}

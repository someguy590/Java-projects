import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.BoxLayout;

import mutableRequisiteP.*;

public class RequisiteMaker extends JFrame {
	
	private JPanel leftLayout;
	private JPanel rightLayout;
	private JPanel bottomLayout;

	
	private JButton addBtn;
	
	private JPanel namePanel;
	private JLabel nameLbl;
	private JTextField nameField;
	
	private JPanel descPanel;
	private JLabel descLbl;
	private JTextField descField;
	
	private JPanel duePanel;
	private JLabel dueLbl;
	private JTextField dueField;
	
	private JPanel weightPanel;
	private JLabel weightLbl;
	private JTextField weightField;
	
	private JButton linkBtn;
	
	private ArrayList<mutableRequisite> allreqs;
	private List allreqlist;
	private static final long serialVersionUID = 1L;
	
	private Frame reqLinker;
	
	protected int currentID = 0;
	
	private schoolYearSerializable sr;

	public RequisiteMaker(schoolYearSerializable sr) {
		this.sr = sr;
		
		leftLayout = new JPanel();
		rightLayout = new JPanel();
		bottomLayout = new JPanel();
		rightLayout.setLayout(new BoxLayout(rightLayout, BoxLayout.PAGE_AXIS));
		
		setLayout(new BorderLayout());
		leftLayout.setLayout(new BoxLayout(leftLayout, BoxLayout.Y_AXIS));
		bottomLayout.setLayout(new FlowLayout());
		
		//overallLayout.add(leftLayout, BorderLayout.LINE_START);
		
		
		allreqs = new ArrayList<mutableRequisite>(10);
		
		
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		((FlowLayout)namePanel.getLayout()).setAlignment(FlowLayout.LEFT);
		nameLbl = new JLabel("Name");
		namePanel.add(nameLbl);
		nameField = new JTextField("", 30);
		namePanel.add(nameField);
		leftLayout.add(namePanel);
		
		descPanel = new JPanel();
		descPanel.setLayout(new FlowLayout());
		((FlowLayout)descPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		descLbl = new JLabel("Description");
		descPanel.add(descLbl);
		descField = new JTextField("", 30);
		descPanel.add(descField);
		leftLayout.add(descPanel);
		
		duePanel = new JPanel();
		duePanel.setLayout(new FlowLayout());
		((FlowLayout)duePanel.getLayout()).setAlignment(FlowLayout.LEFT);
		dueLbl = new JLabel("DueDate");
		duePanel.add(dueLbl);
		dueField = new JTextField("", 30);
		duePanel.add(dueField);
		leftLayout.add(duePanel);
		
		weightPanel = new JPanel();
		weightPanel.setLayout(new FlowLayout());
		((FlowLayout)weightPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		weightLbl = new JLabel("Weight");
		weightPanel.add(weightLbl);
		weightField = new JTextField("", 30);
		weightPanel.add(weightField);
		leftLayout.add(weightPanel);

		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
			System.exit(0);
			}
		});

		
		allreqlist = new List();
		rightLayout.add(allreqlist);
		
		addBtn = new JButton("Create Requisite");
		addBtn.setPreferredSize(new Dimension(200,100));
		rightLayout.add(addBtn);
		
		linkBtn = new JButton("Set Pre-reqs");
		bottomLayout.add(linkBtn);
		
		addBtn.addActionListener(addRequisiteButtonListener());
		linkBtn.addActionListener(RequisiteLinkButtonListener());
		
		add(leftLayout, BorderLayout.CENTER);
		add(rightLayout, BorderLayout.LINE_END);
		add(bottomLayout, BorderLayout.PAGE_END);

		setTitle("Create Requisites");
		pack();
		setSize(800, 500);
		System.out.println("Setsize");
		setVisible(true);
	}
	
	private void updateAllReqList(){
		allreqlist.removeAll();
		for(int i = 0; i<allreqs.size(); i++){
			allreqlist.add(""+i+") "+ allreqs.get(i).name);
		}
	}

	public static void main(String[] args) {
		//new RequisiteMaker();

	}
	
	private ActionListener addRequisiteButtonListener(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().isEmpty()){
					return; //TODO: warn about empty name.
				}
				long date;
				int weight;
				try{
					date = Long.parseLong(dueField.getText());
					weight = Integer.parseInt(weightField.getText());
				}catch (NumberFormatException ex){
					return;
					//TODO:  Notify user that date or weight is wrong format.
				}
				mutableRequisite r = new mutableRequisite(nameField.getText(), descField.getText(), weight, allreqs.size(), date);
				if (currentID >= allreqs.size())
					allreqs.add(r);
				else
					allreqs.set(currentID, r);
				updateAllReqList();
				nameField.setText("");
				descField.setText("");
				dueField.setText("");
				weightField.setText("");
				currentID = allreqs.size();
			}
		};
	}
	
	private ActionListener RequisiteLinkButtonListener(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				reqLinker = new RequisiteLinker(allreqs, sr);
			}
		};
	}
}

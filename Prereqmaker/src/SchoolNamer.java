import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.BoxLayout;

import mutableRequisiteP.*;

public class SchoolNamer extends JFrame {
	
	private JPanel leftLayout;
	private JPanel bottomLayout;

	
	private JPanel namePanel;
	private JLabel nameLbl;
	private JTextField nameField;
	
	private JPanel yearPanel;
	private JLabel yearLbl;
	private JTextField yearField;
	
	private JPanel semesterPanel;
	private JLabel semesterLbl;
	private JTextField semesterField;
	
	private JPanel majorPanel;
	private JLabel majorLbl;
	private JTextField majorField;
	
	private JButton linkBtn;
	
	private Frame reqLinker;
	
	private static final long serialVersionUID = 1L;
	
	protected int currentID = 0;

	public SchoolNamer() {
		
		leftLayout = new JPanel();
		bottomLayout = new JPanel();
		
		setLayout(new BorderLayout());
		leftLayout.setLayout(new BoxLayout(leftLayout, BoxLayout.Y_AXIS));
		bottomLayout.setLayout(new FlowLayout());
		
		
		
		namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		((FlowLayout)namePanel.getLayout()).setAlignment(FlowLayout.LEFT);
		nameLbl = new JLabel("Name");
		namePanel.add(nameLbl);
		nameField = new JTextField("", 30);
		namePanel.add(nameField);
		leftLayout.add(namePanel);
		
		yearPanel = new JPanel();
		yearPanel.setLayout(new FlowLayout());
		((FlowLayout)yearPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		yearLbl = new JLabel("Year");
		yearPanel.add(yearLbl);
		yearField = new JTextField("", 30);
		yearPanel.add(yearField);
		leftLayout.add(yearPanel);
		
		semesterPanel = new JPanel();
		semesterPanel.setLayout(new FlowLayout());
		((FlowLayout)semesterPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		semesterLbl = new JLabel("Semester");
		semesterPanel.add(semesterLbl);
		semesterField = new JTextField("", 30);
		semesterPanel.add(semesterField);
		leftLayout.add(semesterPanel);
		
		majorPanel = new JPanel();
		majorPanel.setLayout(new FlowLayout());
		((FlowLayout)majorPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		majorLbl = new JLabel("Major");
		majorPanel.add(majorLbl);
		majorField = new JTextField("", 30);
		majorPanel.add(majorField);
		leftLayout.add(majorPanel);

		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
			System.exit(0);
			}
		});
		
		linkBtn = new JButton("Set Pre-reqs");
		linkBtn.addActionListener(RequisiteLinkButtonListener());
		bottomLayout.add(linkBtn);
		
		add(leftLayout, BorderLayout.CENTER);
		add(bottomLayout, BorderLayout.PAGE_END);

		setTitle("Select School");
		setSize(800, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SchoolNamer();
	}
	
	private ActionListener RequisiteLinkButtonListener(){
		return new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				schoolYearSerializable sr = new schoolYearSerializable();
				sr.semester = semesterField.getText();
				sr.schoolName = nameField.getText();
				sr.major = majorField.getText();
				try{
					sr.year = Short.parseShort(yearField.getText());
				}
				catch (Exception ex){
					sr.year = 2000;
				}
				reqLinker = new RequisiteMaker(sr);
			}
		};
	}
}

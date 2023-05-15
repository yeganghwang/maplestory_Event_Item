package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.SearchControl;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class SearchUI {

	private JFrame frame;
	public JTextField nameTextbox;
	public JTextField selectTextbox;
	public String table_name="chair_table";
	public JTable searchTable;
	public JScrollPane searchScroll;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private SearchUI This = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUI window = new SearchUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		control.SearchControl scontrol = new control.SearchControl();
		
		frame = new JFrame();
		frame.setTitle("Search");
		frame.setBounds(100, 100, 514, 456);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel nameLabel = new JLabel("Name :");
		nameLabel.setBounds(26, 51, 61, 16);
		frame.getContentPane().add(nameLabel);
		
		JLabel selectLabel = new JLabel("Chair : ");
		selectLabel.setBounds(168, 51, 114, 16);
		frame.getContentPane().add(selectLabel);
		
		JRadioButton chairRadioButton = new JRadioButton("Chair");
		chairRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLabel.setText(chairRadioButton.getText().concat(" : "));
				table_name = "chair_table";
			}
		});
		chairRadioButton.setSelected(true);
		buttonGroup.add(chairRadioButton);
		chairRadioButton.setBounds(16, 16, 65, 23);
		frame.getContentPane().add(chairRadioButton);
		
		
		JRadioButton ridingRadioButton = new JRadioButton("Riding");
		ridingRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLabel.setText(ridingRadioButton.getText().concat(" : "));
				table_name = "riding_table";
			}
		});
		buttonGroup.add(ridingRadioButton);
		ridingRadioButton.setBounds(112, 16, 72, 23);
		frame.getContentPane().add(ridingRadioButton);
		
		JRadioButton damageskinRadioButton = new JRadioButton("Damage Skin");
		damageskinRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLabel.setText(damageskinRadioButton.getText().concat(" : "));
				table_name = "damageskin_table";
			}
		});
		buttonGroup.add(damageskinRadioButton);
		damageskinRadioButton.setBounds(206, 16, 114, 23);
		frame.getContentPane().add(damageskinRadioButton);
			
		String header[] = { "Name", "World", "Result" };

		String contents[][] = {{"GM웡키", "이노시스", "웡스토랑은 웡키와 함께! 의자"}};
		
		searchScroll = new JScrollPane();
		searchScroll.setBounds(26, 118, 456, 273);
		frame.getContentPane().add(searchScroll);
		
			searchTable = new JTable();
			searchScroll.setViewportView(searchTable);
			searchTable.setModel(new DefaultTableModel(contents,header));
			searchTable.getColumnModel().getColumn(0).setMaxWidth(250);
			searchTable.getColumnModel().getColumn(0).setMinWidth(10);
			searchTable.getColumnModel().getColumn(1).setMaxWidth(150);
			searchTable.getColumnModel().getColumn(1).setMinWidth(10);
			searchTable.getColumnModel().getColumn(2).setPreferredWidth(30);
			searchTable.getColumnModel().getColumn(2).setMinWidth(10);
		
		
		nameTextbox = new JTextField();
		nameTextbox.setBounds(26, 67, 130, 26);
		frame.getContentPane().add(nameTextbox);
		nameTextbox.setColumns(10);
		
		selectTextbox = new JTextField();
		selectTextbox.setColumns(10);
		selectTextbox.setBounds(168, 67, 130, 26);
		frame.getContentPane().add(selectTextbox);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//Search 버튼을 눌렀을 때
				scontrol.getResult(This);
			}
		});
		searchButton.setBounds(332, 31, 102, 59);
		frame.getContentPane().add(searchButton);
		
		JButton deleteButton = new JButton("DEL");
		deleteButton.addActionListener(new ActionListener() {	//DEL 버튼을 눌렀을 때
			public void actionPerformed(ActionEvent e) {
				scontrol.deleteItem(This);
			}
		});
		deleteButton.setForeground(Color.RED);
		deleteButton.setBounds(435, 31, 47, 59);
		frame.getContentPane().add(deleteButton);
		
		
		
		

	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public void dispose() {
		frame.dispose();
	}
}

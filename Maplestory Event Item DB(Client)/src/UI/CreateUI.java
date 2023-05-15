package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateUI {

	private JFrame frame;
	public JTextField nameTextbox;
	public JTextField selectTextbox;
	public String table_name="chair_table";
	public JLabel selectLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private CreateUI This = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUI window = new CreateUI();
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
	public CreateUI() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		control.CreateControl ccontrol = new control.CreateControl();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Create");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {	//Create 버튼을 눌렀을 때
			public void actionPerformed(ActionEvent e) {
				ccontrol.inputData(This);				
			}
		});
		createButton.setBounds(235, 130, 117, 59);
		frame.getContentPane().add(createButton);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(67, 100, 61, 16);
		frame.getContentPane().add(nameLabel);
		
		selectLabel = new JLabel("Chair");
		selectLabel.setBounds(67, 150, 130, 16);
		frame.getContentPane().add(selectLabel);

		
		JRadioButton chairRadioButton = new JRadioButton("Chair");
		chairRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLabel.setText(chairRadioButton.getText());
				table_name = "chair_table";
			}
		});
		chairRadioButton.setSelected(true);
		buttonGroup.add(chairRadioButton);
		chairRadioButton.setBounds(77, 65, 65, 23);
		frame.getContentPane().add(chairRadioButton);
		
		
		JRadioButton ridingRadioButton = new JRadioButton("Riding");
		ridingRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLabel.setText(ridingRadioButton.getText());
				table_name = "riding_table";
			}
		});
		buttonGroup.add(ridingRadioButton);
		ridingRadioButton.setBounds(154, 65, 72, 23);
		frame.getContentPane().add(ridingRadioButton);
		
		JRadioButton damageskinRadioButton = new JRadioButton("Damage Skin");
		damageskinRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectLabel.setText(damageskinRadioButton.getText());
				table_name = "damageskin_table";
			}
		});
		buttonGroup.add(damageskinRadioButton);
		damageskinRadioButton.setBounds(238, 65, 114, 23);
		frame.getContentPane().add(damageskinRadioButton);

			
		
		
		nameTextbox = new JTextField();
		nameTextbox.setBounds(67, 120, 130, 26);
		frame.getContentPane().add(nameTextbox);
		nameTextbox.setColumns(10);
		
		selectTextbox = new JTextField();
		selectTextbox.setColumns(10);
		selectTextbox.setBounds(67, 170, 130, 26);
		frame.getContentPane().add(selectTextbox);
		
		
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}

package ca.mcgill.ecse.carshop.view;

import ca.mcgill.ecse.carshop.controller.BusinessHourTO;
import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class TechnicianView extends JFrame{
	private static final long serialVersionUID = -44269335015542L;

	private static JFrame frame;
	private List<BusinessHourTO> businessHours;
	private List<BusinessHourTO> garageBusinessHours;
	private JLabel dayLabel = new JLabel("Day:");
	private JLabel startTimeLabel = new JLabel("Start Time:");
	private JLabel endTimeLabel = new JLabel("End Time:");
	private JComboBox dayComboBox = new JComboBox();
	private JButton btnAddBusinessHour = new JButton("Add Garage Hours");
	private JButton btnRemoveBusinessHour = new JButton("Remove Garage Hours");
	private JComboBox timeComboBox = new JComboBox();
	private ArrayList<String> toRemoveStart= new ArrayList<String>();
	private UpdateAccountView updateAccView;
	
	private ArrayList<String> toRemoveEnd=new ArrayList<String>();
	private JLabel messageLabel = new JLabel("");
	private JTextField startTimeTextField = new JTextField();
	private JTextField endTimeTextField = new JTextField();
	private final JLabel lblWelcomeYouAre = new JLabel("Welcome! You are a Technician");
	
	/**
	 * Create the application.
	 */
	public TechnicianView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		setFrame(frame);
		getFrame().getContentPane().setEnabled(false);
		getFrame().setBounds(100, 100, 850, 326);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		getFrame().setTitle("Technician's Page");


		dayLabel.setBounds(269, 63, 70, 15);
		frame.getContentPane().add(dayLabel);
		startTimeLabel.setFont(new Font("Dialog", Font.BOLD, 14));


		startTimeLabel.setBounds(59, 90, 93, 29);

		frame.getContentPane().add(startTimeLabel);
		endTimeLabel.setFont(new Font("Dialog", Font.BOLD, 14));


		endTimeLabel.setBounds(59, 142, 93, 26);
		frame.getContentPane().add(endTimeLabel);

		//		

		//Get available BusinessHours
		businessHours = CarShopController.getBusinessHours();

		String[] days = new String [businessHours.size()];
		//Set selectable days combobox

		for(int i=0; i<days.length; i++) {
			days[i] = businessHours.get(i).getDayOfWeek();
		}

		for(int i=0; i<days.length; i++) {
			dayComboBox.addItem(days[i]);
		}
		dayComboBox.setToolTipText("select day");
		dayComboBox.setEditable(true);
		dayComboBox.setSelectedIndex(-1);
		dayComboBox.setBounds(227, 48, 93, 24);
		frame.getContentPane().add(dayComboBox);

		dayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dayComboBox.setSelectedItem(dayComboBox.getSelectedItem());
				refreshTimeComboBox();
		
			}
		});

				btnAddBusinessHour.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						int i = dayComboBox.getSelectedIndex();
						messageLabel.setText("");
						if(i<0) {
							messageLabel.setText(" Garage Hours not added. Owner should set up business hours first");
							
						}
						else if(startTimeTextField.getText().length()!=5 || endTimeTextField.getText().length()!=5) {
							messageLabel.setText("Invalid Input. Try format [hr:mm ");
						}
						else {
							BusinessHourTO hourTO = new BusinessHourTO((String) dayComboBox.getSelectedItem(), startTimeTextField.getText(), endTimeTextField.getText());				
							try {
								
								CarShopController.addGarageHours(hourTO, CarShopController.getRecentlyLoggedInUser(), CarShopController.getRecentlyLoggedInUser());
								messageLabel.setText("New hour(s) added successfully");
							} catch (InvalidInputException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
						}
						
			});
		btnAddBusinessHour.setBounds(48, 214, 193, 25);
		frame.getContentPane().add(btnAddBusinessHour);

		JButton signOutButton = new JButton("sign out");
		signOutButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CarShopController.logOut();
				refreshData();
				LogInView.technicianView.getFrame().setVisible(false);
				CarShopController.getLogInView().getFrame().setVisible(true);
			}
		});
		signOutButton.setBounds(721, 0, 117, 25);
		frame.getContentPane().add(signOutButton);


		btnRemoveBusinessHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = timeComboBox.getSelectedIndex();
				messageLabel.setText("");
				if(i<0) {
					messageLabel.setText("There are no hours to remove");
					
				}else {
					
					BusinessHourTO hourTO = new BusinessHourTO((String) dayComboBox.getSelectedItem(), toRemoveStart.get(i), toRemoveEnd.get(i));				
					try {
						CarShopController.removeGarageHours(hourTO, CarShopController.getRecentlyLoggedInUser(), CarShopController.getRecentlyLoggedInUser());
						messageLabel.setText("New hour(s) removed successfully");
						//refreshData();
					} catch (InvalidInputException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				
					
			}
		});
		btnRemoveBusinessHour.setBounds(513, 214, 196, 25);
		frame.getContentPane().add(btnRemoveBusinessHour);

		JButton btnUpdateAccount = new JButton("update acc");
		btnUpdateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateAccView = new UpdateAccountView();
				updateAccView.setVisible(true);
				getFrame().setVisible(false);
				
				
			}
		});
		btnUpdateAccount.setBounds(592, 0, 117, 25);
		frame.getContentPane().add(btnUpdateAccount);

		JLabel timeLabel = new JLabel("Time:");
		timeLabel.setBounds(406, 136, 70, 15);
		frame.getContentPane().add(timeLabel);

		dayComboBox.setEditable(true);
		dayComboBox.setBounds(330, 58, 124, 24);
		frame.getContentPane().add(dayComboBox);

		


		// String[] time = new String [garageBusinessHours.size()];
		//Set selectable days combobox

		//			 for(int i=0; i<days.length; i++) {
		//				 days[i] = businessHours.get(i).getDayOfWeek();
		//			 }



		//		String[] times = new String[day.length];
		//		for(int i=0; i<days.length; i++) {
		//			 dayComboBox.addItem(days[i]);
		//		 }



		//refreshTimeComboBox();
		timeComboBox.setToolTipText("select time to remove");
		timeComboBox.setSelectedIndex(-1);
		timeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timeComboBox.setSelectedItem(timeComboBox.getSelectedItem());
				
			}
		});



		timeComboBox.setEditable(true);
		timeComboBox.setBounds(470, 131, 317, 37);
		frame.getContentPane().add(timeComboBox);

		JSeparator separator = new JSeparator();
		separator.setBounds(338, 118, -33, 115);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(351, 90, 37, 149);
		frame.getContentPane().add(separator_1);
		messageLabel.setForeground(Color.BLUE);
		
		
		messageLabel.setBounds(180, 236, 502, 26);
		frame.getContentPane().add(messageLabel);
		
		
		startTimeTextField.setBounds(170, 90, 114, 29);
		frame.getContentPane().add(startTimeTextField);
		startTimeTextField.setColumns(10);
		
		
		endTimeTextField.setColumns(10);
		endTimeTextField.setBounds(170, 135, 114, 29);
		frame.getContentPane().add(endTimeTextField);
		lblWelcomeYouAre.setForeground(Color.BLUE);
		lblWelcomeYouAre.setBounds(230, 10, 224, 15);
		
		frame.getContentPane().add(lblWelcomeYouAre);


	}

	private void refreshTimeComboBox(){
		//timeComboBox.removeAllItems();
		String time = null;
		garageBusinessHours = CarShopController.getGarageBusinessHours(CarShopController.getRecentlyLoggedInUser());
		timeComboBox.removeAllItems();
		
		if(toRemoveStart.size()!=0) {
			toRemoveStart.clear();
			toRemoveEnd.clear();
			
		}
		
		
		for(BusinessHourTO bh: garageBusinessHours) {
			if(dayComboBox.getSelectedItem()!= null) {
				if(bh.getDayOfWeek().equals(dayComboBox.getSelectedItem())) {
					time = "Start Time: " + bh.getStartTime() + ", End Time: "	+ bh.getEndTime();	
					
					
					timeComboBox.addItem(time);
					toRemoveStart.add(bh.getStartTime());
					toRemoveEnd.add(bh.getEndTime());
				}
			}
		}
		
	
		
	         }


	private void refreshData() {
		businessHours = CarShopController.getBusinessHours();

	}
	public static void setFrame(JFrame frame) {
		
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
	}

	public static JFrame getFrame() {
		return frame;
	}
}

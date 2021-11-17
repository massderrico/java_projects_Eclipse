package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.*;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class CustomerView extends JFrame{
	private JLabel errorMessage;
	private String error;
	private String appointmentHeadings[] = {"Date", "Start Time", "Service"};
	private String appointmentData[][] = {};
	private JFrame frame;
	private JScrollPane appScrollPane;
	private JTable appointments;
	private JComboBox cancelAppointmentBox;
	private JButton cancelAppointmentButton, makeAppointmentButton, updateAppointmentButton;
	private JLabel cancelAppointmentLabel;
	private JFrame frmCancelAnAppointment;
	public static UpdateAppointmentView updateAppointmentView;
	public static UpdateAccountView updateAccount;
	public static MakeAppointmentView makeAppointment;
	private JButton updateAccountButton;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelAppointment window = new CancelAppointment();
					window.frmCancelAnAppointment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public CustomerView() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		

		// error message
		errorMessage=new JLabel();
		errorMessage.setForeground(Color.RED);
		errorMessage.setText(error);
		
		// Cancel Appointment Button
		cancelAppointmentButton=new JButton();
		cancelAppointmentButton.setText("Cancel appointment");
		
		makeAppointmentButton=new JButton();
		makeAppointmentButton.setText("Make an appointment");
		
		updateAppointmentButton=new JButton();
		updateAppointmentButton.setText("Update appointment");
		
		updateAccountButton=new JButton();
		updateAccountButton.setText("Update Account");
		
		// Cancel Appointment CnoboBox
		ToAppointment[] apps=new ToAppointment[CarShopController.getCustomerToAppointments(CarShopController.getLoggedInUser()).size()];
		for(int i=0; i<CarShopController.getCustomerToAppointments(CarShopController.getLoggedInUser()).size();i++) {
			apps[i]=CarShopController.getCustomerToAppointments(CarShopController.getLoggedInUser()).get(i);
		}
		cancelAppointmentBox=new JComboBox<ToAppointment>(apps);
		cancelAppointmentBox.addItem("");
		// Cancel appointment button action
		cancelAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					cancelAppointmentButtonActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void cancelAppointmentButtonActionPerformed(ActionEvent evt) throws Exception {
				// TODO Auto-generated method stub
				try {
					CarShopController.CancelToAppointment((ToAppointment) cancelAppointmentBox.getSelectedItem());
				}
				catch(Exception e) {
					error=e.getMessage();
				}
				refreshAppointmentOverview();
			}
		});
		
		makeAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					makeAppointmentButtonActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void makeAppointmentButtonActionPerformed(ActionEvent evt) throws Exception {
				// TODO Auto-generated method stub
				try {
					makeAppointment= new MakeAppointmentView();
					
					makeAppointment.setVisible(true);
				}
				catch(Exception e) {
					error=e.getMessage();
				}
				refreshAppointmentOverview();
			}
		});
		updateAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					updateAppointmentButtonActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void updateAppointmentButtonActionPerformed(ActionEvent evt) throws Exception {
				// TODO Auto-generated method stub
				try {
					if(cancelAppointmentBox.getSelectedIndex() != 0) {
						updateAppointmentView = new UpdateAppointmentView((ToAppointment)cancelAppointmentBox.getSelectedItem());
					}
					
					updateAppointmentView.setVisible(true);
				}
				catch(Exception e) {
					error=e.getMessage();
				}
				refreshAppointmentOverview();
			}
		});
		
		updateAccountButton.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					updateAccountButtonActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void updateAccountButtonActionPerformed(ActionEvent evt) throws Exception {
				// TODO Auto-generated method stub
				try {
					updateAccount = new UpdateAccountView();
					
					updateAccount.setVisible(true);
				}
				catch(Exception e) {
					error=e.getMessage();
				}
				refreshAppointmentOverview();
			}
		});
		
		appointments = new JTable(appointmentData,appointmentHeadings);
		appScrollPane = new JScrollPane(appointments);
		this.add(appScrollPane);
		Dimension d2 = appointments.getPreferredSize();
		appScrollPane.setPreferredSize(new Dimension(d2.width, 100));
		appScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.setTitle("Customer Home Page");			
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
					
		
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(
					layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
							.addComponent(makeAppointmentButton)
							.addComponent(cancelAppointmentBox)
							.addGroup(layout.createParallelGroup()
									.addComponent(updateAppointmentButton)
									.addComponent(cancelAppointmentButton)
									)
							)
					.addComponent(appScrollPane)
					)
				.addComponent(updateAccountButton)
				);
				
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(
					layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(makeAppointmentButton)
							.addComponent(cancelAppointmentBox)
							.addGroup(layout.createSequentialGroup()
									.addComponent(updateAppointmentButton)
									.addComponent(cancelAppointmentButton)
									)
							)
					.addComponent(appScrollPane)
					)
				.addComponent(updateAccountButton)
				);
		pack();
		
		
		
	}
	
	
	public void refreshAppointmentOverview() throws Exception {
		// TODO Step 4: refresh daily overview (add whole method body)
			DefaultTableModel table = new DefaultTableModel(0, 0);
			table.setColumnIdentifiers(appointmentHeadings);
			appointments.setModel(table);
			for (ToAppointment a : CarShopController.getCustomerToAppointments(CarShopController.getLoggedInUser())) {
				Object[] object = {a.getDay(), a.getStartTime(), a.getBookingName()};
				table.addRow(object);
			}
			Dimension d1 = appointments.getPreferredSize();
			appScrollPane.setPreferredSize(new Dimension(d1.width, 100));
	}
}

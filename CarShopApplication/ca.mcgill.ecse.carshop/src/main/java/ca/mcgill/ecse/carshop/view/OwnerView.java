package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.awt.Font;
import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.sql.Time;
import java.sql.Date;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import ca.mcgill.ecse.carshop.controller.*;

public class OwnerView extends JFrame{
		
		// nath
		private JFrame frame1;
		private JTextField servTextBox;
		private JTextField durationTextBox;
		
		private JFrame frame;
		private JTextField textField;
		private JTextField textField_1;

		private JLabel serviceBoxLabel;
		private JLabel newServiceNameLabel;
		private JLabel newDurationLabel;
		private JLabel garageLabel;
		private JComboBox<String[]> garageBox;
		private JComboBox<String[]> serviceComboBox;
		private JButton submitButton;
		private JButton btnNewButton;
		private JLabel servSectionLabel;
		private JLabel garageBoxLabel;
		private JButton subButton1;
		private JComboBox comboBox1;
		private JLabel durationLabel;
		private JLabel servNameLabel;
		private JLabel addServiceLabel;
		private JLabel updateServiceLabel;
		String error = null;
		// error
		private JLabel Error;
		
		//*Tiff's Service Combo***************************************************************//
		// for defining a service combo (owner only)
		private JLabel createServiceComboLabel;
		private JLabel comboNameLabel;
		private JTextField comboName;
		private JLabel comboMainServiceLabel;
		private JTextField comboMainService;
		private JLabel comboServicesLabel;
		private JTextField comboServices;
		private JLabel comboMandLabel;
		private JTextField comboMand;
		private JLabel comboInstruct1;
		private JLabel comboInstruct2;
		private JLabel businessInfoLbl, businessHourLbl;
		private DefaultTableModel table;
				
		//*Tiff's Service Combo***************************************************************//
		// for updating a service combo (owner only)
		private JLabel updateServiceComboLabel;
		private JLabel updateComboOldNameLabel;
		private JTextField updateComboOldName;
		private JLabel updateComboMainServiceLabel;
		private JTextField updateComboMainService;
		private JLabel updateComboServicesLabel;
		private JTextField updateComboServices;
		private JLabel updateComboMandLabel;
		private JTextField updateComboMand;
		private JLabel updateComboInstruct1;
		private JLabel updateComboInstruct2;
		private JLabel updateComboNewNameLabel;
		private JTextField updateComboNewName;
		
		// for updating a service combo (owner only)
		private JLabel comboOldNameLabel;
		private JTextField comboOldName;
		
		// The buttons for making/updating a SC
		private JButton createComboButton;
		private JButton updateComboButton;
		
		//*Tiff's Appointment Management Buttons***************************************************************//
		// i.e. end appointment, declare noShows
		
		
		private JLabel appointmentSectionLabel;
		private JLabel appointmentBoxLabel;
		private JComboBox<ToAppointment> appointmentBox;
		private JComboBox<String> stringAppointmentBox;
		private HashMap<Integer, ToAppointment> appointmentList;
		
		private JButton endAppointmentButton;
		private JButton noShowsButton;
		
		private JButton startAppointmentButton;
		private JButton backButton;
		
		private JLabel pageName;
				
		
		
		/*Ramin:*/
		
		// UI elements
		private JLabel errorMessage;
		// driver
		private JTextField nameTxt, addressTxt, phoneNumTxt, emailTxt;
		private JTextField bHourDayTxt, bHourStartTxt, bHourEndTxt;
		//private JTextField timeSlotStartDateTxt, timeSlotEndDateTxt, timeSlotStartTimeTxt, timeSlotEndTimeTxt, timeSlotTypeTxt;
		private JLabel bHourDayLbl, bHourStartLbl, bHourEndLbl;
		private JLabel nameLbl, addressLbl, phoneNumLbl, emailLbl, businessLbl;
		//private JLabel timeSlotStartDateLbl, timeSlotEndDateLbl, timeSlotStartTimeLbl, timeSlotEndTimeLbl, timeSlotTypeLbl;
		private JButton setUpBusinessButton, updateBusinessButton;
		private JButton addBusinessHourButton, updateBusinessHourButton, removeBusinessHourButton; //addTimeSlotButton, updateTimeSlotButton, removeTimeSlotButton;
		private JComboBox<String> daysOfWeek, /*timeSlotTypeList,*/ businessHourList; //timeSlotList;
		private JTable businessHourTable, appointmentTable;
		private String businessHourHeadings[] = {"Day", "Opening", "Closing"};
		private String appointmentHeadings[] = {"Date", "Start Time", "Service", "Customer"};
		private String businessHourData[][] = {};
		private String appointmentData[][] = {};
		private JScrollPane scrollBHour, scrollApp;
		private ToBusiness business;
		/*private static final int TABLE_HEIGHT = 200;
		private static final int WIDTH_BUS_ROUTE_VISUALIZATION = 200;
		private static final int HEIGHT_BUS_ROUTE_VISUALIZATION = 200;*/
		
		private void initComponent() {		
			
			///nath's code
			/*frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			*/
			addServiceLabel = new JLabel("Add Service");
			//lblNewLabel.setBounds(196, 41, 108, 16);
			//lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			//frame.getContentPane().add(lblNewLabel);
			
			servTextBox = new JTextField();
			//textField.setBounds(181, 105, 130, 26);
			
			//frame.getContentPane().add(textField);
			
			//textField.setColumns(10);
			
			servNameLabel = new JLabel("Service Name:");
			//lblNewLabel_1.setBounds(76, 110, 88, 16);
			//frame.getContentPane().add(lblNewLabel_1);
			
			durationLabel = new JLabel("Duration:");
			//lblNewLabel_1_1.setBounds(76, 146, 59, 16);
			//frame.getContentPane().add(lblNewLabel_1_1);
			
			durationTextBox = new JTextField();
			//textField_1.setBounds(181, 141, 130, 26);
			//textField_1.setColumns(10);
			//frame.getContentPane().add(textField_1);
			
			JLabel minutesLabel = new JLabel("minutes");
			//lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			//lblNewLabel_2.setBounds(323, 146, 61, 16);
			//frame.getContentPane().add(lblNewLabel_2);
			
			//frame.getContentPane().add(btnNewButton_1);
			
			comboBox1 = new JComboBox(CarShopController.getGarageList());
			comboBox1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			//comboBox.setBounds(181, 179, 130, 27);
			//frame.getContentPane().add(comboBox);
			
			garageBoxLabel = new JLabel("Garage:");
			//lblNewLabel_3.setBounds(76, 183, 61, 16);
			//frame.getContentPane().add(lblNewLabel_3);
			
			subButton1 = new JButton("Submit");
			//btnNewButton.setBounds(337, 218, 88, 29);
			subButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(servTextBox.getText() + ": " + durationTextBox.getText());
					try {
						CarShopController.addService(servTextBox.getText(), durationTextBox.getText(),comboBox1.getSelectedItem().toString());
					} catch (Exception e1) {
						e1.printStackTrace(); //debugging
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			});
			//frame.getContentPane().add(btnNewButton);
			
			//*Nath's Service Update***************************************************************//
			
			//frame = new JFrame();
			//frame.setBounds(100, 100, 450, 300);
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.getContentPane().setLayout(null);
			
			serviceBoxLabel = new JLabel("Select the service to be updated:");
			//lblNewLabel.setBounds(38, 48, 232, 16);
			//frame.getContentPane().add(lblNewLabel);
			
			newServiceNameLabel = new JLabel("New Service Name:");
			//lblNewLabel_1.setBounds(38, 118, 126, 16);
			//frame.getContentPane().add(lblNewLabel_1);
			
			newDurationLabel = new JLabel("New Duration:");
			//lblNewLabel_1_1.setBounds(38, 150, 126, 16);
			//frame.getContentPane().add(lblNewLabel_1_1);
			
			garageLabel = new JLabel("Garage:");
			//lblNewLabel_1_2.setBounds(38, 182, 61, 16);
			//frame.getContentPane().add(lblNewLabel_1_2);
			
			textField = new JTextField();
			//textField.setBounds(176, 113, 130, 26);
			//frame.getContentPane().add(textField);
			//textField.setColumns(10);
			
			textField_1 = new JTextField();
			//textField_1.setColumns(10);
			//textField_1.setBounds(176, 145, 130, 26);
			//frame.getContentPane().add(textField_1);
			
			garageBox = new JComboBox(CarShopController.getGarageList());
			
			//comboBox_1.setBounds(174, 178, 132, 27);
			//frame.getContentPane().add(comboBox_1);
			
			serviceComboBox = new JComboBox(CarShopController.getServiceList());
			
			serviceComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			//comboBox.setBounds(250, 44, 155, 27);
			//frame.getContentPane().add(comboBox);
			
			btnNewButton = new JButton("< Back");
			//btnNewButton.setBounds(0, 0, 76, 29);
			//frame.getContentPane().add(btnNewButton);
			
			submitButton = new JButton("Submit");
			
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						CarShopController.updateService(serviceComboBox.getSelectedItem().toString(), textField.getText(), textField_1.getText(), garageBox.getSelectedItem().toString());
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			});
			
			//btnNewButton_1.setBounds(315, 224, 117, 29);
			//frame.getContentPane().add(btnNewButton_1);
			
			
		
			servSectionLabel = new JLabel("Service Wizard");
			updateServiceLabel = new JLabel("Update Service");
			
			//****************************************************************************//
			
			Error = new JLabel();
			pageName = new JLabel();
			pageName.setText("Service Combo Wizard");
			
			//*Tiff's Service Combo***************************************************************//
			// for defining a service combo (owner only)
			createServiceComboLabel = new JLabel();
			createServiceComboLabel.setText("Create Service Combo");
			
			comboNameLabel = new JLabel();
			comboNameLabel.setText("Combo Name");
			comboName = new JTextField();
			comboMainServiceLabel = new JLabel();
			comboMainServiceLabel.setText("Combo Main Service");
			comboMainService = new JTextField();
			comboServicesLabel = new JLabel();
			comboServicesLabel.setText("Services in Combo");
			comboServices = new JTextField();
			comboMandLabel = new JLabel();
			comboMandLabel.setText("Mandatory Settings");
			comboMand = new JTextField();
			comboInstruct1 = new JLabel();
			comboInstruct1.setText("(Format: service1,service2,etc.)");
			comboInstruct2 = new JLabel();
			comboInstruct2.setText("(Format: true,false,etc.)");
			
			businessInfoLbl = new JLabel();
			businessInfoLbl.setText("Business Info");
			businessHourLbl = new JLabel();
			businessHourLbl.setText("Business Hours");
			
			// for updating a service combo (owner only)
			
			updateServiceComboLabel = new JLabel();
			updateServiceComboLabel.setText("Update Service Combo");
			
			updateComboOldNameLabel = new JLabel();
			updateComboOldNameLabel.setText("Combo to Change");
			
			updateComboOldName = new JTextField();
			
			updateComboNewNameLabel = new JLabel();
			updateComboNewNameLabel.setText("Combo New Name");
			
			updateComboNewName = new JTextField();
			
			updateComboMainServiceLabel = new JLabel();
			updateComboMainServiceLabel.setText("New Main Service");
			
			updateComboMainService = new JTextField();
			
			updateComboServicesLabel = new JLabel();
			updateComboServicesLabel.setText("Services in Combo");
			
			updateComboServices = new JTextField();
			
			updateComboMandLabel = new JLabel();
			updateComboMandLabel.setText("New Mandatory Settings");
			
			updateComboMand = new JTextField();
			
			updateComboInstruct1 = new JLabel();
			updateComboInstruct1.setText("(Format: service1,service2,etc.)");
			updateComboInstruct2 = new JLabel("(Format: true,false,etc.)");
			
			
			
			
			// The buttons for making/updating a SC
			createComboButton = new JButton();
			createComboButton.setText("Create Combo");
			
			updateComboButton = new JButton();
			updateComboButton.setText("Update Combo");
			
			//*Tiff's Appointment Management Buttons***************************************************************//
			// i.e. end appointment, declare noShows
			appointmentSectionLabel = new JLabel();
			appointmentSectionLabel.setText("Appointment");
			
			
			appointmentBoxLabel = new JLabel();
			appointmentBoxLabel.setText("Current Appointments");
			stringAppointmentBox = new JComboBox<String>();
			appointmentBox = new JComboBox<ToAppointment>();
			
			appointmentList = new HashMap<Integer, ToAppointment>();
			
			endAppointmentButton = new JButton();
			endAppointmentButton.setText("End Appointment");
			
			noShowsButton = new JButton();
			noShowsButton.setText("Declare No-Show");
			
			backButton = new JButton();
			backButton.setText("Back");
			
			startAppointmentButton = new JButton();
			startAppointmentButton.setText("Start Appointment");
			
			
			
			// set up actions
			createComboButton.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e) {
					createComboAction(e);
				}

				
			});
			
			updateComboButton.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					updateComboAction(e);
				}
			});
			
			startAppointmentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					startAppointmentAction(e);
				}
			});
			
			endAppointmentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					endAppointmentAction(e);
				}
			});
			
			noShowsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					noShowAction(e);
				}
			});
			
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					backAction(e);
				}
			});
			
			
			
			/*Ramin's stuff************************************************/
			// elements for error message
			errorMessage = new JLabel();
			errorMessage.setForeground(Color.RED);
			
			// elements for driver
			nameTxt = new JTextField();
			addressTxt = new JTextField();
			phoneNumTxt = new JTextField();
			emailTxt = new JTextField();
			bHourDayTxt = new JTextField();
			bHourStartTxt = new JTextField();
			bHourEndTxt = new JTextField();
			/*timeSlotStartDateTxt = new JTextField();
			timeSlotStartTimeTxt = new JTextField();
			timeSlotEndDateTxt = new JTextField();
			timeSlotEndTimeTxt = new JTextField();
			timeSlotTypeTxt = new JTextField();*/
			nameLbl = new JLabel();
			addressLbl = new JLabel();
			phoneNumLbl = new JLabel();
			emailLbl = new JLabel();
			businessLbl = new JLabel();
			if(CarShopApplication.getCarShop().getBusiness() != null) {
				businessLbl.setText("Name: " + CarShopApplication.getCarShop().getBusiness().getName() + ", " + "Address: " + CarShopApplication.getCarShop().getBusiness().getAddress() + ", " + "Phone Number: " + CarShopApplication.getCarShop().getBusiness().getPhoneNumber() + ", " + "Email: " + CarShopApplication.getCarShop().getBusiness().getEmail());
				businessLbl.setForeground(Color.magenta);
			}

			bHourDayLbl = new JLabel();
			bHourStartLbl = new JLabel();
			bHourEndLbl = new JLabel();
			/*timeSlotStartDateLbl = new JLabel();
			timeSlotStartTimeLbl = new JLabel();
			timeSlotEndDateLbl = new JLabel();
			timeSlotEndTimeLbl = new JLabel();
			timeSlotTypeLbl = new JLabel();*/
			nameLbl.setText("Name:");
			addressLbl.setText("Address:");
			phoneNumLbl.setText("Phone Number:");
			emailLbl.setText("Email:");
			
			bHourDayLbl.setText("Day:");
			bHourStartLbl.setText("Start Time:");
			bHourEndLbl.setText("End Time:");
			/*timeSlotStartDateLbl.setText("Start Date:");
			timeSlotStartTimeLbl.setText("Start Time:");
			timeSlotEndDateLbl.setText("End Date:");
			timeSlotEndTimeLbl.setText("End Time:");
			timeSlotTypeLbl.setText("Type:");*/
			setUpBusinessButton = new JButton();
			updateBusinessButton = new JButton();
			addBusinessHourButton = new JButton();
			updateBusinessHourButton = new JButton();
			removeBusinessHourButton = new JButton();
			/*addTimeSlotButton = new JButton();
			updateTimeSlotButton = new JButton();
			removeTimeSlotButton = new JButton();*/
			setUpBusinessButton.setText("Set Up Business Info");
			updateBusinessButton.setText("Update Business Info");
			addBusinessHourButton.setText("Add Business Hour");
			updateBusinessHourButton.setText("Update");
			removeBusinessHourButton.setText("Remove");
			/*addTimeSlotButton.setText("Add Time Slot");
			updateTimeSlotButton.setText("Update");
			removeTimeSlotButton.setText("Remove");*/
			
			daysOfWeek = new JComboBox<String>(new String[0]);
			daysOfWeek.addItem("Select a day of week");
			daysOfWeek.addItem("Monday");
			daysOfWeek.addItem("Tuesday");
			daysOfWeek.addItem("Wednesday");
			daysOfWeek.addItem("Thursday");
			daysOfWeek.addItem("Friday");
			daysOfWeek.addItem("Saturday");
			daysOfWeek.addItem("Sunday");
			
			/*timeSlotTypeList = new JComboBox<String>(new String[0]);
			timeSlotTypeList.addItem("Select a type of time slot");
			timeSlotTypeList.addItem("vacation");
			timeSlotTypeList.addItem("holiday");*/
			
			businessHourList = new JComboBox<String>(new String[0]);
			businessHourList.addItem("Pick one to update/remove");
			/*timeSlotList = new JComboBox<String>(new String[0]);
			timeSlotList.addItem("Pick one to update/remove");*/
			
			businessHourTable = new JTable(businessHourData, businessHourHeadings);

			scrollBHour = new JScrollPane(businessHourTable);
			this.add(scrollBHour);
			Dimension d = businessHourTable.getPreferredSize();
			scrollBHour.setPreferredSize(new Dimension(d.width, 100));
			scrollBHour.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			appointmentTable = new JTable(appointmentData, appointmentHeadings);

			scrollApp = new JScrollPane(appointmentTable);
			this.add(scrollApp);
			Dimension d2 = appointmentTable.getPreferredSize();
			scrollApp.setPreferredSize(new Dimension(d2.width, 100));
			scrollApp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			// global settings
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setTitle("Car Shop System");

			// listeners for driver
			setUpBusinessButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					setUpBusinessInfoActionPerformed(evt);
				}
			});
			updateBusinessButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					updateBusinessInfoActionPerformed(evt);
				}
			});
			
			addBusinessHourButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addBusinessHourActionPerformed(evt);
				}
			});

			updateBusinessHourButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					updateBusinessHourActionPerformed(evt);
				}
			});

			removeBusinessHourButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					removeBusinessHourActionPerformed(evt);
				}
			});
			

						
			/* Back to Tiff's Stuff*****************************************************/
			// add the title to the window
			this.setTitle("Owner Menu");			
			
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
						
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//*Tiff's + Ramin's Vertical Group***************************************************************//
			
			JSeparator hSeparator1 = new JSeparator(SwingConstants.HORIZONTAL);
			JSeparator hSeparator2 = new JSeparator(SwingConstants.HORIZONTAL);
			JSeparator hSeparator3 = new JSeparator(SwingConstants.HORIZONTAL);
			JSeparator vSeparator1 = new JSeparator(SwingConstants.VERTICAL);
			JSeparator verticalSeparator1 = new JSeparator(SwingConstants.VERTICAL);
			JSeparator verticalSeparator2 = new JSeparator(SwingConstants.VERTICAL);
			JSeparator verticalSeparator3 = new JSeparator(SwingConstants.VERTICAL);
			JSeparator seperatorLine1 = new JSeparator();
			JSeparator seperatorLine2 = new JSeparator();
			
			
			layout.setVerticalGroup(
					layout.createSequentialGroup()
					.addGroup(
					layout.createParallelGroup()
					.addGroup(
							layout.createParallelGroup()
							.addGroup(layout.createParallelGroup()
									.addGroup(layout.createSequentialGroup()
											//.addComponent(vSeparator1)
											.addComponent(errorMessage)
											.addGroup(layout.createParallelGroup()
													.addComponent(businessInfoLbl)
													.addComponent(businessLbl))
											.addGroup(layout.createParallelGroup()
													.addComponent(nameLbl)
													.addComponent(nameTxt)
													.addComponent(addressLbl)
													.addComponent(addressTxt)
													)
											.addGroup(layout.createParallelGroup()
													.addComponent(phoneNumLbl)
													.addComponent(phoneNumTxt)
													.addComponent(emailLbl)
													.addComponent(emailTxt)
													)
											.addGroup(layout.createParallelGroup()
													.addComponent(setUpBusinessButton)
													.addComponent(updateBusinessButton)
													)
											.addComponent(seperatorLine1)
											.addComponent(businessHourLbl)
											.addComponent(scrollBHour)
											.addGroup(layout.createParallelGroup()
													.addComponent(bHourDayLbl)
													.addComponent(daysOfWeek)
													.addComponent(addBusinessHourButton)
													)
											.addGroup(layout.createParallelGroup()
													.addComponent(bHourStartLbl)
													.addComponent(bHourStartTxt)
													.addComponent(businessHourList)
													)
											.addGroup(layout.createParallelGroup()
													.addComponent(bHourEndLbl)
													.addComponent(bHourEndTxt)
													.addComponent(updateBusinessHourButton)
													.addComponent(removeBusinessHourButton)
													)
											.addGroup(layout.createParallelGroup()
													.addComponent(seperatorLine2))
											)
									)
							)
					
					
					.addComponent(verticalSeparator1)
					.addGroup(
							layout.createSequentialGroup()
							.addComponent(pageName)
							.addComponent(Error)
							.addComponent(hSeparator1)
							.addGroup(
									layout.createParallelGroup()
									.addGroup(
											layout.createSequentialGroup()
											.addComponent(createServiceComboLabel)
											.addGroup(
													layout.createParallelGroup()
													.addGroup(
															layout.createSequentialGroup()
															.addComponent(comboNameLabel)
															.addComponent(comboMainServiceLabel)
															.addComponent(comboServicesLabel)
															)
													.addGroup(
															layout.createSequentialGroup()
															.addComponent(comboName)
															.addComponent(comboMainService)
															.addComponent(comboServices)
															)
													
													)
											.addComponent(comboInstruct1)
											.addGroup(
													layout.createParallelGroup()
													.addComponent(comboMandLabel)
													.addComponent(comboMand)
													)
											.addComponent(comboInstruct2)
											.addComponent(createComboButton)
											
											)
									// vertical separator
									.addComponent(vSeparator1)
									// second group for updating a combo
									.addGroup(
											layout.createSequentialGroup()
											.addComponent(updateServiceComboLabel)
											.addGroup(
													layout.createParallelGroup()
													.addGroup(
															layout.createSequentialGroup()
															.addComponent(updateComboOldNameLabel)
															.addComponent(updateComboNewNameLabel)
															.addComponent(updateComboMainServiceLabel)
															.addComponent(updateComboServicesLabel)
															)
													.addGroup(
															layout.createSequentialGroup()
															.addComponent(updateComboOldName)
															.addComponent(updateComboNewName)
															.addComponent(updateComboMainService)
															.addComponent(updateComboServices)
															)
													
													)
											.addComponent(updateComboInstruct1)
											.addGroup(
													layout.createParallelGroup()
													.addComponent(updateComboMandLabel)
													.addComponent(updateComboMand)
													)
											.addComponent(updateComboInstruct2)
											.addComponent(updateComboButton)
											
											)
												)
							
							
							)
					
					.addComponent(verticalSeparator3)
					// second horizontal separator
					
					.addGroup(
							layout.createSequentialGroup()
						.addComponent(servSectionLabel)
					  .addGroup(
					  layout.createSequentialGroup()
							.addComponent(addServiceLabel)
							.addGroup(layout.createParallelGroup()
									.addComponent(servNameLabel)
									.addComponent(servTextBox)
									)
							.addGroup(layout.createParallelGroup()
									.addComponent(durationLabel)
									.addComponent(durationTextBox)
									.addComponent(minutesLabel)
									)
							.addGroup(layout.createParallelGroup()
									.addComponent(garageBoxLabel)
									.addComponent(comboBox1)
									)
							.addComponent(subButton1)
							)
					  .addComponent(hSeparator3)
					  .addComponent(updateServiceLabel)
					  .addGroup(
							  layout.createParallelGroup()
							  // insert
							  	.addGroup(
							  			layout.createSequentialGroup()
							  			.addComponent(serviceBoxLabel)
							  			.addComponent(newServiceNameLabel)
							  			.addComponent(newDurationLabel)
							  			.addComponent(garageLabel)
							  			
							  			
							  			)
							  	.addGroup(
							  			layout.createSequentialGroup()
							  			.addComponent(serviceComboBox)
							  			.addComponent(textField)
							  			.addComponent(textField_1)
							  			.addComponent(garageBox)
							  			)
							  	
							  
							  )
					  			.addComponent(submitButton)
							)
					
					) 
					
							
					.addComponent(hSeparator2)
					
					
							
					.addGroup(layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
									.addComponent(appointmentSectionLabel)
									.addGroup(
											layout.createParallelGroup()
											.addComponent(appointmentBoxLabel)
											.addComponent(stringAppointmentBox)
											)
									.addGroup(
												layout.createParallelGroup()
												.addComponent(startAppointmentButton)
												.addComponent(endAppointmentButton)
												.addComponent(noShowsButton)
											)
									)
							.addComponent(scrollApp)
							)
					);
					
								
			
			//*Tiff's + Ramin's Horizontal Group***************************************************************//
			layout.setHorizontalGroup(
					layout.createParallelGroup()
					.addGroup(
					layout.createSequentialGroup()
					
					.addGroup(
							layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(errorMessage)
									.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup()
												.addComponent(nameLbl)
												.addComponent(phoneNumLbl)
												.addComponent(bHourDayLbl)
												.addComponent(bHourStartLbl)
												.addComponent(bHourEndLbl)
												/*.addComponent(timeSlotTypeLbl)
												.addComponent(timeSlotStartDateLbl)
												.addComponent(timeSlotStartTimeLbl)
												.addComponent(timeSlotEndDateLbl)
												.addComponent(timeSlotEndTimeLbl)*/
												)
										.addGroup(layout.createParallelGroup()
												.addComponent(setUpBusinessButton)
												.addComponent(nameTxt)
												.addComponent(phoneNumTxt)
												.addComponent(daysOfWeek)
												.addComponent(bHourStartTxt)
												.addComponent(bHourEndTxt)
												/*.addComponent(timeSlotTypeList)
												.addComponent(timeSlotStartDateTxt)
												.addComponent(timeSlotStartTimeTxt)
												.addComponent(timeSlotEndDateTxt)
												.addComponent(timeSlotEndTimeTxt)*/
												)
										.addGroup(layout.createParallelGroup()
												.addComponent(addressLbl)
												.addComponent(emailLbl))
										.addGroup(layout.createParallelGroup()
												.addComponent(addressTxt)
												.addComponent(emailTxt)
												.addComponent(updateBusinessButton)
												.addComponent(addBusinessHourButton)
												.addComponent(businessHourList)
												.addGroup(layout.createSequentialGroup()
														.addComponent(updateBusinessHourButton)
														.addComponent(removeBusinessHourButton))
												/*.addComponent(addTimeSlotButton)
												.addComponent(timeSlotList)
												.addGroup(layout.createSequentialGroup()
														.addComponent(updateTimeSlotButton)
														.addComponent(removeTimeSlotButton))*/
												)
									
									)
									.addGroup(layout.createSequentialGroup()
											.addComponent(businessInfoLbl)
											.addComponent(businessLbl))
									.addComponent(businessHourLbl)
									.addComponent(scrollBHour)
									.addComponent(seperatorLine1)
									.addComponent(seperatorLine2))
							)
					
					.addComponent(verticalSeparator1)
					.addGroup(
							layout.createParallelGroup()
							.addComponent(pageName)
							.addComponent(Error)
							.addComponent(hSeparator1)
							.addGroup(
									layout.createSequentialGroup()
										.addGroup(
												layout.createParallelGroup()
												.addComponent(createServiceComboLabel)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(comboNameLabel)
														.addComponent(comboName)
														)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(comboMainServiceLabel)
														.addComponent(comboMainService)
														)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(comboServicesLabel)
														.addComponent(comboServices)
														)
												.addComponent(comboInstruct1)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(comboMandLabel)
														.addComponent(comboMand)
														)
												.addComponent(comboInstruct2)
												.addComponent(createComboButton)
												)
										.addComponent(vSeparator1)
										.addGroup(
												layout.createParallelGroup()
												.addComponent(updateServiceComboLabel)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(updateComboOldNameLabel)
														.addComponent(updateComboOldName)
														)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(updateComboNewNameLabel)
														.addComponent(updateComboNewName)
														)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(updateComboMainServiceLabel)
														.addComponent(updateComboMainService)
														)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(updateComboServicesLabel)
														.addComponent(updateComboServices)
														)
												.addComponent(updateComboInstruct1)
												.addGroup(
														layout.createSequentialGroup()
														.addComponent(updateComboMandLabel)
														.addComponent(updateComboMand)
														)
												.addComponent(updateComboInstruct2)
												.addComponent(updateComboButton)
												)
									
									)
							
							
							)
					.addComponent(verticalSeparator3)
					
					//naths stuff *******************************************************/
					
					.addGroup(
							layout.createParallelGroup()
					.addComponent(servSectionLabel)
					 .addGroup(
					 layout.createParallelGroup()
					.addComponent(hSeparator2)
					.addComponent(addServiceLabel)

					.addGroup(
							layout.createSequentialGroup()
							.addComponent(servNameLabel)
							.addComponent(servTextBox)
							
							)
					.addGroup(
							layout.createSequentialGroup()							
							.addComponent(durationLabel)
							.addComponent(durationTextBox)
							.addComponent(minutesLabel)
							)
					.addGroup(
							layout.createSequentialGroup()
							.addComponent(garageBoxLabel)
							.addComponent(comboBox1)							
							)
					.addComponent(subButton1)
					)
					 .addComponent(hSeparator3)
					 .addComponent(updateServiceLabel)
					 .addGroup(
							 layout.createParallelGroup()
							 // insert here
							 .addGroup(
									 layout.createSequentialGroup()
									 .addComponent(serviceBoxLabel)
									 .addComponent(serviceComboBox)
									 )
							 
							 .addGroup(
									 layout.createSequentialGroup()
									 .addComponent(newServiceNameLabel)
									 .addComponent(textField_1)
									 )
							 .addGroup(
									 layout.createSequentialGroup()
									 .addComponent(newDurationLabel)
									 .addComponent(textField)
									 )
							 
							
							 .addGroup(
									 layout.createSequentialGroup()
									 .addComponent(garageLabel)
									 .addComponent(garageBox)
									 )						 
							 .addComponent(submitButton)
							 
							 )
					 
					 
					)
					
					
					
					)
					
					
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(appointmentSectionLabel)
									.addGroup(
											layout.createSequentialGroup()
											.addComponent(appointmentBoxLabel)
											.addComponent(stringAppointmentBox)							
											)
									.addGroup(
											layout.createSequentialGroup()
											.addComponent(startAppointmentButton)
											.addComponent(endAppointmentButton)
											.addComponent(noShowsButton)
											)
									)
							.addComponent(scrollApp)
							)
				
					);
					
					
					
					
			
			
			
			
			// set the sizes to be fixed to one-another
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboMandLabel, comboNameLabel,
					comboServicesLabel, comboMainServiceLabel});
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {comboMandLabel, comboNameLabel,
					comboServicesLabel, comboMainServiceLabel});
			
			//layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboMand, comboName,
			//		comboServices, comboMainService});
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {comboMand, comboName,
					comboServices, comboMainService});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {updateComboMandLabel, updateComboOldNameLabel,
					updateComboNewNameLabel, updateComboServicesLabel, updateComboMainServiceLabel});
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {updateComboMandLabel, updateComboOldNameLabel,
					updateComboNewNameLabel, updateComboServicesLabel, updateComboMainServiceLabel,
					updateComboMand, updateComboOldName, updateComboNewName, updateComboServices, updateComboMainService});
			
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {appointmentBoxLabel, startAppointmentButton, endAppointmentButton,
					noShowsButton, stringAppointmentBox});
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {startAppointmentButton, endAppointmentButton,
					noShowsButton});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {servTextBox, durationTextBox, appointmentBoxLabel});
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {servTextBox, durationTextBox, appointmentBoxLabel});
			
			
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {textField, textField_1, serviceBoxLabel,
																	serviceComboBox, newServiceNameLabel, newDurationLabel,
																	garageLabel, garageBox});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {serviceBoxLabel,
																serviceComboBox, newServiceNameLabel, newDurationLabel,
																garageLabel, garageBox});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {textField, textField_1, garageBox});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {stringAppointmentBox, daysOfWeek});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {servNameLabel,durationLabel,garageBoxLabel});
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {servNameLabel,durationLabel,garageBoxLabel});
			
			
			layout.linkSize(SwingConstants.VERTICAL, new Component[] {servNameLabel,durationLabel,garageBoxLabel,
					servTextBox,durationTextBox,comboBox1});
			
			layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {servTextBox,durationTextBox,comboBox1,
					garageBox});
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			
			pack();
		}
		
		public OwnerView() {
			initComponent();
			refreshPage();
		}
		

		//*Tiff's Action***************************************************************//
		private void createComboAction(ActionEvent e) {
			error = null;
			// if any of the slots are null
			if (comboName.getText().length() == 0
					|| comboMainService.getText().length() == 0
					|| comboServices.getText().length() == 0
					|| comboMand.getText().length() == 0) {
				error = "All fields must be filled.";
				refreshPage();
				return;				
			}			
			
			ToUser user = CarShopController.getCurrentToUser();
			// put the service name list into a string array
			ArrayList<String> servList = new ArrayList<>();
			String[] sCut = comboServices.getText().split(",");
			for (String s: sCut) {
				servList.add(s);
			}
			
			// transform the mandatory list into a boolean array
			ArrayList<Boolean> booList = new ArrayList<>();
			String[] bCut = comboMand.getText().split(",");
			for (String b: bCut) {
				booList.add(Boolean.parseBoolean(b));
			}
			
			try {
				// it is 'owner' temporarily. Eventually I'll use TOs instead and put that here to avoid importing the model
				CarShopController.defineServiceCombo(user.getUsername(), comboName.getText(), comboMainService.getText(), servList, booList);
			} catch (Exception er) {
				error = er.getMessage();
			}
			refreshPage();
		}
		
		//*Tiff's Action***************************************************************//
		private void updateComboAction(ActionEvent e) {
				error = null;
				ToUser user = CarShopController.getCurrentToUser();
				
				// if any of the slots are null
				if (updateComboOldName.getText().length() == 0
						|| updateComboNewName.getText().length() == 0
						|| updateComboMainService.getText().length() == 0
						|| updateComboServices.getText().length() == 0
						|| updateComboMand.getText().length() == 0) {
					error = "All fields must be filled.";
					refreshPage();
					return;				
				}	
				
				// put the service name list into a string array
				ArrayList<String> servList = new ArrayList<>();
				String[] sCut = updateComboServices.getText().split(",");
				for (String s: sCut) {
					servList.add(s);
				}
				
				// transform the mandatory list into a boolean array
				ArrayList<Boolean> booList = new ArrayList<>();
				String[] bCut = updateComboMand.getText().split(",");
				for (String b: bCut) {
					booList.add(Boolean.parseBoolean(b));
				}
				
				try {
					// it is 'owner' temporarily. Eventually I'll use TOs instead and put that here to avoid importing the model
					CarShopController.updateServiceCombo(user.getUsername(), updateComboOldName.getText(), updateComboNewName.getText(), 
							updateComboMainService.getText(), servList, booList);
				} catch (Exception er) {
					error = er.getMessage();
				}
				refreshPage();
		}
		
		//*Tiff's Action***************************************************************//
		private void  startAppointmentAction(ActionEvent e) {
			ToUser user = CarShopController.getCurrentToUser();
			
			if (stringAppointmentBox.getSelectedIndex() == 0) {
				error = "An appointment must be selected.";
				refreshPage();
				return;
			}
			
			try {
				CarShopController.startAppointment(CarShopController.ToUserToModelUser(user.getUsername()), 
																	CarShopController.curDateToString(), 
																	CarShopController.convertTOAppointmentToModelAppointment((ToAppointment) appointmentBox.getItemAt(stringAppointmentBox.getSelectedIndex() - 1)));
			} catch(Exception er) {
				error = er.getMessage();				
			}	
			refreshPage();
		}
		
		//*Tiff's Action***************************************************************//
		private void endAppointmentAction(ActionEvent e) {
			ToUser user = CarShopController.getCurrentToUser();
			
			if (stringAppointmentBox.getSelectedIndex() == 0) {
				error = "An appointment must be selected.";
				refreshPage();
				return;
			}
			
			try {
				CarShopController.endAppointment(CarShopController.ToUserToModelUser(user.getUsername()), 
									CarShopController.curDateToString(), 
									CarShopController.convertTOAppointmentToModelAppointment((ToAppointment) appointmentBox.getItemAt(stringAppointmentBox.getSelectedIndex() - 1)));
			} catch (Exception er) {
				error = er.getMessage();
			}
			refreshPage();
		}
		
		//*Tiff's Action***************************************************************//
		private void noShowAction(ActionEvent e) {
			
			ToUser user = CarShopController.getCurrentToUser();
			if (stringAppointmentBox.getSelectedIndex() == 0) {
				error = "An appointment must be selected.";
				refreshPage();
				return;
			}
			
			try {
				CarShopController.noShow(CarShopController.ToUserToModelUser(user.getUsername()), 
								CarShopController.curDateToString(),
								CarShopController.convertTOAppointmentToModelAppointment((ToAppointment) appointmentBox.getItemAt(stringAppointmentBox.getSelectedIndex() - 1)));
			} catch (Exception er) {
				error = er.getMessage();
			}
			refreshPage();
		}
		
		//*Tiff's Action***************************************************************//
		private void backAction(ActionEvent e) {
			System.exit(0);
		}
		
		
		//*Tiff's Action***************************************************************//
		private void refreshPage() {
			
			// reset all text boxes to null;
			
			Error.setText(error);

			errorMessage.setText(error);
			
			if (error == null || error.length() == 0) {
				comboName.setText("");
	
				comboMainService.setText("");
	
				comboServices.setText("");
	
				comboMand.setText("");
				
				updateComboOldName.setText("");
				
				updateComboNewName.setText("");
				
				updateComboMainService.setText("");
				
				updateComboServices.setText("");
				
				updateComboMand.setText("");
				
				// clears out the appointment boxes
				if (stringAppointmentBox.getItemCount() != 0) {
					stringAppointmentBox.removeAll();
				}
				
				stringAppointmentBox.addItem("Select An Appointment");
				if (appointmentBox.getItemCount() != 0) {
					appointmentBox.removeAll();
				}
				
				for (ToAppointment a: CarShopController.getAllAppointmentsToTOAppointment()) {
					stringAppointmentBox.addItem(a.getBookingName() + " at " + a.getStartTime() + " on " + a.getDay());
					appointmentBox.addItem(a);
				}
			
			}
			refreshData();
			pack();
		}
		
		
		/*Ramin's Methods:*/
		
		
		private void refreshData() {
			errorMessage.setText(error);
			if (error == null || error.length() == 0) {

				nameTxt.setText("");
				addressTxt.setText("");
				phoneNumTxt.setText("");
				emailTxt.setText("");
				bHourStartTxt.setText("");
				bHourEndTxt.setText("");
				/*timeSlotStartDateTxt.setText("");
				timeSlotStartTimeTxt.setText("");
				timeSlotEndDateTxt.setText("");
				timeSlotEndTimeTxt.setText("");
				timeSlotTypeList.setSelectedIndex(0);*/
				daysOfWeek.setSelectedIndex(0);
				businessHourList.setSelectedIndex(0);
				
				

				if(CarShopApplication.getCarShop().getBusiness() != null) {
					businessLbl.setText("Name: " + CarShopApplication.getCarShop().getBusiness().getName() + ", " + "Address: " + CarShopApplication.getCarShop().getBusiness().getAddress() + ", " + "Phone Number: " + CarShopApplication.getCarShop().getBusiness().getPhoneNumber() + ", " + "Email: " + CarShopApplication.getCarShop().getBusiness().getEmail());
					businessLbl.setForeground(Color.magenta);
				}
				for(int i = 1; i<businessHourList.getItemCount(); i++) {
					businessHourList.removeItemAt(i);
				}
				for(ToBusinessHour b: CarShopController.getBusinessHoursR()) {
					businessHourList.addItem(b.getDay() + "," + b.getStart() + "-" + b.getEnd());
				}
				
				
				/*for(int i = 0; i<timeSlotList.getItemCount(); i++) {
					timeSlotList.removeItemAt(i);
				}
				for(ToTimeSlot t: CarShopController.getTimeSlots()) {
					timeSlotList.addItem(t.getType() + "," + t.getStartD()+"@" + t.getStartT() + "-" + t.getEndD() + "@"+t.getEndT());
				}*/
				refreshTable();
				
			}
			pack();
		}
		
		private void refreshTable() {
			table = new DefaultTableModel(0, 0);
			table.setColumnIdentifiers(businessHourHeadings);
			businessHourTable.setModel(table);
			for (ToBusinessHour b : CarShopController.getBusinessHoursR()) {
				Object[] object = {b.getDay(), b.getStart(), b.getEnd()};
				table.addRow(object);
			}
			Dimension d = businessHourTable.getPreferredSize();
			scrollBHour.setPreferredSize(new Dimension(d.width, 100));
			
			table = new DefaultTableModel(0, 0);
			table.setColumnIdentifiers(appointmentHeadings);
			appointmentTable.setModel(table);
			for (ToAppointment a : CarShopController.getAllAppointmentsToTOAppointment()) {
				Object[] object = {a.getDay(), a.getStartTime(), a.getBookingName(), a.getCustomer()};
				table.addRow(object);
			}
			Dimension d1 = appointmentTable.getPreferredSize();
			scrollApp.setPreferredSize(new Dimension(d1.width, 100));
		}
		
		private void setUpBusinessInfoActionPerformed(java.awt.event.ActionEvent evt) {
			// clear error message and basic input validation
			error = "";
			boolean elementsAdded = !nameTxt.getText().isEmpty() && !addressTxt.getText().isEmpty() && !phoneNumTxt.getText().isEmpty() && !emailTxt.getText().isEmpty();
			if (!elementsAdded) {
				error = "Need to provide name, address, phone number and email of business!";
			}
			else {
				try {
					CarShopController.setUpBusinessInfo(nameTxt.getText(), addressTxt.getText(), phoneNumTxt.getText(), emailTxt.getText());
				}
				catch(Exception e) {
					error = e.getMessage();
				}
			}
			
			// update visuals
			refreshData();
		}
		
		private void updateBusinessInfoActionPerformed(java.awt.event.ActionEvent evt) {
			// clear error message and basic input validation
			error = "";
			boolean elementsAdded = !nameTxt.getText().isEmpty() || !addressTxt.getText().isEmpty() || !phoneNumTxt.getText().isEmpty() || !emailTxt.getText().isEmpty();
			if (!elementsAdded) {
				error = "Need to provide new name, address, phone number or email of business!";
			}
			else {
				try {
					CarShopController.updateBusinessInfo(nameTxt.getText(), addressTxt.getText(), phoneNumTxt.getText(), emailTxt.getText());
				}
				catch(Exception e) {
					error = e.getMessage();
				}
			}
			
			// update visuals
			refreshData();
		}

		private void addBusinessHourActionPerformed(java.awt.event.ActionEvent evt) {
			// clear error message and basic input validation
			error = "";
			boolean elementsAdded = !bHourStartTxt.getText().isEmpty() && !bHourEndTxt.getText().isEmpty() && !daysOfWeek.getSelectedItem().toString().equalsIgnoreCase("Select a day of week");
			if (!elementsAdded) {
				error = "Need to provide day, start time and end time of new business hour!";
			}
			else {
				try {
					Time startTime = Time.valueOf(bHourStartTxt.getText() + ":00");
					Time endTime = Time.valueOf(bHourEndTxt.getText() + ":00");
					System.out.println(CarShopApplication.getCarShop().getBusiness().getBusinessHours().size());
					CarShopController.addBusinessHours(daysOfWeek.getSelectedItem().toString(), startTime, endTime);
					System.out.println(CarShopApplication.getCarShop().getBusiness().getBusinessHours().size());
				}
				catch(Exception e) {
					error = e.getMessage();
				}
			}
			
			// update visuals
			refreshData();
		}
		
		private void updateBusinessHourActionPerformed(java.awt.event.ActionEvent evt) {
			// clear error message and basic input validation
			error = "";
			boolean elementsAdded = !bHourStartTxt.getText().isEmpty() && !bHourEndTxt.getText().isEmpty() && !daysOfWeek.getSelectedItem().toString().equalsIgnoreCase("Select a day of week");
			if (!elementsAdded) {
				error = "Need to provide new day, start time and end time of business hour!";
			}
			boolean selectedHour = !(businessHourList.getSelectedIndex() == 0);
			if(!selectedHour) {
				error = "Need to select business hour to update from drop down menu!";
			}
			else {
				try {
					Time startTime = Time.valueOf(bHourStartTxt.getText() + ":00");
					Time endTime = Time.valueOf(bHourEndTxt.getText() + ":00");
					String[] bHourComps = businessHourList.getSelectedItem().toString().split(",");
					String[] bHourTimes = bHourComps[1].split("-");
					Time origStart = Time.valueOf(bHourTimes[0]);
					CarShopController.updateBusinessHours(bHourComps[0], origStart, daysOfWeek.getSelectedItem().toString(), startTime, endTime);
				}
				catch(Exception e) {
					error = e.getMessage();
				}
			}
			
			// update visuals
			refreshData();
		}
		
		private void removeBusinessHourActionPerformed(java.awt.event.ActionEvent evt) {
			// clear error message and basic input validation
			error = "";
			boolean elementsAdded = !(businessHourList.getSelectedIndex() == 0);
			if (!elementsAdded) {
				error = "Need to select business hour to remove from drop down menu!";
			}
			else {
				try {
					String[] bHourComps = businessHourList.getSelectedItem().toString().split(",");
					String[] bHourTimes = bHourComps[1].split("-");
					Time origStart = Time.valueOf(bHourTimes[0]);
					CarShopController.removeBusinessHours(bHourComps[0], origStart);
				}
				catch(Exception e) {
					error = e.getMessage();
				}
			}
			
			// update visuals
			refreshData();
		}
		
	}
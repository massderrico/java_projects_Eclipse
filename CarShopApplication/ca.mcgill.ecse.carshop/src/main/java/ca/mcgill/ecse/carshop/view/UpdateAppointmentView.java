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
public class UpdateAppointmentView extends JFrame{
	private ToAppointment appointment;
	private List<JCheckBox> services;
	private JCheckBox individualService;
	private JButton submit;
	private JTextField serviceTxt, dateTxt, timeTxt;
	private ToServiceCombo serviceCombo;
	private ToService service;
	private JLabel serviceLbl, timeLbl, dateLbl;
	private String error;
	public UpdateAppointmentView(ToAppointment selectedAppointment) {
		appointment = selectedAppointment;
		initComponent();
	}
	private void initComponent() {
		service = null;
		serviceCombo = null;
		serviceCombo = CarShopController.searchServiceComboByName(appointment.getBookingName());
		service = CarShopController.searchServiceByName(appointment.getBookingName());
		if(serviceCombo == null && service == null) {
			error = "No service or service combo found";
		}
		String presetTxt = "";
		if(serviceCombo != null) {
			for(int i = 0; i < serviceCombo.getServices().length; i++) {
				presetTxt += serviceCombo.getService(i);
			}
		}
		
		serviceTxt.setText(presetTxt);
		
		submit = new JButton();
		submit.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					submitButtonActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void submitButtonActionPerformed(ActionEvent evt) throws Exception {
				// TODO Auto-generated method stub
				try {
					String[] times = timeTxt.getText().split(",");
					String[] services = serviceTxt.getText().split(",");
					if(timeTxt.getText().isEmpty() || !(times.length == services.length)) {
						error = "Need to input enough start times to match number of services";
						CarShopController.updateAppointmentTime(CarShopController.getRecentlyLoggedInUser(), dateTxt.getText(), timeTxt.getText(), CarShopApplication.getLocalDate().toString()+CarShopApplication.getLocalTime().toString(), serviceTxt.getText(), appointment);
					}
					
				}
				catch(Exception e) {
					error=e.getMessage();
				}
				//refreshAppointmentOverview();
			}
		});
		
		serviceLbl = new JLabel();
		serviceLbl.setText("Enter services (seperate by commas if needed): ");
		dateLbl = new JLabel();
		dateLbl.setText("Enter date for services (in format yyyy-mm-dd): ");
		timeLbl = new JLabel();
		timeLbl.setText("Enter start times for each service (seperated with commas if needed): ");
		
		dateTxt = new JTextField();
		timeTxt = new JTextField();
		
		this.setTitle("Update Appointment Page");			
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
					
		
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(serviceLbl)
						.addComponent(serviceTxt)
						.addComponent(dateLbl)
						.addComponent(dateTxt)
						.addComponent(timeLbl)
						.addComponent(timeTxt)
						.addComponent(submit)
						)
				);
				
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(serviceLbl)
						.addComponent(serviceTxt)
						.addComponent(dateLbl)
						.addComponent(dateTxt)
						.addComponent(timeLbl)
						.addComponent(timeTxt)
						.addComponent(submit)
						)
				);
		pack();
		
		
	}
}

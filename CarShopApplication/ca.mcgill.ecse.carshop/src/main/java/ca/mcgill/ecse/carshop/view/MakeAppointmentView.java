package ca.mcgill.ecse.carshop.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.sql.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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


import ca.mcgill.ecse.carshop.controller.*;

public class MakeAppointmentView extends JFrame {

	private static final long serialVersionUID = -4426310869335015542L;
	
	// UI elements
	private JLabel errorMessage;
	// appointment
	private JLabel makeAppLabel;
	private JButton makeAppointmentButton;
	private JButton cancelButton;
	private JButton selectServiceButton;
	private JLabel timeLabel;
	private ArrayList<JCheckBox> listService;
	private ArrayList<JTextField> listTime;
	private JComboBox<String> selectedBS;
	private HashMap<Integer, ToBookableService> bookableServices;
	private JLabel dateLabel;
	private JTextField dateTextField;
	private String selectedServices;
	private String selectedDate;
	private String selectedTimes;
	private String selectedBookableService;


	// data elements
	private String error = null;



	/** Creates new form BtmsPage */
	public MakeAppointmentView() {
		initComponents();
		refreshData();
	}

	/** This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements Chosen Bookable Service
		makeAppLabel=new JLabel();
		makeAppLabel.setText("Choose a bookable service: ");
		
		// Schedule an appointment
		makeAppointmentButton=new JButton();
		makeAppointmentButton.setText("Schedule an Appointment: ");
		
		// Pick a date
		dateLabel=new JLabel();
		dateLabel.setText("Enter a date using the format YY-[M]M-[D]D:");
		dateTextField=new JTextField();
		
		// Time Label
		timeLabel=new JLabel();
		timeLabel.setText("Enter a date using the format HH:MM");
		
		// Select a bookable service
		selectedBS=new JComboBox<String>(new String[0]);
		bookableServices=new HashMap<Integer,ToBookableService>();
		selectedServices="";
		
		// Select checked service
		selectServiceButton=new JButton();
		selectServiceButton.setText("Select service...");
		
		// Cancel button
//		cancelButton=new JButton();
//		cancelButton.setText("Cancel...");
		
		// List service
		listService=new ArrayList<JCheckBox>();
		
		// List time
		listTime=new ArrayList<JTextField>();
		
		
		int index=0;
		for(ToBookableService tbs:CarShopController.getToBookableService()) {
			bookableServices.put(index, tbs);
           selectedBS.addItem(tbs.getName());
            index++;
		};
		
	    selectedBS.setSelectedIndex(-1);
	   
		
	    
	    // Shows available services move  down????
	    selectedBS.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				selectedServiceActionPerformed(evt);
			}

			private void selectedServiceActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				error="";
				if(selectedBS.getSelectedIndex()<0) {
					error="Please select a service";
				}
				else {
					for(int i=0; i<bookableServices.get(selectedBS.getSelectedIndex()).getService().split(",").length;i++) {
						String info=bookableServices.get(selectedBS.getSelectedIndex()).getService().split(",")[i]+", "+
					    CarShopController.getToServiceDurationWithName(bookableServices.get(selectedBS.getSelectedIndex()).getService().split(",")[i])+", "
					    +bookableServices.get(selectedBS.getSelectedIndex()).getMandatory().split(",")[i];
						JCheckBox service=new JCheckBox();
						JTextField time=new JTextField();
						listService.add(service);
						listTime.add(time);
						service.setText(info);
						
						if(bookableServices.get(selectedBS.getSelectedItem()).getMandatory().split(",")[i].contains("true")) {
							service.setSelected(true);
							service.setText("*"+info);
							service.setForeground(Color.GRAY);
						}
						else ;
					}
				}
				
			}
		});
	    
	    
    	// horizontal line elements
 		JSeparator horizontalLineTop = new JSeparator();
 		JSeparator horizontalLineMiddle1 = new JSeparator();
 		JSeparator horizontalLineMiddle2 = new JSeparator();
 		JSeparator horizontalLineBottom = new JSeparator();
 		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		this.add(dateLabel,layout);
		this.add(dateTextField,layout);
		this.add(makeAppLabel,layout);
		this.add(selectedBS,layout);
		for(int i=0;i<listTime.size();i++) {
			this.add(listService.get(i),layout);
			this.add(listTime.get(i),layout);
		}
	    this.add(selectServiceButton,layout);
	    this.add(makeAppointmentButton,layout);
	    
	    
	    
	    
	    
	    // Add action listener
	    selectServiceButton.addActionListener(new java.awt.event.ActionListener() {
	    	public void actionPerformed(java.awt.event.ActionEvent evt) {
	    		selectServiceButtonActionPerformed(evt);
	    	}
	    });
		
		
		// Add Action Listener
		makeAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				makeAppointmentButtonActionPerformed(evt);
			}
		});
	}



	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			for(JTextField d:listTime) {
				d.setText("");
			}
			dateTextField.setText("");
			selectedBS.setSelectedIndex(-1);
		}
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	
	
	
	private void makeAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error=null;
		for(JTextField t:listTime) {
			if(selectedTimes.isEmpty()) {
				selectedTimes+=t.getText();
			}
			else {
				selectedTimes+=","+t.getText();
			}
		}
		try {
			CarShopController.MakeToAppointment(selectedDate, selectedBookableService, selectedServices, selectedTimes);
		}catch(InvalidInputException e){
			error=e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void selectServiceButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		for(JCheckBox c:listService) {
			if(c.isSelected() && !listTime.isEmpty()) {
				if(selectedServices.isEmpty()) {
					selectedServices+=c.getText();
				}
				else {
					selectedServices+=","+c.getText();
				}
			}
			else {
				error="Please pick a date AND a corresponding timeslot";
				errorMessage.setText(error);;
			}
		}
		refreshData();
	}
}

package ca.mcgill.ecse.carshop.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ca.mcgill.ecse.carshop.application.CarShopApplication;
import ca.mcgill.ecse.carshop.controller.*;
import ca.mcgill.ecse.carshop.model.User;




public class UpdateAccountView extends JFrame {

	private String error;
	private JLabel updateAccountErrorMessage;
	private JLabel username;
	private JLabel password;
	private JTextField usertext;
	private JPasswordField passtext;
	private JButton updateAccount;
	private JLabel updateAccountLabel;
	private JButton backButton;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					UpdateAccountView frame = new UpdateAccountView();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public UpdateAccountView() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);

		getContentPane().setLayout(null);

		username = new JLabel("Username");
		username.setBounds(68, 97, 102, 16);
		getContentPane().add(username);

		password = new JLabel("Password");
		password.setBounds(68, 140, 102, 16);
		getContentPane().add(password);

		usertext = new JTextField();
		usertext.setBounds(182, 92, 210, 26);
		getContentPane().add(usertext);
		usertext.setColumns(10);

		passtext = new JPasswordField();
		passtext.setBounds(182, 135, 210, 26);
		getContentPane().add(passtext);
		passtext.setColumns(10);

		updateAccount = new JButton("Update Account");
		updateAccount.setBounds(68, 176, 324, 29);
		getContentPane().add(updateAccount);

		updateAccountLabel = new JLabel("Change Username/Password");
		updateAccountLabel.setBounds(68, 62, 324, 16);
		getContentPane().add(updateAccountLabel);

		updateAccountErrorMessage = new JLabel("");
		updateAccountErrorMessage.setBounds(68, 34, 324, 16);
		getContentPane().add(updateAccountErrorMessage);

		backButton = new JButton("Back");
		backButton.setBounds(6, 243, 117, 29);
		getContentPane().add(backButton);

		backButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				backButtonActionPerformed(evt);
			}});

		updateAccount.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateAccountButtonActionPerformed(evt);
			}});

	}

	private void refreshData() {
		// error

		updateAccountErrorMessage.setText(error);
		usertext.setText("");
		passtext.setText("");
		// route
	}
	private void backButtonActionPerformed(ActionEvent evt) {
		// clear error message


		if(CarShopController.getRecentlyLoggedInUser().contains("Technician")) {
			this.setVisible(false);
			JFrame frame= TechnicianView.getFrame();
			frame.setVisible(true);
		}
		else {
			error = null;
			this.setVisible(false);
		}
	}

	private void updateAccountButtonActionPerformed(ActionEvent evt) {
		// clear error message
		error = null;

		// call the controller
		try {
			CarShopController.updateAccount(usertext.getText(), passtext.getText());
			updateAccountErrorMessage.setForeground(Color.green);
			error = "Account Changes made";


		} 
		catch (Exception e) {
			error = e.getMessage();
			updateAccountErrorMessage.setForeground(Color.red);
		}
		refreshData();
	}




}


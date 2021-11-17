package ca.mcgill.ecse.carshop.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ca.mcgill.ecse.carshop.controller.CarShopController;
import ca.mcgill.ecse.carshop.controller.InvalidInputException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class LogInView extends JFrame{
	private static final long serialVersionUID = -4426310869335015542L;
	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField userNameTextField;
	private JButton logInButton;
	private JLabel messageLabel;
	private JButton signupButton;
	public static TechnicianView technicianView;
	public static OwnerView ownerView;
	public static CustomerView customerView;
	public LogInView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().getContentPane().setEnabled(false);
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		getFrame().setTitle("CarShop Application");

		JLabel userNameLabel = new JLabel("Username:");
		userNameLabel.setBounds(48, 85, 118, 22);
		getFrame().getContentPane().add(userNameLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(136, 130, 187, 22);
		getFrame().getContentPane().add(passwordField);
		
		
		//massimo
		signupButton = new JButton("Sign Up");
		signupButton.setBounds(76, 187, 118, 24);
		//getContentPane().add(signupButton);
getFrame().getContentPane().add(signupButton);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(48, 123, 108, 35);
		getFrame().getContentPane().add(passwordLabel);

		userNameTextField = new JTextField();
		userNameTextField.setBounds(136, 85, 177, 22);
		getFrame().getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		//userNameTextField.setText(getName());
		
		//sign up btn
		signupButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				signUpButtonActionPerformed(evt);
			}});

		logInButton = new JButton("Login");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CarShopController.login(userNameTextField.getText(), passwordField.getText());
					
					//User is successfully logged in
					//For Technicians
					if(userNameTextField.getText().contains("Technician")) {
						technicianView = new TechnicianView();
						technicianView.setVisible(true);
						technicianView.getFrame().setVisible(true);
						getFrame().setVisible(false);
					}
					//For the owner
					else if(userNameTextField.getText().equals("owner")){
						ownerView = new OwnerView();
						
						ownerView.setVisible(true);
						getFrame().setVisible(false);
						//messageLabel.setText("You are a the owner :)");
					}
					//For a customer
					else {
						try {
							customerView = new CustomerView();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						customerView.setVisible(true);
						getFrame().setVisible(false);
					}

				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}


			}
		});
		logInButton.setBounds(206, 186, 117, 25);
		getFrame().getContentPane().add(logInButton);

		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.GREEN);
		messageLabel.setBounds(58, 164, 350, 15);
		getFrame().getContentPane().add(messageLabel);
		
		signupButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				signUpButtonActionPerformed(evt);
			}});
	}

	
	private void signUpButtonActionPerformed(ActionEvent evt) {
		// clear error message
	//	error = null;
		
		// call the controller
		try {
			CarShopController.signUp(userNameTextField.getText(), passwordField.getText());
			//signUpErrorMessage.setForeground(Color.green);
			//signUpErrorMessage.setText("New Account Created");
			//System.out.println(User.getWithUsername(userNameTextField.getText()).toString());
			//userNameTextField.setText("");
			//passwordField.setText("");
		
		} 
		catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e.getMessage());
			//error = e.getMessage();
//			signUpErrorMessage.setForeground(Color.red);;
//			signUpErrorMessage.setText(error);
		}
	}
	
	public void setPasswordField() {
		passwordField.setText(null);
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

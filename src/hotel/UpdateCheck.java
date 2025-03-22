package hotel;

import java.awt.BorderLayout;
import java.awt.*;

import java.sql.*;	
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCheck extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_Status;
	private JTextField txt_Date;
	private JTextField txt_Time;
	private JTextField txt_Payment;

    Choice c1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCheck frame = new UpdateCheck();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close(){
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UpdateCheck() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Modern color palette
		Color primaryColor = new Color(41, 128, 185); // Blue
		Color secondaryColor = new Color(52, 152, 219); // Lighter blue
		Color accentColor = new Color(231, 76, 60);    // Red accent
		Color bgColor = new Color(236, 240, 241);      // Light gray background
		Color textColor = new Color(44, 62, 80);       // Dark text
		
		// Set frame background
		getContentPane().setBackground(bgColor);
		
		// Create a header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(primaryColor);
		headerPanel.setBounds(0, 0, 950, 60);
		headerPanel.setLayout(null);
		contentPane.add(headerPanel);
		
		JLabel lblUpdateCheckStatus = new JLabel("Guest Check-In Details");
		lblUpdateCheckStatus.setForeground(Color.WHITE);
		lblUpdateCheckStatus.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblUpdateCheckStatus.setBounds(25, 15, 300, 30);
		headerPanel.add(lblUpdateCheckStatus);
                
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        Image img = i1.getImage().getScaledInstance(476, 270, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel l1 = new JLabel(scaledIcon);
        l1.setBounds(450, 80, 476, 270);
        contentPane.add(l1);
		
		// Create form panel with rounded corners
		JPanel formPanel = new JPanel();
		formPanel.setBackground(Color.WHITE);
		formPanel.setBounds(15, 75, 420, 320);
		formPanel.setLayout(null);
		formPanel.setBorder(new CompoundBorder(new LineBorder(primaryColor, 1), new EmptyBorder(20, 20, 20, 20)));
		contentPane.add(formPanel);
		
		JLabel lblNewLabel = new JLabel("Guest ID:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setForeground(textColor);
		lblNewLabel.setBounds(20, 20, 120, 25);
		formPanel.add(lblNewLabel);
                
        c1 = new Choice();
        c1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        c1.setBackground(Color.WHITE);
        c1.setForeground(textColor);
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                c1.add(rs.getString("number"));    
            }
        }catch(Exception e){ }
        c1.setBounds(170, 20, 220, 25);
		formPanel.add(c1);
		
		JLabel lblNewLabel_1 = new JLabel("Room Number:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(textColor);
		lblNewLabel_1.setBounds(20, 60, 120, 25);
		formPanel.add(lblNewLabel_1);
                
        txt_ID = new JTextField();
        txt_ID.setBounds(170, 60, 220, 30);
        txt_ID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt_ID.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txt_ID);
		
		JLabel lblNewLabel_2 = new JLabel("Guest Name:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(textColor);
		lblNewLabel_2.setBounds(20, 100, 120, 25);
		formPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Check-in Status:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(textColor);
		lblNewLabel_3.setBounds(20, 140, 120, 25);
		formPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount Paid:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_4.setForeground(textColor);
		lblNewLabel_4.setBounds(20, 180, 120, 25);
		formPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pending Amount:");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_5.setForeground(textColor);
		lblNewLabel_5.setBounds(20, 220, 120, 25);
		formPanel.add(lblNewLabel_5);
		
		txt_Status = new JTextField();
		txt_Status.setBounds(170, 100, 220, 30);
		txt_Status.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Status.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txt_Status);
		
		txt_Date = new JTextField();
		txt_Date.setBounds(170, 140, 220, 30);
		txt_Date.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Date.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txt_Date);
		
		txt_Time = new JTextField();
		txt_Time.setBounds(170, 180, 220, 30);
		txt_Time.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Time.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txt_Time);
		
		txt_Payment = new JTextField();
		txt_Payment.setBounds(170, 220, 220, 30);
		txt_Payment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Payment.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		formPanel.add(txt_Payment);
		
		// Button panel with proper alignment
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(bgColor);
		buttonPanel.setBounds(15, 410, 910, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		contentPane.add(buttonPanel);
		
		// Apply modern button styling using a helper method
		JButton btnCheck = createStyledButton("Retrieve Details", secondaryColor);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try{
                    String s1 = c1.getSelectedItem();
                    conn c = new conn();
                    ResultSet rs1 = c.s.executeQuery("select * from customer where number = "+s1);
                    
                    while(rs1.next()){
                        txt_ID.setText(rs1.getString("room_number"));    
                        txt_Status.setText(rs1.getString("name"));    
                        txt_Date.setText(rs1.getString("status"));    
                        txt_Time.setText(rs1.getString("deposit"));    
                    }
                }catch(Exception ee){}
                
                try{
                    String total = "";
                    conn c  = new conn();
                    ResultSet rs2 = c.s.executeQuery("select * from room where room_number = "+txt_ID.getText());
                    while(rs2.next()){
                        total = rs2.getString("price"); 
                        
                    }
                    String paid = txt_Time.getText();
                    int pending = Integer.parseInt(total)- Integer.parseInt(paid);
                    
                    txt_Payment.setText(Integer.toString(pending));
                    
                }catch(Exception ee){}
			}
		});
		buttonPanel.add(btnCheck);
		
		JButton btnUpdate = createStyledButton("Save Changes", primaryColor);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
                try{
                    conn c = new conn();
                    
                    String s1 = c1.getSelectedItem();
                    String s2 = txt_ID.getText(); //room_number;    
                    String s3 = txt_Status.getText(); //name    
                    String s4 = txt_Date.getText(); //status;    
                    String s5 = txt_Time.getText(); //deposit    
                    
                    c.s.executeUpdate("update customer set room_number = '"+s2+"', name = '"+s3+"', status = '"+s4+"', deposit = '"+s5+"' where number = '"+s1+"'");
                    
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                    new Reception().setVisible(true);
                    setVisible(false);
                }catch(Exception ee){
                    System.out.println(ee);
                }				
			}
		});
		buttonPanel.add(btnUpdate);
		
		JButton btnExit = createStyledButton("Back to Reception", accentColor);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
                setVisible(false);
			}
		});
		buttonPanel.add(btnExit);
	}
	
	// Helper method to create consistently styled buttons
	private JButton createStyledButton(String text, Color bgColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setBackground(bgColor);
		button.setForeground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(180, 35));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Add hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(bgColor.darker());
			}
			
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(bgColor);
			}
		});
		
		return button;
	}
}
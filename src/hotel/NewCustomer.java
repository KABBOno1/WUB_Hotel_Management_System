package hotel;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCustomer extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField t1,t2,t3,t4,t5,t6;
        JComboBox comboBox;
        JRadioButton r1,r2;
        Choice c1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer frame = new NewCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCustomer() throws SQLException {
		
                setBounds(530, 200, 850, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                // Set modern look and feel
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Set a gradient background panel
                JPanel backgroundPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        int w = getWidth(), h = getHeight();
                        Color color1 = new Color(240, 248, 255);
                        Color color2 = new Color(230, 230, 250);
                        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                        g2d.setPaint(gp);
                        g2d.fillRect(0, 0, w, h);
                    }
                };
                backgroundPanel.setBounds(0, 0, 850, 550);
                backgroundPanel.setLayout(null);
                contentPane.add(backgroundPanel);
                
                // Add form panel with slight shadow for better content organization
                JPanel formPanel = new JPanel();
                formPanel.setLayout(null);
                formPanel.setBounds(30, 70, 410, 420);
                formPanel.setBackground(new Color(255, 255, 255, 180));
                formPanel.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200)), 
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                backgroundPanel.add(formPanel);
                
                ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
                Image i3 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(500, 70, 300, 420);
                backgroundPanel.add(l1);
		
		JLabel lblName = new JLabel("NEW CUSTOMER FORM");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblName.setForeground(new Color(45, 62, 80));
		lblName.setBounds(30, 20, 300, 40);
		backgroundPanel.add(lblName);
                
                // Consistent label alignment, width and positioning
                int labelX = 25;
                int fieldX = 185;
                int startY = 25;
                int rowHeight = 45;
                int labelWidth = 160;
                int fieldWidth = 180;
                int fieldHeight = 30;
                
                JLabel lblId = new JLabel("ID Type:");
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblId.setForeground(new Color(45, 62, 80));
		lblId.setBounds(labelX, startY, labelWidth, 25);
		formPanel.add(lblId);
                
                comboBox = new JComboBox(new String[] {"Passport", "Voter Id", "Driving license"});
                comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                comboBox.setBounds(fieldX, startY, fieldWidth, fieldHeight);
                comboBox.setBackground(Color.WHITE);
                comboBox.setBorder(new LineBorder(new Color(200, 200, 200)));
		formPanel.add(comboBox);
                
                JLabel l2 = new JLabel("Number:");
                l2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		l2.setForeground(new Color(45, 62, 80));
		l2.setBounds(labelX, startY + rowHeight, labelWidth, 25);
		formPanel.add(l2);
                
                t1 = new JTextField();
                t1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t1.setBounds(fieldX, startY + rowHeight, fieldWidth, fieldHeight);
		t1.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 5, 2, 5)));
		formPanel.add(t1);
		t1.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblName_1.setForeground(new Color(45, 62, 80));
		lblName_1.setBounds(labelX, startY + rowHeight*2, labelWidth, 25);
		formPanel.add(lblName_1);
		
		t2 = new JTextField();
		t2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t2.setBounds(fieldX, startY + rowHeight*2, fieldWidth, fieldHeight);
		t2.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 5, 2, 5)));
		formPanel.add(t2);
		t2.setColumns(10);
               
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblGender.setForeground(new Color(45, 62, 80));
		lblGender.setBounds(labelX, startY + rowHeight*3, labelWidth, 25);
		formPanel.add(lblGender);
                
                ButtonGroup genderGroup = new ButtonGroup();
                
                JPanel radioPanel = new JPanel();
                radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
                radioPanel.setBounds(fieldX, startY + rowHeight*3, fieldWidth, fieldHeight);
                radioPanel.setBackground(new Color(0, 0, 0, 0));
                formPanel.add(radioPanel);
                
                r1 = new JRadioButton("Male");
                r1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                r1.setBackground(new Color(0, 0, 0, 0));
                r1.setForeground(new Color(45, 62, 80));
                radioPanel.add(r1);
                
                r2 = new JRadioButton("Female");
                r2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                r2.setBackground(new Color(0, 0, 0, 0));
                r2.setForeground(new Color(45, 62, 80));
                radioPanel.add(r2);
                
                genderGroup.add(r1);
                genderGroup.add(r2);
                
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCountry.setForeground(new Color(45, 62, 80));
		lblCountry.setBounds(labelX, startY + rowHeight*4, labelWidth, 25);
		formPanel.add(lblCountry);
		
		t3 = new JTextField();
		t3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t3.setBounds(fieldX, startY + rowHeight*4, fieldWidth, fieldHeight);
		t3.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 5, 2, 5)));
		formPanel.add(t3);
		t3.setColumns(10);
		
		JLabel lblReserveRoomNumber = new JLabel("Allocated Room:");
		lblReserveRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblReserveRoomNumber.setForeground(new Color(45, 62, 80));
		lblReserveRoomNumber.setBounds(labelX, startY + rowHeight*5, labelWidth, 25);
		formPanel.add(lblReserveRoomNumber);
                
                // Style the Choice component to match other fields
                JPanel choicePanel = new JPanel();
                choicePanel.setLayout(new BorderLayout());
                choicePanel.setBounds(fieldX, startY + rowHeight*5, fieldWidth, fieldHeight);
                choicePanel.setBorder(new LineBorder(new Color(200, 200, 200)));
                choicePanel.setBackground(Color.WHITE);
                formPanel.add(choicePanel);
                
                c1 = new Choice();
                c1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                c1.setBackground(Color.WHITE);
                c1.setForeground(new Color(45, 62, 80));
                try{
                    conn c = new conn();
                    ResultSet rs = c.s.executeQuery("select * from room");
                    while(rs.next()){
                        c1.add(rs.getString("room_number"));    
                    }
                }catch(Exception e){ }
                choicePanel.add(c1, BorderLayout.CENTER);
		
		JLabel lblCheckInStatus = new JLabel("Checked-In:");
		lblCheckInStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCheckInStatus.setForeground(new Color(45, 62, 80));
		lblCheckInStatus.setBounds(labelX, startY + rowHeight*6, labelWidth, 25);
		formPanel.add(lblCheckInStatus);
		
		t5 = new JTextField();
		t5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t5.setBounds(fieldX, startY + rowHeight*6, fieldWidth, fieldHeight);
		t5.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 5, 2, 5)));
		formPanel.add(t5);
		t5.setColumns(10);
		
		JLabel lblDeposite = new JLabel("Deposit:");
		lblDeposite.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDeposite.setForeground(new Color(45, 62, 80));
		lblDeposite.setBounds(labelX, startY + rowHeight*7, labelWidth, 25);
		formPanel.add(lblDeposite);
		
		t6 = new JTextField();
		t6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t6.setBounds(fieldX, startY + rowHeight*7, fieldWidth, fieldHeight);
		t6.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200)), new EmptyBorder(2, 5, 2, 5)));
		formPanel.add(t6);
		t6.setColumns(10);

                // Center the buttons for better alignment
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
                buttonPanel.setBounds(0, startY + rowHeight*8, 410, 50);
                buttonPanel.setBackground(new Color(0, 0, 0, 0));
                formPanel.add(buttonPanel);
                
		// Improved Add button with modern styling
		JButton btnNewButton = new JButton("Add Customer");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            conn c = new conn();
                            String radio = null;
                            
                            if(r1.isSelected()){ 
                                radio = "Male";
                            }
                            else if(r2.isSelected()){ 
                                radio = "Female";
                            }
                            
                            String s6 = c1.getSelectedItem();
                          
                            try{
	    			
                                String s1 = (String)comboBox.getSelectedItem(); 
	    			String s2 =  t1.getText();
	    			String s3 =  t2.getText();
                                String s4 =  radio;
	    			String s5 =  t3.getText();
	    			String s7 =  t5.getText();
                                String s8 =  t6.getText();
                                
                                String q1 = "insert into customer values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
                                String q2 = "update room set availability = 'Occupied' where room_number = "+s6;
                                c.s.executeUpdate(q1);
                                c.s.executeUpdate(q2);
	    			
	    			
	    			JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                                new Reception().setVisible(true);
                                setVisible(false);
	    		}catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
		    		catch(NumberFormatException s){
		    			JOptionPane.showMessageDialog(null, "Please enter a valid Number");
			}
			}
		});
		btnNewButton.setPreferredSize(new Dimension(140, 35));
                btnNewButton.setBackground(new Color(70, 130, 180));
                btnNewButton.setForeground(Color.WHITE);
                btnNewButton.setBorder(new EmptyBorder(5, 10, 5, 10));
                btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnNewButton.setFocusPainted(false);
		buttonPanel.add(btnNewButton);
		
		// Improved Back button with modern styling
		JButton btnExit = new JButton("Back");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            new Reception().setVisible(true);
                            setVisible(false);
			}
		}); 
		btnExit.setPreferredSize(new Dimension(140, 35));
                btnExit.setBackground(new Color(211, 211, 211));
                btnExit.setForeground(new Color(45, 62, 80));
                btnExit.setBorder(new EmptyBorder(5, 10, 5, 10));
                btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnExit.setFocusPainted(false);
		buttonPanel.add(btnExit);
                
                // Add hover effect for buttons
                btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        btnNewButton.setBackground(new Color(100, 149, 237));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        btnNewButton.setBackground(new Color(70, 130, 180));
                    }
                });
                
                btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        btnExit.setBackground(new Color(192, 192, 192));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        btnExit.setBackground(new Color(211, 211, 211));
                    }
                });
                
                setUndecorated(false);
                setResizable(false);
                setTitle("New Customer Registration");
	}
}
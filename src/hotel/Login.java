package hotel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    JLabel l1, l2, titleLabel;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;
    JPanel mainPanel, formPanel, imagePanel;
    
    Login() {
        super("Hotel Management System - Login");
        
        // Set a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Main container with BorderLayout
        setLayout(new BorderLayout());
        
        // Create main panel with gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(240, 248, 255);
                Color color2 = new Color(230, 230, 250);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Title at top
        titleLabel = new JLabel("Hotel Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 64, 95));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Form panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Username label and field
        l1 = new JLabel("Username");
        l1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        formPanel.add(l1, gbc);
        
        t1 = new JTextField(15);
        t1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t1.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(204, 204, 204), 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(t1, gbc);
        
        // Password label and field
        l2 = new JLabel("Password");
        l2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(l2, gbc);
        
        t2 = new JPasswordField(15);
        t2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t2.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(204, 204, 204), 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(t2, gbc);
        
        // Panel for buttons with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        
        // Login button
        b1 = new JButton("Login");
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(70, 130, 180));
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);
        b1.setPreferredSize(new Dimension(120, 40));
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.addActionListener(this);
        buttonPanel.add(b1);
        
        // Cancel button
        b2 = new JButton("Cancel");
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(180, 70, 70));
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);
        b2.setPreferredSize(new Dimension(120, 40));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.addActionListener(this);
        buttonPanel.add(b2);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 10, 10, 10);
        formPanel.add(buttonPanel, gbc);
        
        // Image panel
        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        
        // Load image with proper error handling
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
            Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel l3 = new JLabel(i3);
            l3.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
            imagePanel.add(l3, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel noImageLabel = new JLabel("Image not found");
            noImageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            noImageLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(noImageLabel, BorderLayout.CENTER);
        }
        
        // Add components to main panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(imagePanel, BorderLayout.EAST);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
        
        // Window settings
        setSize(650, 400);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1) {
            try {
                conn c1 = new conn();
                String u = t1.getText();
                String v = t2.getText();
                
                String q = "select * from login where username='"+u+"' and password='"+v+"'";
                
                ResultSet rs = c1.s.executeQuery(q); 
                if(rs.next()){ 
                    new Dashboard().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
                    t1.setText("");
                    t2.setText("");
                    t1.requestFocus();
                }
            } catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(ae.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] arg) {
        new Login();
    }
}
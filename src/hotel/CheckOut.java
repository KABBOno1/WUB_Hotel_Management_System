package hotel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class CheckOut extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    private JPanel contentPane;
    private JTextField t1;
    Choice c1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CheckOut frame = new CheckOut();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void close() {
        this.dispose();
    }

    /**
     * Create the frame.
     * @throws SQLException 
     */
    public CheckOut() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 850, 400);
        setTitle("Guest Check Out");
        
        // Create main panel with gradient background
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(240, 248, 255);
                Color color2 = new Color(202, 228, 241);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(20, 20));
        
        // Left panel for form
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        contentPane.add(leftPanel, BorderLayout.WEST);
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        leftPanel.add(titlePanel);
        
        JLabel lblCheckOut = new JLabel("Guest Check Out");
        lblCheckOut.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblCheckOut.setForeground(new Color(30, 80, 130));
        titlePanel.add(lblCheckOut);
        
        leftPanel.add(Box.createVerticalStrut(30));
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(2, 1, 15, 20));
        leftPanel.add(formPanel);
        
        // Room number selection panel
        JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numberPanel.setOpaque(false);
        formPanel.add(numberPanel);
        
        JLabel lblName = new JLabel("Guest Number:");
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        numberPanel.add(lblName);
        
        c1 = new Choice();
        c1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        c1.setBackground(Color.WHITE);
        c1.setForeground(Color.DARK_GRAY);
        
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()) {
                c1.add(rs.getString("number"));    
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        numberPanel.add(c1);
        
        JButton searchButton = new JButton("Find");
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(new CompoundBorder(
                new LineBorder(new Color(70, 130, 180).darker(), 1),
                new EmptyBorder(5, 10, 5, 10)));
        numberPanel.add(searchButton);
        
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    conn c = new conn();
                    String number = c1.getSelectedItem();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = " + number);
                    
                    if(rs.next()) {
                        t1.setText(rs.getString("room_number"));    
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        // Room number panel
        JPanel roomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roomPanel.setOpaque(false);
        formPanel.add(roomPanel);
        
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roomPanel.add(lblRoomNumber);
        
        t1 = new JTextField(15);
        t1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t1.setBorder(new CompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(5, 10, 5, 10)));
        t1.setEditable(false);
        t1.setBackground(new Color(240, 240, 240));
        roomPanel.add(t1);
        
        leftPanel.add(Box.createVerticalStrut(30));
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        leftPanel.add(buttonPanel);
        
        JButton btnCheckOut = new JButton("Complete Check Out");
        btnCheckOut.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCheckOut.setBackground(new Color(0, 128, 128));
        btnCheckOut.setForeground(Color.WHITE);
        btnCheckOut.setFocusPainted(false);
        btnCheckOut.setBorder(new CompoundBorder(
                new LineBorder(new Color(0, 128, 128).darker(), 1),
                new EmptyBorder(10, 20, 10, 20)));
        btnCheckOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnCheckOut);
        
        btnCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = c1.getSelectedItem();
                String s1 = t1.getText();
                
                if (s1.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a guest first", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String deleteSQL = "Delete from customer where number = " + id;
                String q2 = "update room set availability = 'Available' where room_number = " + s1;
                
                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to check out guest #" + id + " from room " + s1 + "?", 
                        "Confirm Check Out", 
                        JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    conn c = new conn();
                    try {
                        c.s.executeUpdate(deleteSQL);
                        c.s.executeUpdate(q2);
                        JOptionPane.showMessageDialog(null, "Check Out Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new Reception().setVisible(true);
                        setVisible(false);
                    } catch(SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        JButton btnExit = new JButton("Back to Reception");
        btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnExit.setBackground(new Color(211, 211, 211));
        btnExit.setForeground(Color.DARK_GRAY);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(new CompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                new EmptyBorder(10, 20, 10, 20)));
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnExit);
        
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        
        // Right panel for image
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        contentPane.add(rightPanel, BorderLayout.CENTER);
        
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
            Image i3 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            ImageIcon i2 = new ImageIcon(i3);
            JLabel l1 = new JLabel(i2);
            l1.setBorder(new EmptyBorder(10, 10, 10, 10));
            rightPanel.add(l1, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set window to center of screen
        setLocationRelativeTo(null);
    }
}
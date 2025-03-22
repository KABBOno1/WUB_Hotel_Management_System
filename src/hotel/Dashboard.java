package hotel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class Dashboard extends JFrame {
    
    private JPanel sideMenu;
    private JButton receptionBtn, addEmployeeBtn, addRoomBtn, addDriverBtn;
    
    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
    
    public Dashboard() {
        super("WUB HOTEL MANAGEMENT SYSTEM");
        
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Background Panel with Image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT);
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel titleLabel = new JLabel("WUB HOTEL by Kabbo WELCOMES YOU");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 36));
        titleLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
        headerPanel.add(titleLabel);
        
        // Side Menu Panel
        sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBackground(new Color(0, 0, 0, 180)); // Semi-transparent black
        sideMenu.setBorder(new EmptyBorder(20, 10, 20, 10));
        sideMenu.setPreferredSize(new Dimension(250, getHeight()));
        
        // Dashboard label for the side menu
        JLabel dashboardLabel = new JLabel("DASHBOARD");
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        dashboardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dashboardLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        sideMenu.add(dashboardLabel);
        
        // Create styled buttons for the side menu
        receptionBtn = createMenuButton("RECEPTION", new Color(52, 152, 219));
        addEmployeeBtn = createMenuButton("ADD EMPLOYEE", new Color(46, 204, 113));
        addRoomBtn = createMenuButton("ADD ROOMS", new Color(155, 89, 182));
        addDriverBtn = createMenuButton("ADD DRIVERS", new Color(243, 156, 18));
        
        // Add buttons to side menu with spacing
        sideMenu.add(receptionBtn);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 15)));
        sideMenu.add(addEmployeeBtn);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 15)));
        sideMenu.add(addRoomBtn);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 15)));
        sideMenu.add(addDriverBtn);
        
        // Add event listeners to the buttons
        receptionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Reception();
            }
        });
        
        addEmployeeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddEmployee().setVisible(true);
                } catch(Exception e) {}
            }
        });
        
        addRoomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddRoom().setVisible(true);
                } catch(Exception e) {}
            }
        });
        
        addDriverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddDrivers().setVisible(true);
                } catch(Exception e) {}
            }
        });
        
        // Main content area (center)
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridBagLayout());
        
        JLabel welcomeLabel = new JLabel("Welcome to the Hotel Management System");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        contentPanel.add(welcomeLabel);
        
        // Add all components to the background
        backgroundLabel.add(headerPanel, BorderLayout.NORTH);
        backgroundLabel.add(sideMenu, BorderLayout.WEST);
        backgroundLabel.add(contentPanel, BorderLayout.CENTER);
        
        // Add the background to the frame
        add(backgroundLabel);
        
        // Keep the menu bar for backward compatibility
        createMenuBar();
        
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private JButton createMenuButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Montserrat", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(220, 50));
        button.setPreferredSize(new Dimension(220, 50));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        return button;
    }
    
    private void createMenuBar() {
        // Create menu bar (kept for backward compatibility)
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(36, 37, 42));
        
        JMenu hotelSystem = new JMenu("WUB HOTEL MANAGEMENT");
        hotelSystem.setForeground(Color.WHITE);
        JMenuItem guestDetails = new JMenuItem("RECEPTION");
        hotelSystem.add(guestDetails);
        
        JMenu hotelSystemAdmin = new JMenu("ADMIN");
        hotelSystemAdmin.setForeground(Color.WHITE);
        
        JMenuItem guestDetailshello1 = new JMenuItem("ADD EMPLOYEE");
        JMenuItem hotelDetailshello2 = new JMenuItem("ADD ROOMS");
        JMenuItem hotelDetailshello3 = new JMenuItem("ADD DRIVERS");
        
        hotelSystemAdmin.add(guestDetailshello1);
        hotelSystemAdmin.add(hotelDetailshello2);
        hotelSystemAdmin.add(hotelDetailshello3);
        
        // Connect menu items to the same actions as sidebar buttons
        guestDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                new Reception();
            }
        });
        
        guestDetailshello1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddEmployee().setVisible(true);
                } catch(Exception e) {}
            }
        });
        
        hotelDetailshello2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddRoom().setVisible(true);
                } catch(Exception e) {}
            }
        });
        
        hotelDetailshello3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddDrivers().setVisible(true);
                } catch(Exception e) {}
            }
        });
        
        menuBar.add(hotelSystem);
        menuBar.add(hotelSystemAdmin);
        setJMenuBar(menuBar);
    }
}
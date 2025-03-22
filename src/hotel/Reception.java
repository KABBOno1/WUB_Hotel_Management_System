package hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Reception extends JFrame {

    private JPanel contentPane;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel sidebarPanel;
    private Color primaryColor = new Color(41, 128, 185);
    private Color secondaryColor = new Color(52, 152, 219);
    private Color textColor = new Color(44, 62, 80);
    private Color accentColor = new Color(230, 126, 34);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Reception());
    }
    
    public Reception() {
        setTitle("Hotel Management System - Reception");
        setBounds(100, 100, 1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        // Create sidebar panel
        createSidebar();
        
        // Create main content area with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBackground(Color.WHITE);
        
        // Add welcome panel
        createWelcomePanel();
        
        contentPane.add(sidebarPanel, BorderLayout.WEST);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
        sidebarPanel.setBackground(primaryColor);
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        
        // Add logo/title
        JLabel titleLabel = new JLabel("Hotel Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        // Panel to hold the title for proper centering
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(primaryColor);
        titlePanel.add(titleLabel);
        titlePanel.setMaximumSize(new Dimension(250, 80));
        sidebarPanel.add(titlePanel);
        
        // Add separator
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(250, 1));
        separator.setForeground(new Color(255, 255, 255, 100));
        sidebarPanel.add(separator);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add menu buttons
        addMenuButton("New Customer", "NewCustomer");
        addMenuButton("Room Management", "Room");
        addMenuButton("Employee Info", "Employee");
        addMenuButton("Customer Info", "CustomerInfo");
        addMenuButton("Manager Info", "ManagerInfo");
        addMenuButton("Check Out", "CheckOut");
        addMenuButton("Update Check Status", "UpdateCheck");
        addMenuButton("Update Room Status", "UpdateRoom");
        addMenuButton("Pick up Service", "PickUp");
        addMenuButton("Search Room", "SearchRoom");
        
        sidebarPanel.add(Box.createVerticalGlue());
        
        // Add logout button at bottom
        JButton logoutButton = createStyledButton("Log Out");
        logoutButton.setBackground(accentColor);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            try {
                new Login().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.setBackground(primaryColor);
        logoutPanel.add(logoutButton);
        logoutPanel.setMaximumSize(new Dimension(250, 60));
        sidebarPanel.add(logoutPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }
    
    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(Color.WHITE);
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Welcome to Hotel Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(textColor);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        
        welcomePanel.add(headerPanel, BorderLayout.NORTH);
        
        // Center content with image
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        if (originalIcon.getIconWidth() > 0) {
            Image img = originalIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            centerPanel.add(imageLabel);
        } else {
            JLabel placeholderLabel = new JLabel("Welcome to Reception");
            placeholderLabel.setFont(new Font("Arial", Font.BOLD, 32));
            placeholderLabel.setForeground(primaryColor);
            centerPanel.add(placeholderLabel);
        }
        
        welcomePanel.add(centerPanel, BorderLayout.CENTER);
        
        // Add the welcome panel to main panel
        mainPanel.add(welcomePanel, "Welcome");
        cardLayout.show(mainPanel, "Welcome");
    }
    
    private void addMenuButton(String text, String destination) {
        JButton button = createStyledButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(220, 40));
        button.setForeground(textColor);
        
        button.addActionListener(e -> {
            try {
                switch (destination) {
                    case "NewCustomer":
                        loadPanel(new NewCustomer().getContentPane(), "NewCustomer");
                        break;
                    case "Room":
                        loadPanel(new Room().getContentPane(), "Room");
                        break;
                    case "Employee":
                        loadPanel(new Employee().getContentPane(), "Employee");
                        break;
                    case "CustomerInfo":
                        loadPanel(new CustomerInfo().getContentPane(), "CustomerInfo");
                        break;
                    case "ManagerInfo":
                        loadPanel(new ManagerInfo().getContentPane(), "ManagerInfo");
                        break;
                    case "CheckOut":
                        loadPanel(new CheckOut().getContentPane(), "CheckOut");
                        break;
                    case "UpdateCheck":
                        loadPanel(new UpdateCheck().getContentPane(), "UpdateCheck");
                        break;
                    case "UpdateRoom":
                        loadPanel(new UpdateRoom().getContentPane(), "UpdateRoom");
                        break;
                    case "PickUp":
                        loadPanel(new PickUp().getContentPane(), "PickUp");
                        break;
                    case "SearchRoom":
                        loadPanel(new SearchRoom().getContentPane(), "SearchRoom");
                        break;
                    default:
                        cardLayout.show(mainPanel, "Welcome");
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading panel: " + ex.getMessage(), 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(primaryColor);
        buttonPanel.setMaximumSize(new Dimension(250, 50));
        buttonPanel.add(button);
        
        sidebarPanel.add(buttonPanel);
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2.setColor(getBackground().darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(getBackground().brighter());
                } else {
                    g2.setColor(getBackground());
                }
                
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 36));
        
        return button;
    }
    
    private void loadPanel(Container sourcePanel, String name) {
        // Check if panel already exists
        for (Component c : mainPanel.getComponents()) {
            if (c.getName() != null && c.getName().equals(name)) {
                cardLayout.show(mainPanel, name);
                return;
            }
        }
        
        // Create a new panel to host the content
        JPanel newPanel = new JPanel(new BorderLayout());
        newPanel.setName(name);
        newPanel.setBackground(Color.WHITE);
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel(name);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(textColor);
        titleLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        newPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Transfer components from source panel to our content panel
        Component[] components = sourcePanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] subComponents = panel.getComponents();
                for (Component subComponent : subComponents) {
                    // Adjust button styles if needed
                    if (subComponent instanceof JButton) {
                        JButton originalButton = (JButton) subComponent;
                        JButton newButton = createStyledButton(originalButton.getText());
                        newButton.setBackground(secondaryColor);
                        newButton.setForeground(Color.WHITE);
                        
                        // Copy action listeners
                        for (ActionListener al : originalButton.getActionListeners()) {
                            newButton.addActionListener(al);
                        }
                        
                        // Set bounds or constraints based on layout
                        if (panel.getLayout() == null) { // Null layout
                            newButton.setBounds(originalButton.getBounds());
                            contentPanel.setLayout(null);
                            contentPanel.add(newButton);
                        } else {
                            panel.remove(originalButton);
                            panel.add(newButton);
                            contentPanel.add(panel);
                        }
                    } else {
                        // For non-button components, just add the panel containing them
                        contentPanel.add(panel);
                        break;
                    }
                }
            } else {
                contentPanel.add(component);
            }
        }
        
        newPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Add the new panel to main panel with the CardLayout
        mainPanel.add(newPanel, name);
        cardLayout.show(mainPanel, name);
    }
}
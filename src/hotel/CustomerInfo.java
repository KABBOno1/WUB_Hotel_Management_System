package hotel;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

public class CustomerInfo extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    private Color primaryColor = new Color(41, 128, 185); // Blue
    private Color accentColor = new Color(52, 152, 219);  // Lighter blue
    private Color textColor = new Color(44, 62, 80);      // Dark blue-gray
    private Color bgColor = new Color(236, 240, 241);     // Light gray

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerInfo frame = new CustomerInfo();
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
    public CustomerInfo() throws SQLException {
        setTitle("Customer Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 900, 600);
        setLocationRelativeTo(null); // Center on screen - fixes null location error
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(bgColor);
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        JLabel titleLabel = new JLabel("Customer Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Table panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        tablePanel.setBackground(bgColor);
        contentPane.add(tablePanel, BorderLayout.CENTER);
        
        // Column headers panel
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new GridLayout(1, 8, 5, 0));
        columnPanel.setBackground(new Color(52, 73, 94));
        columnPanel.setBorder(new EmptyBorder(8, 5, 8, 5));
        tablePanel.add(columnPanel, BorderLayout.NORTH);
        
        // Add column headers with consistent styling
        String[] columns = {"ID", "Number", "Name", "Gender", "Country", "Room", "Check-in Status", "Deposit"};
        for (String col : columns) {
            JLabel label = new JLabel(col);
            label.setFont(new Font("Segoe UI", Font.BOLD, 13));
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            columnPanel.add(label);
        }
        
        // Table
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(bgColor);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Load Data button
        JButton btnLoadData = new JButton("Refresh Data");
        btnLoadData.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLoadData.setForeground(Color.WHITE);
        btnLoadData.setBackground(accentColor);
        btnLoadData.setFocusPainted(false);
        btnLoadData.setBorder(new EmptyBorder(10, 20, 10, 20));
        btnLoadData.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    conn c = new conn();
                    String displayCustomersql = "select * from Customer";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    // Improve table appearance after loading data
                    JTableHeader header = table.getTableHeader();
                    header.setBackground(new Color(52, 73, 94));
                    header.setForeground(Color.WHITE);
                    header.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    
                    // Center align table content
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
                        {
                            setHorizontalAlignment(SwingConstants.CENTER);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        buttonPanel.add(btnLoadData);
        
        // Back button
        JButton btnBack = new JButton("Return to Reception");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setForeground(textColor);
        btnBack.setBackground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(new EmptyBorder(10, 20, 10, 20));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        buttonPanel.add(btnBack);
        
        // Add hover effects for buttons
        addButtonHoverEffect(btnLoadData, accentColor, new Color(41, 128, 185));
        addButtonHoverEffect(btnBack, Color.WHITE, new Color(236, 240, 241));
    }
    
    // Helper method to add hover effects to buttons
    private void addButtonHoverEffect(JButton button, Color defaultColor, Color hoverColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
            }
        });
    }
}
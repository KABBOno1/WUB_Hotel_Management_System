package hotel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ManagerInfo extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel lblName, lblAge, lblGender, lblJob, lblSalary, lblPhone, lblAadhar, lblGmail;
    private JButton btnLoadData, btnExit;
    private Color primaryColor = new Color(53, 59, 72);
    private Color accentColor = new Color(0, 151, 230);
    private Color textColor = Color.WHITE;
    private Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
    private Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

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
                    ManagerInfo frame = new ManagerInfo();
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
    public ManagerInfo() throws SQLException {
        // Set frame properties
        setTitle("Manager Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        setLocationRelativeTo(null); // Fix null location error
        
        // Create main panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));
        
        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setLayout(new GridLayout(1, 8, 5, 5));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        // Add header labels
        lblName = createHeaderLabel("Name");
        lblAge = createHeaderLabel("Age");
        lblGender = createHeaderLabel("Gender");
        lblJob = createHeaderLabel("Job");
        lblSalary = createHeaderLabel("Salary");
        lblPhone = createHeaderLabel("Phone");
        lblAadhar = createHeaderLabel("Aadhar");
        lblGmail = createHeaderLabel("Gmail");
        
        headerPanel.add(lblName);
        headerPanel.add(lblAge);
        headerPanel.add(lblGender);
        headerPanel.add(lblJob);
        headerPanel.add(lblSalary);
        headerPanel.add(lblPhone);
        headerPanel.add(lblAadhar);
        headerPanel.add(lblGmail);
        
        // Create table with scroll pane
        table = new JTable();
        table.setRowHeight(30);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setSelectionBackground(accentColor);
        table.setSelectionForeground(Color.WHITE);
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        
        // Add table header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(primaryColor);
        header.setForeground(Color.WHITE);
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        // Create styled buttons
        btnLoadData = createStyledButton("Load Managers", accentColor);
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String displayCustomersql = "select * from Employee where job = 'Manager'";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    // Style table after loading data
                    styleTable();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading data: " + e1.getMessage());
                }
            }
        });
        
        btnExit = createStyledButton("Return to Reception", new Color(220, 53, 69));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        
        buttonPanel.add(btnLoadData);
        buttonPanel.add(btnExit);
    }
    
    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        label.setForeground(textColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(buttonFont);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void styleTable() {
        // Apply custom cell renderer for alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                    c.setForeground(Color.BLACK);
                }
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });
    }
}
package hotel;
import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.JTableHeader;

public class Employee extends JFrame {
	Connection conn = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblJob;
	private JLabel lblName;
	private JLabel lblDepartment;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() throws SQLException {
		// Set frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 200, 1000, 600);
		setTitle("Employee Management");
		
		// Create content pane with modern look
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setBackground(new Color(240, 240, 245));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1000, 70);
		headerPanel.setBackground(new Color(41, 128, 185));
		headerPanel.setLayout(null);
		contentPane.add(headerPanel);
		
		// Add title to header
		JLabel titleLabel = new JLabel("Employee Information");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(25, 20, 300, 30);
		headerPanel.add(titleLabel);
		
		// Table panel with shadow effect
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(25, 85, 935, 400);
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBorder(BorderFactory.createCompoundBorder(
			new MatteBorder(1, 1, 3, 3, new Color(220, 220, 220)),
			BorderFactory.createEmptyBorder(1, 1, 1, 1)
		));
		tablePanel.setLayout(new BorderLayout());
		contentPane.add(tablePanel);
		
		// Create table with improved styling
		table = new JTable();
		table.setRowHeight(30);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.setGridColor(new Color(230, 230, 230));
		table.setShowGrid(true);
		table.setFillsViewportHeight(true);
		
		// Style table header
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 14));
		header.setBackground(new Color(52, 152, 219));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(0, 35));
		
		// Add table to scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		
		// Column headers panel
		JPanel columnHeadersPanel = new JPanel();
		columnHeadersPanel.setBounds(25, 85, 935, 35);
		columnHeadersPanel.setBackground(new Color(52, 152, 219));
		columnHeadersPanel.setLayout(null);
		
		// Create button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(25, 500, 935, 50);
		buttonPanel.setBackground(new Color(240, 240, 245));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		contentPane.add(buttonPanel);
		
		// Create modern styled buttons
		JButton btnLoadData = new JButton("Load Employee Data");
		btnLoadData.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLoadData.setFocusPainted(false);
		btnLoadData.setBackground(new Color(41, 128, 185));
		btnLoadData.setForeground(Color.WHITE);
		btnLoadData.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnLoadData.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn c = new conn();
					String displayCustomersql = "select * from Employee";
					ResultSet rs = c.s.executeQuery(displayCustomersql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonPanel.add(btnLoadData);
		
		JButton btnExit = new JButton("Return to Reception");
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnExit.setFocusPainted(false);
		btnExit.setBackground(new Color(149, 165, 166));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		buttonPanel.add(btnExit);
		
		// Column labels - these are now hidden as they're part of the table header
		// Adding them here for compatibility with your existing code
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(41, 11, 46, 14);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		
		lblJob = new JLabel("Age");
		lblJob.setBounds(159, 11, 46, 14);
		lblJob.setVisible(false);
		contentPane.add(lblJob);
		
		lblName = new JLabel("Gender");
		lblName.setBounds(273, 11, 46, 14);
		lblName.setVisible(false);
		contentPane.add(lblName);
		
		lblDepartment = new JLabel("Job");
		lblDepartment.setBounds(416, 11, 86, 14);
		lblDepartment.setVisible(false);
		contentPane.add(lblDepartment);
                
		JLabel l1 = new JLabel("Salary");
		l1.setBounds(536, 11, 86, 14);
		l1.setVisible(false);
		contentPane.add(l1);
                
		JLabel l2 = new JLabel("Phone");
		l2.setBounds(656, 11, 86, 14);
		l2.setVisible(false);
		contentPane.add(l2);
                
		JLabel l3 = new JLabel("Aadhar");
		l3.setBounds(786, 11, 86, 14);
		l3.setVisible(false);
		contentPane.add(l3);
                
		JLabel l4 = new JLabel("Gmail");
		l4.setBounds(896, 11, 86, 14);
		l4.setVisible(false);
		contentPane.add(l4);
	}
}
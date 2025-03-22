package hotel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

public class Room extends JFrame {
	Connection conn = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblAvailability;
	private JLabel lblCleanStatus;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblRoomNumber;
	private JLabel lblId;
	
	public Room() throws SQLException {
		// UI Improvements
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Frame Setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1100, 600);
		setTitle("Room Information");
		
		// Content Pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 248, 255)); // Light blue background
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Background Image Panel
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(500, 0, 600, 600);
		imagePanel.setLayout(null);
		contentPane.add(imagePanel);
		
		// Fix the image loading issue by checking if resource exists
		ImageIcon i1 = null;
		try {
			i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
			Image i3 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
			ImageIcon i2 = new ImageIcon(i3);
			JLabel l1 = new JLabel(i2);
			l1.setBounds(0, 0, 600, 600);
			imagePanel.add(l1);
		} catch (Exception e) {
			// Create a placeholder panel if image can't be loaded
			JPanel placeholderPanel = new JPanel();
			placeholderPanel.setBackground(new Color(100, 149, 237)); // Cornflower blue
			placeholderPanel.setBounds(0, 0, 600, 600);
			imagePanel.add(placeholderPanel);
			
			JLabel placeholderText = new JLabel("Hotel Room Management");
			placeholderText.setFont(new Font("Segoe UI", Font.BOLD, 24));
			placeholderText.setForeground(Color.WHITE);
			placeholderText.setBounds(150, 250, 300, 50);
			placeholderPanel.add(placeholderText);
		}
		
		// Title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 500, 40);
		titlePanel.setBackground(new Color(70, 130, 180)); // Steel blue
		titlePanel.setLayout(null);
		contentPane.add(titlePanel);
		
		// Table Headers with improved styling
		Font headerFont = new Font("Segoe UI", Font.BOLD, 12);
		Color headerForeground = Color.WHITE;
		
		lblId = new JLabel("Room Number");
		lblId.setBounds(12, 10, 90, 20);
		lblId.setFont(headerFont);
		lblId.setForeground(headerForeground);
		titlePanel.add(lblId);
		
		lblAvailability = new JLabel("Availability");
		lblAvailability.setBounds(119, 10, 90, 20);
		lblAvailability.setFont(headerFont);
		lblAvailability.setForeground(headerForeground);
		titlePanel.add(lblAvailability);
		
		lblCleanStatus = new JLabel("Clean Status");
		lblCleanStatus.setBounds(216, 10, 90, 20);
		lblCleanStatus.setFont(headerFont);
		lblCleanStatus.setForeground(headerForeground);
		titlePanel.add(lblCleanStatus);
		
		lblNewLabel = new JLabel("Price");
		lblNewLabel.setBounds(330, 10, 90, 20);
		lblNewLabel.setFont(headerFont);
		lblNewLabel.setForeground(headerForeground);
		titlePanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Bed Type");
		lblNewLabel_1.setBounds(417, 10, 90, 20);
		lblNewLabel_1.setFont(headerFont);
		lblNewLabel_1.setForeground(headerForeground);
		titlePanel.add(lblNewLabel_1);
		
		// Table with scroll pane
		table = new JTable();
		table.setRowHeight(25);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.setGridColor(new Color(220, 220, 220));
		table.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 40, 500, 400);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(scrollPane);
		
		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 450, 500, 100);
		buttonPanel.setBackground(new Color(240, 248, 255));
		buttonPanel.setLayout(null);
		contentPane.add(buttonPanel);
		
		// Modern Buttons
		JButton btnLoadData = createStyledButton("View Rooms", 80, 20, 150, 40);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn c = new conn();
					String displayCustomersql = "select * from Room";
					ResultSet rs = c.s.executeQuery(displayCustomersql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					// Apply cell renderer for better table appearance
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.CENTER);
					
					for (int i = 0; i < table.getColumnCount(); i++) {
						table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonPanel.add(btnLoadData);
		
		JButton btnNewButton = createStyledButton("Back to Reception", 270, 20, 150, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		buttonPanel.add(btnNewButton);
		
		// Set frame to be visible
		setLocationRelativeTo(null);
	}
	
	// Helper method to create styled buttons
	private JButton createStyledButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFont(new Font("Segoe UI", Font.BOLD, 12));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(new Color(70, 130, 180));
		button.setForeground(Color.WHITE);
		button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		// Add hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(100, 149, 237));
			}
			
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(70, 130, 180));
			}
		});
		
		return button;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room frame = new Room();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
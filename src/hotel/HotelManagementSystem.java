package hotel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class HotelManagementSystem extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1;
    Timer timer;
    float alpha = 0f;
    boolean fadeIn = true;

    public HotelManagementSystem() {
        setTitle("Hotel Management System");
        setSize(800, 430); // Reduced width for a more compact frame
        setLayout(null);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i3 = i1.getImage().getScaledInstance(800, 430, Image.SCALE_DEFAULT); // Scale image to fit the frame
        ImageIcon i2 = new ImageIcon(i3);
        l1 = new JLabel(i2);
        l1.setBounds(0, 0, 800, 430); // Set bounds for the background image
        add(l1);

        // Add the title label at the bottom
        JLabel lid = new JLabel("WUB HOTEL MANAGEMENT SYSTEM");
        lid.setBounds(50, 300, 700, 50); // Position at the bottom
        lid.setFont(new Font("Serif", Font.BOLD, 40)); // Set font and size
        lid.setForeground(Color.RED); // Set text color
        l1.add(lid); // Add the label to the background

        // Add the "Next" button
        b1 = new JButton("Next");
        b1.setBounds(650, 350, 120, 40); // Adjust position and size
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        b1.setFocusPainted(false);
        b1.addActionListener(this);

        // Button hover effect
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setBackground(Color.BLACK);
                b1.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(Color.WHITE);
                b1.setForeground(Color.BLACK);
            }
        });
        l1.add(b1); // Add the button to the background

        // Timer for smooth blinking animation
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fadeIn) {
                    alpha += 0.05f;
                    if (alpha >= 1f) {
                        alpha = 1f;
                        fadeIn = false;
                    }
                } else {
                    alpha -= 0.05f;
                    if (alpha <= 0f) {
                        alpha = 0f;
                        fadeIn = true;
                    }
                }
                lid.setForeground(new Color(1f, 0f, 0f, alpha)); // Apply alpha transparency
            }
        });
        timer.start();

        // Play background music (optional)
        playBackgroundMusic("music.wav");

        setVisible(true); // Make the frame visible
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new Login().setVisible(true); // Open the login screen
        this.setVisible(false); // Hide the current frame
        timer.stop(); // Stop the timer when moving to the next screen
    }

    private void playBackgroundMusic(String filePath) {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HotelManagementSystem window = new HotelManagementSystem();
        window.setVisible(true);
    }
}
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ébel Zsolt
 */
public class MainWindow extends JFrame {
    
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu data;
    private JMenuItem exitMenuItem;
    private JMenuItem citiesMenuItem;

    public MainWindow() {
        initComponents();        
    }

    private void initComponents() {
        setTitle("Swing application with JDBC using World sample database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        setSize(625, 650);
        setResizable(false);
        setJMenuBar(createMenuBar());
        
        try {
            getContentPane().add(new MyBackgroundPanel());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Background image is missing!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        setLocationRelativeTo(null);
    }

    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        file.add(exitMenuItem);

        data = new JMenu("Data");
        citiesMenuItem = new JMenuItem("Load cities");
        data.add(citiesMenuItem);

        menuBar.add(file);
        menuBar.add(data);
        return menuBar;
    }
    
    public JMenuItem getExitMenu() {
        return exitMenuItem;
    }
    
    public JMenuItem getLoadCitiesMenu() {
        return citiesMenuItem;
    }
    
    public void addMenuActionListener (ActionListener menuActionListener) {
        exitMenuItem.addActionListener(menuActionListener);
        citiesMenuItem.addActionListener(menuActionListener);        
    }    
    
    private class MyBackgroundPanel extends JPanel {
        URL url = getClass().getResource("worldMap03.jpg");
        Image img = new ImageIcon(url).getImage();

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
}

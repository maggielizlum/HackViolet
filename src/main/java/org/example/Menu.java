package org.example;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Menu extends JPanel{
    Graphics g;
    Timer time;
    boolean selectionMade;
    Button pitchTrainer;
    Button notation;
    JFrame frame;
    BufferedImage image;
    public Menu(Graphics g, JFrame frame, BufferedImage image){
        this.g = g;
        this.frame = frame;
        this.image = image;
        pitchTrainer = new Button("Pitch Training");
        notation = new Button("Humming Notation");
        frame.add(pitchTrainer);
        frame.add(notation);
        frame.setVisible(true);
        pitchTrainer.setBounds(500, 500, 50, 50);
        notation.setBounds(400, 500, 50, 50);
        selectionMade = false;
        time = new Timer(10, new TimerListener());
        time.start();
        addKeyListener(new MouseAndKeyboard.Keyboard());
        addMouseListener(new MouseAndKeyboard.Mouse());
    }



    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            pitchTrainer.repaint();
            notation.repaint();
            repaint();
        }
    }

    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0 , frame.getWidth(), frame.getHeight(), null);
    }

}

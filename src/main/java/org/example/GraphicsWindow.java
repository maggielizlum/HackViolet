package org.example;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GraphicsWindow {

    private final Dimension size;


    public GraphicsWindow(){
        size = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        JFrame frame = new JFrame("Hackviolet Pitch Perfection");
        frame.setSize(size);
        frame.setLocation(0, 0 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Menu(g, frame, image));

    }
}

package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class GraphicsWindow  extends JPanel{

    private final Dimension size;
    BufferedImage image;
    Timer time;
    MouseAndKeyboard mouseAndKeyboard;
    ProgramRunner program;
    Graphics g;
    JFrame frame;
    byte expectedState;

    /*this is used particularly because Notation creation and more importantly pitch don't like being instantiated
    multiple times. I have extended it, because I don't foresee this project scaling huge. I think we can keep all 3
    processes in memory at once.
     */
    ProgramRunner[] sidelinedProcess;


    /**
     * This class creates a singular JFrame that will be passed between all applications that need to use it to allow
     * for each implementation to be separated.
     */
    public GraphicsWindow(){
        size = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Hackviolet Pitch Perfection");


        frame.setSize(size);
        frame.setLocation(0, 0 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);


        program = new Menu(frame);
        expectedState = program.getState();
        sidelinedProcess = new ProgramRunner[3];


        image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        setFocusable(true);


        mouseAndKeyboard = new MouseAndKeyboard();
        addKeyListener(mouseAndKeyboard);
        addMouseListener(mouseAndKeyboard);
        addMouseMotionListener(mouseAndKeyboard);
        time = new Timer(10, new GraphicsWindow.TimerListener());
        time.start();

    }


    /**
     * calls the individual type of programs update method. then checks if it needs to change state. and updates
     * accordingly. then Repaints. Currently, there is a known issue where you cannot enter the NotionCreator state more
     * than once or the system crashes. For now, it has been fixed by adding sidelinedProcess
     */
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            program.update(g, mouseAndKeyboard);
            byte state = program.getState();
            if(state != expectedState){
                sidelinedProcess[expectedState] = program;
                switch(state){
                    case 0:
                        if(sidelinedProcess[0] != null){
                            program = sidelinedProcess[0];
                            program.updateState((byte) 0);
                        }else {
                            program = new Menu(frame);
                        }
                        break;
                    case 1:
                        if(sidelinedProcess[1] != null){
                            program = sidelinedProcess[1];
                            program.updateState((byte) 1);
                        }else{
                            program = new NotationCreator(frame);
                        }
                        break;
                    case 2:
                        if(sidelinedProcess[2] != null){
                            program = sidelinedProcess[2];
                            program.updateState((byte) 2);
                        }else {
                            program = new PitchTrainer(frame);
                        }
                        break;
                    default:
                        System.out.println("Something has gone horribly wrong\n" +
                                "Check that any changes to state do what you want them to");
                        System.exit(-1);
                }
                expectedState = program.getState();
            }
            repaint();
        }
    }








    /**
     * Everything in the time listener that draws/paints will paint to the buffer. Then this method paints the
     * buffer to the actual frame. This method is important
     * @param g an instance of the Graphics class passed between applications that need a common graphics object
     */
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0 , frame.getWidth(), frame.getHeight(), null);
    }


}

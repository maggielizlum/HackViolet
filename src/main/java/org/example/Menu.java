package org.example;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;


public class Menu extends ProgramRunner{
    int frameWidth;
    int frameHeight;
    private final ImageIcon pitchTrainerReg; //regular
    private final ImageIcon notationReg;
    private final ImageIcon pitchTrainerNeg; //negative
    private final ImageIcon notationNeg;
    private final Rectangle notationButtonBounds;
    private final Rectangle pitchButtonBounds;



    /**
     * gives Menu local access to all graphics objects needed to run the window. Initialized ImageIcons for all images
     * used on this menu screen. Sets up a timerListener and all peripherals needed.
     */
    public Menu(JFrame frame){
        pitchTrainerReg = new ImageIcon("src/main/GraphicsResources/PitchTrainingRegular.png");
        notationReg = new ImageIcon("src/main/GraphicsResources/HummingNotationRegular.png");
        pitchTrainerNeg = new ImageIcon("src/main/GraphicsResources/PitchTrainingInverted.png");
        notationNeg = new ImageIcon("src/main/GraphicsResources/HummingNotationInverted.png");
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
        int buttonSize = frameWidth/3;
        pitchButtonBounds = new Rectangle(frameWidth/2-5*buttonSize/4, frameHeight/2-buttonSize/4, buttonSize, buttonSize/2);
        notationButtonBounds = new Rectangle(frameWidth/2+buttonSize/4, frameHeight/2-buttonSize/4, buttonSize, buttonSize/2);

        state = 0;
    }


    /**
     * Called by the graphics library, this method will draw everything needed for this particular child of ProgramRunner
     * @param g Graphics object that will draw to a buffered Image
     * @param mouseAndKeyboard a class that has access to many peripheral methods.
     */
    void update(Graphics g, MouseAndKeyboard mouseAndKeyboard){
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, frameWidth, frameHeight);
        if (pitchButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
            g.drawImage(pitchTrainerNeg.getImage(), pitchButtonBounds.x, pitchButtonBounds.y, pitchButtonBounds.width, pitchButtonBounds.height, null);
        }else{
            g.drawImage(pitchTrainerReg.getImage(), pitchButtonBounds.x, pitchButtonBounds.y, pitchButtonBounds.width, pitchButtonBounds.height, null);
        }
        if (notationButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
            g.drawImage(notationReg.getImage(), notationButtonBounds.x, notationButtonBounds.y, notationButtonBounds.width, notationButtonBounds.height, null);
        }else{
            g.drawImage(notationNeg.getImage(), notationButtonBounds.x, notationButtonBounds.y, notationButtonBounds.width, notationButtonBounds.height, null);
        }

        if(mouseAndKeyboard.isLeftMouseDown()){
            if(notationButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                state = 1;
            }
            else if(pitchButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                state = 2;
            }
        }
    }


}

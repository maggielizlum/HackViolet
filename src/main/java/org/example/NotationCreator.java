package org.example;
import javax.swing.*;
import java.awt.*;


public class NotationCreator extends  ProgramRunner{

    int frameWidth;
    int frameHeight;
    private Rectangle notationButtonBounds;
    private Rectangle pitchButtonBounds;
    private final Pitch pitch;
    private final Rectangle startButtonBounds;
    private final Rectangle pauseButtonBounds;
    private final Rectangle saveButtonBounds;
    private final Rectangle backButtonBounds;
    private final ImageIcon startButton;
    private final ImageIcon pauseButton;
    private final ImageIcon saveButton;
    private final ImageIcon backButton;


    /**
     * 4 buttons and instantiates an instance of pitch;
     * @param frame JFrame used to display images from this class
     */
    public NotationCreator(JFrame frame){
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();
        pitch = new Pitch();


        startButton = new ImageIcon("src/main/GraphicsResources/playButton.png");
        pauseButton = new ImageIcon("src/main/GraphicsResources/pauseButton.png");
        saveButton = new ImageIcon("src/main/GraphicsResources/saveButton.png");
        backButton = new ImageIcon("src/main/GraphicsResources/ExitButton.png");
        int buttonSize = frameWidth/25;
        startButtonBounds = new Rectangle(frameWidth-2*buttonSize, buttonSize/8, buttonSize, buttonSize);
        pauseButtonBounds = new Rectangle(frameWidth-4*buttonSize, buttonSize/8, buttonSize, buttonSize);
        saveButtonBounds = new Rectangle(frameWidth-6*buttonSize, buttonSize/8, buttonSize, buttonSize);
        backButtonBounds = new Rectangle(buttonSize/2, buttonSize/8, buttonSize, buttonSize);


        state = 1;
    }



    /**
     * Called by the graphics library, this method will draw everything needed for this particular child of ProgramRunner
     * @param g Graphics object that will draw to a buffered Image
     * @param mouseAndKeyboard a class that has access to many peripheral methods.
     */
    @Override
    void update(Graphics g, MouseAndKeyboard mouseAndKeyboard) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, frameWidth, frameHeight);

        //put a background on any button that the mouse is hovering over
        g.setColor(Color.LIGHT_GRAY);
        if(startButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
            g.fillRoundRect(startButtonBounds.x, startButtonBounds.y, startButtonBounds.width, startButtonBounds.height, 15, 15);
            //round rect just puts a bit of curve on the corners
        }else if(pauseButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
            g.fillRoundRect(pauseButtonBounds.x, pauseButtonBounds.y, pauseButtonBounds.width, pauseButtonBounds.height, 15, 15);
        }else if(saveButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
            g.fillRoundRect(saveButtonBounds.x, saveButtonBounds.y, saveButtonBounds.width, saveButtonBounds.height, 15, 15);
        }else if(backButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
            g.fillRoundRect(backButtonBounds.x, backButtonBounds.y, backButtonBounds.width, backButtonBounds.height, 15, 15);
        }


        //draw buttons and text
        String pitchStr = pitch.getPitch() + " Hz";
        g.setColor(Color.BLACK);
        g.drawString(pitchStr, 500, 500);
        g.drawImage(startButton.getImage(), startButtonBounds.x, startButtonBounds.y, startButtonBounds.width, startButtonBounds.height, null);
        g.drawImage(pauseButton.getImage(), pauseButtonBounds.x, pauseButtonBounds.y, pauseButtonBounds.width, pauseButtonBounds.height, null);
        g.drawImage(saveButton.getImage(), saveButtonBounds.x, saveButtonBounds.y, saveButtonBounds.width, saveButtonBounds.height, null);
        g.drawImage(backButton.getImage(), backButtonBounds.x, backButtonBounds.y, backButtonBounds.width, backButtonBounds.height, null);

        //check for button collision while clicking
        if(mouseAndKeyboard.isLeftMouseDown()){
            if(startButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                pitch.run();
                //new and funny issue: the second you start this method. Nothing else works. Trying to close the tab.
                //however, you still do get input and you can see the current pitch, so... semi success?
            }else if(pauseButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                pitch.close();
            }else if(saveButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                //will be implemented later
            }else if(backButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                state = 0;
            }
        }
    }




}

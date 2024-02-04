package org.example;

import javax.swing.*;
import java.awt.*;

public class PitchTrainer extends ProgramRunner{
    private int frameWidth;
    private int frameHeight;
    private final ImageIcon backButton;
    private final Rectangle backButtonBounds;

    public PitchTrainer(JFrame frame){
        frameWidth = frame.getWidth();
        frameHeight = frame.getHeight();

        int buttonSize = frameWidth/25;

        backButton = new ImageIcon("src/main/GraphicsResources/ExitButton.png");
        backButtonBounds = new Rectangle(buttonSize/2, buttonSize/8, buttonSize, buttonSize);

        state = 2;
    }


    @Override
    void update(Graphics g, MouseAndKeyboard mouseAndKeyboard) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, frameWidth, frameHeight);
        g.drawImage(backButton.getImage(), backButtonBounds.x, backButtonBounds.y, backButtonBounds.width, backButtonBounds.height, null);


        if(mouseAndKeyboard.isLeftMouseDown()){
            if(backButtonBounds.contains(mouseAndKeyboard.getMouseLocation())){
                state = 0;
            }
        }
    }
}

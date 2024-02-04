package org.example;

import java.awt.*;

public abstract class ProgramRunner {
    protected byte state; //0 will be for Menu, 1 for NotationCreator, and 2 for PitchTrainer

    byte getState(){
        return state;
    }

    /**
     * There is probably a better way to do this than to manually reset the state. But on well
     */
    void updateState(byte newState){
        state = newState;
    }

    /**
     * Called by the graphics library, this method will draw everything needed for this particular child of ProgramRunner
     * @param g Graphics object that will draw to a buffered Image
     * @param mouseAndKeyboard a class that has access to many peripheral methods.
     */
    abstract void update(Graphics g, MouseAndKeyboard mouseAndKeyboard);

}

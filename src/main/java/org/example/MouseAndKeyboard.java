package org.example;

import java.awt.*;
import java.awt.event.*;

/**
 * Class with implementations for KeyListener, MouseListener, and MouseMotionListener
 * Useful as a single stop package for all peripherals
 */
public class MouseAndKeyboard implements KeyListener, MouseListener, MouseMotionListener{
    private Point mouseLocation;
    boolean leftMouseDown;

    public MouseAndKeyboard(){
        mouseLocation = new Point();
    }

    public Point getMouseLocation(){
        return mouseLocation;
    }
    public boolean isLeftMouseDown(){
        return leftMouseDown;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }










    @Override
    public void mouseClicked(MouseEvent e) {

    }


    /**
     * It is important to note that the implementation here will have LeftMouseDown as true
     * for as long as it is held down. This method will not make any distinction for how long it is
     * held or the moment the button is pressed.
     * @param e the Event recorded by MouseMotionListener
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            leftMouseDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            leftMouseDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed and then Mouse is moved
     * @param mouseEvent MouseEvent as described in Documentation
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Invoked any time the mouse motion is detected regardless of buttons pressed
     * @param mouseEvent MouseEvent as described in Documentation
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseLocation = mouseEvent.getLocationOnScreen();
    }

}

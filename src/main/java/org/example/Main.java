package org.example;



public class Main
{
    public static void main(String[] args)
    {
        Pitch p = new Pitch();
        float time = System.currentTimeMillis();
        while(true){
            p.getPitch();
        }
    }
}
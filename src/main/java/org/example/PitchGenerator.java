package org.example;
import javax.sound.midi.*;
import java.util.Random;
import java.util.Scanner;

public class PitchGenerator {

    private Synthesizer synth;
    private MidiChannel[] channels;
    private Instrument[] instruments;
    private Instrument instrument;
    public PitchGenerator() {
        try {
            synth = MidiSystem.getSynthesizer();
            channels = synth.getChannels();
            instruments = synth.getDefaultSoundbank().getInstruments();
            instrument = instruments[0];
            synth.open();
            synth.loadInstrument(instrument);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Plays a random pitch and prompts the user to guess it
    public void guessTheNote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess this pitch!");
        String noteName = getNoteName(playRandomPitch()); //it will always wait for this, slow
        //try threading?
        String guess = scanner.nextLine().toString();
        if (noteName.equals(guess)) {
            System.out.println("Correct!");
        }
        else {
            System.out.println("Nope! That note was a " + noteName);
        }
    }

    //Plays two notes and asks the user to guess the interval
    public void guessTheInterval() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess this interval!");
        int[] notes = playTwoRandomNotes();
        int intervalValue = Math.abs(notes[0] - notes[1]);
        String interval = getIntervalName(intervalValue);
        String guess = scanner.nextLine().toString();
        if (interval.equals(guess)) {
            System.out.println("Correct!");
        }
        else {
            System.out.println("Nope! That interval was: " + interval);
        }
    }

    //Plays a triad and asks the user to guess it
    public void guessTheTriad() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess this triad!");
        int[] notes = playRandomTriad();
        int interval1 = Math.abs(notes[1] - notes[0]);
        int interval2 = Math.abs(notes[2] - notes[1]);
        String triadName = getTriadName(notes[0], interval1, interval2);
        String guess = scanner.nextLine();
        if (triadName.equals(guess)) {
            System.out.println("Correct!");
        }
        else {
            System.out.println("Nope! That triad was: " + triadName);
        }
    }

    //Plays a random pitch
    public int playRandomPitch() {
        Random generator = new Random();
        int note = generator.nextInt(70) + 30; //midi pitches are from 0-127
        try {
            channels[0].noteOn(note, 60);
            //it's weirdly delayed
            Thread.sleep(5000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        channels[0].noteOff(note, 60);
        synth.close();
        return note;
    }

    //Plays two random pitches simultaneously
    public int[] playTwoRandomNotes() {
        Random generator = new Random();
        int note1 = generator.nextInt(13) + 48;
        int note2 = generator.nextInt(12) + note1 + 1; //second note is at most an octave higher than first?
        int[] notes = new int[2];
        notes[0] = note1;
        notes[1] = note2;
        try {
            channels[0].noteOn(note1, 60);
            channels[0].noteOn(note2, 60);
            Thread.sleep(3000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        channels[0].noteOff(note1, 60);
        channels[0].noteOff(note2, 60);
        synth.close();
        return notes;
    }

    //Plays a random triad (3-note chord)
    public int[] playRandomTriad() {
        Random generator = new Random();
        int note1 = generator.nextInt(13) + 55; //start in a normal range
        int note2 = generator.nextInt(2) + 3 + note1; //generates minor or major third above?
        int note3 = generator.nextInt(2) + 3 + note2; //generates minor or major third above?
        int[] notes = new int[3];
        notes[0] = note1;
        notes[1] = note2;
        notes[2] = note3;
        try {
            channels[0].noteOn(note1, 60);
            channels[0].noteOn(note2, 60);
            channels[0].noteOn(note3, 60);
            Thread.sleep(4000);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        channels[0].noteOff(note1, 60);
        channels[0].noteOff(note2, 60);
        channels[0].noteOff(note3, 60);
        synth.close();
        return notes;
    }

    //Converts midi note number to a note name
    private String getNoteName(int noteValue) {
        switch(noteValue % 12) {
            case 0: return "C";
            case 1: return "C#";
            case 2: return "D";
            case 3: return "D#";
            case 4: return "E";
            case 5: return "F";
            case 6: return "F#";
            case 7: return "G";
            case 8: return "G#";
            case 9: return "A";
            case 10: return "A#";
            case 11: return "B";
            default: return "H"; //this is stupid
        }
    }

    //Converts a difference of two midi numbers to an interval
    private String getIntervalName(int intervalValue) {
        switch(intervalValue) {
            case 0: return "octave";
            case 1: return "minor second";
            case 2: return "major second";
            case 3: return "minor third";
            case 4: return "major third";
            case 5: return "perfect fourth";
            case 6: return "tritone";
            case 7: return "perfect fifth";
            case 8: return "minor sixth";
            case 9: return "major sixth";
            case 10: return "minor seventh";
            case 11: return "major seventh";
            default: return "something weird happened...";
        }
    }

    //Converts two interval numbers to a triad type
    public String getTriadName(int root, int interval1, int interval2) {
        String rootName = getNoteName(root);
        if (interval1 == 3) {
            //minor third
            if (interval2 == 3) {
                //minor third
                return rootName + " diminished";
            }
            else {
                //major third
                return rootName + " minor";
            }
        }
        else if (interval1 == 4) {
            if (interval2 == 3) {
                //minor third
                 return rootName + " major";
            }
            else {
                //major third
                return rootName + " augmented";
            }
        }
        return "The chord could not be identified";
    }
}

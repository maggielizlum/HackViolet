package org.example;
import javax.sound.midi.*;
import java.util.Arrays;

public class PitchGenerator {

    private Synthesizer synth;
    private MidiChannel[] channels;
    private Instrument[] instruments;
    public PitchGenerator() {
        try {
            synth = MidiSystem.getSynthesizer();
            channels = synth.getChannels();
            instruments = synth.getAvailableInstruments();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void playRandomPitch() {
        Instrument instrument = instruments[0];
        int note = 60; //middle C
        synth.loadInstrument(instrument);
        channels[0].noteOn(note, 60);
        try {
            Thread.sleep(1000); //play for 1 ms
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("I played a note!");
        channels[0].noteOff(note, 60);
    }
}

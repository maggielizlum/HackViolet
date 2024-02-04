package org.example;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Pitch {

    private be.tarsos.dsp.AudioDispatcher ad;
    private PitchProcessor pp;
    private Pipe.SourceChannel ReadPipe;
    private ByteBuffer buffer;

    private int bufferSize = 128;
    Thread process;

    public Pitch() {
        try {
            ad = AudioDispatcherFactory.fromDefaultMicrophone(2048, 1024);
        } catch(Exception e){
            e.printStackTrace();
        }
        buffer = ByteBuffer.allocate(bufferSize);

        Pipe pipe = null ;
        PitchHandler ph = null;
        try{
            pipe = Pipe.open();
            ph = new PitchHandler(pipe.sink());
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        ReadPipe = pipe.source();
        pp = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 44100, 2048, ph);
        float[] buffer = new float[2048];
        ad.setAudioFloatBuffer(buffer);
        ad.addAudioProcessor(pp);


        
        process = new Thread(ad, "Audio Dispatcher");
        process.start();

    }

    public void close(){
        process.interrupt();
    }

    public void getPitch() {
        try{
            ReadPipe.read(buffer);
        }catch(Exception e){
            e.printStackTrace();
        }


    }



    private class PitchHandler implements PitchDetectionHandler{
        private Pipe.SinkChannel input;
        private ByteBuffer buffer;
        public PitchHandler(Pipe.SinkChannel sink){
            buffer = ByteBuffer.allocate(bufferSize);
            input = sink;
        }

        @Override
        public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
            double pitchInHz =pitchDetectionResult.getPitch();
            buffer.putDouble(pitchInHz);
            while(buffer.hasRemaining()){
                try {
                    input.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}




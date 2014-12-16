package ru.Vladimir;

public class Electric extends MusicInstrument {

    Electric() {
        prepareToPlay();
    }

    @Override
    public void prepareToPlay() {
        connectToCombo();
        super.prepareToPlay();
    }

    @Override
    public void stopPlaying(){
        disconnectFromCombo();
        super.stopPlaying();
    }

    private void disconnectFromCombo() {
        System.out.println("Combo: turn off");
        System.out.println("Instrument disconnected from combo!");
    }

    private void connectToCombo() {
        System.out.println("Combo: turn on");
        System.out.println("Instrument connected to combo!");
    }
}

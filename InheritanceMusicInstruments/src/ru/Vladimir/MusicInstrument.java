package ru.Vladimir;

public class MusicInstrument {

    MusicInstrument()
    {
        System.out.println();
    }

    public void prepareToPlay() {
        System.out.println("Ready to play!");
    }

    public void stopPlaying() {
        System.out.println("Instrument was put on the stand.");
    }

    public void play(char note) {
        System.out.println("note played: " + note);
    }
}

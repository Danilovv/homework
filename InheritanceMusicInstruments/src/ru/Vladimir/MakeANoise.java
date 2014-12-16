package ru.Vladimir;

public class MakeANoise {
    public static void main(String[] args) {
        MusicInstrument instrument = new YamahaRBX375();
        instrument.play('G');
        instrument.stopPlaying();
        instrument = new WarwickCorvette5();
        instrument.play('F');
        instrument.stopPlaying();
    }
}

package ru.Vladimir;

public class YamahaRBX375 extends Electric {
    YamahaRBX375() {
        System.out.println(this.getClass().getSimpleName());
    }

    @Override
    public void play(char note) {
        super.play(note);
        System.out.println("Short sustain..");
        System.out.println("Aggressive sound..");
    }
}

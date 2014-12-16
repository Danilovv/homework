package ru.Vladimir;

public class Fretless extends Electric {
    @Override
    public void play(char note) {
        super.play(note);
        System.out.println("Soft sound..");
    }
}

package ru.Vladimir;

public class WarwickCorvette5 extends Fretless {
    WarwickCorvette5() {
        System.out.println(this.getClass().getSimpleName());
    }

    @Override
    public void play(char note) {
        super.play(note);
        System.out.println("Long sustain..");
    }

}

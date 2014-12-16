package com.company;

import java.awt.*;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ ////////////////////////
// Defines a Card class
//
// used by CardPile
////////////////////////
class Card {
    // data fields for colors and suits
    final static int width = 50;
    final static int height = 70;

    final static int red = 0;
    final static int black = 1;

    final static int heart = 0;
    final static int spade = 1;
    final static int diamond = 2;
    final static int club = 3;

    final static int ace = 0;
    final static int king = 12;

    private static String names[] = {"A", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K"};

    // data fields
    private boolean faceup;
    private int rank;
    private int suit;

    // constructor
    Card(int s, int r) {
        suit = s;
        rank = r;
        faceup = false;
    }

    // get rank of card as an int in the interval [0, 12]
    public int rank() {
        return rank;
    }

    // get suit of card as an int in the interval [0, 3]
    public int suit() {
        return suit;
    }

    // true if card is face up, false otherwise
    public boolean faceUp() {
        return faceup;
    }

    // change value of faceup
    public void flip() {
        faceup = !faceup;
    }

    // true if card is ace, false otherwise
    public boolean isAce() {
        return rank == ace;
    }

    // true if card is king, false otherwise
    public boolean isKing() {
        return rank == king;
    }

    // return color of card as an int in the range [0,1]
    public int color() {
        if (suit() == heart || suit() == diamond)
            return red;

        return black;
    }

    // draw the card
    public void draw(Graphics g, int x, int y, boolean marked) {

        // clear rectangle, draw border
        g.clearRect(x, y, width, height);
        if (marked) g.setColor(Color.red);
        else g.setColor(Color.black);
        g.drawRect(x, y, width, height);

        // draw body of card
        if (faceUp()) {
            if (color() == red)
                g.setColor(Color.red);
            else
                g.setColor(Color.black);

            g.drawString(names[rank()], x + 3, y + 15);

            if (suit() == heart) {
                g.drawLine(x + 25, y + 30, x + 35, y + 20);
                g.drawLine(x + 35, y + 20, x + 45, y + 30);
                g.drawLine(x + 45, y + 30, x + 25, y + 60);
                g.drawLine(x + 25, y + 60, x + 5, y + 30);
                g.drawLine(x + 5, y + 30, x + 15, y + 20);
                g.drawLine(x + 15, y + 20, x + 25, y + 30);
            } else if (suit() == spade) {
                g.drawLine(x + 25, y + 20, x + 40, y + 50);
                g.drawLine(x + 40, y + 50, x + 10, y + 50);
                g.drawLine(x + 10, y + 50, x + 25, y + 20);
                g.drawLine(x + 23, y + 45, x + 20, y + 60);
                g.drawLine(x + 20, y + 60, x + 30, y + 60);
                g.drawLine(x + 30, y + 60, x + 27, y + 45);
            } else if (suit() == diamond) {
                g.drawLine(x + 25, y + 20, x + 40, y + 40);
                g.drawLine(x + 40, y + 40, x + 25, y + 60);
                g.drawLine(x + 25, y + 60, x + 10, y + 40);
                g.drawLine(x + 10, y + 40, x + 25, y + 20);
            } else if (suit() == club) {
                g.drawOval(x + 20, y + 25, 10, 10);
                g.drawOval(x + 25, y + 35, 10, 10);
                g.drawOval(x + 15, y + 35, 10, 10);
                g.drawLine(x + 23, y + 45, x + 20, y + 55);
                g.drawLine(x + 20, y + 55, x + 30, y + 55);
                g.drawLine(x + 30, y + 55, x + 27, y + 45);
            }
        } else // face down
        {
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(x + 15, y + 5, x + 15, y + 65);
            g.drawLine(x + 35, y + 5, x + 35, y + 65);
            g.drawLine(x + 5, y + 20, x + 45, y + 20);
            g.drawLine(x + 5, y + 35, x + 45, y + 35);
            g.drawLine(x + 5, y + 50, x + 45, y + 50);
        }
    }
}

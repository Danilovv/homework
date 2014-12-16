/*
  Simple Solitaire Card Game in Java
  Written by Tim Budd, Oregon State University, 1996
  
  Modified by Laurentiu Cristofor, UMass Boston, 1998
  */

package com.company;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

///////////////////////////
// Defines Solitaire class
///////////////////////////
public class Solitaire extends Applet {
    final static int no_card_piles      = 13;
    final static int no_suit_piles      = 4;
    final static int no_table_piles     = 7;

    final static int topMargin  = 40;
    final static int leftMargin = 5;
    final static int distTable  = 5;
    final static int distSuit   = 10;

    static DeckPile deckPile;
    static DiscardPile discardPile;
    static TablePile tableau[];
    static SuitPile suitPile[];
    static CardPile allPiles[];

    protected void initialize() {
        // first allocate the arrays
        allPiles = new CardPile[no_card_piles];
        suitPile = new SuitPile[no_suit_piles];
        tableau = new TablePile[no_table_piles];

        // then fill them in
        int xDeck = leftMargin + (no_table_piles - 1) * (Card.width + distTable);
        allPiles[0] = deckPile = new DeckPile(xDeck, topMargin);                        // еще не просмотренные карты (рубашкой вверх)
        allPiles[1] = discardPile = new DiscardPile(xDeck - Card.width - distSuit,      // просмотренные карты (рубашкой вниз)
                topMargin);

        for (int i = 0; i < no_suit_piles; i++)
            allPiles[2 + i] = suitPile[i] =
                    new SuitPile(leftMargin + (Card.width + distSuit) * i, topMargin);

        for (int i = 0; i < no_table_piles; i++)
            allPiles[6 + i] = tableau[i] =
                    new TablePile(leftMargin + (Card.width + distTable) * i,
                            Card.height + distTable + topMargin, i + 1);

        showStatus("Welcome to Solitaire!");
    }

    protected boolean gameEnded() {
        if (!deckPile.empty())
            return false;

        if (!discardPile.empty())
            return false;

        for (int i = 0; i < no_table_piles; i++)
            if (!tableau[i].empty())
                return false;

        // if we reached this point then all piles are empty
        // notify the user in the status bar
        showStatus("Congratulations! You have won this game.");

        return true;
    }

    private int last_x = 0;
    private int last_y = 0;
    private int marked_pile = 0;
    private int selected_pile = 0;


    public void init() {
        // Create a new game button
        Button b = new Button("New game");

        // Define, instantiate, and register a listener to handle button presses
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  // start a new game
                initialize();
                repaint();
            }
        });

        // Add the button to the applet
        add(b);

        // Define, instantiate and register a MouseListener object.
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                allPiles[marked_pile].setNotMark();
                for (int i = 0; i < no_card_piles; i++) {
                    if((allPiles[i].includes(e.getX(),e.getY()) || allPiles[i].includesPile(e.getX(),e.getY())) && i!= selected_pile) {
                        allPiles[i].setMark();
                        marked_pile = i;
                        //paint(getGraphics());
                        repaint();
                    }
                }
                showStatus("selected pile = " + selected_pile + "marked_pile = " + marked_pile);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(mousePressed) {

                }
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (gameEnded())
                    return;

                mousePressed = true;
                showStatus("Hold and drag where u need.. or just press card on deck pile");
                last_x = e.getX();
                last_y = e.getY();

                for (int i = 0; i < no_card_piles; i++) {
                    if (allPiles[i].includes(last_x, last_y)) {
                            allPiles[i].setMark();
                            marked_pile = i;
                            selected_pile = i;
                            repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameEnded())
                    return;

                if (marked_pile != 0) {
                    allPiles[selected_pile].select(e.getX(), e.getY(), marked_pile);
                    repaint();
                } /*else if (marked_pile >= 0 && marked_pile <= 3) {

                }*/ else {
                    allPiles[marked_pile].select(e.getX(), e.getY(), -1);
                }
                showStatus("Press and hold mouseButton" + selected_pile + "  " + marked_pile);

                mousePressed = false;
                allPiles[marked_pile].setNotMark();
                repaint();
            }
        });

        initialize();
    }

    private boolean mousePressed = false;

    public void paint(Graphics g) {
        for (int i = 0; i < no_card_piles; i++)
            allPiles[i].display(g);
    }
}
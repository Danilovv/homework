package com.company;

import java.awt.*;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ /////////////////////////////
// Defines a TablePile class
/////////////////////////////
class TablePile extends CardPile {
    final static int ydist = 25;

    TablePile(int x, int y, int c) {
        // initialize the parent class
        super(x, y);

        // then initialize our pile of cards
        for (int i = 0; i < c; i++)
            addCard(Solitaire.deckPile.pop());

        // flip topmost card face up
        top().flip();
    }

    public boolean canTake(Card aCard) {
        if (empty())
            return aCard.isKing();

        Card topCard = top();

        // if our topmost card is face down
        // we can't accept another card
        if (!topCard.faceUp())
            return false;

        return (aCard.color() != topCard.color()) &&
                (aCard.rank() == topCard.rank() - 1);
    }

    public boolean includes(int tx, int ty) {
        if (empty())
            return false;

        // don't test bottom of card
        return x <= tx && tx <= x + Card.width &&
                y <= ty;
    }

    @Override
    public void select(int tx, int ty, int index) {
        if (empty())
            return;

        // if face down, then flip
        Card topCard = top();
        if (!topCard.faceUp()) {
            topCard.flip();
            return;
        }

        // see if any suit pile can take card
        if (index!=-1) {
            for (int i = 0; i < Solitaire.no_suit_piles; i++)
                if (Solitaire.suitPile[i].canTake(topCard)) {
                    Solitaire.suitPile[i].addCard(pop());
                    return;
                }
        }
        else {
            if (index > Solitaire.no_suit_piles &&
                    Solitaire.allPiles[index].canTake(topCard)) {
                Solitaire.allPiles[index].addCard(pop());
                return;
            }
        }

        // try to create a build
        CardPile build = new CardPile(0, 0);

        // get the cards for the build from the suit pile
        while (!empty()) {
            // stop if we reached a card that is face down
            if (!top().faceUp())
                break;

            build.addCard(pop());
        }

        // We don't allow the user to play a King card
        // that is at the bottom of a table pile
        // to another table pile
        if (build.top().isKing() && empty()) {
            while (!build.empty())
                addCard(build.pop());
            return;
        }

        // if we have to play only one card
        if (build.top() == topCard) {
            // put it back into the table pile
            addCard(build.pop());

            // we have already tried the suit piles
            // see if any other table pile can take card
            if(index==-1) {
                for (int i = 0; i < Solitaire.no_table_piles; i++)
                    if (Solitaire.tableau[i].canTake(topCard)) {
                        Solitaire.tableau[i].addCard(pop());
                        return;
                    }
            }
            else {
                if(index > Solitaire.no_suit_piles + 2) {
                    /*if (Solitaire.tableau[index - Solitaire.no_table_piles + 1].canTake(topCard)) {
                        Solitaire.tableau[index - Solitaire.no_table_piles + 1].addCard(pop());
                        return;
                    }*/
                    if (Solitaire.allPiles[index].canTake(topCard)) {
                        Solitaire.allPiles[index].addCard(pop());
                        return;
                    }
                }
            }
        } else // we got ourselves a build to play
        {
            topCard = build.top();

            // see if any other table pile can take this build
            if(index==-1) {
                for (int i = 0; i < Solitaire.no_table_piles; i++)
                    if (Solitaire.tableau[i].canTake(topCard)) {
                        while (!build.empty())
                            Solitaire.tableau[i].addCard(build.pop());

                        return;
                    }
            }
            else {
                if (Solitaire.tableau[index - Solitaire.no_table_piles + 1].canTake(topCard)) {
                    while (!build.empty())
                        Solitaire.tableau[index - Solitaire.no_table_piles + 1].addCard(build.pop());

                    return;
                }
            }

            // can't play the build?
            // then we must restore our pile
            while (!build.empty())
                addCard(build.pop());
        }
    }

    private void stackDisplay(Graphics g) {
        // holds y-coordinate of cards in pile
        int localy = y;

        LinkedList reverseCardList = new LinkedList();

        // get iterator for our list
        ListIterator iterator = cardList.iterator();

        // build reversed order list
        while (!iterator.atEnd()) {
            reverseCardList.add(iterator.current());
            iterator.next();
        }

        // get iterator for reversed order list
        iterator = reverseCardList.iterator();

        // Go through the reversed order list
        // and draw each card in the list
        while (!iterator.atEnd()) {
            ((Card) iterator.current()).draw(g, x, localy, isMarked());
            localy += ydist;
            iterator.next();
        }
    }

    public void display(Graphics g) {
        stackDisplay(g);
    }
}

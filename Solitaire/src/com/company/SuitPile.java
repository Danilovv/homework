package com.company;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ ////////////////////////////
// Defines a SuitPile class
////////////////////////////
class SuitPile extends CardPile {
    SuitPile(int x, int y) {
        super(x, y);
    }

    public boolean canTake(Card aCard) {
        if (empty())
            return aCard.isAce();

        Card topCard = top();
        return (aCard.suit() == topCard.suit()) &&
                (aCard.rank() == 1 + topCard.rank());
    }

    @Override
    public void select(int tx, int ty, int index) {
        if (empty())
            return;

        Card topCard = top();

        // check the TablePile's
        for (int i = 0; i < Solitaire.no_table_piles; i++)
            if (Solitaire.tableau[i].canTake(topCard)) {
                Solitaire.tableau[i].addCard(pop());
                return;
            }
    }
}

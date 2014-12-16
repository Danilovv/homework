package com.company;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ ///////////////////////////////
// Defines a DiscardPile class
///////////////////////////////
class DiscardPile extends CardPile {
    DiscardPile(int x, int y) {
        super(x, y);
    }

    public void addCard(Card aCard) {
        if (!aCard.faceUp())
            aCard.flip();

        super.addCard(aCard);
    }

    @Override
    public void select(int tx, int ty, int index) {
        if (empty())
            return;

        Card topCard = top();

        // check the SuitPile's first
        for (int i = 0; i < Solitaire.no_suit_piles; i++)
            if (Solitaire.suitPile[i].canTake(topCard)) {
                Solitaire.suitPile[i].addCard(pop());
                return;
            }

            if (Solitaire.allPiles[index].canTake(topCard)) {
                Solitaire.allPiles[index].addCard(pop());
                return;
            }
    }
}

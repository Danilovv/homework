package com.company;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ ////////////////////////////
// Defines a DeckPile class
////////////////////////////
class DeckPile extends CardPile {
    DeckPile(int x, int y) {
        // first initialize parent
        super(x, y);

        // then create the new deck
        // first put them into a local pile
        CardPile pileOne = new CardPile(0, 0);
        CardPile pileTwo = new CardPile(0, 0);

        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j <= 12; j++) {
                pileOne.addCard(new Card(i, j));
                count++;
            }

        // then pull them out randomly
        for (; count > 0; count--) {
            int limit = ((int) (Math.random() * 1000)) % count;

            // move down to a random location in pileOne
            // while poping the cards into pileTwo
            for (int i = 0; i < limit; i++)
                pileTwo.addCard(pileOne.pop());

            // then add the card found there
            // to our LinkedList object: cardList
            addCard(pileOne.pop());

            // now put back into pileOne the cards
            // that we poped into pileTwo
            while (!pileTwo.empty())
                pileOne.addCard(pileTwo.pop());
        }
    }

    @Override
    public void select(int tx, int ty, int index) {
        // if deck becomes empty, refill from discard pile
        if (empty()) {
            while (!Solitaire.discardPile.empty()) {
                addCard(Solitaire.discardPile.pop());
                top().flip();
            }
        } else
            Solitaire.discardPile.addCard(pop());
    }
}

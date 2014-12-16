package com.company;

import java.awt.*;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ //////////////////////////////////////
// Defines a CardPile class
//
// used as a base to build card piles
//////////////////////////////////////
class CardPile {
    // coordinates of the card pile
    protected int x;
    protected int y;

    private boolean marked;

    // linked list of cards
    protected LinkedList cardList;

    CardPile(int xl, int yl) {
        x = xl;
        y = yl;
        cardList = new LinkedList();
        marked = false;
    }

    /////////////////////////////////////
    // access to cards are not overridden

    // true if pile is empty, false otherwise
    public final boolean empty() {
        return cardList.empty();
    }

    public void setMark() {
        marked = true;
    }

    public void setNotMark() {
        marked = false;
    }

    public boolean isMarked() {
        return marked;
    }

    // inspect card at the top of pile
    public final Card top() {
        return (Card) cardList.front();
    }

    // pop card at the top of pile
    public final Card pop() {
        return (Card) cardList.pop();
    }

    /////////////////////////////////////////
    // the following are sometimes overridden

    // true if point falls inside pile, false otherwise
    public boolean includes(int tx, int ty) {
        if(getNoCards()>0) {
            return x <= tx && tx <= x + Card.width &&
                    y <= ty && ty <= y + Card.height;
        }
        else return x <= tx && tx <= x + Card.width &&
                y <= ty && ty <= y + Card.height;

    }

    public boolean includesPile(int tx, int ty) {
        return x <= tx && tx <= x + Card.width &&
                //y <= ty && ty <= y + Card.height;
                ty >= Card.height + Solitaire.topMargin + Solitaire.distTable+3;
    }

    // to be overridden by descendants
    public void select(int tx, int ty, int index){
        // do nothing
    }

    // add a card to pile
    public void addCard(Card aCard) {
        cardList.add(aCard);
    }

    // draw pile
    public void display(Graphics g) {

        g.setColor(Color.black);

        if (cardList.empty())
            g.drawRect(x, y, Card.width, Card.height);
        else
            top().draw(g, x, y, isMarked());
    }

    // to be overridden by descendants
    public boolean canTake(Card aCard) {
        return false;
    }

    // get number of cards in pile
    public int getNoCards() {
        int count = 0;
        ListIterator iterator = cardList.iterator();

        while (!iterator.atEnd()) {
            count++;
            iterator.next();
        }

        return count;
    }
}

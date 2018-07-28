/*
Erik Chacon
    This card class creates a "card" by combining the suit from the Suit class
    and a value from the Value class

*/
public class Card {
    
    //instance variables
    private Suit suit;
    private Value value;
    
    //const that creates a card with two parameters, one value and one suit
    public Card(Suit suit, Value value){
        this.value = value;
        this.suit = suit;
    }
    // to String method that will print out the value and suit of a card
    // ex: "Ace of Spades"
    @Override
    public String toString(){
        return this.value.toString() + " of  " + this.suit.toString();
    }
    
    //gets value of card (getter)
    public Value getValue(){
        return this.value;
    }
            
}

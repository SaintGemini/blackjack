/*
    Erik Chacon
    Deck class creates, initialized decks, and includes methods to create and shuffle
*/


//import arraylist from java api for cards
import java.util.ArrayList;
//import from collections for shuffle method
import java.util.Collections;

public class Deck{
    
    //instance variable
    private final ArrayList<Card> cards;
    
    
    //constructor
    public Deck(){
        this.cards = new ArrayList<>();
    }
    
    // adds card to deck
    // get add method from java.util.ArrayList;
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }
    
    //removes card from whatever deck is called
    //get remove method from java.util.ArrayList;
    public void removeCard(int x){
        this.cards.remove(x);
    }
    
    //gets card
    //gets get method from java.util.ArrayList;
    public Card getCard(int x){
        return this.cards.get(x);
    }
    
      //gets size of deck (int)
      //gets size method from java.util.ArrayList;
      public int getSize() {
        int size = this.cards.size();
        return  size;
     
    }
    
    //make deck using an advanced nested for loop
    public void create52Deck(){
        //for each Suit called "cardsuit" in the Suit class, get values
        for(Suit cardSuit : Suit.values()){
            //and for every Value called "cardValue" in the Value class
            for(Value cardValue : Value.values()){
                //loop through all values with one suit, then repeat with next suit, etc...
                //add new cards with all combinations of one suit and one value
                //using both parameters correctly for Card
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }
    

       //shuffles deck four times using for loop and the shuffle method
       //imported from the Collections api
        public void shuffle(){  
        //shuffles deck four times to insure random variation
        for(int i = 0; i < 4; i++){
            Collections.shuffle(this.cards);
            i++;
            }
        }
        
    
    //draws(adds) top card from a deck and removes it from deck
    public void drawFromDeck(Deck anyDeck){
     //get top card (card 0)
     this.cards.add(anyDeck.getCard(0));  
     //remove card 0 from deck so it is not repeated
     anyDeck.removeCard(0);
    }  
    
    //overloads shuffle method
    //shuffles when discard deck reaches a certain size (every 5 or 6 hands)
    public void shuffle(Deck side){
        System.out.println("\n \n \n Reshuffling deck. Hold please.");
        //draws from discard deck until playdeck has 52 cards
        while(this.getSize() < 52){
            this.drawFromDeck(side);     
            }         
        // for loop that shuffles deck a total of 
        // 9 times for complete randomness
        for (int i = 0; i < 9; i++){
        this.shuffle(); 
        i++;
        }   
    }
}

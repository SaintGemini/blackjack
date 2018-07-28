
import java.util.ArrayList;


/**
Erik Chacon
Hand class creates and initializes hands. Includes methods that draws, discards
* and calculates total value of hands.
 */
public class Hand extends Deck{
    
    
    //instance variable
    private final ArrayList<Card> cards;
    
    //const
    //creates a hand using an arrayList to hold values
    public Hand(){
        this.cards = new ArrayList<>();
    }
    
    //removes card from whatever deck or hand is called
    //get remove method from java.util.ArrayList;
    @Override
    public void removeCard(int x){
        this.cards.remove(x);
    }
    
    // adds card to either a hand or deck (whichever is called)
    // get add method from java.util.ArrayList;
    @Override
    public void addCard(Card card){
    this.cards.add(card);
    }
    
    //gets card int value
    //gets get method from java.util.ArrayList;
    @Override
    public Card getCard(int x){
        return this.cards.get(x);
    }
    
      //gets size of deck for discard deck in main class
    @Override
      public int getSize() {
        int size = this.cards.size();
        return  size;
     
    }
      
    
    //gets total value of hand
    public int handValue(){
        
        int totalHandValue = 0;
        //for all the cards in hand(any amount of cards)...
        for(Card allCards : this.cards){
            int ace = 0;
            //set values
            switch(allCards.getValue()){
                case TWO: totalHandValue += 2; 
                    break;
                case THREE: totalHandValue += 3; 
                    break;
                case FOUR: totalHandValue += 4; 
                    break;
                case FIVE: totalHandValue += 5; 
                    break;
                case SIX: totalHandValue += 6; 
                    break;
                case SEVEN: totalHandValue += 7; 
                    break;
                case EIGHT: totalHandValue += 8; 
                    break;
                case NINE: totalHandValue += 9; 
                    break;
                case TEN: totalHandValue += 10; 
                    break;
                case JACK: totalHandValue += 10; 
                    break;
                case QUEEN: totalHandValue += 10; 
                    break;
                case KING: totalHandValue += 10; 
                    break;
                //satisfies rule that Ace is equal to 11 if hand is equal to or
                //less than 10
                case ACE:  if(totalHandValue > 10){
                              ace = 1;
                              totalHandValue += ace;
                            } else if (totalHandValue <= 10) {
                             ace = 11;
                             totalHandValue += ace;       
                             }
                    break;
            }
        }
        //get value of hand
        return totalHandValue;
    }
    //draws(adds) top card from a deck and removes it from deck
    @Override
    public void drawFromDeck(Deck anyDeck){
     //get top card (card 0)
     this.cards.add(anyDeck.getCard(0));  
     //remove card 0 from deck so it is not repeated
     anyDeck.removeCard(0);
    }  

    //method that puts cards in hand into side deck so cards do not get repeated
    public void discardHand(Deck discard){
        int size = this.cards.size();
        for(int i=0; i < size; i++){
            discard.addCard(this.getCard(i));
            }           
        for(int i=0; i < size; i++){
        this.removeCard(0);
        }
    }   
}

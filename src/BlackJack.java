/*
Erik Chacon
    This BlackJack class is where the main game logic is. 
*/

//import from Scanner class to get user input for game
import java.util.Scanner;
import java.util.InputMismatchException;

public class BlackJack {

    
    public static void main(String[] args) {
        
       //welcome message
       System.out.println("It's time to gamble! Let's play some Blackjack.");
       
       //for user input for in game use
       Scanner input = new Scanner(System.in);
       
       //create single 52 card deck and shuffle it
       Deck playDeck = new Deck();
       playDeck.create52Deck();
       playDeck.shuffle();
       
       //creates hand for player and dealer
       Hand playerHand = new Hand();
       Hand dealerHand = new Hand();
       
       //discard deck for discarding hands after each round
       Deck discardDeck = new Deck();
       
       
       //starts the player with $10,000 in their own "bank"
       int playerBank = 10000;
       
       
       //Game Loop
       // Allows player to play if they have money
          while (playerBank > 0) {           
              
           //asks what the bet is
              System.out.println(" \n You have $" + playerBank + "\n How much do you want to bet?");
         
          
           //gets bet from player with user input with exception handling
           int bet = 0;
              
           while (bet <= 0){
               try{
               bet = input.nextInt();
               if(bet > 0) {
                   break;
               }
               } 
               
               catch (InputMismatchException e){
                   System.out.println("Please only enter numbers!");
                   break;
               }
           }   
           
            //initialize new round boolean
            boolean newRound = false;
           
            
            //if bet is more than what they have in their bank
            if (bet > playerBank) {
                System.out.println("You do not have that much money. Stop waisting my time.");
                break;
            }
            
            //if player tries to bet $0 or less
            if (bet <= 0) {
                System.out.println("Please bet an appriopriate ammount.");
                break;
            }
             
            // booleans for various situations
            boolean playerTurn = true;
            boolean doubleDown = false;
            boolean blackjack = false;
            boolean bust = false;
            boolean dealerBlackjack = false;
            boolean push = false;
           
            
           //start dealing (alternating between player and dealer) (player first)
            playerHand.drawFromDeck(playDeck);
            dealerHand.drawFromDeck(playDeck);
            playerHand.drawFromDeck(playDeck);
            dealerHand.drawFromDeck(playDeck);
            
            //while loop for player turn
            while(playerTurn == true){
                
                //shows player initial hand and the value of the hand
                System.out.println("\n");
                for(int i = 0; i < playerHand.getSize(); i++) {
                System.out.println(playerHand.getCard(i).toString());
                }
                System.out.println("\n You have - " + playerHand.handValue() );
                

             //Shows the first card the dealer was dealt
               System.out.println("\n \n Dealer has - " + dealerHand.getCard(0).toString()
               + " showing");
               
               /*
                    These upcoming three situations I put in front of the player 
                    turn because if they occur within the initial two card draw, 
                    the player does not get a chance
                    to hit, stand or double. The rules of the game take over
                    and a new round starts.
               */
               
               
               
                 // if both player and dealer get blackjack
                 if(playerHand.handValue() == 21 && dealerHand.handValue() == 21){
                    push = true;
                    break;
                }
               
                  //if player gets blackjack
                if(playerHand.handValue() == 21) {
                    System.out.println("Blackjack!");
                    blackjack = true;
                    break;
                    
                }
                
                //if dealer gets blackjack
                if(dealerHand.handValue() == 21) {
                    dealerBlackjack = true;
                    break;
                }
               
               //asks if player wants to hit, stand or double
                System.out.println(" \n \n Would you like to (0)Hit or (1)Stand or (2)Double");
                
                //gets input from player
                int nextMove = 3;
                
               //throws exception if input is not among the choices
               if (nextMove == 3){
               try{
               nextMove = input.nextInt();
               } catch (InputMismatchException e){
                   System.out.println("Please only enter numbers next time!");
                   System.exit(100);
                }
               }
                

                //if they choose to hit, draw another card
                if(nextMove == 0){
                    playerHand.drawFromDeck(playDeck);
               }
                
                
                //if stay, break out of player turn
                if(nextMove == 1) {
                    break;
                }
                
                
                //if double, break out of player turn
                if(nextMove == 2){
                    doubleDown = true;
                    break;                    
                }
               
                //sets default move to stay if player does not input given options
//                if (nextMove != 0 && nextMove != 1 && nextMove != 2){
//                    break;
//                }
                
                
                //if player got 21 with more than 2 cards
                if(playerHand.handValue() == 21 && nextMove == 0) {
                    System.out.println("You got 21!");
                    for(int i = 0; i < playerHand.getSize(); i++) {
                        System.out.println(playerHand.getCard(i).toString());
                    }
                 break;     
                }
                
                //if they bust, go to result
                if (playerHand.handValue() > 21) {           
                    bust = true;
                    break;
                    
                }    
            }//end player turn
            
            
            
             
            
          //if player gets blackjack
          if(blackjack == true){
              //blackjack pays 3:2
              playerBank += (bet * 1.5);
              //System.out.println("BlackJack!");
              newRound = true;
          }
          
          //if dealer got blackjack, player lost bet, new round begins
          if(dealerBlackjack == true) {
              System.out.println("\n Dealer got blackjack!");
              playerBank -= bet;
              newRound = true;
          }
          
          //when player doubles down. Draw one card, double bet, dealer turn
          if(doubleDown == true){
              playerHand.drawFromDeck(playDeck);
              bet = bet * 2;
              //shows entire 3 card hand
              for(int i = 0; i < playerHand.getSize(); i++) {
                System.out.println(playerHand.getCard(i).toString());
            }
              System.out.print("\n You have - " + playerHand.handValue()); 
          }
            
            
            //if player bust
            if (bust == true) {
                   System.out.print("\n You have - " + playerHand.handValue() + "\n");
                   for(int i = 0; i < playerHand.getSize(); i++) {
                    System.out.println(playerHand.getCard(i).toString());
                }
                   System.out.println("\n You bust!");
                   playerBank -= bet;
                   newRound = true;
                }
            
            
            //makes dealer hit until 17
            while(dealerHand.handValue() < 17 && playerHand.handValue() < 21){
                dealerHand.drawFromDeck(playDeck);
            }
            
               //if dealer bust
            if(dealerHand.handValue() > 21 && playerHand.handValue() <= 21) {
                playerBank += bet;
                System.out.println("\n \n Dealer bust! Here is your money.");
                newRound = true;
                }
            
    
          
            //if round ends in push
            if(playerHand.handValue() == dealerHand.handValue() ||
                    push == true){
                System.out.println("\n \n It's a push! Let's begin a new round.");
                newRound = true;
            }        
            
            
            //if player wins round
            if(playerHand.handValue() > dealerHand.handValue()
                    && playerHand.handValue() <=21){
                System.out.println("\n You win! Here is your money!");
                playerBank += bet;
                newRound = true;                
            }
            
            
            //if player lost round
           if(dealerHand.handValue() > playerHand.handValue() 
                   && dealerHand.handValue() <= 21 && dealerBlackjack == false) {
               System.out.println("\n \n You lost!");
               // System.out.print(playerHand.handValue());
               playerBank -= bet;
               newRound = true;
           }

            //show dealers cards
            System.out.println("\n Dealer has - " + dealerHand.handValue() + "\n");
            for(int i = 0; i < dealerHand.getSize(); i++) {
                System.out.println(dealerHand.getCard(i));
            }
            
            
            //discards cards in hands to discard deck
            playerHand.discardHand(discardDeck);
            dealerHand.discardHand(discardDeck);
          
            
            /*
                shuffles playdeck every 5 or 6 of hands by getting cards back 
                from discard deck, then shuffling
            */
            if(discardDeck.getSize() >= 25){
            playDeck.shuffle(discardDeck);
            newRound = true;
            }
            
        } //end while(playerBank > 0) loop
          
          
            //prints message when player runs out of money (game ends)
            System.out.println("\n \n Come back when you have cash!");
       

    }
    
}

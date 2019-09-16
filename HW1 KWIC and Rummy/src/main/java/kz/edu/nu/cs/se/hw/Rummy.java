package kz.edu.nu.cs.se.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Starter code for a class that implements the <code>PlayableRummy</code>
 * interface. A constructor signature has been added, and method stubs have been
 * generated automatically in eclipse.
 * 
 * Before coding you should verify that you are able to run the accompanying
 * JUnit test suite <code>TestRummyCode</code>. Most of the unit tests will fail
 * initially.
 * 
 * @see PlayableRummy
 * @see TestRummyCode
 *
 */
public class Rummy implements PlayableRummy {
	
	private String[] players;
	private Steps step;
	private int currentPlayer;
	private String[] suits = { "C", "D", "H", "S", "M" };
	private String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
	private ArrayList<String> cardsInDeck = new ArrayList<String>();
	private ArrayList<String> cardsInDiscardPile =new ArrayList<String>();
	private Map <Integer, String[]> handOfPlayer = new HashMap<Integer, String[]>();
	private ArrayList<String[]> melds = new ArrayList<String[]>();
	private int winner;
	private String lastFromDP = new String();
	private char[] validSequence = {'A', '2', '3', '4', '5', '6', '7', '8', '9', '1','J', 'Q', 'K', 'A'};
	
    public Rummy(String... players) {
    		this.players = players;
    		if (players.length < 2) {
            	throw new RummyException("Not enough players", RummyException.NOT_ENOUGH_PLAYERS);
            }
            if (players.length > 6) {
            	throw new RummyException("Too many players", RummyException.EXPECTED_FEWER_PLAYERS);
            }
    		step = Steps.WAITING;
    		for (String rank : ranks) {
    			for (String suit : suits) {
    				cardsInDeck.add(rank + suit);
    			}
    		}
    }

    @Override
    public String[] getPlayers() {
        return this.players;
    }

    @Override
    public int getNumPlayers() {
        int numPlayers = players.length;
        return numPlayers;
    }

    @Override
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public int getNumCardsInDeck() {
        return cardsInDeck.size();
    }

    @Override
    public int getNumCardsInDiscardPile() {
        return cardsInDiscardPile.size();
    }

    @Override
    public String getTopCardOfDiscardPile() {
    	if (cardsInDiscardPile.size() == 0) {
    		throw new RummyException();
    	}
        return cardsInDiscardPile.get(0);
    }

    @Override
    public String[] getHandOfPlayer(int player) {
    	if (player >= players.length) {
			throw new RummyException("Not valid index of player", RummyException.NOT_VALID_INDEX_OF_PLAYER);
    	}
        return handOfPlayer.get(player);
    }

    @Override
    public int getNumMelds() {
        return melds.size();
    }

    @Override
    public String[] getMeld(int i) {
    	if (i >= melds.size()) {
    		throw new RummyException("Not valid index of meld", RummyException.NOT_VALID_INDEX_OF_MELD);
    	}
        return melds.get(i);
    }

    @Override
    public void rearrange(String card) {
        if (step != Steps.WAITING) {
        	throw new RummyException("Expected waiting step", RummyException.EXPECTED_WAITING_STEP);
        }
        cardsInDeck.remove(card);
        cardsInDeck.add(0, card);
    }

    @Override
    public void shuffle(Long l) {
    	if (step != Steps.WAITING) {
        	throw new RummyException("Expected waiting step", RummyException.EXPECTED_WAITING_STEP);
        }
    	Random shuffle = new Random(l);
    	for (int i = 0; i < cardsInDeck.size(); i++) {
		    int randomPosition = shuffle.nextInt(cardsInDeck.size());
		    String temp = cardsInDeck.get(i);
		    cardsInDeck.set(i,cardsInDeck.get(randomPosition));
		    cardsInDeck.set(randomPosition,temp);
    	}
    }

    @Override
    public Steps getCurrentStep() {
        return step;
    }

    @Override
    public int isFinished() {
        if (step == Steps.FINISHED) {
        	return winner;
        }
        return -1;
    }

    @Override
    public void initialDeal() {
    	int numberOfCards;
    	if (step != Steps.WAITING) {
        	throw new RummyException("Expected waiting step", RummyException.EXPECTED_WAITING_STEP);
        }
        currentPlayer = 0; 
        if (players.length == 2) {
        	numberOfCards = 10;
        	String[] hands1 = new String[10];
        	String[] hands2 = new String[10];
        	for (int i = 0; i < numberOfCards; i++) {
        		hands1[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands2[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
             }
        	handOfPlayer.put(0, hands1);
        	handOfPlayer.put(1, hands2);
        	
        }
        else if (players.length == 3) {
        	numberOfCards = 7;
        	String[] hands1 = new String[7];
        	String[] hands2 = new String[7];
        	String[] hands3 = new String[7]; 
        	for (int i = 0; i < numberOfCards; i++) {
        		hands1[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands2[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands3[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
             }
        	handOfPlayer.put(0, hands1);
        	handOfPlayer.put(1, hands2);
        	handOfPlayer.put(2, hands3);
        }
        else if (players.length == 4) {
        	numberOfCards = 7;
        	String[] hands1 = new String[7];
        	String[] hands2 = new String[7];
        	String[] hands3 = new String[7];
        	String[] hands4 = new String[7];
        	for (int i = 0; i < numberOfCards; i++) {
        		hands1[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands2[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands3[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands4[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
             }
        	handOfPlayer.put(0, hands1);
        	handOfPlayer.put(1, hands2);
        	handOfPlayer.put(2, hands3);
        	handOfPlayer.put(3, hands4);
        }
        else if (players.length == 5) {
        	numberOfCards = 6;
        	String[] hands1 = new String[6];
        	String[] hands2 = new String[6];
        	String[] hands3 = new String[6];
        	String[] hands4 = new String[6];
        	String[] hands5 = new String[6];
        	for (int i = 0; i < numberOfCards; i++) {
        		hands1[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands2[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands3[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands4[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands5[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
             }
        	handOfPlayer.put(0, hands1);
        	handOfPlayer.put(1, hands2);
        	handOfPlayer.put(2, hands3);
        	handOfPlayer.put(3, hands4);
        	handOfPlayer.put(4, hands5);
        }
        else {
        	numberOfCards = 6;
        	String[] hands1 = new String[6];
        	String[] hands2 = new String[6];
        	String[] hands3 = new String[6];
        	String[] hands4 = new String[6];
        	String[] hands5 = new String[6];
        	String[] hands6 = new String[6];
        	for (int i = 0; i < numberOfCards; i++) {
        		hands1[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands2[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands3[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands4[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands5[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
        		hands6[i] = cardsInDeck.get(0);
        		cardsInDeck.remove(0);
             }
        	handOfPlayer.put(0, hands1);
        	handOfPlayer.put(1, hands2);
        	handOfPlayer.put(2, hands3);
        	handOfPlayer.put(3, hands4);
        	handOfPlayer.put(4, hands5);
        	handOfPlayer.put(5, hands6);
        	
        }        
//        for (int i = 0; i < players.length; i++) {
//        	String[] hand = new String[numberOfCards];
//        	for (int j = 0; j < numberOfCards; j++) {
//        		hand[j] = cardsInDeck.get(0);
//        		cardsInDeck.remove(0);
//        	}
//        	handOfPlayer.put(i, hand); 
//        }
        cardsInDiscardPile.add(0, cardsInDeck.get(0));
        cardsInDeck.remove(0);
        step = Steps.DRAW;   
    }

    @Override
    public void drawFromDiscard() {
    	if (step != Steps.DRAW) {
        	throw new RummyException("Expected draw step", RummyException.EXPECTED_DRAW_STEP);
    	}
    	String[] oldHand = this.getHandOfPlayer(currentPlayer);
    	String[] newHand = new String[oldHand.length + 1];
    	newHand[0] = cardsInDiscardPile.get(0);
    	lastFromDP = cardsInDiscardPile.get(0);
    	cardsInDiscardPile.remove(0);
    	for (int i = 0; i < oldHand.length; i++) {
    		newHand[i+1] = oldHand[i];
    	}
        handOfPlayer.replace(currentPlayer, newHand);
        step = Steps.MELD;
    }

    @Override
    public void drawFromDeck() {
    	if (step != Steps.DRAW) {
        	throw new RummyException("Expected draw step", RummyException.EXPECTED_DRAW_STEP);
    	}
    	String[] oldHand = this.getHandOfPlayer(currentPlayer);
    	String[] newHand = new String[oldHand.length + 1];
    	if (cardsInDeck.size() == 0) {
    		cardsInDeck = cardsInDiscardPile;
    		cardsInDeck.clear();
    		Collections.reverse(cardsInDeck);	
    	}
    	newHand[0] = cardsInDeck.get(0);
    	cardsInDeck.remove(0);
    	for (int i = 0; i < oldHand.length; i++) {
    		newHand[i+1] = oldHand[i];
    	}
        handOfPlayer.replace(currentPlayer, newHand);
        step = Steps.MELD;
        
    }

    @Override
    public void meld(String... cards) {
    	if (step != Steps.MELD && step != Steps.RUMMY) {
        	throw new RummyException("Expected meld or rummy step", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    	}
    	if (cards.length < 3 || cards.length > 13) {
    		throw new RummyException("Not valid meld", RummyException.NOT_VALID_MELD);
    	}
    	
    	boolean isValid = true;

    	if (cards[0].charAt(0) == cards[1].charAt(0)) {
    		for (int i = 2; i < cards.length; i++) {
    			if ((cards[i].charAt(0) != cards[1].charAt(0))){
    				isValid = false;
    				break;
    			}
    		}
    		if (isValid == false) {
    			throw new RummyException("Not valid meld", RummyException.NOT_VALID_MELD);
    		}
    	}
    	
    	if (cards[0].charAt(1) == cards[1].charAt(1)) {
    		for (int i = 2; i < cards.length; i++) {
    			if ((cards[i].charAt(1) != cards[1].charAt(1))){
    				isValid = false;
    				break;
    			}
    		}
    		if (isValid == false) {
    			throw new RummyException("Not valid meld", RummyException.NOT_VALID_MELD);
    		}
    		
    		int startedIndex = 0;
    		for (int i = 0; i < validSequence.length; i++) {
    			if(validSequence[i] == cards[0].charAt(0)) {
    				startedIndex = i;
    				break;
    			}
    		}
    		int index = 0;
    		for (int i = startedIndex; i < cards.length + startedIndex-1; i++) {
				if(validSequence[i] != cards[index].charAt(0)) {
    				isValid = false;
    				break;
    			} index++;
    		}
    		if (isValid == false) {
    			throw new RummyException("Not valid meld", RummyException.NOT_VALID_MELD);
    		}
    	}
    		
    	String[] oldHand = this.getHandOfPlayer(currentPlayer);
    	boolean inHand;
    	for (String card : cards) {
    		inHand = false;
    		for (String cur : oldHand) {
    			if (card == cur) {
    				inHand = true;
    			}
    		}if (inHand == false) {
    			throw new RummyException("Not in hand", RummyException.EXPECTED_CARDS);
    		}
    	}    	
    	melds.add(0, cards);
    	for (String card : cards) {
    		int index = 0;
    		for (String cur : oldHand) {
    			if (card == cur) {
    				for(int i = index; i < oldHand.length -1; i++){
    		            oldHand[i] = oldHand[i + 1];
    		    	}
    			}
    			index += 1;
    		}
    		handOfPlayer.replace(currentPlayer, oldHand);
    	}
    }

    @Override
    public void addToMeld(int meldIndex, String... cards) {
    	if (step != Steps.MELD && step != Steps.RUMMY) {
        	throw new RummyException("Expected meld or rummy step", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    	}
    	if (meldIndex >= melds.size()) {
    		throw new RummyException("Not valid index of meld", RummyException.NOT_VALID_INDEX_OF_MELD);
    	}
    	String[] meldToAdd = melds.get(meldIndex);
    	boolean isSameRank = false;
    	if (meldToAdd[0].charAt(0) == meldToAdd[1].charAt(0)) {
    		isSameRank = true;
    		for (String card : cards) {
    			if(card.charAt(0) != meldToAdd[0].charAt(0)) {
    				throw new RummyException("Not valid meld", RummyException.NOT_VALID_MELD);
    			}
    		}
    	}
    	if (meldToAdd[0].charAt(1) == meldToAdd[1].charAt(1)) {
    		for (String card : cards) {
    			if(card.charAt(1) != meldToAdd[0].charAt(1)) {
    				throw new RummyException("Not valid meld", RummyException.NOT_VALID_MELD);
    			}
    		}
    		
    	}
    	
    	String[] oldHand = this.getHandOfPlayer(currentPlayer);
    	boolean inHand;
    	for (String card : cards) {
    		inHand = false;
    		for (String cur : oldHand) {
    			if (card == cur) {
    				inHand = true;
    			}
    		}if (inHand == false) {
    			throw new RummyException("Not in hand", RummyException.EXPECTED_CARDS);
    		}
    	}
    	int length = meldToAdd.length + cards.length;
    	String[] updatedMeld = new String[length];
        int pos = 0;
        for (String element : meldToAdd) {
        	updatedMeld[pos] = element;
            pos++;
        }
        for (String element : cards) {
        	updatedMeld[pos] = element;
            pos++;
        }
    	
    	if (isSameRank == true) {
    		melds.set(meldIndex, updatedMeld);
    	}
    	else {
    		char start = meldToAdd[0].charAt(0);
    		char end = meldToAdd[meldToAdd.length -1].charAt(0);
    	}
    	for (String card : cards) {
    		int index = 0;
    		for (String cur : oldHand) {
    			if (card == cur) {
    				for(int i = index; i < oldHand.length -1; i++){
    		            oldHand[i] = oldHand[i + 1];
    		    	}
    			}
    			index += 1;
    		}
    		handOfPlayer.replace(currentPlayer, oldHand);
    	}
        
    }

    @Override
    public void declareRummy() {
    	if (step != Steps.MELD) {
        	throw new RummyException("Expected meld step", RummyException.EXPECTED_MELD_STEP);
    	}
    	step = Steps.RUMMY;      
    	// TODO Auto-generated method stub
    }

    @Override
    public void finishMeld() {
    	if (step != Steps.MELD && step != Steps.RUMMY) {
        	throw new RummyException("Expected meld or rummy step", RummyException.EXPECTED_MELD_STEP_OR_RUMMY_STEP);
    	}
    	// TODO Auto-generated method stub
    	
    	
    	step = Steps.DISCARD;  
    }

    @Override
    public void discard(String card) {
    	if (step != Steps.DISCARD) {
        	throw new RummyException("Expected discard step", RummyException.EXPECTED_DISCARD_STEP);
    	}
    	if (card == lastFromDP) {
    		throw new RummyException("Not valid discard", RummyException.NOT_VALID_DISCARD);
    	}
    	String[] oldHand = this.getHandOfPlayer(currentPlayer);
    	boolean isInHand = false;
    	int index = 0;
    	int aimIndex = 0;
    	for (String cur : oldHand) {
    		if (cur == card) {
    			isInHand = true;
    			aimIndex = index;
    		}
    		index += 1;
    	}
    	if (isInHand == false) {
    		throw new RummyException("Expected cards", RummyException.EXPECTED_CARDS);
    	}
    	for(int i = aimIndex; i < oldHand.length -1; i++){
            oldHand[i] = oldHand[i + 1];
    	}
    	handOfPlayer.replace(currentPlayer, oldHand);
    	cardsInDiscardPile.add(0, card);
    	step = Steps.DRAW;
    	if (currentPlayer == players.length - 1) {
        	currentPlayer = 0;
        } else {
            currentPlayer += 1;
        }
        
    }
    
    

}

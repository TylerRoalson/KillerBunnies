package KillerBunnies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KillerBunnies {
	
	static ArrayList<String> deck = new ArrayList<String>();
	static ArrayList<String> carrotCardDeck = new ArrayList<String>();
	static ArrayList<String> smallCarrotCardDeck = new ArrayList<String>();
	static ArrayList<String> kaballaMarket = new ArrayList<String>();
	static ArrayList<String> playedRunCards = new ArrayList<String>();
	static ArrayList<String> killedBunnies = new ArrayList<String>();
	static ArrayList<String> usedMoney = new ArrayList<String>();
	static ArrayList<String> usedWeapons = new ArrayList<String>();
	static ArrayList<String> obductedBunny = new ArrayList<String>();
	
	static ArrayList<String> compCards = new ArrayList<String>();
	static ArrayList<String> compRunCards = new ArrayList<String>();
	static ArrayList<String> compCarrotCards = new ArrayList<String>();
	static ArrayList<String> compBunnies = new ArrayList<String>();
	static int compDolla = 0;
	
	static ArrayList<String> playerCards = new ArrayList<String>();
	static ArrayList<String> playerRunCards = new ArrayList<String>();
	static ArrayList<String> playerCarrotCards = new ArrayList<String>();
	static ArrayList<String> playerBunnies = new ArrayList<String>();
	static int playerDolla = 0;
	
	static Random random = new Random();
	static Boolean game = true;
	static Scanner s;
	
	public static void main(String[] args) {
		setUp();
		while (game) {
			player();
			computer();
		}
		s.close();
		endGame();
	}
	
	public static void setUp() {
		
		ArrayList<String> deckTemp = new ArrayList<String>();
		ArrayList<String> carrotCardDeckTemp = new ArrayList<String>();
		ArrayList<String> smallCarrotCardDeckTemp = new ArrayList<String>();
		
		int randomCardIndex = 0;
		kaballaMarket.add("3 Dolla - for each Cabbage Card");
		kaballaMarket.add("3 Dolla - for each Water Card");
		kaballaMarket.add("10 Dolla - for each Carrot Card");
		
		try {
			s = new Scanner(new File("deck.txt"));
			while (s.hasNextLine()){
			    deck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int originalSize = deck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(deck.size());
			deckTemp.add(deck.get(randomCardIndex));
			deck.remove(randomCardIndex);
		}
		deck = deckTemp;
		//System.out.println(deck);
		
		try {
			s = new Scanner(new File("carrot.txt"));
			while (s.hasNextLine()){
				carrotCardDeck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		originalSize = carrotCardDeck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(carrotCardDeck.size());
			carrotCardDeckTemp.add(carrotCardDeck.get(randomCardIndex));
			carrotCardDeck.remove(randomCardIndex);
		}
		carrotCardDeck = carrotCardDeckTemp;
		
		try {
			s = new Scanner(new File("carrot.txt"));
			while (s.hasNextLine()){
				smallCarrotCardDeck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		originalSize = smallCarrotCardDeck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(smallCarrotCardDeck.size());
			smallCarrotCardDeckTemp.add(smallCarrotCardDeck.get(randomCardIndex));
			smallCarrotCardDeck.remove(randomCardIndex);
		}
		smallCarrotCardDeck = smallCarrotCardDeckTemp;
		int cardId = 0;
		while (playerCards.size() < 7) {
			cardId = Integer.parseInt(deck.get(0).substring(0, 3));
			if (cardId > 86 && cardId < 102) {
				playerDolla += Integer.parseInt(deck.get(0).substring(6, 8));
			}
			else {
				playerCards.add(deck.get(0));
			}
			deck.remove(0);
		}
		while (compCards.size() < 7) {
			cardId = Integer.parseInt(deck.get(0).substring(0, 3));
			if (cardId > 86 && cardId < 102) {
				compDolla += Integer.parseInt(deck.get(0).substring(6, 8));
			}
			else {
				compCards.add(deck.get(0));
			}
			deck.remove(0);
		}
		for (int i = 0; i < 2; i++) {
			int randomSelect = random.nextInt(compCards.size());
			compRunCards.add(i, compCards.get(randomSelect));
			compCards.remove(randomSelect);
		}
		System.out.println("Kaballa's Market: " + kaballaMarket);
		pause(3);
		System.out.println("Computer's Hand: " + compCards);
		pause(3);
		System.out.println("Computer's Run Cards: " + compRunCards);
		pause(3);
		if (compDolla > 0) {
			System.out.println("Computer's Dolla: " + compDolla);
			pause(3);
		}
		if (playerDolla > 0) {
			System.out.println("Player's Dolla: " + playerDolla);
			pause(3);
		}
		System.out.println("Player's Hand: " + playerCards);
		pause(3);
		s = new Scanner(System.in);
		System.out.println("Which card do you want your top run card to be? (1 - first card, 7 - last card)");
		int choice = s.nextInt() - 1;
		playerRunCards.add(0, playerCards.get(choice));
		playerCards.remove(choice);
		System.out.println("Player's Hand: " + playerCards);
		pause(3);
		System.out.println("Which card do you want your bottom run card to be? (1 - first card, 6 - last card)");
		pause(3);
		choice = s.nextInt() - 1;
		playerRunCards.add(1, playerCards.get(choice));
		playerCards.remove(choice);
		System.out.println("Player's Run Cards: " + playerRunCards);
		pause(3);
	}
	
	public static void player() {
		//clearScreen();
		System.out.println("");
		System.out.println("Player's Turn");
		pause(3);
		System.out.println("You played: " + playerRunCards.get(0));
		pause(3);
		int cardId = 0, choice = 0;
		cardId = Integer.parseInt(playerRunCards.get(0).substring(0, 3));
		if (cardId <= 15) {
			playerBunnies.add(playerRunCards.get(0));
			System.out.println("Your Bunnies: " + playerBunnies);
			pause(3);
		}
		else if (cardId > 15 && cardId < 21 && playerBunnies.size() != 0) {
			playerCarrotCards.add(carrotCardDeck.get(0));
			carrotCardDeck.remove(0);
			System.out.println("Your " + playerCarrotCards.size() + " Carrots: " + playerCarrotCards);
			pause(3);
		}
		else if (cardId > 20 && cardId < 24 && playerBunnies.size() != 0) {
			playerCarrotCards.add(carrotCardDeck.get(0));
			carrotCardDeck.remove(0);
			if (carrotCardDeck.size() > 0) {
				playerCarrotCards.add(carrotCardDeck.get(0));
				carrotCardDeck.remove(0);
			}
			System.out.println("Your " + playerCarrotCards.size() + " Carrots: " + playerCarrotCards);
			pause(3);
		}
		else if (cardId > 30 && cardId < 49 && playerBunnies.size() != 0) {
			int level = Integer.parseInt(playerRunCards.get(0).substring(19, 21));
			usedWeapons.add(playerRunCards.get(0));
			if (compBunnies.size() != 0) {
				System.out.println("Computer's Bunnies: " + compBunnies);
				pause(3);
				System.out.println("Which bunny would you like to target? (1 - first, " + compBunnies.size() + " - last card)");
				int target = s.nextInt() - 1;
				pause(3);
				System.out.println("You targeted " + compBunnies.get(target));
				pause(3);
				System.out.println("They must roll higher than a " + level);
				pause(3);
				System.out.print("They rolled a...");
				pause(5);
				int compRoll = random.nextInt(12) + 1;
				System.out.println(compRoll);
				pause(3);
				if (compRoll > level) {
					System.out.println("It survived");
				}
				else {
					System.out.println("It died");
					killedBunnies.add(compBunnies.get(target));
					compBunnies.remove(target);
				}
			}
		}
		if (carrotCardDeck.size() == 0) {
			game = false;
			return;
		}
		playedRunCards.add(playerRunCards.get(0));
		playerRunCards.remove(0);
		if (deck.size() == 0) {
			reshuffle();
		}
		System.out.println("You drew: " + deck.get(0));
		pause(3);
		playerCards.add(deck.get(0));
		deck.remove(0);
		System.out.println("Your Hand: " + playerCards);
		pause(3);
		System.out.println("Your Top Run Card: " + playerRunCards);
		pause(3);
		s = new Scanner(System.in);
		System.out.println("Which card do you want your bottom run card to be? (1 - first card, 6 - last card)");
		choice = s.nextInt() - 1;
		playerRunCards.add(1, playerCards.get(choice));
		playerCards.remove(choice);
	}
	
	public static void computer() {
		//clearScreen();
		if (carrotCardDeck.size() == 0) {
			return;
		}
		System.out.println("");
		System.out.println("Computer's Turn");
		pause(3);
		System.out.println("They played: " + compRunCards.get(0));
		pause(3);
		int cardId = 0, choice = 0;
		cardId = Integer.parseInt(compRunCards.get(0).substring(0, 3));
		if (cardId <= 15) {
			compBunnies.add(compRunCards.get(0));
			System.out.println("Their Bunnies: " + compBunnies);
			pause(3);
		}
		else if (cardId > 15 && cardId < 21 && compBunnies.size() != 0) {
			compCarrotCards.add(carrotCardDeck.get(0));
			carrotCardDeck.remove(0);
			System.out.println("Their " + compCarrotCards.size() + " Carrots: " + compCarrotCards);
			pause(3);
		}
		else if (cardId > 20 && cardId < 24 && compBunnies.size() != 0) {
			compCarrotCards.add(carrotCardDeck.get(0));
			carrotCardDeck.remove(0);
			if (carrotCardDeck.size() > 0) {
				compCarrotCards.add(carrotCardDeck.get(0));
				carrotCardDeck.remove(0);
			}
			System.out.println("Their " + compCarrotCards.size() + " Carrots: " + compCarrotCards);
			pause(3);
		}
		else if (cardId > 30 && cardId < 49 && compBunnies.size() != 0) {
			int level = Integer.parseInt(compRunCards.get(0).substring(19, 21));
			usedWeapons.add(compRunCards.get(0));
			if (playerBunnies.size() != 0) {
				int randomTarget = random.nextInt(playerBunnies.size());
				System.out.println("They target " + playerBunnies.get(randomTarget));
				pause(3);
				System.out.println("You must roll higher than a " + level);
				pause(3);
				System.out.println("You rolled a...");
				pause(5);
				int playerRoll = random.nextInt(12) + 1;
				System.out.print(playerRoll);
				pause(3);
				if (playerRoll > level) {
					System.out.println("It survived");
				}
				else {
					System.out.println("It died");
					killedBunnies.add(playerBunnies.get(randomTarget));
					playerBunnies.remove(randomTarget);
				}
				pause(3);
			}
		}
		if (carrotCardDeck.size() == 0) {
			game = false;
			return;
		}
		playedRunCards.add(compRunCards.get(0));
		compRunCards.remove(0);
		if (deck.size() == 0) {
			reshuffle();
		}
		System.out.println("They drew: " + deck.get(0));
		pause(3);
		compCards.add(deck.get(0));
		deck.remove(0);
		System.out.println("Computer's Hand: " + compCards);
		pause(3);
		System.out.println("Computer Thinks...");
		pause(3);
		choice = random.nextInt(compCards.size());
		compRunCards.add(1, compCards.get(choice));
		compCards.remove(choice);
		System.out.println("Computer's Run Cards: " + compRunCards);
		pause(3);
	}
	
	public static void reshuffle() {
		deck.addAll(playedRunCards);
		ArrayList<String> deckTemp = new ArrayList<String>();
		int originalSize = deck.size(), randomCardIndex;
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(deck.size());
			deckTemp.add(deck.get(randomCardIndex));
			deck.remove(randomCardIndex);
		}
		deck = deckTemp;
	}
	
	public static void removeCarrot() {
		for (int i = 0; i < compCarrotCards.size(); i++) {
			if (smallCarrotCardDeck.get(0).equals(compCarrotCards.get(i))) {
				compCarrotCards.remove(i);
			}
		}
		for (int i = 0; i < playerCarrotCards.size(); i++) {
			if (smallCarrotCardDeck.get(0).equals(playerCarrotCards.get(i))) {
				playerCarrotCards.remove(i);
			}
		}
	}
	
	public static void pause(int amount) {
		int a = amount;
		try {
		    Thread.sleep(a*1000);
		}
		catch (InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 31; i++) {
			System.out.println("");
		}
	}
	
	public static void endGame() {
		System.out.println("");
		System.out.println("Game Over!");
		if (compBunnies.size() != 0 && playerBunnies.size() != 0) {
			goodGame();
		}
		else if (compBunnies.size() == 0 && playerBunnies.size() != 0) {
			poorComp();
		}
		else if (compBunnies.size() != 0 && playerBunnies.size() == 0) {
			poorPlayer();
		}
		else {
			badGame();
		}
	}
	
	public static void goodGame() {
		System.out.println("Computer's Bunnies: " + compBunnies);
		System.out.println("Player's Bunnies: " + playerBunnies);
		carrotAnnouncements();
		if (playerCarrotCards.size() > compCarrotCards.size()) {
			System.out.println("Player Wins!");
		}
		else {
			System.out.println("Computer Wins!");
		}
	}
	
	public static void poorComp() {
		System.out.println("Computer Had No Bunnies...");
		pause(3);
		System.out.println("So Their Carrots Go To You!");
		playerCarrotCards.addAll(compCarrotCards);
		compCarrotCards.removeAll(compCarrotCards);
		pause(3);
		System.out.println("Player's Bunnies: " + playerBunnies);
		carrotAnnouncements();
		System.out.println("Player Wins!");
	}
	
	public static void poorPlayer() {
		System.out.println("You Had No Bunnies...");
		pause(3);
		System.out.println("Sadly, Your Carrots Go To Them!");
		compCarrotCards.addAll(playerCarrotCards);
		playerCarrotCards.removeAll(playerCarrotCards);
		pause(3);
		System.out.println("Computer's Bunnies: " + compBunnies);
		carrotAnnouncements();
		System.out.println("Computer Wins!");
	}
	
	public static void badGame() {
		System.out.println("Sadly, No One Had Bunnies...");
		pause(3);
		System.out.println("So No One Wins, But Let's See Who Would've!");
		pause(3);
		carrotAnnouncements();
		if (playerCarrotCards.size() > compCarrotCards.size()) {
			System.out.println("Player Would've Won!");
		}
		else {
			System.out.println("Computer Would've Won!");
		}
	}
	
	public static void carrotAnnouncements() {
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				System.out.println("Computer's Carrots: " + compCarrotCards);
				pause(1);
				System.out.println("Player's Carrots: " + playerCarrotCards);
				pause(1);
				System.out.println("1st Carrot Out: " + smallCarrotCardDeck.get(0));
				removeCarrot();
				smallCarrotCardDeck.remove(0);
				pause(5);
			}
			else if (i == 6) {
				System.out.println("Computer's Carrots: " + compCarrotCards);
				pause(1);
				System.out.println("Player's Carrots: " + playerCarrotCards);
				pause(1);
				System.out.println("Last Carrot Out: " + smallCarrotCardDeck.get(0));
				removeCarrot();
				smallCarrotCardDeck.remove(0);
				pause(5);
			}
			else {
				System.out.println("Computer's Carrots: " + compCarrotCards);
				pause(1);
				System.out.println("Player's Carrots: " + playerCarrotCards);
				pause(1);
				System.out.println("Next Carrot Out: " + smallCarrotCardDeck.get(0));
				removeCarrot();
				smallCarrotCardDeck.remove(0);
				pause(5);
			}
		}
		System.out.println("Winning Carrot: " + smallCarrotCardDeck.get(0));
		pause(3);
	}
}
package KillerBunnies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KillerBunnies {
	
	//Global 'decks' or arrays
	static ArrayList<String> deck = new ArrayList<String>();
	static ArrayList<String> carrotDeck = new ArrayList<String>();
	static ArrayList<String> smallCarrotDeck = new ArrayList<String>();
	static ArrayList<String> playedRunCards = new ArrayList<String>();
	static ArrayList<String> killedBunnies = new ArrayList<String>();
	static ArrayList<String> usedWeapons = new ArrayList<String>();
	static ArrayList<String> usedCabbage = new ArrayList<String>();
	static ArrayList<String> usedWater = new ArrayList<String>();
	static ArrayList<String> cabbageDeck = new ArrayList<String>();
	static ArrayList<String> waterDeck = new ArrayList<String>();
	//static ArrayList<String> obductedBunny = new ArrayList<String>();
	
	//Computer's arrays and variables
	static ArrayList<String> compCards = new ArrayList<String>();
	static ArrayList<String> compRunCards = new ArrayList<String>();
	static ArrayList<String> compCarrots = new ArrayList<String>();
	static ArrayList<String> compBunnies = new ArrayList<String>();
	static double compCabbage = 0.0;
	static double compWater = 0.0;
	static double compDolla = 0.0;
	
	//Player's arrays and variables
	static ArrayList<String> playerCards = new ArrayList<String>();
	static ArrayList<String> playerRunCards = new ArrayList<String>();
	static ArrayList<String> playerCarrots = new ArrayList<String>();
	static ArrayList<String> playerBunnies = new ArrayList<String>();
	static double playerCabbage = 0.0;
	static double playerWater = 0.0;
	static double playerDolla = 0.0;
	
	//Global variables
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
		ArrayList<String> carrotDeckTemp = new ArrayList<String>();
		ArrayList<String> smallCarrotDeckTemp = new ArrayList<String>();
		ArrayList<String> cabbageDeckTemp = new ArrayList<String>();
		ArrayList<String> waterDeckTemp = new ArrayList<String>();
		
		int randomCardIndex = 0;
		
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
				carrotDeck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		originalSize = carrotDeck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(carrotDeck.size());
			carrotDeckTemp.add(carrotDeck.get(randomCardIndex));
			carrotDeck.remove(randomCardIndex);
		}
		carrotDeck = carrotDeckTemp;
		
		try {
			s = new Scanner(new File("carrot.txt"));
			while (s.hasNextLine()){
				smallCarrotDeck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		originalSize = smallCarrotDeck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(smallCarrotDeck.size());
			smallCarrotDeckTemp.add(smallCarrotDeck.get(randomCardIndex));
			smallCarrotDeck.remove(randomCardIndex);
		}
		smallCarrotDeck = smallCarrotDeckTemp;
		try {
			s = new Scanner(new File("cabbage.txt"));
			while (s.hasNextLine()){
				cabbageDeck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		originalSize = cabbageDeck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(cabbageDeck.size());
			cabbageDeckTemp.add(cabbageDeck.get(randomCardIndex));
			cabbageDeck.remove(randomCardIndex);
		}
		cabbageDeck = cabbageDeckTemp;
		try {
			s = new Scanner(new File("water.txt"));
			while (s.hasNextLine()){
				waterDeck.add(s.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		originalSize = waterDeck.size();
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(waterDeck.size());
			waterDeckTemp.add(waterDeck.get(randomCardIndex));
			waterDeck.remove(randomCardIndex);
		}
		waterDeck = cabbageDeckTemp;
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
		pause(2);
		//System.out.println("Computer's Hand: " + compCards);
		compHand();
		pause(2);
		//System.out.println("Computer's Run Cards: " + compRunCards);
		compRunCards();
		pause(2);
		if (compDolla > 0) {
			System.out.println("Computer's Dolla: " + compDolla);
			pause(2);
		}
		if (playerDolla > 0) {
			System.out.println("Player's Dolla: " + playerDolla);
			pause(2);
		}
		//System.out.println("Player's Hand: " + playerCards);
		playerHand();
		pause(2);
		s = new Scanner(System.in);
		System.out.println("Which card do you want your top run card to be? (1 - first card, 7 - last card)");
		int choice = s.nextInt() - 1;
		playerRunCards.add(0, playerCards.get(choice));
		playerCards.remove(choice);
		//System.out.println("Player's Hand: " + playerCards);
		playerHand();
		pause(2);
		System.out.println("Which card do you want your bottom run card to be? (1 - first card, 6 - last card)");
		pause(2);
		choice = s.nextInt() - 1;
		playerRunCards.add(1, playerCards.get(choice));
		playerCards.remove(choice);
		//System.out.println("Player's Run Cards: " + playerRunCards);
		playerRunCards();
		pause(2);
	}
	
	public static void player() {
		//clearScreen();
		int cardId = 0, choice = 0;
		String decision = "";
		System.out.println();
		System.out.println("Player's Turn");
		pause(2);
		System.out.println("You played: " + playerRunCards.get(0).substring(6));
		pause(2);
		cardId = Integer.parseInt(playerRunCards.get(0).substring(0, 3));
		if (cardId <= 15) {
			playerBunnies.add(playerRunCards.get(0));
			playerBunnies();
		}
		else if (cardId >= 16 && cardId <= 20 && playerBunnies.size() != 0) {
			playerCarrots.add(carrotDeck.get(0));
			carrotDeck.remove(0);
			System.out.println("Your " + playerCarrots.size() + " Carrots: " + playerCarrots);
		}
		else if (cardId >= 21 && cardId <= 23 && playerBunnies.size() != 0) {
			playerCarrots.add(carrotDeck.get(0));
			carrotDeck.remove(0);
			if (carrotDeck.size() > 0) {
				playerCarrots.add(carrotDeck.get(0));
				carrotDeck.remove(0);
			}
			System.out.println("Your " + playerCarrots.size() + " Carrots: " + playerCarrots);
		}
		else if (cardId >= 24 && cardId <= 30 && playerBunnies.size() != 0) {
			int cabbage = Integer.parseInt(playerRunCards.get(0).substring(21, 23));
			int water = Integer.parseInt(playerRunCards.get(0).substring(36, 38));
			if (compBunnies.size() != 0) {
				//System.out.println("Computer's Bunnies: " + compBunnies);
				compBunnies();
				pause(2);
				System.out.println("Which bunny would you like to target? (1 - first, " + compBunnies.size() + " - last card)");
				int target = s.nextInt() - 1;
				pause(2);
				System.out.println("You targeted " + compBunnies.get(target).substring(6));
				pause(2);
				System.out.println("They must feed their bunny " + cabbage + " Cabbage and " + water + " Water");
				compBunnies.set(target, compBunnies.get(target) + " - " + cabbage + "C" + water + "W");
			}
		}
		else if (cardId >= 31 && cardId <= 48 && playerBunnies.size() != 0) {
			int level = Integer.parseInt(playerRunCards.get(0).substring(19, 21));
			usedWeapons.add(playerRunCards.get(0));
			if (compBunnies.size() != 0) {
				//System.out.println("Computer's Bunnies: " + compBunnies);
				compBunnies();
				pause(2);
				System.out.println("Which bunny would you like to target? (1 - first, " + compBunnies.size() + " - last card)");
				int target = s.nextInt() - 1;
				pause(2);
				System.out.println("You targeted " + compBunnies.get(target).substring(6));
				pause(2);
				System.out.println("They must roll higher than a " + level);
				pause(2);
				System.out.print("They rolled a... ");
				pause(4);
				int compRoll = random.nextInt(12) + 1;
				System.out.println(compRoll);
				pause(2);
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
		pause(2);
		if (carrotDeck.size() == 0) {
			game = false;
			return;
		}
		playedRunCards.add(playerRunCards.get(0));
		playerRunCards.remove(0);
		if (deck.size() == 0) {
			reshuffle();
		}
		while(playerCards.size() < 6) {
			cardId = Integer.parseInt(deck.get(0).substring(0, 3));
			if (cardId >= 87 && cardId <= 101) {
				System.out.println("You drew: " + deck.get(0).substring(6));
				pause(2);
				playerDolla += Integer.parseInt(deck.get(0).substring(6, 8));
				System.out.println("Player's Dolla: " + playerDolla);
				pause(2);
				deck.remove(0);
				System.out.println("Player draws again...");
				pause(2);
			}
			else {
				playerCards.add(deck.get(0));
				System.out.println("You drew: " + deck.get(0).substring(6));
				pause(2);
				deck.remove(0);
			}
		}
		//System.out.println("Your Hand: " + playerCards);
		playerHand();
		pause(2);
		//System.out.println("Your Top Run Card: " + playerRunCards);
		playerRunCards();
		pause(2);
		for (int i = 0; i < playerBunnies.size(); i++) {
			if (playerBunnies.get(i).endsWith("W")){
				int cabbage = Integer.parseInt(playerBunnies.get(i).substring(playerBunnies.get(i).length() - 4, playerBunnies.get(i).length() - 3));
				int water = Integer.parseInt(playerBunnies.get(i).substring(playerBunnies.get(i).length() - 2, playerBunnies.get(i).length() - 1));
				System.out.println("You must feed " + playerBunnies.get(i).substring(6) + " " + cabbage + " Cabbage and " + water + " Water");
				pause(2);
				System.out.println("Would you like to try and feed the bunny? (Y - Yes, N - No)");
				decision = s.next();
				if (decision.equalsIgnoreCase("Y")) {
					while (cabbageDeck.size() != 0 && waterDeck.size() != 0 && playerDolla > 2 && (playerCabbage < cabbage || playerWater < water)) {
						if (playerCabbage < cabbage) {
							System.out.println("You bought a Cabbage Card with " + cabbageDeck.get(0).substring(0, 4) + " Units");
							playerCabbage += Double.parseDouble(cabbageDeck.get(0).substring(0, 4));
							cabbageDeck.remove(0);
							playerDolla -= 3;
							pause(2);
						}
						if (playerWater < water) {
							System.out.println("You bought a Water Card with " + waterDeck.get(0).substring(0, 4) + " Units");
							playerWater += Double.parseDouble(waterDeck.get(0).substring(0, 4));
							waterDeck.remove(0);
							playerDolla -= 3;
							pause(2);
						}
					}
				}
				if (playerCabbage >= cabbage && playerWater >= water && decision.equalsIgnoreCase("Y")) {
					System.out.println("Player's Cabbage: " + playerCabbage);
					pause(2);
					System.out.println("Player's Water: " + playerWater);
					pause(2);
					System.out.println("Player has enough Cabbage and Water");
					pause(2);
					playerCabbage = playerCabbage - cabbage;
					playerWater = playerWater - water;
					System.out.println("Player's Cabbage: " + playerCabbage);
					pause(2);
					System.out.println("Player's Water: " + playerWater);
					pause(2);
					playerBunnies.set(i, playerBunnies.get(i).substring(0, playerBunnies.get(i).length() - 7));
					System.out.println(playerBunnies.get(i).substring(6) + " survives");
				}
				else {
					System.out.println("Player doesn't have enough Cabbage and Water");
					pause(2);
					System.out.println(playerBunnies.get(i).substring(6) + " dies");
					playerBunnies.remove(i);
				}
				pause(2);
			}
		}
		if (playerDolla > 2) {
			System.out.println("You have " + playerDolla + " Dolla, would you like you purchase anything? (Y - yes, N - No)");
			s = new Scanner(System.in);
			decision = s.next();
			if (decision.equalsIgnoreCase("Y")) {
				while (decision.equalsIgnoreCase("Y")) {
					System.out.println("Cabbage Card - 3 Dolla");
					pause(2);
					System.out.println("Water Card - 3 Dolla");
					pause(2);
					System.out.println("Carrot Card - 10 Dolla");
					pause(2);
					System.out.println("(1 - Cabbage Card, 2 - Water Card, 3 - Carrot Card, 4 - None)");
					s = new Scanner(System.in);
					choice = s.nextInt();
					pause(2);
					if (choice == 1) {
						if (playerDolla >= 2) {
							if (cabbageDeck.size() == 0) {
								System.out.println("Sorry, no Cabbage Cards remain");
							}
							else {
								System.out.println("You drew a Cabbage Card with " + cabbageDeck.get(0).substring(0, 4) + " Units");
								playerCabbage += Double.parseDouble(cabbageDeck.get(0).substring(0, 4));
								cabbageDeck.remove(0);
								playerDolla -= 3;
							}
						}
					}
					else if (choice == 2) {
						if (playerDolla >= 3) {
							if (waterDeck.size() == 0) {
								System.out.println("Sorry, no Water Cards remain");
							}
							else {
								System.out.println("You drew a Water Card with " + waterDeck.get(0).substring(0, 4) + " Units");
								playerWater += Double.parseDouble(waterDeck.get(0).substring(0, 4));
								waterDeck.remove(0);
								playerDolla -= 3;
							}
						}
					}
					else if (choice == 3) {
						if (playerDolla >= 10) {
							playerCarrots.add(carrotDeck.get(0));
							carrotDeck.remove(0);
							System.out.println("Your " + playerCarrots.size() + " Carrots: " + playerCarrots);
							playerDolla -= 10;
							if (carrotDeck.size() == 0) {
								game = false;
								return;
							}
						}
					}
					pause(2);
					if (choice >= 1 && choice <= 3) {
						System.out.println("Player Cabbage Units: " + playerCabbage);
						pause(2);
						System.out.println("Player Water Units: " + playerWater);
						pause(2);
						System.out.println("Player Carrots: " + playerCarrots.size());
						pause(2);
						System.out.println("You have " + playerDolla + " Dolla, would you like you purchase anything? (Y - yes, N - No)");
						s = new Scanner(System.in);
						decision = s.next();
					}
					else {
						decision = "N";
					}
				}
			}
		}
		pause(2);
		s = new Scanner(System.in);
		System.out.println("Which card do you want your bottom run card to be? (1 - first card, 6 - last card)");
		choice = s.nextInt() - 1;
		playerRunCards.add(1, playerCards.get(choice));
		playerCards.remove(choice);
		pause(2);
	}
	
	public static void computer() {
		//clearScreen();
		if (carrotDeck.size() == 0) {
			game = false;
			return;
		}
		System.out.println();
		System.out.println("Computer's Turn");
		pause(2);
		System.out.println("They played: " + compRunCards.get(0).substring(6));
		pause(2);
		int cardId = 0, choice = 0;
		cardId = Integer.parseInt(compRunCards.get(0).substring(0, 3));
		if (cardId <= 15) {
			compBunnies.add(compRunCards.get(0));
			//System.out.println("Their Bunnies: " + compBunnies);
			compBunnies();
		}
		else if (cardId >= 16 && cardId <= 20 && compBunnies.size() != 0) {
			compCarrots.add(carrotDeck.get(0));
			carrotDeck.remove(0);
			System.out.println("Their " + compCarrots.size() + " Carrots: " + compCarrots);
		}
		else if (cardId >= 21 && cardId <= 23 && compBunnies.size() != 0) {
			compCarrots.add(carrotDeck.get(0));
			carrotDeck.remove(0);
			if (carrotDeck.size() > 0) {
				compCarrots.add(carrotDeck.get(0));
				carrotDeck.remove(0);
			}
			System.out.println("Their " + compCarrots.size() + " Carrots: " + compCarrots);
		}
		else if (cardId >= 24 && cardId <= 30 && compBunnies.size() != 0) {
			int cabbage = Integer.parseInt(compRunCards.get(0).substring(21, 23));
			int water = Integer.parseInt(compRunCards.get(0).substring(36, 38));
			if (playerBunnies.size() != 0) {
				int randomTarget = random.nextInt(playerBunnies.size());
				System.out.println(playerBunnies.get(randomTarget).substring(6) + " must now feed " + cabbage + " Cabbage and " + water + " Water");
				playerBunnies.set(randomTarget, playerBunnies.get(randomTarget) + " - " + cabbage + "C" + water + "W");
			}
		}
		else if (cardId >= 31 && cardId <= 48 && compBunnies.size() != 0) {
			int level = Integer.parseInt(compRunCards.get(0).substring(19, 21));
			usedWeapons.add(compRunCards.get(0));
			if (playerBunnies.size() != 0) {
				int randomTarget = random.nextInt(playerBunnies.size());
				System.out.println("They target " + playerBunnies.get(randomTarget).substring(6));
				pause(2);
				System.out.println("You must roll higher than a " + level);
				pause(2);
				System.out.print("You rolled a... ");
				pause(5);
				int playerRoll = random.nextInt(12) + 1;
				System.out.println(playerRoll);
				pause(2);
				if (playerRoll > level) {
					System.out.println("It survived");
				}
				else {
					System.out.println("It died");
					killedBunnies.add(playerBunnies.get(randomTarget));
					playerBunnies.remove(randomTarget);
				}
			}
		}
		pause(2);
		if (carrotDeck.size() == 0) {
			game = false;
			return;
		}
		playedRunCards.add(compRunCards.get(0));
		compRunCards.remove(0);
		if (deck.size() == 0) {
			reshuffle();
		}
		while(compCards.size() < 6) {
			cardId = Integer.parseInt(deck.get(0).substring(0, 3));
			if (cardId >= 87 && cardId <= 101) {
				System.out.println("They drew: " + deck.get(0).substring(6));
				pause(2);
				compDolla += Integer.parseInt(deck.get(0).substring(6, 8));
				System.out.println("Computer's Dolla: " + compDolla);
				pause(2);
				deck.remove(0);
				System.out.println("Computer draws again...");
				pause(2);
			}
			else {
				compCards.add(deck.get(0));
				System.out.println("They drew: " + deck.get(0).substring(6));
				pause(2);
				deck.remove(0);
			}
		}
		//System.out.println("Computer's Hand: " + compCards);
		compHand();
		pause(2);
		for (int i = 0; i < compBunnies.size(); i++) {
			if (compBunnies.get(i).endsWith("W")){
				int cabbage = Integer.parseInt(compBunnies.get(i).substring(compBunnies.get(i).length() - 4, compBunnies.get(i).length() - 3));
				int water = Integer.parseInt(compBunnies.get(i).substring(compBunnies.get(i).length() - 2, compBunnies.get(i).length() - 1));
				System.out.println("The computer must feed " + compBunnies.get(i).substring(6) + " " + cabbage + " Cabbage and " + water + " Water");
				while (cabbageDeck.size() != 0 && waterDeck.size() != 0 && compDolla >= 3 && compCabbage < cabbage && compWater < water) {
					if (compCabbage < cabbage) {
						System.out.println("They bought a Cabbage Card with " + cabbageDeck.get(0).substring(0, 4) + " Units");
						compCabbage += Double.parseDouble(cabbageDeck.get(0).substring(0, 4));
						cabbageDeck.remove(0);
						compDolla -= 3;
						pause(2);
					}
					if (compWater < water && compDolla >= 3) {
						System.out.println("They bought a Water Card with " + waterDeck.get(0).substring(0, 4) + " Units");
						compWater += Double.parseDouble(waterDeck.get(0).substring(0, 4));
						waterDeck.remove(0);
						compDolla -= 3;
						pause(2);
					}
				}
				if (compCabbage >= cabbage && compWater >= water) {
					System.out.println("Computer's Cabbage: " + compCabbage);
					pause(2);
					System.out.println("Computer's Water: " + compWater);
					pause(2);
					System.out.println("Computer has enough Cabbage and Water");
					pause(2);
					compCabbage = compCabbage - cabbage;
					compWater = compWater - water;
					System.out.println("Computer's Cabbage: " + compCabbage);
					pause(2);
					System.out.println("Computer's Water: " + compWater);
					compBunnies.set(i, compBunnies.get(i).substring(0, compBunnies.get(i).length() - 7));
					System.out.println(compBunnies.get(i).substring(6) + " survives");
				}
				else {
					System.out.println("Computer doesn't have enough Cabbage and Water");
					pause(2);
					System.out.println(compBunnies.get(i).substring(6) + " dies");
					compBunnies.remove(i);
				}
				pause(2);
			}
		}
		while (compDolla >= 20 && compBunnies.size() != 0) {
			System.out.println("Computer decides to buy a carrot");
			pause(2);
			compCarrots.add(carrotDeck.get(0));
			carrotDeck.remove(0);
			System.out.println("Their " + compCarrots.size() + " Carrots: " + compCarrots);
			if (carrotDeck.size() == 0) {
				game = false;
				return;
			}
			pause(2);
			compDolla -= 10;
			System.out.println("Computer's Dolla: " + compDolla);
			pause(2);
			
		}
		System.out.println("Computer Thinks...");
		pause(2);
		choice = random.nextInt(compCards.size());
		compRunCards.add(1, compCards.get(choice));
		compCards.remove(choice);
		//System.out.println("Computer's Run Cards: " + compRunCards);
		compRunCards();
		pause(2);
	}
	
	public static void playerBunnies() {
		System.out.print("Player's Bunnies: ");
		for (int i = 0; i < playerBunnies.size(); i++) {
			if (playerBunnies.size() == 1) {
				System.out.println("[" + playerBunnies.get(i).substring(6) + "]");
			}
			else if (i == 0) {
				System.out.print("[" + playerBunnies.get(i).substring(6));
			}
			else if (i == playerBunnies.size() - 1) {
				System.out.println(", " + playerBunnies.get(i).substring(6) + "]");
			}
			else {
				System.out.print(", " + playerBunnies.get(i).substring(6));
			}
		}
	}
	
	public static void compBunnies() {
		System.out.print("Computer's Bunnies: ");
		for (int i = 0; i < compBunnies.size(); i++) {
			if (compBunnies.size() == 1) {
				System.out.println("[" + compBunnies.get(i).substring(6) + "]");
			}
			else if (i == 0) {
				System.out.print("[" + compBunnies.get(i).substring(6));
			}
			else if (i == compBunnies.size() - 1) {
				System.out.println(", " + compBunnies.get(i).substring(6) + "]");
			}
			else {
				System.out.print(", " + compBunnies.get(i).substring(6));
			}
		}
	}
	
	public static void playerRunCards() {
		System.out.print("Player's Run Cards: ");
		for (int i = 0; i < playerRunCards.size(); i++) {
			if (playerRunCards.size() == 1) {
				System.out.println("[" + playerRunCards.get(i).substring(6) + "]");
			}
			else if (i == 0) {
				System.out.print("[" + playerRunCards.get(i).substring(6));
			}
			else if (i == playerRunCards.size() - 1) {
				System.out.println(", " + playerRunCards.get(i).substring(6) + "]");
			}
			else {
				System.out.print(", " + playerRunCards.get(i).substring(6));
			}
		}
	}
	
	public static void playerHand() {
		System.out.print("Player's Hand: ");
		for (int i = 0; i < playerCards.size(); i++) {
			if (i == 0) {
				System.out.print("[" + playerCards.get(i).substring(6));
			}
			else if (i == playerCards.size() - 1) {
				System.out.println(", " + playerCards.get(i).substring(6) + "]");
			}
			else {
				System.out.print(", " + playerCards.get(i).substring(6));
			}
		}
	}
	
	public static void compRunCards() {
		System.out.print("Computer's Run Cards: ");
		for (int i = 0; i < compRunCards.size(); i++) {
			if (compRunCards.size() == 1) {
				System.out.println("[" + compRunCards.get(i).substring(6) + "]");
			}
			else if (i == 0) {
				System.out.print("[" + compRunCards.get(i).substring(6));
			}
			else if (i == compRunCards.size() - 1) {
				System.out.println(", " + compRunCards.get(i).substring(6) + "]");
			}
			else {
				System.out.print(", " + compRunCards.get(i).substring(6));
			}
		}
	}
	
	public static void compHand() {
		System.out.print("Computer's Hand: ");
		for (int i = 0; i < compCards.size(); i++) {
			if (i == 0) {
				System.out.print("[" + compCards.get(i).substring(6));
			}
			else if (i == compCards.size() - 1) {
				System.out.println(", " + compCards.get(i).substring(6) + "]");
			}
			else {
				System.out.print(", " + compCards.get(i).substring(6));
			}
		}
	}
	
	public static void reshuffle() {
		System.out.println("Reshuffling...");
		deck.addAll(playedRunCards);
		ArrayList<String> deckTemp = new ArrayList<String>();
		int originalSize = deck.size(), randomCardIndex;
		
		for (int i = 0; i < originalSize; i++) {
			randomCardIndex = random.nextInt(deck.size());
			deckTemp.add(deck.get(randomCardIndex));
			deck.remove(randomCardIndex);
		}
		deck = deckTemp;
		pause(2);
	}
	
	public static void removeCarrot() {
		for (int i = 0; i < compCarrots.size(); i++) {
			if (smallCarrotDeck.get(0).equals(compCarrots.get(i))) {
				compCarrots.remove(i);
			}
		}
		for (int i = 0; i < playerCarrots.size(); i++) {
			if (smallCarrotDeck.get(0).equals(playerCarrots.get(i))) {
				playerCarrots.remove(i);
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
		//System.out.println("Computer's Bunnies: " + compBunnies);
		compBunnies();
		//System.out.println("Player's Bunnies: " + playerBunnies);
		playerBunnies();
		carrotAnnouncements();
		if (playerCarrots.size() > compCarrots.size()) {
			System.out.println("Player Wins!");
		}
		else {
			System.out.println("Computer Wins!");
		}
	}
	
	public static void poorComp() {
		System.out.println("Computer Had No Bunnies...");
		pause(2);
		System.out.println("So Their Carrots Go To You!");
		playerCarrots.addAll(compCarrots);
		compCarrots.removeAll(compCarrots);
		pause(2);
		//System.out.println("Player's Bunnies: " + playerBunnies);
		playerBunnies();
		carrotAnnouncements();
		System.out.println("Player Wins!");
	}
	
	public static void poorPlayer() {
		System.out.println("You Had No Bunnies...");
		pause(2);
		System.out.println("Sadly, Your Carrots Go To Them!");
		compCarrots.addAll(playerCarrots);
		playerCarrots.removeAll(playerCarrots);
		pause(2);
		//System.out.println("Computer's Bunnies: " + compBunnies);
		compBunnies();
		carrotAnnouncements();
		System.out.println("Computer Wins!");
	}
	
	public static void badGame() {
		System.out.println("Sadly, No One Had Bunnies...");
		pause(2);
		System.out.println("So No One Wins, But Let's See Who Would've!");
		pause(2);
		carrotAnnouncements();
		if (playerCarrots.size() > compCarrots.size()) {
			System.out.println("Player Would've Won!");
		}
		else {
			System.out.println("Computer Would've Won!");
		}
	}
	
	public static void carrotAnnouncements() {
		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				System.out.println("Computer's Carrots: " + compCarrots);
				pause(1);
				System.out.println("Player's Carrots: " + playerCarrots);
				pause(1);
				System.out.println("1st Carrot Out: " + smallCarrotDeck.get(0));
				removeCarrot();
				smallCarrotDeck.remove(0);
				pause(4);
			}
			else if (i == 6) {
				System.out.println("Computer's Carrots: " + compCarrots);
				pause(1);
				System.out.println("Player's Carrots: " + playerCarrots);
				pause(1);
				System.out.println("Last Carrot Out: " + smallCarrotDeck.get(0));
				removeCarrot();
				smallCarrotDeck.remove(0);
				pause(4);
			}
			else {
				System.out.println("Computer's Carrots: " + compCarrots);
				pause(1);
				System.out.println("Player's Carrots: " + playerCarrots);
				pause(1);
				System.out.println("Next Carrot Out: " + smallCarrotDeck.get(0));
				removeCarrot();
				smallCarrotDeck.remove(0);
				pause(4);
			}
		}
		System.out.println("Winning Carrot: " + smallCarrotDeck.get(0));
	}
}
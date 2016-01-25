package kitordersystem;

import java.util.Scanner;

public class MainMenu {
	public static void main(String[] args){
		System.out.println("Please choose an option:\n1: Create database (Please exercise caution with this)\n2: Show the GUI\nq: Quit");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		switch (input) {
		case "1": System.out.println("You selected to create the database");
		break;
		case "2": System.out.println("You have selected to show the GUI");
		break;
		case "q": System.out.println("You have selected to quit the program");
		break;
		default: System.out.println("That is not a valid command");
		break;
		}
	}
}

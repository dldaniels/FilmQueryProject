package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	// instance of database accesor object
	DatabaseAccessor db = new DatabaseAccessorObject();

	// main method
	public static void main(String[] args) {
		// instance of film query app
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		// call to app launch
		app.launch();
	}

//  private void test() {
//    Film film = db.findFilmById(1234);
//    System.out.println(film);
//  }
	// method to laucnh application
	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	// method to begin user interface
	private void startUserInterface(Scanner input) {
		List<Film> films = new ArrayList<>();
		boolean keepGoing = true;
		Film film;

		System.out.println("Welcome to the Crudflix film database, where all of our movies make");
		System.out.println("The Wayans Brothers film \"White Chicks\" look like a cinematic masterpiece");
		System.out.println(
				"Browse through our worst variety of low budget films that you never knew you wanted to watch");
		System.out.println();
		int choice = 0;
		do {
			try {
				System.out.println("-----------------Main Menu-----------------");
				System.out.println();
				System.out.println("----Select which number you would like-----");
				System.out.println("1. Look up a film by film id number");
				System.out.println("2. Search a film by keyword");
				System.out.println("3. Exit");
				// invalid input exception is handled in try/catch
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid option");
				input.nextLine();

			}

			try {
				switch (choice) {
				case 1:
					System.out.println("Enter film ID number: ");
					film = db.findFilmById(input.nextInt());
					if (film == null) {
						System.out.println("Unable to find a film with that ID number");

					} else {
						System.out.println(film.displayFilm());
						System.out.println();
						System.out.println();
						System.out.println("What would you like to do next");
						System.out.println("Enter 1 to view all film details");
						System.out.println("Enter 2 to return to main menu");
						int subChoice = input.nextInt();
						switch (subChoice) {
						case 1:
							System.out.println(film.toString());
							break;
						case 2:
							break;
						default:
							System.out.println("Please enter a valid input");
							break;
						}
					}
					break;

				case 2:
					System.out.println("Enter keyword to search");
					films = db.findFilmByKeyword(input.next());
					if (films.isEmpty()) {
						System.out.println("Unable to find a matching film");

					} else {
						System.out.println("Here are the films we have found that may be relevant to your search.");
						System.out.println();

						for (Film matchingFilms : films) {
							System.out.println(matchingFilms.displayFilm());
							System.out.println();
						}
						System.out.println("What would you like to do next");
						System.out.println("Enter 1 to view all film details");
						System.out.println("Enter 2 to return to main menu");
						int subChoice = input.nextInt();
						switch (subChoice) {
						case 1:
							System.out.println(films.toString());
							break;
						case 2:
							break;
						default:
							System.out.println("Please enter a valid input");
							break;
						}
					}

					break;
				case 3:
					System.out.println("Bye");
					keepGoing = false;
					break;

				default:
					System.out.println("Please enter a valid input");
					break;

				}
			} catch (InputMismatchException e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid input, please enter a valid input");
			}

		} while (keepGoing);
	}

}

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
		while (keepGoing) {
			System.out.println("-----------------Main Menu-----------------");
			System.out.println();
			System.out.println("----Select which number you would like-----");
			System.out.println("1. Look up a film by film id number");
			System.out.println("2. Search a film by keyword");
			System.out.println("3. Exit");
			int choice;
			// invalid input exception is handled in try/catch
			try {
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid option");
				break;
			}
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
				}
				break;
			case 3:
				System.out.println("Bye");
				keepGoing = false;
				break;

			}
		}
	}
//	private void subMenu(Scanner input) {
//		
//		System.out.println("Select 1 to view all details of film");
//		System.out.println("Select 2 to return to main menu");
//		int choice;
//		
//		try {
//			choice = input.nextInt();
//		} catch (InputMismatchException e) {
//			System.out.println("Invalid input. Please enter a valid option");
//			
//		}
//		switch(choice) {
//		case 1: 
//			db.
//			
//		}
//	}

}

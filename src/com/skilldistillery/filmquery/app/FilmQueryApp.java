package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		try {
			app.test();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(11);
		System.out.println(film);
		Actor actor = db.findActorById(8);
		System.out.println(actor);
		List<Actor> actors = db.findActorsByFilmId(10);
		System.out.println(actors);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		System.out.println("Please make a selection:");
		System.out.println("1. Look up a film by the film id \n2. Look up a film by a search keyword \n3. Exit");
		String selection;
		int filmID;
		String searchKeyword;
		do {
			selection = input.nextLine();

			if (selection.equals("1")) {
				System.out.println("Please enter in a film id:");
				filmID = input.nextInt();
				Film film = db.findFilmById(filmID);
				if (film != null) {
					System.out.println(film);
				} else {
					System.out.println("No film matches this id.");
				}
			}
			if (selection.equals("2")) {
				System.out.println("Please enter in the search keyword");
				searchKeyword = input.next();
				List<Film> allFilms = db.findFilmsWithSearchKeyWord(searchKeyword);

				if (allFilms.size() != 0) {
					for (Film film : allFilms) {
						System.out.println(film);

					}
				} else {
					System.out.println("No film matches this keyword.");
				}
			}
		} while (!selection.equals("3"));
		System.out.println("Goodbye");

	}

}

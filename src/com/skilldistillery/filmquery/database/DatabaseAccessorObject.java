package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {

		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		Film film = null;
		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rating, language.name FROM film JOIN language ON language.id = film.language_id WHERE film.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); 
			film.setId(filmResult.getInt("id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getInt("release_year"));
			film.setRating(filmResult.getString("rating"));
			film.setLanguage(filmResult.getString("language.name"));
			film.setFilmActors(findActorsByFilmId(filmId));
			
			filmResult.close();
			stmt.close();
			conn.close();
		}
		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		Actor actor = null;

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); 
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));

			actorResult.close();
			stmt.close();
			conn.close();
		}
		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		Actor actor = null;
		String sql = "SELECT actor.first_name, actor.last_name, film_actor.film_id FROM actor JOIN film_actor ON film_actor.actor_id = actor.id JOIN film ON film.id = film_actor.film_id WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery(); 
		while (rs.next()) {
			actor = new Actor();
			actor.setId(rs.getInt("film_id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));

			actors.add(actor);
		}
		rs.close();
		stmt.close();
		conn.close();
	
		return actors;
	}
	
	public List<Film> findFilmsWithSearchKeyWord(String userSearchKw) throws SQLException {
		List<Film> filmsWithUserSearchKw = new ArrayList<>();
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT film.id, film.title, film.description, film.release_year, film.rating, language.name FROM film JOIN language ON language.id = film.language_id WHERE film.title LIKE ? OR film.description LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + userSearchKw + "%");
		stmt.setString(2, "%" + userSearchKw + "%");
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Film film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setRating(rs.getString("rating"));
			film.setDescription(rs.getString("description"));
			film.setLanguage(rs.getString("language.name"));
			film.setFilmActors(findActorsByFilmId(rs.getInt("id")));
			


			filmsWithUserSearchKw.add(film);
		}
			rs.close();
			stmt.close();
			conn.close();
		return filmsWithUserSearchKw;
		
	}

}

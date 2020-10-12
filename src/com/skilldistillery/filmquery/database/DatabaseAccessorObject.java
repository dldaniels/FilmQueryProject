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
//Database connection fields
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String password = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
	}

	// method to find film by film id number
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			// establish connection to DB
			Connection conn = DriverManager.getConnection(URL, user, password);
			// sql query
			String sql = "SELECT * FROM film WHERE film.id = ?";
			// prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);
			// execute query
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			// process data
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId));

			}

			rs.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		// return data object
		return film;
	}

	// method to find actor by actor id
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			// establish connection to DB
			Connection conn = DriverManager.getConnection(URL, user, password);
			// sql query
			String sql = "SELECT * FROM actor WHERE id = ?";
			// prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);
			// execute query
			pst.setInt(1, actorId);
			ResultSet rs = pst.executeQuery();
			// process data
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));

			}

			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return data object
		return actor;
	}

//method to find actor by film id. Used to create cast list.
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;

		try {
			// establish connection to DB
			Connection conn = DriverManager.getConnection(URL, user, password);
			// sql query
			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
			// prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);
			// execute query
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			// process data
			while (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));

				actors.add(actor);

			}

			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return data object
		return actors;
	}

	// method to find film by key word search
	@Override
	public List<Film> findFilmByKeyword(String keyWord) {
		Film film = null;
		List<Film> films = new ArrayList<>();

		try {
			// establish connection to DB
			Connection conn = DriverManager.getConnection(URL, user, password);
			// sql query
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";
			// prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);
			// execute query
			pst.setString(1, "%" + keyWord + "%");
			pst.setString(2, "%" + keyWord + "%");
			ResultSet rs = pst.executeQuery();
			// process data

			while (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(film.getId()));
				films.add(film);

			}
			rs.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return data object
		return films;
	}

	// method to find language from language id
	@Override
	public String findLanguage(int languageId) {
		StringBuilder builder = new StringBuilder();

		try {
			// establish connection to DB
			Connection conn = DriverManager.getConnection(URL, user, password);
			// sql query
			String sql = "SELECT name FROM language WHERE language.id = ?";
			// prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);
			// execute query
			pst.setInt(1, languageId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				builder.append(rs.getString("name"));

			}

			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return data
		return builder.toString();
	}

}

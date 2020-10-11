package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String user = "student";
	private static final String password = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
	}

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
				// film.setCategoryId(rs.getInt("category));
				film.setActors(findActorsByFilmId(filmId));

			}

			rs.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return film;
	}

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
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}

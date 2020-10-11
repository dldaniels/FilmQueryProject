package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

			}

		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

}

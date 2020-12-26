package com.app.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.PlayerSearchDAO;
import com.app.dao.util.PostresqlConnection;
import com.app.exception.BussinessException;
import com.app.modal.Player;

public class PlayerSearchDAOImpl implements PlayerSearchDAO {

	@Override
	public Player getPlayerById(int id) throws BussinessException {
		
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player where id=?" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
			}
			else
				throw new BussinessException("Player Not Found at id: "+id);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BussinessException {
		Player player = null;
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact ,team_name, gender, dob from test.player where contact=?" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDouble(1, contact);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
			}
			else
				throw new BussinessException("Player Not Found with Contact: "+contact);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BussinessException {
		
		List<Player> playersList = new ArrayList<>();
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player order by id" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getInt("id"));
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
				playersList.add(player);
			}
			
			if(playersList.size() == 0)
			{
				throw new BussinessException("No Player/s Found.");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return playersList;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BussinessException {
		List<Player> playersList = new ArrayList<>();
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player where age=? order by id" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, age);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getInt("id"));
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
				playersList.add(player);
			}
			
			if(playersList.size() == 0)
			{
				throw new BussinessException("No Player/s Found.");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return playersList;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BussinessException {
		
		List<Player> playersList = new ArrayList<>();
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player where gender=? order by id" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, gender);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getInt("id"));
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
				playersList.add(player);
			}
			
			if(playersList.size() == 0)
			{
				throw new BussinessException("No Player/s Found with Gender: "+gender);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return playersList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamname) throws BussinessException {
		
		List<Player> playersList = new ArrayList<>();
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player where team_name=? order by id" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, teamname);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getInt("id"));
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
				playersList.add(player);
			}
			
			if(playersList.size() == 0)
			{
				throw new BussinessException("No Player/s Found in Team: "+teamname);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return playersList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BussinessException {
		
		List<Player> playersList = new ArrayList<>();
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player where name=? order by id" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getInt("id"));
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
				playersList.add(player);
			}
			
			if(playersList.size() == 0)
			{
				throw new BussinessException("No Player/s Found with name: "+name);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return playersList;
	}

	@Override
	public List<Player> getPlayersByDob(String dob) throws BussinessException {
		List<Player> playersList = new ArrayList<>();
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			Date date = Date.valueOf(dob);
			
			String sql = "select id, name , age, contact, team_name, gender, dob from test.player where dob=? order by id" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDate(1, date);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				//System.out.println(rs.getInt("id"));
				player = new Player();
				player.setId(rs.getInt("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				player.setContact(rs.getLong("contact"));
				player.setTeamname(rs.getString("team_name"));
				player.setGender(rs.getString("gender"));
				player.setDob(rs.getDate("dob"));
				playersList.add(player);
			}
			
			if(playersList.size() == 0)
			{
				throw new BussinessException("No Player/s Found with Date of Birth: "+dob);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return playersList;
	}

}

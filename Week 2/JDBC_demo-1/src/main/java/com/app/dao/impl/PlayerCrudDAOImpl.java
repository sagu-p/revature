package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.PlayerCrudDAO;
import com.app.dao.util.PostresqlConnection;
import com.app.exception.BussinessException;
import com.app.modal.Player;

public class PlayerCrudDAOImpl implements PlayerCrudDAO {

	@Override
	public int createPlayer(Player player) throws BussinessException {
		int c = 0;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String query = "insert into test.player (id , name , age, contact, team_name, gender, dob) values (?,?,?,?,?,?,?)";
			
			PreparedStatement preaparedStatement = conn.prepareStatement(query);
			preaparedStatement.setInt(1, player.getId());
			preaparedStatement.setString(2, player.getName());
			preaparedStatement.setInt(3, player.getAge());
			preaparedStatement.setLong(4, player.getContact());
			preaparedStatement.setString(5, player.getTeamname());
			preaparedStatement.setString(6, player.getGender());
			preaparedStatement.setDate(7, new java.sql.Date(player.getDob().getTime()));
			
			c = preaparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return c;
	}

	@Override
	public void deletePlayer(int id) throws BussinessException {
		
		int c = 0;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "delete from test.player where id=?" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			c = preparedStatement.executeUpdate();
			
			if(c == 0)
				throw new BussinessException("No Data Deleted");
			else
				System.out.println("Data DELETE Successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public int updatePlayerContact(int id, long newContact) throws BussinessException {
		
		int c = 0;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "update test.player set contact=? where id=?" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, newContact);
			preparedStatement.setInt(2, id);
			c = preparedStatement.executeUpdate();
			
			if(c == 0)
				throw new BussinessException("No Data Updated");
			else
				System.out.println("Data UPDATE Successfully.");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return c;
	}

	@Override
	public Player getPlayerById(int id) throws BussinessException {
		
		Player player = null;
		
		try (Connection conn = PostresqlConnection.getConnection()){
			
			String sql = "select name , age, contact, team_name, gender, dob from test.player where id=?" ;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				player = new Player();
				player.setId(id);
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
				throw new BussinessException("No Players Found.");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return playersList;
	}

}

package com.app.dao;

import java.util.List;

import com.app.exception.BussinessException;
import com.app.modal.Player;

public interface PlayerSearchDAO {
	
	public Player getPlayerById(int id) throws BussinessException;
	public Player getPlayerByContact(long contact) throws BussinessException;
	public List<Player> getAllPlayers() throws BussinessException;
	public List<Player> getPlayersByAge(int age) throws BussinessException;
	public List<Player> getPlayersByGender(String gender) throws BussinessException;
	public List<Player> getPlayersByTeamName(String teamname) throws BussinessException;
	public List<Player> getPlayersByName(String name) throws BussinessException;
	public List<Player> getPlayersByDob(String dob) throws BussinessException;

}

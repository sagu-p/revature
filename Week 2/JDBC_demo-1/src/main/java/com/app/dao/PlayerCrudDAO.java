package com.app.dao;

import java.util.List;

import com.app.exception.BussinessException;
import com.app.modal.Player;

public interface PlayerCrudDAO {
	
	public int createPlayer(Player player) throws BussinessException;
	public void deletePlayer(int id) throws BussinessException;
	public int updatePlayerContact(int id, long newContact) throws BussinessException;
	public Player getPlayerById(int id) throws BussinessException ;
	public List<Player> getAllPlayers() throws BussinessException;

}

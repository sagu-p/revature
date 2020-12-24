package com.app.dao;

import java.util.List;

import com.app.exception.BussinessException;
import com.app.modal.Player;

public interface PlayerSearchDAO {
	
	public Player getPlayerByContact(long contact) throws BussinessException;
	public List<Player> getPlayersByGender(String gender) throws BussinessException;

}

package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.PlayerSearchDAO;
import com.app.dao.impl.PlayerSearchDAOImpl;
import com.app.exception.BussinessException;
import com.app.modal.Player;
import com.app.service.PlayerSearchService;

public class PlayerSearchSearchImpl implements PlayerSearchService {

	PlayerSearchDAO playerSearchDAO = new PlayerSearchDAOImpl();
	
	@Override
	public Player getPlayerById(int id) throws BussinessException {
		Player player = null;
		if(id>0 & id<200)
		{
			player = playerSearchDAO.getPlayerById(id);
		}
		else {
			throw new BussinessException("Enter Valid ID.");
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BussinessException {
		Player player = null;
		if((contact+"").length() == 10)
		{
			player = playerSearchDAO.getPlayerByContact(contact);
		}
		else
		{
			throw new BussinessException("Enter Valid Length of Number.");
		}
		return player;
	}

	@Override
	public List<Player> getAllPlayers() throws BussinessException {
		List<Player> playersList = new ArrayList<>();
		playersList = playerSearchDAO.getAllPlayers();
		return playersList;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BussinessException {
		List<Player> playersByGenderList = new ArrayList<>();
		if(age>=20 & age<46)
			playersByGenderList = playerSearchDAO.getPlayersByAge(age);
		else
			throw new BussinessException("Invalid Age. (Age must be between 20 and 45)");
		
		return playersByGenderList;
	}

	@Override
	public List<Player> getPlayersByGender(String gender) throws BussinessException {
		List<Player> playersByGenderList = new ArrayList<>();
		if(gender.equalsIgnoreCase("male") | gender.equalsIgnoreCase("female")) {
			gender = gender.toUpperCase().charAt(0)+"";
		}
		if(gender.equals("M") | gender.equals("F"))
		{
			playersByGenderList = playerSearchDAO.getPlayersByGender(gender);
		}
		else {
			throw new BussinessException("Invalid Gender.");
		}
		
		return playersByGenderList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamname) throws BussinessException {
		List<Player> playersByTeamNameList = new ArrayList<>();
		playersByTeamNameList = playerSearchDAO.getPlayersByTeamName(teamname);
		return playersByTeamNameList;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BussinessException {
		List<Player> playersByNameList = new ArrayList<>();
		playersByNameList = playerSearchDAO.getPlayersByName(name);
		return playersByNameList;
	}

	@Override
	public List<Player> getPlayersByDob(String dob) throws BussinessException {
		List<Player> playersByDobList = new ArrayList<>();
		if( dob.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
			playersByDobList = playerSearchDAO.getPlayersByDob(dob);
		else
			throw new BussinessException("Invalid Date Formate");
		return playersByDobList;
	}

}

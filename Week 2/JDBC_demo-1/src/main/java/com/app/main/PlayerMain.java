package com.app.main;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.app.dao.PlayerCrudDAO;
import com.app.dao.PlayerSearchDAO;
import com.app.dao.impl.PlayerCrudDAOImpl;
import com.app.dao.impl.PlayerSearchDAOImpl;
import com.app.exception.BussinessException;
import com.app.modal.Player;

public class PlayerMain {
	
	public static void main(String[] args) {
		
		PlayerCrudDAO dao = new PlayerCrudDAOImpl();
		PlayerSearchDAO searchDao = new PlayerSearchDAOImpl();
		Player player;
		
		int id, age;
		String name, team, gen;
		long contact;
		
		Scanner sc= new Scanner(System.in);
		
		boolean exit = true;
		
		System.out.println("Player Table Operations:");
		
		while(exit) {
		
			System.out.println("\n\nTo INSERT Enter 1.");
			System.out.println("To DELETE by ID Enter 2.");
			System.out.println("To UPDATE Contact Number according to ID Enter 3.");
			System.out.println("To Show Player By ID Enter 4.");
			System.out.println("To Show Player By Contact Enter 5.");
			System.out.println("To Show All Players grouped with Gender Enter 6.");
			System.out.println("To Show All Players Enter 7.");
			System.out.println("To Exit Enter any Number except above.");
			System.out.print("Enter Your Choise: ");
			int ch = sc.nextInt();
			
			
			
			switch(ch)
			{
				case 1:
					System.out.println("\nEnter Data for Inseration:");
					System.out.print("Enter ID: ");
					id = sc.nextInt();
					System.out.print("Enter Name: ");
					name = sc.next();
					System.out.print("Enter Age: ");
					age = sc.nextInt();
					System.out.print("Enter Contact Number: ");
					contact = sc.nextLong();
					System.out.print("Enter Team Name: ");
					team = sc.next();
					System.out.print("Enter Gender: ");
					gen = sc.next().toUpperCase();
					//player = new Player(102, "Bhumi", 21, 5432167890L, "IND", "F", new Date());
					player = new Player(id, name, age, contact, team, gen, new Date());
					try {
						if(dao.createPlayer(player) != 0)
							System.out.println("Player Created Successfully.");
					} catch (BussinessException e) {
						System.out.println(e);
					}
					break;
					
				case 2:
						
					try {
						System.out.println("\nTo DELETE the data according to ID:");
						System.out.print("Enter ID: ");
						id = sc.nextInt();
						dao.deletePlayer(id);
					} catch (BussinessException e2) {
						System.out.println(e2.getMessage());
					}
					break;
					
				case 3:
					try {
						System.out.println("\nTo UPDATE Contact Number the data according to ID:");
						System.out.print("Enter ID: ");
						id = sc.nextInt();
						System.out.print("Enter Contact Number: ");
						contact = sc.nextLong();
						dao.updatePlayerContact(id, contact);
					} catch (BussinessException e2) {
						System.out.println(e2.getMessage());
					}
					break;
					
				case 4:					
					try {						
						System.out.println("\nTo get the data according to ID:");
						System.out.print("Enter ID: ");
						id = sc.nextInt();
						player = dao.getPlayerById(id);
						System.out.println(player);
					} catch (BussinessException e) {
						System.out.println(e);
					}
					break;
					
				case 5:
					try {
						System.out.println("\nTo get the data according to Contact Number:");
						System.out.print("Enter Contact Number: ");
						contact = sc.nextLong();
						player = searchDao.getPlayerByContact(contact);
						System.out.println(player);
					} catch (BussinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				
				case 6:
					try {
						
						System.out.println("\nTo Get Player's information according to Gender:");
						System.out.print("Enter Gender: ");
						gen = sc.next().toUpperCase();						
						List<Player> lst = searchDao.getPlayersByGender(gen);
						System.out.println("Number Players with Gender "+ gen +", Found in Databse is "+lst.size());
						for(Player p : lst)
						{
							System.out.println(p);
						}
					} catch (BussinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 7:
					try {
							List<Player> lst = dao.getAllPlayers();
							System.out.println("Number Players Found in Databse is "+lst.size());
							for(Player p : lst)
							{
								System.out.println(p);
							}
					} catch (BussinessException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				default:
					System.out.println("\nThank You!.");
					exit = false;
					break;
			}
			
			
		}
		
		
		
		/*
		Player player = new Player(101, "Payal", 22, 1234567890, "USA", "F", new Date());
		
		try {
			if(dao.createPlayer(player) != 0)
				System.out.println("Player Created Successfully.");
		} catch (BussinessException e) {
			System.out.println(e);
		}
		
		*/
		
		//dao.updatePlayerContact(1, 9727822689L);
		//dao.deletePlayer(6);
		
		/*
		try {
			player = dao.getPlayerById(1);
			System.out.println(player);
			
			
			
			List<Player> lst = dao.getAllPlayers();
			System.out.println("Number Players Found in Databse is "+lst.size());
			for(Player p : lst)
			{
				System.out.println(p);
			}
			
		} catch (BussinessException e) {
			System.out.println(e.getMessage());
		}
		*/
		
	}

}

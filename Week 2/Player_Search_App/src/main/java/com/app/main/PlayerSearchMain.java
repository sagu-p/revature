package com.app.main;

import java.util.List;
import java.util.Scanner;

import com.app.exception.BussinessException;
import com.app.modal.Player;
import com.app.service.PlayerSearchService;
import com.app.service.impl.PlayerSearchSearchImpl;

public class PlayerSearchMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PlayerSearchService playerSearchService = new PlayerSearchSearchImpl();
		Player player;
		
		System.out.println("Welcome to Player Search Console Application:");
		System.out.println("==============================================");
		
		int ch = 0;
		
		do
		{
			System.out.println("\n\n1) Get Player By ID.");
			System.out.println("2) Get Player By Contact Number.");
			System.out.println("3) Get Player By Player's Name.");
			System.out.println("4) Get Player By Player's Date of Birth.");
			System.out.println("5) Get Players By Gender.");
			System.out.println("6) Get Players By Team Name.");
			System.out.println("7) Get Players By Player's Age.");
			System.out.println("8) Get All Playes.");
			System.out.println("9) Exit.");
			System.out.print("Enter Your Choise: ");
			try {
				ch = Integer.parseInt(sc.next());
			}
			catch (NumberFormatException e)
			{
				System.out.println("Enter Valid Choise. Don't Enter Characture/s or Space/s.");
			}
			
			switch (ch) {
			case 1:
				try {
					System.out.println("Search Player by the ID.");
					System.out.print("Enter ID: ");
					int id = Integer.parseInt(sc.next());
					player = playerSearchService.getPlayerById(id);
					System.out.println("\n"+player);
				}catch(NumberFormatException e)
				{
					System.out.println("Dont Enter Charature/s or space/s..");
				} catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
				
			case 2:
				
				try {
					System.out.println("Search Player by the Contact Number:");
					System.out.print("Enter Contact Number: ");
					long contact = Long.parseLong(sc.next());
					player = playerSearchService.getPlayerByContact(contact);
					System.out.println("\n"+player);
				}catch(NumberFormatException e)
				{
					System.out.println("Enter Valid Contact Number with length of 10.");
				}
				catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 3:
				System.out.println("Search Player/s by their Name.");
				System.out.print("Enter Name: ");
				String name = new String(sc.next());
				try {
					List<Player> playersByNameList = playerSearchService.getPlayersByName(name);
					System.out.println("\nList of Player/s, Whose Name is "+name+".");
					System.out.println("There are "+playersByNameList.size()+" Player/s.");
					for(Player p: playersByNameList)
						System.out.println(p);
				} catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 4:
				System.out.println("Search Player/s by their Date of Birth.");
				System.out.print("Enter Date of Birth (yyyy-dd-mm) : ");
				String dob = new String(sc.next());
				try {
					List<Player> playersByDobList = playerSearchService.getPlayersByDob(dob);
					System.out.println("\nList of Player/s, Whose Name is "+dob+".");
					System.out.println("There are "+playersByDobList.size()+" Player/s.");
					for(Player p: playersByDobList)
						System.out.println(p);
				} catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 5:
				System.out.println("Search Player/s by their Gender.");
				System.out.print("Enter Gender: ");
				String gender = new String(sc.next());
				try {
					List<Player> playersByNameList = playerSearchService.getPlayersByGender(gender);
					System.out.println("\nList of Player/s, Whose Gender is "+gender+".");
					System.out.println("There are "+playersByNameList.size()+" Player/s.");
					for(Player p: playersByNameList)
						System.out.println(p);
				} catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 6:
				System.out.println("Search Player/s by their Team Name.");
				System.out.print("Enter Team Name: ");
				String teamname = new String(sc.next());
				try {
					List<Player> playersByTeamNameList = playerSearchService.getPlayersByTeamName(teamname);
					System.out.println("\nList of Player/s, Whose Name is "+teamname+".");
					System.out.println("There are "+playersByTeamNameList.size()+" Player/s.");
					for(Player p: playersByTeamNameList)
						System.out.println(p);
				} catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 7:
				try {
					System.out.println("Search Player by the Age.");
					System.out.print("Enter Age: ");
					int age = Integer.parseInt(sc.next());
					List<Player> playersByAgeList = playerSearchService.getPlayersByAge(age);
					System.out.println("There are "+playersByAgeList.size()+" total Players.");
					for(Player p: playersByAgeList)
						System.out.println(p);
				}catch(NumberFormatException e)
				{
					System.out.println("Dont Enter Charature/s or space/s.");
				}
				catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 8:
				System.out.println("\nList of All Players.");
				try {
					List<Player> playersList = playerSearchService.getAllPlayers();
					System.out.println("There are "+playersList.size()+" total Players.");
					for(Player p: playersList)
						System.out.println(p);
				} catch (BussinessException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 9:
				System.out.println("Thank you For using the Application.");
				break;

			default:
				System.out.println("Enter Valid Choise.");
				break;
			}
			
		}while(ch!=9);

	}

}

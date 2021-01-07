package com.app.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.app.modal.Player;
import com.app.modal.PlayerTeam;
import com.app.modal.Team;

public class TestMainProject {

	public static void main(String[] args) {

		Scanner sc= new Scanner(System.in);
		
		Player player =null;
		Player player1 = new Player(103, "Sagar", 21, 5432167890L, "USA", "M", new Date());
		Player player3 = new Player(102, "Bhumi", 21, 5432167890L, "IND", "F", new Date());
		
		Team team = new Team("Red Bull");
		Team team1 = new Team("Black Panther");
		
		
		//PlayerTeam playerT = new PlayerTeam(player, team);
		PlayerTeam playerT1 = new PlayerTeam(player1, team1);
		
		Map<Player, Team> playerTeam = new LinkedHashMap<>();
		
		for(int i=1;i<3;i++) {
			player = new Player(102, "Bhumi", 21, 5432167890L, "IND", "F", new Date());
			playerTeam.put(player, team);
		}
			
		playerTeam.put(player1, team1);
		playerTeam.put(player3, team);
		
		int i=1;
		for( Map.Entry<Player, Team> e: playerTeam.entrySet() ) {
			System.out.println(i++);
			System.out.println(e.getKey());
			System.out.println(e.getValue()+"\n");
		}
		System.out.print("Enter: ");
		i = sc.nextInt()-1;
		Set<Player> playerSet = playerTeam.keySet();
		List<Player> playerList = new ArrayList<>(playerSet);
		//System.out.println(playerList.get(i));
		
		
		
		//Account obj is got tehn REQ. for status update
		System.out.println("NEW VJDH: "+ playerTeam.get(playerList.get(i)));
		
		
		
			
		
		/*
		List<PlayerTeam> playerTeam = new ArrayList<>();
		playerTeam.add(playerT);
		playerTeam.add(playerT1);
		//System.out.println(playerTeam);
		
		int j= 1;
		for(PlayerTeam i : playerTeam) {
			System.out.println(j++);
			System.out.println(i);
		}
		
		System.out.print("Eneter: ");
		int k = sc.nextInt() - 1;
		System.out.println(playerTeam.get(k));
		*/
		
		sc.close();
		
		
		
		

	}

}

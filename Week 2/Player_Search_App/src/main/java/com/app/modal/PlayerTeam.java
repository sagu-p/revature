package com.app.modal;

public class PlayerTeam {

	private Player player;
	private Team team;
	
	PlayerTeam(){
		
	}

	public PlayerTeam(Player player, Team team) {
		super();
		this.player = player;
		this.team = team;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "player=" + player + "\nteam=" + team + "\n" ;
	}
	
	
	
}

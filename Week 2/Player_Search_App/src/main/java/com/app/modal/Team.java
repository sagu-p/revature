package com.app.modal;

public class Team {

	private String team_name;
	
	public Team() {
		
	}

	public Team(String team_name) {
		super();
		this.team_name = team_name;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	@Override
	public String toString() {
		return "Team [team_name=" + team_name + "]";
	}
	
	
	
}

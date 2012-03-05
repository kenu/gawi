package net.okjsp.gawi;

import java.util.Date;

public class Game {

	private int choice;
	private int computerChoice;
	private String judgement;
	private Date datetime;

	public Game(int choice, int computerChoice, String judgement, Date datetime) {
		this.choice = choice;
		this.computerChoice = computerChoice;
		this.judgement = judgement;
		this.datetime = datetime;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getComputerChoice() {
		return computerChoice;
	}

	public void setComputerChoice(int computerChoice) {
		this.computerChoice = computerChoice;
	}

	public String getJudgement() {
		return judgement;
	}

	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}

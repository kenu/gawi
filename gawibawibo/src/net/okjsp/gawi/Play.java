package net.okjsp.gawi;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Play {

	public String[] items = { "가위", "바위", "보" };
	private List<Game> list;

	/**
	 * @param args
	 */
	static public void main(String[] args) {
		Play play = new Play();
		play.load();
		while (play.game()) {
			System.out.println("====\n\n\n====");
		}
	}

	public void load() {
		list = DataAccessObject.load();
		System.out.println(list.size() + " records loaded.");
		showStat();
		System.out.println("====");
	}

	public boolean game() {
		for (int i = 0; i < items.length; i++) {
			System.out.print(i + ":" + items[i] + " ");
		}

		System.out.print("\n----\n선택하세요:");

		Scanner scanner = new Scanner(System.in);
		String next = scanner.next();
		int choice = Integer.parseInt(next);

		if (choice < 0 || choice > 2) {
			System.out.println("0~2 숫자만 입력해주세요.\n게임을 종료합니다.");
			return false;
		}

		int computerChoice = getComputerChoice();

		System.out.println("----\n" + "당신: " + items[choice] + "\n컴퓨터: "
				+ items[computerChoice]);

		String judgement = judge(choice, computerChoice);

		System.out.println("----\n" + judgement);

		save(choice, computerChoice, judgement);

		showStat();

		return true;
	}

	public int getComputerChoice() {
		return new Random(System.nanoTime()).nextInt(3);
	}

	private void showStat() {
		System.out.println("\n----\ntotal: " + list.size());
		win = 0;
		even = 0;
		lose = 0;
		for (Game game : list) {
			if (game.getJudge() == 0) {
				even = even + 1;
			} else if (game.getJudge() == 1) {
				win = win + 1;
			} else {
				lose = lose + 1;
			}
		}
		if (list.size() == 0) {
			System.out.println("전적이 없습니다.");
			return;
		}
		int rate = (win * 100 / list.size());
		System.out.println(win + "승 " + even + "무 " + lose + "패");
		System.out.println("승률: " + rate + "%");
	}

	private int win;
	private int even;
	private int lose;
	
	public int getTotal() {
		return (list == null) ? 0 : list.size();
	}
	public int getWin() {
		return win;
	}
	public int getEven() {
		return even;
	}
	public int getLose() {
		return lose;
	}
	public int getRate() {
		return (win * 100 / getTotal());
	}

	public void save(int choice, int computerChoice, String judgement) {
		Date datetime = new Date();
		Game game = new Game(choice, computerChoice, judgement, datetime);
		if (list == null) {
			load();
		} else {
			list.add(game);
		}
		DataAccessObject.save(game);
	}

	public String judge(int choice, int computerChoice) {
		String judgement;

		if (choice == computerChoice) {
			judgement = "비겼습니다.";
		} else {
			int diff = computerChoice - choice;
			if ((diff == 1) || (diff == -2)) {
				judgement = "컴퓨터가 이겼습니다.";
			} else {
				judgement = "당신이 이겼습니다.";
			}
		}
		return judgement;
	}
}

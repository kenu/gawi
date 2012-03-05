package net.okjsp.gawi;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Play {

	private static List<Game> list;
	static DataAccessObject dao = new DataAccessObject();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		load();
		while (game()) {
			System.out.println("====\n\n\n====");
		}
	}

	private static void load() {
		list = dao.load();
		System.out.println(list.size() + " records loaded.");
		showStat();
		System.out.println("====");
	}

	public static boolean game() {
		String[] items = { "가위", "바위", "보" };
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

		int computerChoice = new Random(System.nanoTime()).nextInt(3);

		System.out.println("----\n" + "당신: " + items[choice] + "\n컴퓨터: "
				+ items[computerChoice]);

		String judgement = judge(choice, computerChoice);

		System.out.println("----\n" + judgement);

		save(choice, computerChoice, judgement);

		showStat();

		return true;
	}

	private static void showStat() {
		System.out.println("\n----\ntotal: " + list.size());
		int win = 0;
		int even = 0;
		int lose = 0;
		for (Game game : list) {
			if (game.getJudgement().equals("비겼습니다.")) {
				even = even + 1;
			} else if (game.getJudgement().equals("당신이 이겼습니다.")) {
				win = win + 1;
			} else {
				lose = lose + 1;
			}
		}
		int rate = (win * 100 / list.size());
		System.out.println(win + "승 " + even + "무 " + lose + "패");
		System.out.println("승률: " + rate + "%");
	}

	private static void save(int choice, int computerChoice, String judgement) {
		Date datetime = new Date();
		Game game = new Game(choice, computerChoice, judgement, datetime);
		list.add(game);
		dao.save(game);
	}

	public static String judge(int choice, int computerChoice) {
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

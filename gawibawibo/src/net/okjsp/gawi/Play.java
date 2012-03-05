package net.okjsp.gawi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Play {

	private static ArrayList<Game> list;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (game()) {
			System.out.println("====\n\n\n====");
		}
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
		
		return true;
	}

	private static void save(int choice, int computerChoice, String judgement) {
		Date datetime = new Date();
		Game game = new Game(choice, computerChoice, judgement, datetime );
		list.add(game);
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

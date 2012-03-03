package net.okjsp.gawi;

import java.util.Random;
import java.util.Scanner;

public class Play {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (game()) {
			System.out.println("====");
		}
	}

	public static boolean game() {
		String[] items = { "가위", "바위", "보" };
		for (int i = 0; i < items.length; i++) {
			System.out.println(i + ":" + items[i]);
		}

		System.out.print("----\n선택하세요:");

		Scanner scanner = new Scanner(System.in);
		String next = scanner.next();
		int choice = Integer.parseInt(next);

		if (choice < 0 || choice > 2) {
			System.out.println("0~2 숫자만 입력해주세요.\n게임을 종료합니다.");
			return false;
		}

		int computerChoice = new Random(System.nanoTime()).nextInt(3);

		System.out.println("----\n" + "당신: " + items[choice] + "컴퓨터: "
				+ items[computerChoice] + "\n");

		String judgement = judge(choice, computerChoice);

		System.out.println("----\n" + judgement);

		return true;
	}

	public static String judge(int choice, int computerChoice) {
		String judgement;

		if (choice == computerChoice) {
			judgement = "비겼습니다.";
		} else if ((choice == 0 && computerChoice == 1)
				|| (choice == 1 && computerChoice == 2)
				|| (choice == 2 && computerChoice == 0)) {
			judgement = "컴퓨터가 이겼습니다.";
		} else {
			judgement = "당신이 이겼습니다.";
		}
		return judgement;
	}
}

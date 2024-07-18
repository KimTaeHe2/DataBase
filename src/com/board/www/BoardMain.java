package com.board.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.service.BoardService;
import com.board.www.service.MemberService;

public class BoardMain {
	// 필드
	public static Scanner scanner = new Scanner(System.in); // 입력받는 객체
	// public static BoardDAO boardDAO = new BoardDAO(); // jdbc 담당
	public static Connection connection = null;
	public static MemberDTO loginMember = null; // 로그인 후 객체

	// 생성자
	public BoardMain() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 1단계
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.111.103:1521:orcl", "boardtest",
					"boardtest"); // 2단계
		} catch (ClassNotFoundException e) {
			// 1단계 예외처리
			System.out.println("드라이버명 또는 ojdbc6.jar를 확인해주세요.");
			e.printStackTrace();
		} catch (SQLException e) {
			// 2단계 예외처리
			System.out.println("url, id, pw 또는 쿼리문이 잘못되었습니다.");
			e.printStackTrace();
			System.exit(0); // 프로세스 강제 종료
		} // try/catch 종료

	} // BoardMain 종료

	// 메서드

	public static void main(String[] args) {
		// jdbc를 활용하여 게시판 구현
		// 기본적인 설정 : 스캐너, jdbc연동, 주메뉴

		BoardMain boardMain = new BoardMain(); // 생성자 호출 -> 1단계, 2단계 실행
		boolean run = true;
		System.out.println("MBC 아카데미 대나무숲 오신걸 환영합니다.");
		System.out.println("================================");
		while (run) {
			System.out.println("1. 회원 | 2. 게시판 | 3. 종료");
			System.out.println("================================");
			System.out.print(">>> ");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				MemberService memberService = new MemberService();
				loginMember = memberService.memberMenu(scanner, loginMember, connection);
				System.out.println(loginMember.getMid() + " 님 환영합니다.");
				// 회원 서비스에서 나올때 로그인 정보가 유지되야 됨.
				break;
				
			case 2:
				BoardService boardService = new BoardService();
				boardService.list(connection);
				break;
				
			case 3:
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
				
				default:
					System.out.println("잘못된 입력입니다. 1~3까지 입력해주세요.");
				
			} // switch 종료
		} // while 종료

	} // main 종료

} // class 종료

package com.board.www.service;

import java.sql.Connection;
import java.util.Scanner;

import com.board.www.dao.MemberDAO;
import com.board.www.dto.MemberDTO;

public class MemberService {
	// 회원에 대한 처리 C(회원가입) R(로그인) U(회원정보수정) D(회원탈퇴)

	public MemberDTO memberMenu(Scanner scanner, MemberDTO loginMember, Connection connection) { // while문으로 부메뉴 반복 처리
		System.out.println("회원관리용 서비스로 진입");
		boolean memberRun = true;
		while (memberRun) {
			System.out.println("1.회원가입 2. 로그인 3. 회원수정 4. 회원탈퇴 5. 종료");
			System.out.print(">>> ");
			int memberSelect = scanner.nextInt();
			switch (memberSelect) {
			case 1:
				join(scanner, connection);
				break;
			case 2:
				loginMember = login(scanner, loginMember, connection);
				break;
			case 3:
				if(loginMember.getMid() != null) {
				loginMember = modify(scanner, loginMember, connection);
				}else {
					System.out.println("로그인후 이용해주세요.");
				}
				break;
			case 4:
				if(loginMember.getMid() != null) {
				delete(scanner, loginMember, connection);
				}else {
					System.out.println("로그인후 이용해주세요.");
				}
				break;
			case 5:
				System.out.println("회원관리 메뉴를 종료합니다.");
				memberRun = false;
				break;
			} // switch 종료

		} // while 종료
		return loginMember;
	} // memberMenu 종료

	public void join(Scanner scanner, Connection connection) { // 회원가입용 메서드
		System.out.println("회원가입 페이지 입니다. 사용하실 정보를 입력해주세요.");
		System.out.print("ID : ");
		String joinId = scanner.next();
		System.out.print("PW : ");
		String joinPw = scanner.next();
		
		MemberDTO joinUser = new MemberDTO(joinId, joinPw);
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.register(connection, joinUser);
	} // join 종료

	public MemberDTO login(Scanner scanner, MemberDTO loginMember, Connection connection) {
		System.out.println("로그인 메서드로 진입");
		System.out.print("ID : ");
		String loginId = scanner.next();
		System.out.print("PW : ");
		String loginPw = scanner.next();
		MemberDTO loginMemberDTO = new MemberDTO(loginId, loginPw);
		// 키보드로 입력받은 값을 객체로 생성
		
		MemberDAO memberDAO = new MemberDAO();
		return memberDAO.login(connection, loginMemberDTO); // DB에서 넘어온 객체를 리턴한다.
	} // login 종료

	public MemberDTO modify(Scanner scanner, MemberDTO loginMember, Connection connection) {
		System.out.println("회원정보 수정 메서드로 진입");
		System.out.println("현재 사용중인 정보");
		System.out.println("ID : " + loginMember.getMid());
		System.out.println("PW : " + loginMember.getMpw());
		System.out.println("변경하실 ID와 PW를 입력해주세요");
		System.out.println("================================");
		System.out.print("ID : ");
		String modifyId = scanner.next();
		System.out.print("PW : ");
		String modifyPw = scanner.next();
		
		MemberDTO modifyUser = new MemberDTO(modifyId, modifyPw);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO loginMemberDTO = new MemberDTO();
		return memberDAO.update(modifyUser, loginMember, connection, loginMemberDTO);
	} // modify 종료

	public void delete(Scanner scanner, MemberDTO loginMember, Connection connection) {
		System.out.println("회원탈퇴 메서드로 진입");
			System.out.println("현재 사용중인 정보");
			System.out.println("ID : " + loginMember.getMid());
			System.out.println("PW : " + loginMember.getMpw());
			System.out.println("================================");
			System.out.println("정말 삭제하시겠습니까?");
			System.out.println("================================");
			System.out.println("1. YES  2. NO");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				MemberDAO deleteUser = new MemberDAO();
				deleteUser.delete(loginMember, connection);
				break;

			case 2:
				System.out.println("이전으로 돌아갑니다.");
				break;

			default:
				System.out.println("잘못된 입력입니다. 1~2까지 입력해주세요");

			} // switch 종료

	} // delete 종료
}// class 종료

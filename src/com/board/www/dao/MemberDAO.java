package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.MemberDTO;

public class MemberDAO {
	// 회원 DB에 대한 C(회원가입) R(로그인) U(회원정보수정) D(회원탈퇴)
	
	
	public MemberDAO() {} // 기본 생성자
	public MemberDAO(Connection connection) {} // 커스텀 생성자

	public void register(Connection connection, MemberDTO joinUser) { // 회원가입 처리
		
		try {
			String sql = "insert into member (mno, mid, mpw, mdate) values (board_seq.nextval,?,?,sysdate)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, joinUser.getMid());
			preparedStatement.setString(2, joinUser.getMpw());
			
			int result = preparedStatement.executeUpdate();
			
			if(result > 0 ) {
				System.out.println(result + " 행의 데이터가 추가되었습니다.");
				connection.commit();
			}else{
				System.out.println("결과 : " + result + "회원가입에 실패하였습니다.");
				connection.rollback();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	} // register 종료
	
	public MemberDTO login(Connection connection, MemberDTO loginMemberDTO) { // 로그인 처리
		// connection -> main에서 넘어온 jdbc 1,2단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어있다.
		// DB에 있는 로그인 값을 찾아 옴
		
		MemberDTO loginDTO = new MemberDTO(); // 리턴용 빈객체
		
		try {
			String sql = "select mno, mid, mpw, mdate from member where mid =? and mpw =?";
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMemberDTO.getMid());
			// 서비스에서 받은 id가 첫번째 ? 에 적용
			preparedStatement.setString(2, loginMemberDTO.getMpw());
			// 서비스에서 받은 pw가 두번째 ? 에 적용
			
			ResultSet resultSet = preparedStatement.executeQuery();
			// 위에서 만든 쿼리문을 실행하고 경과를 resultSet 표로 받는다.
			
			while(resultSet.next()) {
				loginDTO.setMno(resultSet.getInt("mno"));
				loginDTO.setMid(resultSet.getString("mid"));
				loginDTO.setMpw(resultSet.getString("mpw"));
				loginDTO.setMdate(resultSet.getDate("mdate"));
				// resultSet 표에 있는 정보를 MemberDTo 객체에 넣음
			} // while 종료
			
			resultSet.close();
			preparedStatement.close();
			
		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다.");
			System.out.println("관리자 : sql문을 확인하세요");
			System.out.println("회원 : id와 pw를 확인하세요.");
			e.printStackTrace();
		}
		return loginDTO; // 로그인 완성용 객체
		
	} // login 종료
	
	public MemberDTO update(MemberDTO modifyUser, MemberDTO loginMember, Connection connection, MemberDTO loginMemberDTO) { // 회원 정보 수정
			MemberDTO loginDTO = new MemberDTO();
		try {
			String sql = "update member set mid=?, mpw=? where mno=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modifyUser.getMid());
			preparedStatement.setString(2, modifyUser.getMpw());
			preparedStatement.setInt(3, loginMember.getMno());
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				System.out.println(result + " 행의 정보가 업데이트 되었습니다.");
				System.out.println("다시 로그인 해주세요.");
				connection.commit();
			
			}else {
				System.out.println("업데이트에 실패하였습니다");
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginDTO;
	} // update 종료
	
	public void delete(MemberDTO loginMember, Connection connection) { // 회원 탈퇴

		try {
			String sql = "delete from member where mid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMember.getMid());
			int result = preparedStatement.executeUpdate();
			
			if(result > 0) {
				System.out.println("계정 삭제가 완료되었습니다.");
			}else {
				System.out.println("계정 삭제 실패. 찾는 값이 없습니다.");
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	} // delete 종료

}

package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {
	// 데이터베이스 처리용 CRUD
	public void list(Connection connection) {
		// BoardDTO boardDTO = null;
		try {
			// boardDTO = new BoardDTO();
			String sql = "select bno, btitle, bcontent, bwriter, bdate from board order by bno desc";
			PreparedStatement preparedStatement =connection.prepareStatement(sql); // 3단계
			ResultSet resultSet = preparedStatement.executeQuery(); // 4단계
			while(resultSet.next()) { // 표형식으로 리턴된 값 유무 판단.
				System.out.print(resultSet.getInt("bno")+"\t");
				System.out.print(resultSet.getString("btitle")+"\t");
//				System.out.print(resultSet.getInt("bcontent")+"\t");
				System.out.print(resultSet.getString("bwriter")+"\t");
				System.out.println(resultSet.getDate("bdate")+"\t");
				
				
//				boardDTO.setBno(resultSet.getInt("bno"));
//				boardDTO.setBtitle(resultSet.getString("btitle"));
//				boardDTO.setBcontent(resultSet.getString("bcontent"));
//				boardDTO.setBwriter(resultSet.getString("bwriter"));
//				boardDTO.setBdate(resultSet.getDate("bdate"));
				
			} // while 종료
			// 5단계
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardDAO.list() SQL문 오류");
			e.printStackTrace();
		} // try/catch 종료
		
	} // list 종료

} // class 종료

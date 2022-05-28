package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PhoneDao {

	//필드
			private Connection conn = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;

			private String driver = "oracle.jdbc.driver.OracleDriver";
			private String url = "jdbc:oracle:thin:@localhost:1521:xe";
			private String id = "phonedb";
			private String pw = "phonedb";
			
			// 생성자

			// 메소드-gs

			// 메소드-일반

			// --DB연결 메소드
			private void getConnection() {

				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);

					// 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);
					
				} catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}

			}
			
			// --자원정리 메소드
				private void close() {
					// 5. 자원정리
					try {
						
						if (rs != null) { 
							rs.close(); 
						}
						if (pstmt != null) {
							pstmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						System.out.println("error:" + e);
					}
					
				}
		
				public List<PersonVo> phoneSelect() {

					// 리스트 준비
					List<PersonVo> personList = new ArrayList<PersonVo>();

					getConnection();
					
					try {
						
						// 3. SQL문 준비 / 바인딩 / 실행
						// SQL문 준비
						String query = "";
						query += " select  person_id, ";
						query += "         name, ";
						query += "         hp, ";
						query += "         company ";
						query += " from person ";

						// 바인딩
						pstmt = conn.prepareStatement(query);

						// 실행
						// ResultSet 가져오기
						rs = pstmt.executeQuery();

						// 4.결과처리
						// 반복문으로 Vo 만들기 list에 추가하기
						while (rs.next()) {
							int personId = rs.getInt("person_id");
							String name = rs.getString("name");
							String hp = rs.getString("hp");
							String company = rs.getString("company");

							PersonVo personVo = new PersonVo(personId, name, hp, company );

							personList.add(personVo);
						}

					} catch (SQLException e) {
						System.out.println("error:" + e);
					} 
					
					close();

					return personList;
				}
		
				public int phoneInsert(String name, String hp , String company ) {
					int count = -1;
					
					getConnection();
					
					try {
					

					// 3. SQL문 준비 / 바인딩 / 실행
						//sql문 준비
						String query ="";
						query +=" insert into person ";
						query +=" values (seq_person_id.nextval, ?,?,?) ";
						
						
						//바인딩
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, name);
						pstmt.setString(2, hp);
						pstmt.setString(3, company);
						
						//실행
						count = pstmt.executeUpdate();
					// 4.결과처리
						
						System.out.println(count + "건이 등록 되었습니다.");
						
					}  catch (SQLException e) {
					System.out.println("error:" + e);
					} 
					
					close();
					
					return count;
					
					
				}

				public int phoneUpdate( int personId, String name, String hp , String company ) {
					int count = -1;

					getConnection();
					
					try {

						// 3. SQL문 준비 / 바인딩 / 실행
						// SQL문 준비
						String query = "";
						query += " UPDATE person ";
						query += "	SET name = ? , ";
						query += "     hp = ?, ";
						query += "     company = ? ";
						query += " where person_id = ? ";
						System.out.println(query);

						// 바인딩
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, name);
						pstmt.setString(2, hp);
						pstmt.setString(3, company);
						pstmt.setInt(4, personId);
						
						// 실행
						count = pstmt.executeUpdate();

						// 4.결과처리
						System.out.println(count + "건이 수정 되었습니다.");

					} catch (SQLException e) {
						System.out.println("error:" + e);
					} 
					
					close();
					
					return count;
				}

				public int phoneDelete(int personId) {
					int count = -1;

					getConnection();

					try {
				
						// 3. SQL문 준비 / 바인딩 / 실행
						// SQL문 준비
						String query = "";
						query += " delete from person ";
						query += " where person_id = ? ";
						System.out.println(query);

						// 바인딩
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, personId);

						// 실행
						count = pstmt.executeUpdate();

						// 4.결과처리
						System.out.println(count + "건 삭제 되었습니다.");

					} catch (SQLException e) {
						System.out.println("error:" + e);
					} 
					
					close();

					return count;
				}	
				
}

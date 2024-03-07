package com.ssg.jdbcex.dao;

import com.ssg.jdbcex.domain.TodoVO;
import lombok.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TodoDAO 클래스는 TodoVO에 대한 데이터베이스 처리를 담당한다.
 */
public class TodoDAO {
    // 데이터베이스 컨넥션 풀로부터 연결 후 시간 select now() 쿼리 결과를 담아서 리턴하는 getTime() 메소드 작성
    String now = null;

    /**
     * getTime() 메서드는 데이터베이스로부터 현재 시간을 조회하여 반환한다.
     *
     * @return 데이터베이스에서 조회한 현재 시간
     */
    public String getTime() {
        try (
                Connection connection = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("select now()");
                ResultSet rs = pstmt.executeQuery();
        ) {
            rs.next();
            now = rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return now;
    }

    /**
     * getTime2() 메서드는 예외를 throws하는 방식으로 구현한 getTime() 메서드의 간소화된 버전이다.
     *
     * @return 데이터베이스에서 조회한 현재 시간
     * @throws Exception 조회 과정에서 예외가 발생할 경우 전파
     */
    public String getTime2() throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();
        rs.next();
        String now = rs.getString(1);
        return now;
    }

    /**
     * insert() 메서드는 TodoVO 객체의 정보를 이용하여 INSERT 명령을 실행한다.
     *
     * @param vo 등록할 할일 정보를 담은 TodoVO 객체
     * @return INSERT 명령의 실행 결과를 나타내는 정수값 (성공 시 1, 실패 시 0 등)
     * @throws Exception INSERT 과정에서 예외가 발생할 경우 전파
     */
    public int insert(TodoVO vo) throws Exception {
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("insert into tbl_todo(title,duedate)values (?,?)");
        pstmt.setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        int result = pstmt.executeUpdate();
        return result;
    }

    /**
     * selectAll() 메서드는 모든 할일 정보를 조회하여 TodoVO 리스트로 반환한다.
     *
     * @return 모든 할일 정보를 담은 TodoVO 리스트
     * @throws Exception 조회 과정에서 예외가 발생할 경우 전파
     */
    public List<TodoVO> selectAll() throws Exception {
        String sql = "select * from tbl_todo";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();
        List<TodoVO> todoVOList = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder().tno(rs.getLong("tno"))
                    .title(rs.getString("title")).
                    dueDate(rs.getDate("duedate").toLocalDate())
                    .finished(rs.getBoolean("finished")).build();
            todoVOList.add(vo);
        }
        return todoVOList;
    }

    /**
     * selectOne() 메서드는 tno를 받아서 해당하는 할일 정보를 조회하여 TodoVO로 반환한다.
     *
     * @param tno 조회할 할일 번호
     * @return 특정 할일 번호에 해당하는 할일 정보를 담은 TodoVO 객체
     * @throws Exception 조회 과정에서 예외가 발생할 경우 전파
     */
    public TodoVO selectOne(Long tno) throws Exception {
        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, tno);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        // 결과가 있는 경우에만 처리
        if (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            return vo;
        } else {
            System.out.println("해당하는 값이 없습니다.");
            // 결과가 없으면 null 또는 예외 처리를 수행하는 것이 적절합니다.
            return null;
        }
    }

    /**
     * deleteOne() 메서드는 tno를 받아서 해당하는 할일을 삭제한다.
     *
     * @param tno 삭제할 할일 번호
     * @return 삭제 결과를 나타내는 정수값 (성공 시 1, 실패 시 0 등)
     * @throws Exception 삭제 과정에서 예외가 발생할 경우 전파
     */
    public int deleteOne(Long tno) throws Exception {
        String sql = "DELETE FROM tbl_todo WHERE tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, tno);
        int result = pstmt.executeUpdate();
        return result;
    }

    /**
     * updateOne() 메서드는 TodoVO를 받아서 해당 할일 정보를 업데이트한다.
     *
     * @param todoVO 업데이트할 할일 정보를 담은 TodoVO 객체
     * @throws Exception 업데이트 과정에서 예외가 발생할 경우 전파
     */
    public void updateOne(TodoVO todoVO) throws Exception {
        String sql = "update tbl_todo set title =?, duedate=?, finished =? where tno =?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, todoVO.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3, todoVO.isFinished());
        pstmt.setLong(4, todoVO.getTno());
        pstmt.executeUpdate();
    }
}

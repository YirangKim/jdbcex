package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class TodoDAO {

//    public String getTime(){
//        String now = null;
//
//        try(Connection connection = ConnectionUtil.Instance.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("select now()");
//            ResultSet resultSet = preparedStatement.executeQuery();
//        ) {
//            resultSet.next();
//            now = resultSet.getString(1);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        return now;
//    }

    public String getTime() {

        String now = null;

        try (Connection connection = ConnectionUtil.Instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select now()");
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.next();

            now = resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return now;
    }

    public String getTime2() throws Exception {

        @Cleanup Connection connection = ConnectionUtil.Instance.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        String now = resultSet.getString(1);
        return now;
    }

    public void insert(TodoVO vo) throws Exception {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.Instance.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);


        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());

        preparedStatement.executeUpdate();

    }

    public TodoVO selectOne(Long tno) throws Exception {
        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.Instance.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

}
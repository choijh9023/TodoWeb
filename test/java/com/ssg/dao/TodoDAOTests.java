package com.ssg.dao;

import com.ssg.jdbcex.dao.TodoDAO;
import com.ssg.jdbcex.domain.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

public class TodoDAOTests {
    private TodoDAO todoDAO;

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime()throws Exception{
        System.out.println(todoDAO.getTime());
    }
    @Test
    public void testinsert()throws Exception{
        TodoVO vo = TodoVO.builder().title("test_Todo_VO_TEST").dueDate(LocalDate.of(2024,03,02)).build();
        todoDAO.insert(vo);

    }

    @Test
    public void testlist()throws Exception{
        List<TodoVO> todoVOList = todoDAO.selectAll();
        todoVOList.forEach(vo-> System.out.println(vo)); // 리프트 포이치문
    }
    @Test
    public void testselectone()throws Exception{
        System.out.println("selectone 실험 ");
        todoDAO.selectOne(16L);

    }
    @Test
    public void testdeleteone()throws Exception{
        System.out.println("testdelete실험");
        todoDAO.deleteOne(13L);
    }
    @Test
    public void testUpdateOne()throws Exception{
        System.out.println("testUpdate실험");
        TodoVO vo = TodoVO.builder().title("test_Todo_VO_TEST").dueDate(LocalDate.of(2024,03,02))
                .finished(false).tno(16L).build();
        todoDAO.updateOne(vo);
    }

}

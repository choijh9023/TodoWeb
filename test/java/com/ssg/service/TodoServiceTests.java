package com.ssg.service;

import com.ssg.jdbcex.Service.TodoService;
import com.ssg.jdbcex.domain.TodoVO;
import com.ssg.jdbcex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * 24년 3월 7일 오전 수업
 */
@Log4j2
public class TodoServiceTests {
    // 1. TodoService 객체 선언
    private TodoService todoService;

    // 2. @BeforeEach를 사용하여 ready 메소드를 이용하여 TodoService 객체 생성
    @BeforeEach
    public void ready() {
        // todoService = new TodoService();내가 작성한 코드
        todoService = TodoService.INSTANCE; // 강사님이 작성해준 코드
    }

    //3. @Test :testRegister메소드에 TodoDTO 하나를 빌더를 통해서 (TITLE, Duedate)를 생성한 후  서비스 등록을 수행한다.
    @Test
    public void testRegister()throws Exception{
        TodoDTO vo = TodoDTO.builder().title("test_Todo_VO_TESTNEW").dueDate(LocalDate.now()).build();

        int a =todoService.register(vo);
        Assertions.assertEquals(a,1);

    }
    //4.testRegister() 실행한 후 정상적으로 TodoVo의 내용이 출력되는지 확인



    //5. tbl_todo테이블에 insert가 정상적으로 입력되었는지 확인
@Test
public void testread()throws Exception{
    Long tno = 33L;
       TodoDTO dto= todoService.read(tno);
    //System.out.println(dto);
    log.info(dto);
}
@Test
    public void testdelete()throws Exception{
        Long tno = 38L;
        todoService.delete(tno);
}
}

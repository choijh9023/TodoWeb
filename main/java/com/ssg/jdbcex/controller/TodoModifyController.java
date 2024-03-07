/**
 * TodoModifyController
 * 24년 3월 7일 오후 4시 30분 완료
 */

package com.ssg.jdbcex.controller;

import com.ssg.jdbcex.Service.TodoService;
import com.ssg.jdbcex.dto.TodoDTO;

import javax.print.DocFlavor;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoModifyController", urlPatterns = "/todo/modify")
public class TodoModifyController extends HttpServlet {
    TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // tno 파라미터 가져오기
        String tnoparam = request.getParameter("tno");
        Long tno = Long.parseLong(tnoparam);
        TodoDTO dto = null;
        try {
            // TodoService를 통해 tno에 해당하는 TodoDTO 가져오기
            dto = todoService.read(tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("dto", dto);
        // modify.jsp로 포워딩
        request.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 수정된 TodoDTO 정보 받아오기
        String title = request.getParameter("title");
        String dueDate = request.getParameter("dueDate");
        String tno1 = request.getParameter("tno");
        boolean finished = request.getParameter("finished") != null;
        Long tno = Long.parseLong(tno1);

        TodoDTO dto = TodoDTO.builder()
                .title(title)
                .dueDate(LocalDate.parse(dueDate))
                .finished(finished)
                .tno(tno)
                .build();

        try {
            // TodoService를 통해 TodoDTO 업데이트
            todoService.update(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/todo/list");
    }
}

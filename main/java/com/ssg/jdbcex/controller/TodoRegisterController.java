/**
 * TodoRegisterController
 * 24년 3월 7일 오후 4시 30분 완료
 */

package com.ssg.jdbcex.controller;

import com.ssg.jdbcex.Service.TodoService;
import com.ssg.jdbcex.dao.TodoDAO;
import com.ssg.jdbcex.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name="todoRegisterController",urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TodoRegisterController doGet 선언");
        // register.jsp로 포워딩
        request.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 등록할 TodoDTO 정보 받아오기
        String title = request.getParameter("title");
        String duedate = request.getParameter("duedate");
        System.out.println(title);
        System.out.println(duedate);
        TodoDTO dto = TodoDTO.builder().title(title).dueDate(LocalDate.parse(duedate)).finished(true).build();
        try {
            // TodoService를 통해 TodoDTO 등록
            todoService.register(dto);
        } catch (Exception e) {
            throw new ServletException("insert error");
        }
        response.sendRedirect("/todo/list");
    }
}

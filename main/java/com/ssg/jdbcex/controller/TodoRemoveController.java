/**
 * TodoRemoveController
 * 24년 3월 7일 오후 4시 30분 완료
  */



package com.ssg.jdbcex.controller;

import com.ssg.jdbcex.Service.TodoService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.time.LocalDateTime; // 작성 날짜에 대한 클래스 import

@WebServlet(name="todoRemoveController",urlPatterns = "/todo/remove")
public class TodoRemoveController extends HttpServlet {
    TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doGet은 현재 구현되지 않음
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramTno = request.getParameter("tno");
        Long tno = Long.parseLong(paramTno);
        try {
            // TodoService를 통해 Todo 삭제
            int result = todoService.delete(tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/todo/list");
    }



}

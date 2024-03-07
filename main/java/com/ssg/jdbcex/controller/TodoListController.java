/**
 * TodoListController
 * 24년 3월 7일 오후 4시 30분 완료
 */

package com.ssg.jdbcex.controller;

import com.ssg.jdbcex.Service.TodoService;
import com.ssg.jdbcex.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    // TodoService 객체 생성
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TodoListController Doget Method 선언");
        try {
            // TodoService를 통해 TodoDTO 목록을 가져옴
            List<TodoDTO> dtoList = todoService.list();
            request.setAttribute("dtoList", dtoList);
            // list.jsp로 포워딩
            request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("List error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doPost는 현재 구현되지 않음
    }
}

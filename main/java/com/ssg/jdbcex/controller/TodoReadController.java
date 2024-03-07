/**
 * TodoReadController
 * 24년 3월 7일 오후 4시 30분 완료
 */

package com.ssg.jdbcex.controller;

import com.ssg.jdbcex.Service.TodoService;
import com.ssg.jdbcex.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {
    TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TodoReadController doGet start");
        // tno 파라미터 가져오기
        String tnoparam = request.getParameter("tno");
        Long tno = Long.parseLong(tnoparam);
        System.out.println(tnoparam);
        try {
            // TodoService를 통해 tno에 해당하는 TodoDTO 가져오기
            TodoDTO dto = todoService.read(tno);
            request.setAttribute("dto", dto);
            System.out.println(dto);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        // read.jsp로 포워딩
        request.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // doPost는 현재 구현되지 않음
    }
}

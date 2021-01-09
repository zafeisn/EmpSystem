package com.linjie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpDeletServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 重定向：跳转到deleteEmp.html页面，删除员工信息
		response.sendRedirect(request.getContextPath() + "/deletEmp.html");
	}
	
	
}

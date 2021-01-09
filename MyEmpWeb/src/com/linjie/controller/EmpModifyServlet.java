package com.linjie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpModifyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 重定向：跳转到modifyEmp.html页面进行修改员工信息
		response.sendRedirect(request.getContextPath() + "/modifyEmp.html");
	}
	
}

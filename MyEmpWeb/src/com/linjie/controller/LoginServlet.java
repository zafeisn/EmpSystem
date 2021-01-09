package com.linjie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 连接数据库，验证用户信息
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean loginSuccess = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myweb?useSSL=false&serverTimezone=UTC","root","123456");
			String sql = "select username,password from t_user where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				loginSuccess = true;
			}

		} catch (Exception e) {
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if(loginSuccess) {
			// 登录成功，则跳转到员工显示列表
			//request.getRequestDispatcher("/emp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/emp");
		}else {
			// 否则，返回失败页面
			response.sendRedirect(request.getContextPath() + "/login_error.html");
		}
		
	}
	
	
}

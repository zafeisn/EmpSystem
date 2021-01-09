package com.linjie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpManageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 重定向：跳转到empList.html页面
		//response.sendRedirect(request.getContextPath() + "/empList.html");
		
		// 转发
		//request.getRequestDispatcher("/empList.html").forward(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>                                           ");
		out.print("<html>                                                    ");
		out.print("<head>                                                    ");
		out.print("	<meta charset='UTF-8'>                                   ");
		out.print("	<title>empList Page</title>                              ");
		out.print("</head>                                                   ");
		out.print("	<body>                                                   ");
		out.print("		<h2 align='center'>员工列表</h2>                     ");
		out.print("		<hr width='60%'>                                     ");
		out.print("		<table border='1' align='center' width='40%'>        ");
		out.print("			<tr align='center'>                              ");
		out.print("				<th>序号</th>                                ");
		out.print("				<th>员工编号</th>                            ");
		out.print("				<th>员工姓名</th>                            ");
		out.print("				<th>员工薪水</th>                            ");
		out.print("				<th>入职时间</th>                            ");
		out.print("				<th>操作</th>                                ");
		out.print("			</tr>                                            ");
		
		// 连接数据库，获取数据库员工信息
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myweb?useSSL=false&serverTimezone=UTC","root","123456");
			String sql = "select id,empno,ename,sal,hiredate from emp";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				
				Integer id = rs.getInt("id");
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
				String sal = rs.getString("sal");
				String hiredate = rs.getString("hiredate");
				
				out.print("			<tr>                                             ");
				out.print("				<td>"+id+"</td>                                   ");
				out.print("				<td>"+empno+"</td>                                ");
				out.print("				<td>"+ename+"</td>                               ");
				out.print("				<td>"+sal+"</td>                               ");
				out.print("				<td>"+hiredate+"</td>                               ");
				out.print("				<td>                                         ");
				out.print("					<a href='/MyWeb/emp/modify'>修改</a>     ");
				out.print("					<a href='/MyWeb/emp/delet'>删除</a>      ");
				out.print("				</td>                                        ");
				out.print("			</tr>                                            ");
				
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
		
		out.print("		</table>                                             ");
		out.print("	</body>                                                  ");
		out.print("</html>                                                   ");
	}
	
	
}

package com.zero.homework.controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zero.homework.domain.Nodepad;
import com.zero.homework.util.DBHelper;


public class NodepadControll extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String command = req.getParameter("command");
		if("list".equalsIgnoreCase(command)){
			List<Nodepad> list = getList();
			PrintWriter out = resp.getWriter();
			Gson gson = new Gson();
			String result = gson.toJson(list);
			out.print("rwtraf");
			out.flush();
			out.close();
		}
	}

	private List<Nodepad> getList() {
		String sql = "select * from nodepad order by create_time desc";
		DBHelper helper = new DBHelper(sql);
		try {
			helper.pst.execute();
			ResultSet r = helper.pst.getResultSet();
			List<Nodepad> list = new ArrayList<Nodepad>();
			while(r.next()){
				Nodepad pad = new Nodepad(r.getTimestamp(1), r.getString(2), r.getString(3));
				list.add(pad);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}

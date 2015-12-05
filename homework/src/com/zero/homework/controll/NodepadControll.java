package com.zero.homework.controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.zero.homework.domain.Nodepad;
import com.zero.homework.util.db.DBHelper;
import com.zero.homework.util.response.ResponseResult;


public class NodepadControll extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String command = req.getParameter("command");
		if("list".equalsIgnoreCase(command)){
			List<Nodepad> list = getList();
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("add".equalsIgnoreCase(command)){
			add(req);
			List<Nodepad> list = getList();
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("update".equalsIgnoreCase(command)){
			update(req);
			List<Nodepad> list = getList();
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("search".equalsIgnoreCase(command)){
			List<Nodepad> list = search(req);
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("del".equalsIgnoreCase(command)){
			delete(req);
			List<Nodepad> list = getList();
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("remove".equalsIgnoreCase(command)){
			reomve(req);
			List<Nodepad> list = getList();
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}
	}

	private void reomve(HttpServletRequest req) {
		
		
	}

	private void delete(HttpServletRequest req) {
		
		
	}

	private  List<Nodepad> search(HttpServletRequest req) {
		String sql = "select * from nodepad where 1=1 del='N'  ";
		
		try {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			if(null!=title && !StringUtils.isEmpty(title)){
				sql = sql + " and title like "+ "'%"+title+"%'";
			}
			if(null!=content && !StringUtils.isEmpty(content)){
				sql = sql + " and content like "+ "'%"+content+"%'";
			}
			sql = sql + " order by create_time desc";
			DBHelper helper = new DBHelper(sql);
			helper.pst.execute();
			ResultSet r = helper.pst.getResultSet();
			List<Nodepad> list = new ArrayList<Nodepad>();
			while(r.next()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String temp = sdf.format(r.getTimestamp(1));
				Date date = sdf.parse(temp);
				Nodepad pad = new Nodepad(date, r.getString(2), r.getString(3));
				list.add(pad);
			}
			return list;
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	private void update(HttpServletRequest req) {
		String sql = "UPDATE nodepad  SET title=?, content=? WHERE create_time =?";
		saveOrUpdate(req, sql);
	}

	private void saveOrUpdate(HttpServletRequest req, String sql) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String dateStr  = req.getParameter("createDate");
		DBHelper helper = new DBHelper(sql);
		try {
			PreparedStatement pst = helper.pst;
			pst.setObject(1, title);
			pst.setObject(2, content);
			if(null!=dateStr && !StringUtils.isEmpty(dateStr)){
				pst.setObject(3, dateStr);
			}
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req) {
		String sql = "INSERT INTO nodepad ( `title`, `content`) VALUES (?,? );";
		saveOrUpdate(req, sql);
	}

	private void writeJsonToClient(HttpServletResponse resp, Object obj)
			throws IOException {
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		String result = gson.toJson(obj);
		out.print(result);
		out.flush();
		out.close();
	}

	private List<Nodepad> getList() {
		String sql = "select * from nodepad where del='N' order by create_time desc";
		DBHelper helper = new DBHelper(sql);
		try {
			helper.pst.execute();
			ResultSet r = helper.pst.getResultSet();
			List<Nodepad> list = new ArrayList<Nodepad>();
			while(r.next()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String temp = sdf.format(r.getTimestamp(1));
				Date date = sdf.parse(temp);
				Nodepad pad = new Nodepad(date, r.getString(2), r.getString(3));
				list.add(pad);
			}
			return list;
		} catch ( Exception e) {
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

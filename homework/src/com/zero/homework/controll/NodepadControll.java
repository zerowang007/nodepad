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
import com.zero.homework.util.ResponseResult;
import com.zero.homework.util.db.DBHelper;



public class NodepadControll extends HttpServlet{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String command = req.getParameter("command");
		if("list".equalsIgnoreCase(command)){
			List<Nodepad> list = getList("N");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("add".equalsIgnoreCase(command)){
			Nodepad pad = getNodePadFromRequest(req);
			pad.setCreateDate(new Date());
			saveOrUpdate(pad);
			List<Nodepad> list = getList("N");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("update".equalsIgnoreCase(command)){
			Nodepad pad = getNodePadFromRequest(req);
			saveOrUpdate(pad);
			List<Nodepad> list = getList("N");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("search".equalsIgnoreCase(command)){
			List<Nodepad> list = search(req);
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("del".equalsIgnoreCase(command)){
			Nodepad pad = getNodePadFromRequest(req);
			pad.setDel("Y");
			saveOrUpdate(pad);
			List<Nodepad> list = getList("N");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("remove".equalsIgnoreCase(command)){
			Nodepad pad = getNodePadFromRequest(req);
			reomve(pad);
			List<Nodepad> list = getList("N");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("restore".equalsIgnoreCase(command)){
			Nodepad pad = getNodePadFromRequest(req);
			pad.setDel("N");
			saveOrUpdate(pad);
			List<Nodepad> list = getList("N");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}else if("recycle".equalsIgnoreCase(command)){
			List<Nodepad> list = getList("Y");
			ResponseResult result = new ResponseResult(list);
			writeJsonToClient(resp, result);
		}
	}

	
	private Nodepad getNodePadFromRequest(HttpServletRequest req) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id  = req.getParameter("id");
		Nodepad pad = new Nodepad();
		if(title!=null && !StringUtils.isEmpty(title)){
			pad.setTitle(title);
		}
		if(content!=null && !StringUtils.isEmpty(content)){
			pad.setContent(content);
		}
		if(id!=null && !StringUtils.isEmpty(id)){
			pad.setId(Integer.parseInt(id));
		}
		return pad;
	}

	private void reomve(Nodepad pad) {
		String sql = " delete from nodepad where  id=? ";
		DBHelper helper = new DBHelper(sql);
		PreparedStatement pst = helper.pst;
		try {
			pst.setObject(1, pad.getCreateDate());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	private  List<Nodepad> search(HttpServletRequest req) {
		String sql = "select id,title, content from nodepad where 1=1 del='N'  ";
		
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
				Nodepad pad = new Nodepad(r.getInt(1), r.getString(2), r.getString(3));
				list.add(pad);
			}
			return list;
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	private void saveOrUpdate(Nodepad pad)  {
		String sql = "";
		try {
			if(null==pad.getId()){
				sql = "INSERT INTO nodepad ( `title`, `content`) VALUES (?,? );";
				DBHelper helper = new DBHelper(sql);
				PreparedStatement pst = helper.pst;
				pst.setObject(1, pad.getTitle());
				pst.setObject(2, pad.getContent());
				pst.execute();
			}else {
				sql = "UPDATE nodepad   ";
				List<Object> param = new ArrayList<Object>();
				if(null!=pad.getTitle()&& !StringUtils.isEmpty(pad.getTitle())){
					sql += " SET title=? , ";
					param.add(pad.getTitle());
				}
				if(null!=pad.getContent()&& !StringUtils.isEmpty(pad.getContent())){
					if(sql.contains("SET")){
						sql  +=  " content=? , ";
					}else {
						sql  +=  " SET content=? ,";
					}
					param.add(pad.getContent());
				}
				if(null!=pad.getDel()&& !StringUtils.isEmpty(pad.getDel())){
					if(sql.contains("SET")){
						sql  +=  " del=? , ";
					}else {
						sql  +=  " SET del=? , ";
					}
					param.add(pad.getDel());
				}
				if(sql.contains("SET")){
					sql  +=  " update_time=? , ";
				}else {
					sql  +=  " SET update_time=? , ";
				}
				param.add(new Date());
				sql = sql.substring(0, sql.lastIndexOf(","));
				sql +=  " where id=? ";
				param.add(pad.getId());
				DBHelper helper = new DBHelper(sql);
				PreparedStatement pst = helper.pst;
				if(param.size()>0){
					for(int i=0;i<param.size();i++){
						pst.setObject(i+1, param.get(i));
					}
				}
				pst.execute();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
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

	private List<Nodepad> getList(String flag) {
		String sql = "select id,title, content from nodepad where del=? order by ";
		try {
			if("N".equalsIgnoreCase(flag)){
				sql += " create_time desc ";
			}else {
				sql += " update_time desc ";
			}
			DBHelper helper = new DBHelper(sql);
			helper.pst.setObject(1, flag);
			helper.pst.execute();
			ResultSet r = helper.pst.getResultSet();
			List<Nodepad> list = new ArrayList<Nodepad>();
			while(r.next()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Nodepad pad = new Nodepad(r.getInt(1), r.getString(2), r.getString(3));
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

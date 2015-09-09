package com.gcit.lms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;

public abstract class BaseDatabase<T> {
	private int pageNo = -1;
	private int pageSize = 10;
	
	private Connection connection = null;
	
	public BaseDatabase(Connection conn) throws Exception{
		this.connection = conn;
	}
	

	public Connection getConnection() throws Exception{
		return connection;
	}

	public void save(String query, Object[] vals) throws Exception{
		Connection conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(query);
		int count = 1; //for debugging
		for(Object o: vals){
			stmt.setObject(count, o);
			count++;
		}
		
		stmt.executeUpdate();
	}
	
	public int saveWithID(String query, Object[] vals) throws Exception{
		Connection conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for(Object o: vals){
			stmt.setObject(count, o);
			count++;
		}
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return -1;
		}
	}
	
	public List<?> read(String query, Object[] vals) throws Exception{
		List<T> objects = new ArrayList<T>();
		Connection conn = getConnection();
		int pageNo = getPageNo();
		if(getPageNo()>-1){
			int start = ((pageNo-1)*10) +1;
			if(start>0){
				query = query + " LIMIT "+start+", "+getPageSize();
			}else{
				query = query + " LIMIT 0, "+getPageSize();
			}
		}
		PreparedStatement stmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				stmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = stmt.executeQuery();
		return extractData(rs);
	}
	
	public abstract List<T> extractData(ResultSet rs) throws Exception;
	
	public List<?> readFirstLevel(String query, Object[] vals) throws Exception{
		List<T> objects = new ArrayList<T>();
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		if(vals!=null){
			int count = 1;
			for(Object o: vals){
				stmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = stmt.executeQuery();
		return extractDataFirstLevel(rs);
	}
	
	public abstract List<T> extractDataFirstLevel(ResultSet rs) throws Exception;


	
	public int getPageNo() {
		return pageNo;
	}


	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	
	public int getPageSize() {
		return pageSize;
	}


	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}

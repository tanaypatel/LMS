package com.gcit.lms.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

public class PublisherDatabase extends BaseDatabase {

	public PublisherDatabase(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Publisher publisher) throws Exception {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values(?, ?, ?)",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() });
	}

	public void update(Publisher publisher) throws Exception {
		save("update tbl_publisher set publisherName = ?, publisherAddress=?, publisherPhone=? where publisherId = ?",
				new Object[] { publisher.getPublisherName(),  publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId() });

	}

	public void delete(Publisher publisher) throws Exception {
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] { publisher.getPublisherId() });
	}

	public List<Publisher> readAll(int pageNo,int pageSize) throws Exception{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Publisher>) read("select * from tbl_publisher", null);
		
	}

	public Publisher readOne(int publisherId) throws Exception {
		List<Publisher> publishers = (List<Publisher>) read("select * from tbl_publisher where publisherId = ?", new Object[] {publisherId});
		if(publishers!=null && publishers.size()>0){
			return publishers.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Publisher> publishers =  new ArrayList<Publisher>();
		BookDatabase bdao = new BookDatabase(getConnection());
		
		while(rs.next()){
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			
			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bdao.readFirstLevel("select * from tbl_book where bookId = ?"
					, new Object[] {rs.getInt("publisherId")});
			p.setBooks(books);
			publishers.add(p);
		}
		return publishers;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		List<Publisher> publishers = new ArrayList<Publisher>();
		
		while (rs.next()) {
			
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));	
		}
		return publishers;
	}
	
	

}

package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.database.BookCopiesDatabase;
import com.gcit.lms.database.BookDatabase;
import com.gcit.lms.database.LibBranchDatabase;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.LibBranch;

public class LibraryService {

	public List<LibBranch> listBranches(int pageNo,int pageSize) throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			LibBranchDatabase lib = new LibBranchDatabase(conn); 
			List<LibBranch> branches = lib.readAll(pageNo,pageSize); 
			conn.commit(); 
			return branches; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	} 
	
	public LibBranch ListOneBranch(int branchId) throws Exception {

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 

		try {

			LibBranchDatabase lib = new LibBranchDatabase(conn);
			LibBranch branch = lib.readOne(branchId);
			return branch;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally{
			conn.close();
		}
	}

	public void UpdateLibBranch(LibBranch branch) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if(branch == null){ 
				throw new Exception("The Libraray Branch cannot be null"); 
			}else{
				LibBranchDatabase lib = new LibBranchDatabase(conn);
				lib.update(branch); 
				conn.commit(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public List<Book> listBooks(int pageNo, int pageSize) throws Exception { 
		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		try { 
			BookDatabase bdao = new BookDatabase(conn); 
			List<Book> books = bdao.readAll(pageNo, pageSize);
			conn.commit(); 
			return books; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} finally { 
			conn.close(); 
		} 
	}
	
	public Book listOneBook(int bookId) throws Exception {

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 

		try {

			BookDatabase bdao = new BookDatabase(conn);
			Book book = bdao.readOne(bookId);
			return book;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally{
			conn.close();
		}

	}
	

	
	public void updateBookCopies(int branchId, int bookId, int noOfCopies ) throws Exception{

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 
		BookCopiesDatabase bcdao = new BookCopiesDatabase(conn); 
		BookDatabase bookdao = new BookDatabase(conn); 
		LibBranchDatabase branchdao = new LibBranchDatabase(conn); 

		try{

			if(bookdao.readOne(bookId) == null || branchdao.readOne(branchId) == null){ 
				throw new Exception("The book id or The Branch id does not match any book or branch"); 
			} else {
			
			BookCopies bookcop = new BookCopies(); 
			bookcop.setBookId(bookId); 
			bookcop.setBranchId(branchId); 
			bookcop.setNoOfCopies(noOfCopies); 
		


			LibBranch lb = branchdao.readOne(branchId); 


			}}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			conn.rollback();//not sure if needed 
		} finally { 
			conn.close(); 
		} 

	}
	
	public BookCopies ListOneBookCopie(int branchId, int bookId) throws Exception {

		ConnectionUtil c = new ConnectionUtil(); 
		Connection conn = c.createConnection(); 

		try {
			BookCopiesDatabase dao= new BookCopiesDatabase(conn);
			return dao.readOne(bookId, branchId); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally{
			conn.close();
		}
	}
}

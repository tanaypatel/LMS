package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.database.AuthorDatabase;
import com.gcit.lms.database.BookDatabase;
import com.gcit.lms.database.GenreDatabase;
import com.gcit.lms.database.LibBranchDatabase;
import com.gcit.lms.database.PublisherDatabase;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibBranch;
import com.gcit.lms.domain.Publisher;

public class AdministrativeService {
		
		
		/*----------------------------AUTHOR SECTION---------------------------------*/


		public void createAuthor(Author author) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (author == null || author.getAuthorName() == null || author.getAuthorName().length() == 0) {
					throw new Exception(
							"Author Name cannot be empty");
				} else {
					AuthorDatabase aDB = new AuthorDatabase(conn);
					aDB.create(author);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		/*-------------------------------END AUTHOR---------------------------------*/

		public void updateAuthor(Author author) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(author == null || author.getAuthorName() == null || author.getAuthorName().length() == 0
						){ 
					throw new Exception("The Author cannot be null"); 
				}else{
					AuthorDatabase aDB = new AuthorDatabase(conn);
					aDB.update(author); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}



		public void deleteAuthor(Author author) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(author == null){ 
					throw new Exception("The Author cannot be null to delete"); 
				}else{
					AuthorDatabase aDB = new AuthorDatabase(conn);
					aDB.delete(author); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		public Author listOneAuthor(int authorId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				AuthorDatabase aDB = new AuthorDatabase(conn);
				Author author = aDB.readOne(authorId);
				return author;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Author> listAuthors(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				AuthorDatabase aDB = new AuthorDatabase(conn); 
				List<Author> authors = aDB.readAll(pageNo,pageSize); 
				conn.commit();
				return authors; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 

		

		/*----------------------------END AUTHOR-----------------------------------*/
		
		/*---------------------------Genre------------------------------------------*/

		public void createGenre(Genre genre) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (genre == null || genre.getGenreName() == null
						|| genre.getGenreName().length() == 0) {
					throw new Exception(
							"Genre Name cannot be empty");
				} else {
					GenreDatabase gDB = new GenreDatabase(conn);
					gDB.create(genre);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		public void updateGenre(Genre genre) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(genre == null){ 
					throw new Exception("The Genre cannot be null"); 
				}else{
					GenreDatabase gDB = new GenreDatabase(conn);
					gDB.update(genre); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		public void deleteGenre(Genre genre) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(genre == null){ 
					throw new Exception("The Genre cannot be null"); 
				}else{
					GenreDatabase gDB = new GenreDatabase(conn);
					gDB.delete(genre); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		public Genre listOneGenre(int genre_id) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				GenreDatabase gDB = new GenreDatabase(conn);
				Genre genre = gDB.readOne(genre_id);
				return genre;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Genre> listGenres(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				GenreDatabase gDB = new GenreDatabase(conn); 
				List<Genre> genres = gDB.readAll(pageNo, pageSize); 
				conn.commit();
				return genres; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		


		/*-------------------------END GENRE-----------------------------------*/
		
		/*-------------------------PUBLISHER-----------------------------------*/
		public void createPublisher(Publisher publisher) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (publisher == null 
						|| publisher.getPublisherName() == null	|| publisher.getPublisherName().length() == 0 
						|| publisher.getPublisherAddress() == null
						|| publisher.getPublisherAddress().length() == 0
						|| publisher.getPublisherPhone() == null
						|| publisher.getPublisherPhone().length() == 0) {
					throw new Exception(
							"Publisher Name, Address or Phone cannot be empty");
				} else {
					PublisherDatabase pDB = new PublisherDatabase(conn);
					pDB.create(publisher);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		public void updatePublisher(Publisher publisher) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(publisher == null){ 
					throw new Exception("The Publisher cannot be updated"); 
				}else{
					PublisherDatabase pDB = new PublisherDatabase(conn);
					pDB.update(publisher); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		public void deletePublisher(Publisher publisher) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(publisher == null){ 
					throw new Exception("The Publisher cannot be deleted"); 
				}else{
					PublisherDatabase pDB = new PublisherDatabase(conn);
					pDB.delete(publisher); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		public Publisher listOnePublisher(int PublisherId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				PublisherDatabase pDB = new PublisherDatabase(conn);
				Publisher publisher = pDB.readOne(PublisherId);
				return publisher;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Publisher> listPublishers(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				PublisherDatabase pDB = new PublisherDatabase(conn); 
				List<Publisher> publishers = pDB.readAll(pageNo,pageSize);
				conn.commit();
				return publishers; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		


		/*---------------------------END PUBLISHER----------------------------------*/

		/*--------------------------------BOOK--------------------------------------*/
		public void createBook(Book book) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (book == null 
						|| book.getTitle() == null
						|| book.getTitle().length() == 0) {
					throw new Exception(
							"Book title cannot be empty");
				} else {
					BookDatabase bDB = new BookDatabase(conn);
					bDB.create(book);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		public void updateBook(Book book) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(book == null){ 
					throw new Exception("The Book cannot be null"); 
				}else{
					BookDatabase bDB = new BookDatabase(conn);
					bDB.update(book); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}

		public void deleteBook(Book book) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(book == null ){ 
					throw new Exception("The Book cannot be null"); 
				}else{
					BookDatabase bDB = new BookDatabase(conn);
					bDB.delete(book); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		public Book listOneBook(int bookId) throws Exception {

			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 

			try {

				BookDatabase bDB = new BookDatabase(conn);
				Book book = bDB.readOne(bookId);
				return book;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			} finally{
				conn.close();
			}

		}

		public List<Book> listBooks(int pageNo, int pageSize) throws Exception { 
			ConnectionUtil c = new ConnectionUtil(); 
			Connection conn = c.createConnection(); 
			try { 
				BookDatabase bDB = new BookDatabase(conn); 
				List<Book> books = bDB.readAll(pageNo, pageSize);
				conn.commit(); 
				return books; 
			} catch (Exception e) { 
				e.printStackTrace(); 
				return null; 
			} finally { 
				conn.close(); 
			} 
		} 
		
		


		/*-----------------------------END BOOK-----------------------------------------*/
		
		/*-----------------------------LIBRARY BRANCH-----------------------------------*/

		public void createLibraryBranch(LibBranch branch) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if (branch == null 
						|| branch.getLibraryBranchName() == null
						|| branch.getLibraryBranchName().length() == 0 
						|| branch.getLibraryBranchAddress() == null
						|| branch.getLibraryBranchAddress().length() == 0) {
					throw new Exception(
							"Library Name or Address cannot be empty");
				} else {
					LibBranchDatabase lib = new LibBranchDatabase(conn);
					lib.create(branch);
					conn.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		}


		public void UpdateLibBranch(LibBranch branch) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(branch == null){ 
					throw new Exception("The Library Branch cannot be null"); 
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


		public void deleteLibBranch(LibBranch branch) throws Exception {
			ConnectionUtil c = new ConnectionUtil();
			Connection conn = c.createConnection();
			try {
				if(branch == null){ 
					throw new Exception("The Book cannot be null"); 
				}else{
					LibBranchDatabase lib = new LibBranchDatabase(conn);
					lib.delete(branch); 
					conn.commit(); 
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
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
		
		


		/*---------------------------END LIBRARY BRANCH----------------------------------*/
		
}

	
	



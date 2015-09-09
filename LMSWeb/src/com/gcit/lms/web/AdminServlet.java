package com.gcit.lms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibBranch;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addBook","/viewBooks","/editBook", "/deleteBook", "/searchBooks","/pageBooks",
	"/addAuthor", "/viewAuthors", "/deleteAuthor",
		"/editAuthor",  "/searchAuthors", "/pageAuthors",
		"/addPublisher","/viewPublisher", "/editPublisher",
		"/deletePublisher", "/pagePublishers", "/searchPublishers",
		"/addGenre","/viewGenre", "/editGenre",
		"/deleteGenre", "/pageGenre", "/searchGenres",
		"/addBranch","/viewBranches","/editBranch2","/deleteLibBranch","/searchLibBranches","/pageLibBranches"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
	}

	//DO Get
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/deleteBook":
			deleteBook(request, response);
			break;
		case "/deleteAuthor":
			deleteAuthor(request, response);
			break;
		case "/deletePublisher":
			deletePublisher(request, response);
			break;
		case "/deleteGenre":
			deleteGenre(request, response);
			break;
		case "/deleteLibBranch":
			deleteLibBranch(request, response);
			break;
		default:
			break;
		}
		
	}

	//DO Post
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		
		case "/viewAuthors":
			viewAuthors(request, response);
			break;
		
		case "/addAuthor":
			createAuthor(request, response);
			break;
			
		case "/editAuthor": {
			editAuthor(request, response);
			break;
		}
		case "/addPublisher":
			createPublisher(request, response);
			break;
			
		case "/viewPublishers":
			viewPublishers(request, response);
			break;
		case "/editPublisher": {
			editPublisher(request, response);
			break;
		}
		case "/addGenre":
			createGenre(request, response);
			break;
			
		case "/viewGenres":
			viewGenres(request, response);
			break;
		case "/editGenre": {
			editGenre(request, response);
			break;
		}
		case "/addBook":{
			createBook(request, response);
			break;}
		
		case "/viewBooks":
			viewBooks(request, response);
			break;
		case "/editBook": {
			editBook(request, response);
			break;}
		case "/viewBranches":
			viewLibBranches(request, response);
			break;
		
		case "/addBranch":
			createLibranch(request, response);
			break;
			
		case "/editBranch2": {
			editBranch(request, response);
			break;
		}
		default:
			break;
		}
	}
	
	/***********************************BOOK ***************************************/

	private void createBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		AdministrativeService adminService = new AdministrativeService();
		
		String bookTitle = request.getParameter("bookTitle");
		Book b = new Book();
		b.setTitle(bookTitle);
		

		List <Author> authors= new ArrayList<Author>();
		String [] listOfAuthors = request.getParameterValues("authorId");
	


		 		for(int id = 0; id < listOfAuthors.length; id++){ 
		 			try { 
		 				authors.add(adminService.listOneAuthor(Integer.parseInt(listOfAuthors[id]))); 
		 			} catch (Exception e) { 
		 				//report unsuccessful 
		 				e.printStackTrace(); 
					} 
		 		} 
		 		b.setAuthors(authors); 

		

		 		
		 	List<Genre> genres = new ArrayList<Genre>();
		 	String [] genreList = request.getParameterValues("genreId");
		 	
		 	for(int id=0; id < genreList.length; id++ ){
		 		try {
		 			
		 			genres.add(adminService.listOneGenre(Integer.parseInt(genreList[id])));
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		 		}
		b.setGenres(genres);
		
		
		String[] publishers = request.getParameterValues("publisherId");

		try {
			
			Publisher p = new Publisher();
			p = adminService.listOnePublisher(Integer.parseInt(publishers[0]));
			b.setPublisher(adminService.listOnePublisher(Integer.parseInt(publishers[0])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		try {
			adminService.createBook(b);
			request.setAttribute("result", "Book Added Successfully");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Book add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);
	}
	
	private void editBook(HttpServletRequest request,
			HttpServletResponse response) {
		String bookTitle = request.getParameter("title");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book b = new Book();
		b.setTitle(bookTitle);
		b.setBookId(bookId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.updateBook(b);
			request.setAttribute("result", "Book updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Book update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBooks.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private List<Book> viewBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			return new AdministrativeService().listBooks(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBooks.jsp");
		try {
			new AdministrativeService().deleteBook(book);

			request.setAttribute("result", "Book Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Book Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	
	/***********************************END BOOK****************************************/
	
	/***********************************AUTHOR******************************************/

	private void createAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorName = request.getParameter("authorName");
		Author a = new Author();
		a.setAuthorName(authorName);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createAuthor(a);
			request.setAttribute("result", "Author Added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Author add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewAuthors.jsp");
		rd.forward(request, response);
	}
	
	private void editAuthor(HttpServletRequest request,
			HttpServletResponse response) {
		String authorName = request.getParameter("authorName");
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		Author a = new Author();
		a.setAuthorName(authorName);
		a.setAuthorId(authorId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.updateAuthor(a);
			request.setAttribute("result", "Author updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Author update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewAuthors.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private List<Author> viewAuthors(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().listAuthors(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		author.setAuthorId(Integer.parseInt(authorId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewAuthors.jsp");
		try {
			new AdministrativeService().deleteAuthor(author);

			request.setAttribute("result", "Author Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	/***********************************END AUTHOR************************************/
	
	/***********************************PUBLISHER*************************************/


	private void createPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		Publisher p = new Publisher();
		p.setPublisherName(publisherName);
		p.setPublisherAddress(publisherAddress);
		p.setPublisherPhone(publisherPhone);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createPublisher(p);
			request.setAttribute("result", "Publisher added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		rd.forward(request, response);
	}
	
	
	private void editPublisher(HttpServletRequest request,
			HttpServletResponse response) {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher p = new Publisher();
		p.setPublisherName(publisherName);
		p.setPublisherAddress(publisherAddress);
		p.setPublisherPhone(publisherPhone);
		p.setPublisherId(publisherId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.updatePublisher(p);
			request.setAttribute("result", "Publisher updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private List<Publisher> viewPublishers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().listPublishers(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void pagePublishers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Publisher> publishers = new AdministrativeService().listPublishers(pageNo, 10);
			request.setAttribute("publishers", publishers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		rd.forward(request, response);
	}
	
	

	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		publisher.setPublisherId(Integer.parseInt(publisherId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		try {
			new AdministrativeService().deletePublisher(publisher);

			request.setAttribute("result", "Publisher Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	/***********************************END PUBLISHER**********************************/
	
	
	/***********************************GENRE*****************************************/

	private void createGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genreName = request.getParameter("genreName");
		Genre g = new Genre();
		g.setGenreName(genreName);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createGenre(g);
			request.setAttribute("result", "Genre Added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Genre add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewGenres.jsp");
		rd.forward(request, response);
	}
	
	private void editGenre(HttpServletRequest request,
			HttpServletResponse response) {
		String genreName = request.getParameter("genreName");
		int genreId = Integer.parseInt(request.getParameter("genreId"));
		Genre g = new Genre();
		g.setGenreName(genreName);
		g.setGenreId(genreId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.updateGenre(g);
			request.setAttribute("result", "Genre updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Genre update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewGenres.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private List<Genre> viewGenres(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().listGenres(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	private void deleteGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genreId = request.getParameter("genreId");
		Genre genre = new Genre();
		genre.setGenreId(Integer.parseInt(genreId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewGenres.jsp");
		try {
			new AdministrativeService().deleteGenre(genre);

			request.setAttribute("result", "Genre Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	/***********************************END GENRE*******************************************************/
	
	
	/*********************************** LIBRARY BRANCH*******************************************************/


	private void createLibranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String branchName = request.getParameter("libraryBranchName");
		String branchAddress = request.getParameter("libraryBranchAddress");
		LibBranch lib = new LibBranch();
		lib.setLibraryBranchName(branchName);
		lib.setLibraryBranchAddress(branchAddress);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createLibraryBranch(lib);
			request.setAttribute("result", "Libranch added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Libranch add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBranches.jsp");
		rd.forward(request, response);
	}
	
	
	private void editBranch(HttpServletRequest request,
			HttpServletResponse response) {
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		LibBranch lib = new LibBranch();
		lib.setLibraryBranchName(branchName);
		lib.setLibraryBranchAddress(branchAddress);
		lib.setLibraryBranchId(branchId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.UpdateLibBranch(lib);
			request.setAttribute("result", "LibBranch updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"LibBranch update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBranches.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private List<LibBranch> viewLibBranches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().listBranches(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	private void deleteLibBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String branchId = request.getParameter("branchId");
		LibBranch libbranch = new LibBranch();
		libbranch.setLibraryBranchId(Integer.parseInt(branchId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBranches.jsp");
		try {
			new AdministrativeService().deleteLibBranch(libbranch);

			request.setAttribute("result", "Branch Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	/***********************************END LIBRARY RANCH*******************************************************/
	


}

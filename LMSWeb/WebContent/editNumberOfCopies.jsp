<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.BookCopies"%>



<% 
LibraryService libraryService = new LibraryService();
String branchId = (String) request.getParameter("branchId");
String bookId = (String) request.getParameter("bookId");
 Book book = libraryService.listOneBook(Integer.parseInt(bookId));
%>

<div>
	<form action="addCopies" method="post">
		Enter new number of copies: <input type="text" name="noOfCopies">
		
		 <input type="hidden"
			name="bookId" value=<%=bookId %>> <input type="hidden"
			name="branchId" value=<%=branchId%>> <input type="submit" />
	</form>


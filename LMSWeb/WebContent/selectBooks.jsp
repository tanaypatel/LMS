<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.BookCopies"%>
<%
LibraryService ls = new LibraryService();  
String branchId = (String) request.getAttribute("branchId");
String noOfCopies = (String) request.getAttribute("noOfCopies");

	List<Book> books = null;
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = ls.listBooks(0, 50);

	}
%>

${result }
<br><br><br>
 <table class="table"> 
 	<tr> 
 		<th>Book Title</th> 
 		<th>Select Book To Add Copies</th> 
 	</tr> 
 	<%for(Book b: books){ %> 
 	<tr> 
 		<td><%out.println(b.getTitle()); %></td> 
 		<td><a  href="editNumberOfCopies.jsp?bookId=<%=b.getBookId()%>&branchId=<%=branchId%>&noOfCopies=<%=noOfCopies%>"><button type="button">Select</button></a></td> 
 	</tr> 
 	<%} %> 
 </table> 




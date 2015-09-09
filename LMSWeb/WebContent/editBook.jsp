<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%AdministrativeService adminService = new AdministrativeService();
 String bookId = request.getParameter("bookId");
 Book book = adminService.listOneBook(Integer.parseInt(bookId));
%>
<a href="viewBooks.jsp">Back to Books</a>
<div>
<form action="editBook" method="post">
			Enter Book Name: <input type="text" name="title" value=<%=book.getTitle()%>>
			<input type="hidden" name="bookId" value=<%=book.getBookId() %>>
		<input type="submit"/>
</form>
</div>
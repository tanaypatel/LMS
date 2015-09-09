<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Book> books = null;
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = adminService.listBooks(0, 50);

	}
%>

${result }

<a href="admin.jsp">Back to Admin Home Page</a>

<table class="table" id="booksTable">
	<tr>
		<th>Book ID</th>
		<th>Title Name</th>
		<th>Author of The Book</th>
		<th>Publisher of the Book</th>
		<th>Genre of the Book</th>
		<th>Edit Book</th>
		<th>Delete Book</th>
	</tr>
	<%
		for (Book b: books) {
	%>
	<tr>
		<td>
			<%
				out.println(b.getBookId());
			%>
		</td>
		<td>
			<%
				out.println(b.getTitle());
			%>
		</td>
		<td>
			<%
				out.println(b.getPublisher().getPublisherName());
			%>
		</td>
		<td>
			<%
			for (Author a : b.getAuthors())
			{
			out.println(a.getAuthorName());
			}
			%>
		</td>
		<td>
			<%
			for (Genre g : b.getGenres())
			{
			out.println(g.getGenreName());
			}
			%>
		</td>

		<td><button onClick="javascript:location.href='editBook.jsp?bookId=<%=b.getBookId()%>';">Edit</button></td>
		<td><button type="button"
				onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>


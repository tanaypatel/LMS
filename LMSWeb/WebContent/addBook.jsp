<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%AdministrativeService adminService = new AdministrativeService();
 List<Author> authors = adminService.listAuthors(0, 50);
 List<Genre> genres = adminService.listGenres(0,50);
 List<Publisher> pubs = adminService.listPublishers(0,50);
%>

<br><br><br>
<a href="admin.jsp">Back to Admin Home Page</a>

<form action="addBook" method="post">
	<body>
		<h2>Enter Book Info</h2>
		<table class="table">
			<tr>
				<td>Enter Book Title:</td>
				<td><input type="text" name="bookTitle" /></td>
			</tr>
			<tr>
				<td>Select Author</td>
				<td>
				<select multiple name="authorId">
					<%for(Author a: authors){ %>
						<option value=<%=a.getAuthorId() %>><%=a.getAuthorName() %></option>
					<%} %>
				</select>
				</td>
			</tr>
			<tr>
				<td>Select Genre</td>
				<td>
				<select name="genreId">
					<%for(Genre g: genres){ %>
						<option value=<%=g.getGenreId() %>><%=g.getGenreName() %></option>
					<%} %>
				</select>
				</td>
			</tr>
			<tr>
				<td>Select Publisher</td>
				<td>
				<select name="publisherId">
					<%for(Publisher p: pubs){ %>
						<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
					<%} %>
				</select>
				</td>
			</tr>
		</table>
		<input type="submit">
	</body>
</form>

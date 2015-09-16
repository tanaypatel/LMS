<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Author> authors = null;
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = adminService.listAuthors(0, 50);

	}
%>

<br><br><br>
${result }
 
<a href="admin.jsp">Back to Admin Home Page</a>

<table class="table" id="authorsTable">
	<tr>
		<th>Author ID</th>
		<th>Author Name</th>
		<th>Edit Author</th>
		<th>Delete Author</th>
	</tr>
	<%
		for (Author a : authors) {
	%>
	<tr>
		<td>
			<%
				out.println(a.getAuthorId());
			%>
		</td>
		<td>
			<%
				out.println(a.getAuthorName());
			%>
		</td>
		<td><button type="button" onClick="javascript:location.href='editAuthor.jsp?authorId=<%=a.getAuthorId()%>';">Edit</button></td>
		<td><button type="button"
				onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>


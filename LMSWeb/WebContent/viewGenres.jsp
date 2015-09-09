<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Genre> genres = null;
	if (request.getAttribute("genres") != null) {
		genres = (List<Genre>) request.getAttribute("genres");
	} else {
		genres = adminService.listGenres(0, 50);

	}
%>

${result }
 
 <a href="admin.jsp">Back to Admin Home Page</a>

<table class="table" id="genresTable">
	<tr>
		<th>Genre ID</th>
		<th>Genre Name</th>
		<th>Edit Genre</th>
		<th>Delete Genre</th>
	</tr>
	<%
		for (Genre g : genres) {
	%>
	<tr>
		<td>
			<%
				out.println(g.getGenreId());
			%>
		</td>
		<td>
			<%
				out.println(g.getGenreName());
			%>
		</td>
		<td><button type="button" 
		onclick="javascript:location.href='editGenre.jsp?genreId=<%=g.getGenreId()%>';">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteGenre?genreId=<%=g.getGenreId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>


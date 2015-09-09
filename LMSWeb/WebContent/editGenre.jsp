<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%AdministrativeService adminService = new AdministrativeService();
 String genreId = request.getParameter("genreId");
 Genre genre = adminService.listOneGenre(Integer.parseInt(genreId));
%>
<a href="viewGenres.jsp">Back to Genre</a>
<div>
<form action="editGenre" method="post">
			Enter Genre Name: <input type="text" name="genreName" value=<%=genre.getGenreName()%>>
			<input type="hidden" name="genreId" value=<%=genre.getGenreId() %>>
		<input type="submit"/>
</form>
</div>
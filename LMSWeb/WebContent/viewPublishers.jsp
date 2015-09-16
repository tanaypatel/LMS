<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Publisher> publishers = null;
	if (request.getAttribute("publishers") != null) {
		publishers = (List<Publisher>) request.getAttribute("publishers");
	} else {
		publishers = adminService.listPublishers(0, 50);

	}
%>

<br><br><br>
${result }

<a href="admin.jsp">Back to Admin Home Page</a>

<table class="table" id="publishersTable">
	<tr>
		<th>Publisher ID</th>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>
		<th>Edit Publisher</th>
		<th>Delete Publisher</th>
	</tr>
	<%
		for (Publisher p : publishers) {
	%>
	<tr>
		<td>
			<%
				out.println(p.getPublisherId());
			%>
		</td>
		<td>
			<%
				out.println(p.getPublisherName());
			%>
		</td>
		<td>
			<%
				out.println(p.getPublisherAddress());
			%>
		</td>
		<td>
			<%
				out.println(p.getPublisherPhone());
			%>
		</td>

		<td><button type="button" 
				onclick="javascript:location.href='editPublisher.jsp?publisherId=<%=p.getPublisherId()%>';">Edit</button></td>
		<td><button type="button"
				onclick="javascript:location.href='deletePublisher?publisherId=<%=p.getPublisherId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>


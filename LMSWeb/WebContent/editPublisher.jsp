<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%AdministrativeService adminService = new AdministrativeService();
 String publisherId = request.getParameter("publisherId");
 Publisher publisher = adminService.listOnePublisher(Integer.parseInt(publisherId));
%>
<a href="viewPublishers.jsp">Back to Publishers</a>
<div>
<form action="editPublisher" method="post">


			<br>Enter Publisher Name: <input type="text" name="publisherName" value=<%=publisher.getPublisherName()%>>

			<br>Enter Publisher Address: <input type="text" name="publisherAddress" value=<%=publisher.getPublisherAddress()%>>

			<br>Enter Publisher Phone: <input type="text" name="publisherPhone" value=<%=publisher.getPublisherPhone()%>>

			<input type="hidden" name="publisherId" value=<%=publisher.getPublisherId() %>>

		<input type="submit"/>
</form>
</div>
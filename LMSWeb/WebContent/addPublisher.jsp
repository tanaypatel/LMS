<%@include file="include.html"%>
<br><br><br>

<a href="admin.jsp">Back to Admin Home Page</a>

<form action="addPublisher" method="post">
	<body>
		<h2>Enter Publisher Info</h2>
		<table class="table">
			<tr>
				<td>Enter Publisher Name:</td>
				<td><input type="text" name="publisherName" /></td>
			</tr>
			<tr>
				<td>Enter Publisher Address:</td>
				<td><input type="text" name="publisherAddress" /></td>
			</tr>
			<tr>
				<td>Enter Publisher Phone:</td>
				<td><input type="text" name="publisherPhone" /></td>
				
			</tr>
		</table>
		<input type="submit">
	</body>
</form>

<%@include file="include.html"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<LibBranch> branches = null;
	if (request.getAttribute("branches") != null) {
		branches = (List<LibBranch>) request.getAttribute("branches");
	} else {
		branches = adminService.listBranches(0, 50);

	}
%>
<br><br><br>
${result }

<a href="admin.jsp">Back to Admin Home Page</a>

<table class="table" id="libBranchesTable">
	<tr>
		<th>Branch ID</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Edit Branch</th>
		<th>Delete Branch</th>
	</tr>
	<%
		for (LibBranch lib : branches) {
	%>
	<tr>
		<td>
			<%
				out.println(lib.getLibraryBranchId());
			%>
		</td>
		<td>
			<%
				out.println(lib.getLibraryBranchName());
			%>
		</td>
		<td>
			<%
				out.println(lib.getLibraryBranchAddress());
			%>
		</td>

		<td><button type="button" 
		onclick="javascript:location.href='editBranch.jsp?branchId=<%=lib.getLibraryBranchId()%>';">Edit</button></td>
		<td><button type="button"
				onclick="javascript:location.href='deleteLibBranch?branchId=<%=lib.getLibraryBranchId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>


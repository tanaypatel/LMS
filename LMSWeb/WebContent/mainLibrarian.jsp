<%@include file="include.html"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%
LibraryService libraryService = new LibraryService();
 String branchId = (String) request.getAttribute("branchId");
 LibBranch lib = libraryService.ListOneBranch(Integer.parseInt(branchId)); 
 
%>
<br><br><br>

<table class="table" id="libBranchesTable">
	<tr>
		<th>Branch ID</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
	</tr>
	<%
	{
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
				onclick="javascript:location.href='editBranch.jsp?branchId=<%=lib.getLibraryBranchId()%>';">Update the Details</button></td>
	</tr>
	
	<td><a onclick="javascript:location.href='selectBooks?branchId=<%=branchId%>';">Add copies of Book to the Branch</a></td></td>
	</tr>
	<%
		}
	%>
</table>


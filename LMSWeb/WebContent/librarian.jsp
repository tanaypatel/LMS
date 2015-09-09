<%@page import="com.gcit.lms.domain.LibBranch"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.LibBranch"%>
<%
	LibraryService libraryService = new LibraryService();
 String branchId = (String) request.getAttribute("branchId");
	List<LibBranch> branches = null;
	if (request.getAttribute("branches") != null) {
		branches = (List<LibBranch>) request.getAttribute("branches");
	} else {
		branches = libraryService.listBranches(0, 50);
	}
%>

<h2>Librarian</h2>
${result}
<h2>Select a branch:</h2>

<table class="table" id="libBranchesTable">
	<tr>
		<th>Branch ID</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Select a Branch</th>
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
				onclick="javascript:location.href='selectLibraryBranch?branchId=<%=lib.getLibraryBranchId()%>';">Select</button></td> 
	</tr>
	<%
		}
	%>
</table>


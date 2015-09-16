<%@include file="include.html"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

<br><br><br>
	<a href="index.jsp">Home</a>
	<h2>Administrator</h2>
	${result}
	<ul>

		<div>
			<h2>
				<strong>Manage Books</strong>
			</h2>

			<a href="addBook.jsp">Add a book to the library</a><br /> <a
				href="viewBooks.jsp"> List all books</a><br /> <br />

			<div>


				<h2>
					<strong>Manage Authors</strong>
				</h2>

				<a href="addAuthor.jsp"> ADD Author</a><br /> <a
					href="viewAuthors.jsp"> List all Authors</a><br />

				<div>
					<h2>
						<strong>Manage Publisher</strong>
					</h2>

					<a href="addPublisher.jsp">Add a Publisher</a><br /> <a
						href="viewPublishers.jsp"> List all the publishers</a><br />
				</div>
			</div>

			<div>
				<h2>
					<strong>Manage Genre</strong>
				</h2>

				<a href="addGenre.jsp">Add Genre</a><br /> <a href="viewGenres.jsp">List
					all the Genres</a><br />

			</div>

			<div>
				<h2>
					<strong>Manage Library Branch</strong>
				</h2>

				<a href="addBranch.jsp">Click here to Add a Branch</a><br /> <a
					href="viewBranches.jsp">Click here to check all the Branches</a><br />
			</div>
		</div>
	</ul>
</body>
</html>




package com.gcit.lms.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

public class GenreDatabase extends BaseDatabase {
	
	public GenreDatabase(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Genre genre) throws Exception {
		save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { genre.getGenreName() });
	}

	public void update(Genre genre) throws Exception {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId()});

	}

	public void delete(Genre genre) throws Exception {
		save("delete from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreId()});
	}

	public List<Genre> readAll(int pageNo,int pageSize) throws Exception{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Genre>) read("select * from tbl_genre", null);
		
	}

	public Genre readOne(int genre_id) throws Exception {
		List<Genre> genres = (List<Genre>) read("select * from tbl_genre where genre_id = ?", new Object[] {genre_id});
		if(genres!=null && genres.size()>0){
			return genres.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Genre> genres =  new ArrayList<Genre>();
		BookDatabase bdao = new BookDatabase(getConnection());
		
		while(rs.next()){
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bdao.readFirstLevel("select * from tbl_book where bookId In"
					+ "(select bookId from tbl_book_genres where genre_id=?)", new Object[] {rs.getInt("genre_id")});
			g.setBooks(books);
			genres.add(g);
		}
		return genres;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws Exception {
		
		List<Genre> genres =  new ArrayList<Genre>();
		while(rs.next()){
		
		Genre g = new Genre();
		g.setGenreId(rs.getInt("genre_id"));
		g.setGenreName(rs.getString("genre_name"));
		
		genres.add(g);
		
		}
		// TODO Auto-generated method stub
		return genres;
	}
	
	


}

package hh.sof3as3.Concerts.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends CrudRepository<Genre, Long> {

	@Query("SELECT g FROM Genre g WHERE LOWER(g.genre_name) LIKE LOWER(concat('%', :query, '%'))")
	List<Genre> search(@Param("query") String query);
	
	@Query("SELECT g FROM Genre g WHERE g.genre_name = :genre_name")
	List<Genre> findByGenreName(@Param("genre_name") String genre_name);
}


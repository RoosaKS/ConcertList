package hh.sof3as3.Concerts.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends CrudRepository<Artist,Long>{

	@Query("SELECT a FROM Artist a JOIN a.genre g WHERE LOWER(a.artist_name) LIKE LOWER(concat('%', :query, '%')) OR LOWER(g.genre_name) LIKE LOWER(concat('%', :query, '%'))")
	List<Artist> search(@Param("query") String query);
	
	@Query("SELECT a FROM Artist a JOIN a.genre g WHERE a.artist_name = :artist_name")
	List<Artist> findByArtistName(@Param("artist_name") String artist_name);
	
	@Query("SELECT a FROM Artist a JOIN a.genre g WHERE g.genre_name = :genre_name")
	List<Artist> findByGenreName(@Param("genre_name") String genre_name);

	@Query("SELECT a FROM Artist a JOIN a.genre g WHERE g.genre_id = :genre_id")
	List<Artist> findByQuery(@Param("genre_id") Long genre_id);


}

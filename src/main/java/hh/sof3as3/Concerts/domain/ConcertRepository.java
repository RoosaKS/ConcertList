package hh.sof3as3.Concerts.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
	
	List<Concert> findByVenue(String venue);
	//List<Concert> findByName(String concert_name);
	
	@Query("SELECT c FROM Concert c JOIN c.artist a WHERE a.artist_name = :artist_name")
	List<Concert> findByArtistName(@Param("artist_name") String artist_name);
	
	@Query("SELECT c FROM Concert c JOIN c.artist a JOIN c.concertType ct WHERE LOWER(c.concert_name) LIKE LOWER(concat('%', :query, '%')) OR LOWER(c.venue) LIKE LOWER(concat('%', :query, '%')) OR c.date LIKE concat('%', :query, '%') OR LOWER(c.city) LIKE LOWER(concat('%', :query, '%')) OR LOWER(a.artist_name) LIKE LOWER(concat('%', :query, '%')) OR LOWER(ct.concertType_name) LIKE LOWER(concat('%', :query, '%'))")
	List<Concert> search(@Param("query") String query);

	@Query("SELECT c FROM Concert c JOIN c.artist a JOIN c.concertType ct WHERE a.artist_id = :artist_id AND ct.concertType_id = :concertType_id")
	List<Concert> findByQuery(@Param("artist_id") Long artist_id, @Param("concertType_id") Long concertType_id);



	
}

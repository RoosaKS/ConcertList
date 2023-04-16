package hh.sof3as3.Concerts.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConcertTypeRepository extends CrudRepository<ConcertType, Long> {
	
	@Query("SELECT ct FROM ConcertType ct WHERE LOWER(ct.concertType_name) LIKE LOWER(concat('%', :query, '%'))")
	List<ConcertType> search(@Param("query") String query);
	
	@Query("SELECT ct FROM ConcertType ct WHERE ct.concertType_name = :concertType_name")
	List<ConcertType> findByConcertTypeName(@Param("concertType_name") String concertType_name);
	
	@Query("SELECT ct FROM ConcertType ct")
	List<ConcertType> findByQuery();

}

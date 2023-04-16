package hh.sof3as3.Concerts;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.Concerts.domain.ArtistRepository;
import hh.sof3as3.Concerts.domain.Concert;
import hh.sof3as3.Concerts.domain.ConcertRepository;
import hh.sof3as3.Concerts.domain.ConcertTypeRepository;

@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class ConcertRepositoryTest {
	
	@Autowired
	private ConcertRepository concertRepository;
	
	@Autowired
	private ConcertTypeRepository concertTypeRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	@Test  // testataan ConcertRepositoryn findByArtist()-metodin toimivuutta
    public void findByNameShouldReturnConcert() {
        List<Concert> concerts = concertRepository.findByArtistName("Blind Channel");
        
        assertThat(concerts).hasSize(1);
        assertThat(concerts.get(0).getVenue()).isEqualTo("Pakkahuone");    }
	
	 @Test // testataan ConcertRepositoryn save()-metodin toimivuutta
	    public void createNewConcert() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	    	Concert concert = new Concert("Fever Dream -tour", LocalDate.parse("04.03.2023", formatter), "Tavastia", "Helsinki", concertTypeRepository.findByConcertTypeName("Concert").get(0) , artistRepository.findByArtistName("Palaye Royale").get(0));
	    	concertRepository.save(concert); // SQL Insert
	    	assertThat(concert.getConcert_id()).isNotNull();
	    } 
	 
	 @Test // testataan ConcertRepositoryn deleteById()-metodin toimivuutta
	    public void deleteConcert() {
	    	concertRepository.deleteById((long)1); 
	    	
	  
	    }  
	
}
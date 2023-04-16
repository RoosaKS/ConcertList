package hh.sof3as3.Concerts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.Concerts.domain.Artist;
import hh.sof3as3.Concerts.domain.ArtistRepository;
import hh.sof3as3.Concerts.domain.GenreRepository;

@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class ArtistRepositoryTest {
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	
	@Test  // testataan ArtistRepositoryn findById()-metodin toimivuutta
    public void findByNameShouldReturnArtist() {
        List<Artist> artists = artistRepository.findByArtistName("Blind Channel");
        
        assertThat(artists).hasSize(1);
        assertThat(artists.get(0).getGenre().getGenre_name()).isEqualTo("Rock");    }
	
	 @Test // testataan ArtistRepositoryn save()-metodin toimivuutta
	    public void createNewArtist() {
	    	Artist artist = new Artist("5 Seconds Of Summer", genreRepository.findByGenreName("Pop").get(0));
	    	artistRepository.save(artist); // SQL Insert
	    	assertThat(artist.getArtist_id()).isNotNull();
	    } 
	 
	 @Test // testataan ArtistRepositoryn deleteById()-metodin toimivuutta
	    public void deleteArtist() {
	    	artistRepository.deleteById((long)1); 
	    	
	  
	    }  
	
}

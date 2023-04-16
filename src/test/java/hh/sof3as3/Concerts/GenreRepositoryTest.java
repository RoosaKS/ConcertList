package hh.sof3as3.Concerts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.Concerts.domain.Genre;
import hh.sof3as3.Concerts.domain.GenreRepository;

@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Test  // testataan GenreRepositoryn findById()-metodin toimivuutta
    public void findByNameShouldReturnGenreId() {
        List<Genre> genres = genreRepository.findByGenreName("Pop");
        
        assertThat(genres).hasSize(1);
        assertThat(genres.get(0).getGenre_id()).isEqualTo(1);    }
	
	 @Test // testataan GenreRepositoryn save()-metodin toimivuutta
	    public void createNewGenre() {

	    	Genre genre = new Genre("K-Pop");
	    	genreRepository.save(genre); // SQL Insert
	    	assertThat(genre.getGenre_id()).isNotNull();
	    } 
	 
	 @Test // testataan GenreRepositoryn deleteById()-metodin toimivuutta
	    public void deleteGenre() {
	    	genreRepository.deleteById((long)1); 
	    	
	  
	    }  

}

package hh.sof3as3.Concerts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.Concerts.domain.ArtistRepository;
import hh.sof3as3.Concerts.domain.Concert;
import hh.sof3as3.Concerts.domain.ConcertRepository;
import hh.sof3as3.Concerts.domain.ConcertTypeRepository;
import hh.sof3as3.Concerts.domain.GenreRepository;
import hh.sof3as3.Concerts.web.ArtistController;
import hh.sof3as3.Concerts.web.ConcertController;
import hh.sof3as3.Concerts.web.ConcertTypeController;
import hh.sof3as3.Concerts.web.GenreController;

@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@SpringBootTest
class ConcertsApplicationTests {
	
	@Autowired
	private ConcertController concertController;
	
	@Autowired
	private ConcertTypeController concertTypeController;
	
	@Autowired
	private GenreController genreController;
	
	@Autowired
	private ArtistController artistController;
		

	@Test
	public void contextLoads() {
		
		assertThat(concertController).isNotNull();
		assertThat(concertTypeController).isNotNull();
		assertThat(genreController).isNotNull();
		assertThat(artistController).isNotNull();


	}
	
	
	

}

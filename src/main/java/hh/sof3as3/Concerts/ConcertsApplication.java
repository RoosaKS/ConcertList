package hh.sof3as3.Concerts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3as3.Concerts.domain.Artist;
import hh.sof3as3.Concerts.domain.ArtistRepository;
import hh.sof3as3.Concerts.domain.Concert;
import hh.sof3as3.Concerts.domain.ConcertRepository;
import hh.sof3as3.Concerts.domain.ConcertType;
import hh.sof3as3.Concerts.domain.ConcertTypeRepository;
import hh.sof3as3.Concerts.domain.Concert_artist;
import hh.sof3as3.Concerts.domain.Concert_artistRepository;
import hh.sof3as3.Concerts.domain.Genre;
import hh.sof3as3.Concerts.domain.GenreRepository;
import hh.sof3as3.Concerts.domain.User;
import hh.sof3as3.Concerts.domain.UserRepository;

@SpringBootApplication
public class ConcertsApplication {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private static final Logger log = LoggerFactory.getLogger(ConcertsApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(ConcertsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(ArtistRepository artistRepository, ConcertRepository concertRepository, GenreRepository genreRepository, ConcertTypeRepository concertTypeRepository, Concert_artistRepository concert_artistRepository,UserRepository userRepository ) {
		return (arg) -> {
			
		Genre genre1 = new Genre ("Pop");
		Genre genre2 = new Genre ("Rock");
		genreRepository.save(genre1);
		genreRepository.save(genre2);
		
		Artist artist1 = new Artist ("Blind Channel", genre2);
		Artist artist2 = new Artist ("Palaye Royale", genre2);
		Artist artist3 = new Artist("Arttu Lindeman", genre2);
		artistRepository.save(artist1);
		artistRepository.save(artist2);
		artistRepository.save(artist3);

		

		ConcertType concertType1 = new ConcertType ("Festival");
		ConcertType concertType2 = new ConcertType ("Concert");
		concertTypeRepository.save(concertType1);
		concertTypeRepository.save(concertType2);
		
	    
	    
	  /*  Set<Artist> artistsSet = new HashSet<>();
	    artistsSet.add(artist1);
	    artistsSet.add(artist3);
	    */
				
		Concert concert1 = new Concert ("Sick & Dangerous Tour 2022", LocalDate.parse("30.12.2022", formatter), "Pakkahuone", "Tampere", concertType2, artist1);
		Concert concert2 = new Concert ("Sick & Dangerous Tour 2022", LocalDate.parse("30.12.2022", formatter), "Pakkahuone", "Tampere", concertType2, artist3);

		concertRepository.save(concert1);
		concertRepository.save(concert2);

        
		/*Concert_artist concert_artist1 = new Concert_artist(concert1, artist1);
		concert_artistRepository.save(concert_artist1);
		Concert_artist concert_artist2 = new Concert_artist(concert1, artist3);
		concert_artistRepository.save(concert_artist2);*/
		
		List<Artist> artists = (List<Artist>)artistRepository.findAll();
		for (Artist artist : artists) {
			System.out.println(artist.toString());
		}
		
		List<Concert> concerts = (List<Concert>)concertRepository.findAll();
		for (Concert concert : concerts) {
			System.out.println(concert.toString());
		}
		
		List<Genre> genres = (List<Genre>)genreRepository.findAll();
		for (Genre genre : genres) {
			System.out.println(genre.toString());
		}
		
		List<ConcertType> concertTypes = (List<ConcertType>)concertTypeRepository.findAll();
		for (ConcertType concertType : concertTypes) {
			System.out.println(concertType.toString());
		}
		
		/*List<Concert_artist> concert_artists = (List<Concert_artist>)concert_artistRepository.findAll();
		for (Concert_artist concert_artist : concert_artists) {
			System.out.println(concert_artist.toString());
		}*/
		
		User user1 = new User ("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@concerts.com", "USER");
		User user2 = new User ("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@concerts.com", "ADMIN");
		User user3 = new User ("roosa", "$2a$10$EmQxnziwDmZ1wr6pNxP4N.njFodN/li3MEfZxc92QlTLYhk5Lv29e", "roosa@roosa.com", "ADMIN");
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		log.info("fetch all concerts");
		for (Concert concert : concertRepository.findAll()) {
			log.info(concert.toString());
		}
	};
			
	}
	
}

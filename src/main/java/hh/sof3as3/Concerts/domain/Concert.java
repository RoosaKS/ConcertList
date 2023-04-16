package hh.sof3as3.Concerts.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Concert {
	
	@Id //pääavainsarake tietokannan taulussa 
	@GeneratedValue(strategy = GenerationType.AUTO)//autom. generoituva id-arvo uudelle kirjalle 

	private Long concert_id;
	@Size(min=3, message="Input must be at least 3 characters long")
	private String concert_name;
	private LocalDate date;
	@Size(min=3 , max=45, message="Input length should be between 3 and 45 characters.")
	private String venue;
	@Size(min=2, max =40, message="Input length should be between 2 and 40 characters.")
	private String city;
	
	@ManyToOne
 	@JsonIgnoreProperties ("concerts")
    @JoinColumn(name = "ConcertType_id")
    private ConcertType concertType;
	
	/*@ManyToMany(cascade = { CascadeType.ALL})
	@JoinTable(
			name = "Concert_Artist",
			joinColumns = {@JoinColumn(name="concert_id")},
			inverseJoinColumns = {@JoinColumn(name = "artist_id")}
			)
	private Set<Artist> artists = new HashSet<>();*/
 	
	@ManyToOne
	@JsonIgnoreProperties ("concerts")
    @JoinColumn(name = "Artist_id")
    private Artist artist;
	
	
	public Concert() {
		super();
		this.concert_name=null;
		this.date=null;
		this.venue=null;
		this.city=null;
	}

	public Concert(String concert_name, LocalDate date, String venue, String city, ConcertType concertType, Artist artist) {
		super();
		this.concert_id = null;
		this.concert_name = concert_name;
		this.date = date;
		this.venue = venue;
		this.city = city;
		this.concertType = concertType;
		this.artist = artist;
	}

	public Concert(Long concert_id, String concert_name, LocalDate date, String venue, String city) {
		super();
		this.concert_id = null;
		this.concert_name = concert_name;
		this.date = date;
		this.venue = venue;
		this.city = city;

	}
	
	public Concert(Long concert_id, String concert_name, LocalDate date, String venue, String city, ConcertType concertType) {
		super();
		this.concert_id = null;
		this.concert_name = concert_name;
		this.date = date;
		this.venue = venue;
		this.city = city;
		this.concertType = concertType;

	}
	
	public Concert(Long concert_id, String concert_name, LocalDate date, String venue, String city, ConcertType concertType, Artist artist) {
		super();
		this.concert_id = null;
		this.concert_name = concert_name;
		this.date = date;
		this.venue = venue;
		this.city = city;
		this.concertType = concertType;
		this.artist = artist;

	}
	

	public void setConcert_id(Long concert_id) {
		this.concert_id = concert_id;
	}
	
	public void setConcert_name(String concert_name) {
		this.concert_name = concert_name;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public Long getConcert_id() {
		return concert_id;
	}
	
	public String getConcert_name() {
		return concert_name;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getVenue() {
		return venue;
	}
	
	public String getCity() {
		return city;
	}
	
	public ConcertType getConcertType() {
		return concertType;
	}

	public void setConcertType(ConcertType concertType) {
		this.concertType = concertType;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	

	@Override
	public String toString() {
		if (this.concertType != null && this.artist != null)
			return "concert_id=" + concert_id + "concert_name" + concert_name + ", date=" + date + ", venue=" + venue + ", city=" +city+ ", concertType ="+ this.getConcertType() +", artist ="+ this.getArtist();		
		else
			return "concert_id=" + concert_id + "concert_name" + concert_name + ", date=" + date + ", venue=" + venue + ", city=" +city;
	}
	
	
}

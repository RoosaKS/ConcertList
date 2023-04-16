package hh.sof3as3.Concerts.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Artist {

	@Id //pääavainsarake tietokannan taulussa 
	@GeneratedValue(strategy = GenerationType.AUTO)//autom. generoituva id-arvo uudelle kirjalle 

	private Long artist_id;
	@Size(min=1, max=30, message="Input length should be between 1 and 30 characters.")
	private String artist_name;
	
	@ManyToOne
 	@JsonIgnoreProperties ("artists")
    @JoinColumn(name = "genre_id")
    private Genre genre;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
	@JsonIgnoreProperties("artist")
	private List<Concert> concerts;
	
	/*@ManyToMany(mappedBy = "artists")
	private Set<Concert> concerts = new HashSet<>();*/
	
	
	public Artist() {
		super();
		this.artist_name = null;
	}


	public Artist(String artist_name, Genre genre) {
		super();
		this.artist_id = null;
		this.artist_name = artist_name;
		this.genre = genre;
	}
	
	public Artist(Long artist_id, String artist_name) {
		super();
		this.artist_id = null;
		this.artist_name = artist_name;
	}
	
	public Artist(Long artist_id, String artist_name,Genre genre) {
		super();
		this.artist_id = null;
		this.artist_name = artist_name;
		this.genre = genre;
	}

	public void setArtist_id(Long artist_id) {
		this.artist_id = artist_id;
	}


	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}


	public Long getArtist_id() {
		return artist_id;
	}


	public String getArtist_name() {
		return artist_name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public List<Concert> getConcerts() {
		return concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}
	
	@Override
	public String toString() {
		if (this.genre != null)
			return "artist_id=" + artist_id + ", artist_name=" + artist_name + ", genre="+ this.getGenre();		
		else
			return "artist_id=" + artist_id + ", artist_name=" + artist_name;
	}
	
	
	
	
	
	
}

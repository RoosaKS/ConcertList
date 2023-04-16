package hh.sof3as3.Concerts.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Genre {
	
	@Id //pääavainsarake tietokannan taulussa 
	@GeneratedValue(strategy = GenerationType.AUTO)//autom. generoituva id-arvo uudelle kirjalle 

	private Long genre_id;
	@Size(min=3, max=30, message="Input length should be between 3 and 20 characters.")
	private String genre_name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	@JsonIgnoreProperties("genre")
	private List<Artist> artists;
	
	public Genre() {
		super();
		this.genre_name =null;
	}


	public Genre(Long genre_id, String genre_name) {
		super();
		this.genre_id = null;
		this.genre_name = genre_name;
	}
	
	public Genre(String genre_name) {
		super();
		this.genre_id = null;
		this.genre_name = genre_name;
	}


	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}


	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}


	public Long getGenre_id() {
		return genre_id;
	}


	public String getGenre_name() {
		return genre_name;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public String toString() {
		return "genre_id=" + genre_id + ", genre_name=" + genre_name;
	}

	
	
	
}

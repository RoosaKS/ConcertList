package hh.sof3as3.Concerts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Concert_artist {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concert_artist_id;
    
    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;
    
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    
    

	public Concert_artist() {
		super();
		this.concert_artist_id =null;
	}

	public Concert_artist(Long concert_artist_id, Concert concert, Artist artist) {
		super();
		this.concert_artist_id = concert_artist_id;
		this.concert = concert;
		this.artist = artist;
	}
	
	public Concert_artist(Concert concert, Artist artist) {
		super();
		this.concert_artist_id = null;
		this.concert = concert;
		this.artist = artist;
	}

	public void setId(Long concert_artist_id) {
		this.concert_artist_id = concert_artist_id;
	}

	public void setConcert(Concert concert) {
		this.concert = concert;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Long getConcert_artist_id() {
		return concert_artist_id;
	}

	public Concert getConcert() {
		return concert;
	}

	public Artist getArtist() {
		return artist;
	}

	@Override
	public String toString() {
		return "Concert_artist concert_artist_id=" + concert_artist_id + ", concert=" + concert + ", artist=" + artist;
	}
    
    
}

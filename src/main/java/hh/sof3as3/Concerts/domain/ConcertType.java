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
public class ConcertType {
	
	@Id //pääavainsarake tietokannan taulussa 
	@GeneratedValue(strategy = GenerationType.AUTO)//autom. generoituva id-arvo uudelle kirjalle 

	private Long concertType_id;
	@Size(min=4, max=25, message="Input length should be between4 and 25 characters.")
	private String concertType_name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "concertType")
	@JsonIgnoreProperties("concertType")
	private List<Concert> concerts;
	
	
	public ConcertType() {
		super();
		this.concertType_name =null;
	}
	
	public ConcertType(String concertType_name) {
		super();
		this.concertType_id = null;
		this.concertType_name = concertType_name;
	}

	public ConcertType(Long concertType_id, String concertType_name) {
		super();
		this.concertType_id = null;
		this.concertType_name = concertType_name;
	}


	public void setConcertType_id(Long concertType_id) {
		this.concertType_id = concertType_id;
	}

	public void setConcertType_name(String concertType_name) {
		this.concertType_name = concertType_name;
	}

	public Long getConcertType_id() {
		return concertType_id;
	}

	public String getConcertType_name() {
		return concertType_name;
	}
	
	public List<Concert> getConcerts() {
		return concerts;
	}

	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}

	@Override
	public String toString() {
		return "concertType_id=" + concertType_id + ", concertType_name=" + concertType_name;
	}

	
	
}

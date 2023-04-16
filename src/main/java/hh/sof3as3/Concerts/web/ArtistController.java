package hh.sof3as3.Concerts.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3as3.Concerts.domain.Artist;
import hh.sof3as3.Concerts.domain.ArtistRepository;
import hh.sof3as3.Concerts.domain.Concert;
import hh.sof3as3.Concerts.domain.ConcertType;
import hh.sof3as3.Concerts.domain.Genre;
import hh.sof3as3.Concerts.domain.GenreRepository;
import jakarta.validation.Valid;

@Controller
public class ArtistController {
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	@RequestMapping(value = "/artists", method = RequestMethod.GET)
	public String getArtists(Model model) {
		// Retrieve all artists from the repository
	List<Artist> artists = (List<Artist>)artistRepository.findAll();
	// Add the artist list to the model
	model.addAttribute("artists",artists);
	return "artistlist";
	}
	
	// API endpoint to retrieve a single artist by ID
	@RequestMapping(value ="artists/{artist_id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Artist> getOneArtist(@PathVariable("artist_id") Long artistId){
		return artistRepository.findById(artistId);
	}
	
	// New artist form
	@RequestMapping(value = "/newartist", method = RequestMethod.GET)
	public String getNewArtistForm(Model model) {
		// Add empty artist object and lists of genres to the model
		model.addAttribute("artist", new Artist());
		model.addAttribute("genres", genreRepository.findAll());
		return "addartist";
	}
	
	// Save a new artist
	@RequestMapping(value = {"/saveartist", "/artists/saveartist"}, method = RequestMethod.POST)
	public String saveArtist(@Valid @ModelAttribute ("artist") Artist artist, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // check for validation errors 
	        model.addAttribute("errors", bindingResult.getAllErrors());
		
	        Iterable<Genre> genres = genreRepository.findAll();
	        model.addAttribute("genres", genres);
	        
			return "addartist";  // return back to form with validation errors
		} else { // no validation errors
			// Check if the artist already exists in the repository
	        List<Artist> existingArtists = artistRepository.findByQuery(
	        		 artist.getGenre().getGenre_id()
	            );
	            if (!existingArtists.isEmpty()) {
	                // If the list of existing artists is not empty, add an error message and return to the "addartist" view
	            	model.addAttribute("error", "Artist already exists!");

	            	Iterable<Genre> genres = genreRepository.findAll();
	    	        model.addAttribute("genres", genres);
	            	return "addartist";
	            }
	        
	     artistRepository.save(artist);
		
		return "redirect:/artists";
		}
	}
	
	@RequestMapping(value= "/artists/delete/{artist_id}", method = RequestMethod.GET)
	public String deleteArtist (@PathVariable("artist_id") Long artistId, Model model) {
	    // Delete the artist with the given ID from the database
		artistRepository.deleteById(artistId);
		
		return "redirect:/artists";
	}

	@RequestMapping(value = "/artists/edit/{artist_id}")
	public String showModArtist(@PathVariable("artist_id") Long artistID, Model model) {
	    // Get the artist with the given ID from the database and add it to the model
		model.addAttribute("artist", artistRepository.findById(artistID));
	    // Add all genres to the model
		model.addAttribute("genres", genreRepository.findAll());
		
		return "editartist";
	}

	@GetMapping("/artists/search") 
    public String searchArtists(@RequestParam("query") String query, Model model){
	    // Search for artists with the given query and add them to the model   
		List<Artist> results = artistRepository.search(query);
	    model.addAttribute("artists", results); 
	    return "artistlist";
    }
 
}

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
	List<Artist> artists = (List<Artist>)artistRepository.findAll();
	model.addAttribute("artists",artists);
	return "artistlist";
	}
	
	@RequestMapping(value ="artists/{artist_id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Artist> getOneArtist(@PathVariable("artist_id") Long artistId){
		return artistRepository.findById(artistId);
	}
	
	@RequestMapping(value = "/newartist", method = RequestMethod.GET)
	public String getNewArtistForm(Model model) {
		model.addAttribute("artist", new Artist());
		model.addAttribute("genres", genreRepository.findAll());
		return "addartist";
	}
	
	@RequestMapping(value = {"/saveartist", "/artists/saveartist"}, method = RequestMethod.POST)
	public String saveArtist(@Valid @ModelAttribute ("artist") Artist artist, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors 
	        model.addAttribute("errors", bindingResult.getAllErrors());
		
	        Iterable<Genre> genres = genreRepository.findAll();
	        model.addAttribute("genres", genres);
	        
			return "addartist";  // return back to form
		} else { // no validation errors
	        
	     artistRepository.save(artist);
		
		return "redirect:/artists";
		}
	}
	
	@RequestMapping(value= "/artists/delete/{artist_id}", method = RequestMethod.GET)
	public String deleteArtist (@PathVariable("artist_id") Long artistId, Model model) {
		artistRepository.deleteById(artistId);
		
		return "redirect:/artists";
	}

	@RequestMapping(value = "/artists/edit/{artist_id}")
	public String showModArtist(@PathVariable("artist_id") Long artistID, Model model) {
		model.addAttribute("artist", artistRepository.findById(artistID));
		model.addAttribute("genres", genreRepository.findAll());
		
		return "editartist";
	}

	@GetMapping("/artists/search") 
    public String searchArtists(@RequestParam("query") String query, Model model){
	   List<Artist> results = artistRepository.search(query);
	    model.addAttribute("artists", results); 
	    return "artistlist";
    }
 
}

package hh.sof3as3.Concerts.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import hh.sof3as3.Concerts.domain.ConcertRepository;
import hh.sof3as3.Concerts.domain.ConcertType;
import hh.sof3as3.Concerts.domain.ConcertTypeRepository;
import hh.sof3as3.Concerts.domain.GenreRepository;
import jakarta.validation.Valid;

@Controller
public class ConcertController {
	
	@Autowired
	ConcertRepository concertRepository;
	@Autowired
	private ArtistRepository artistRepository; 
	
	@Autowired
	private GenreRepository genreRepository; 
	
	@Autowired
	private ConcertTypeRepository concertTypeRepository; 
	
	/*@Autowired
	private Concert_artistRepository concert_artistRepository; */
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String getConcerts(Model model) {
	List<Concert> concerts = (List<Concert>)concertRepository.findAll();
	
	 
	model.addAttribute("concerts",concerts);
	return "concertlist";
	}
	
	@RequestMapping(value= "/concerts", method = RequestMethod.GET)
	public @ResponseBody List<Concert> getConcerts(){
		return (List<Concert>) concertRepository.findAll();
	}
	
	@RequestMapping(value= "concerts/{concert_id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Concert> getOneConcert(@PathVariable("concert_id") Long concertId){
		return concertRepository.findById(concertId);
	}

	
	@RequestMapping(value = "/newconcert", method = RequestMethod.GET)
	public String getNewConcertForm(Model model) {
		model.addAttribute("concert", new Concert());
		model.addAttribute("artists", artistRepository.findAll());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("concertTypes", concertTypeRepository.findAll());
	//	model.addAttribute("concert_artists", concert_artistRepository.findAll());
		
		return "addconcert";
	}
	
	@RequestMapping(value = "/saveconcert", method = RequestMethod.POST)
	public String saveConcert( @Valid @ModelAttribute ("concert") Concert concert, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors 
	        model.addAttribute("errors", bindingResult.getAllErrors());
	        Iterable<Artist> artists = artistRepository.findAll();
	        model.addAttribute("artists", artists);

	        Iterable<ConcertType> concertTypes = concertTypeRepository.findAll();
	        model.addAttribute("concertTypes", concertTypes);

			return "addconcert";  // return back to form
		} else { // no validation errors
		concertRepository.save(concert);
		
		return "redirect:/index";
		}
	}
	
	@RequestMapping(value = "/delete/{concert_id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteConcert(@PathVariable("concert_id") Long Id, Model model) {
		concertRepository.deleteById(Id);
		
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/edit/{concert_id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public String showModConcert(@PathVariable("concert_id") Long id, Model model) {
		model.addAttribute("concert", concertRepository.findById(id));
		model.addAttribute("artists", artistRepository.findAll());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("concertTypes", concertTypeRepository.findAll());
	//	model.addAttribute("concert_artists", concert_artistRepository.findAll());


		return "editconcert";
	}
	
	
	
	@GetMapping("/search") 
	    public String searchConcerts(@RequestParam("query") String query, Model model){
		   List<Concert> results = concertRepository.search(query);
		    model.addAttribute("concerts", results); 
		    return "concertlist";
	    }
	 
	
	 
}

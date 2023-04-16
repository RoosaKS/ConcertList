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

import hh.sof3as3.Concerts.domain.Concert;
import hh.sof3as3.Concerts.domain.Genre;
import hh.sof3as3.Concerts.domain.GenreRepository;
import jakarta.validation.Valid;

@Controller
public class GenreController {
	
	@Autowired
	GenreRepository genreRepository;

	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public String getGenres(Model model) {
	List<Genre> genres = (List<Genre>)genreRepository.findAll();

	model.addAttribute("genres",genres);
	return "genrelist";
	}
	
	@RequestMapping(value ="genres/{genre_id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Genre> getOneGenre(@PathVariable("genre_id") Long genreId){
		return genreRepository.findById(genreId);
	}
	
	@RequestMapping(value = "/newgenre", method = RequestMethod.GET)
	public String getNewGenreForm(Model model) {
		model.addAttribute("genre", new Genre());
		return "addgenre";
	}
	
	@RequestMapping(value = {"/savegenre", "genres/savegenre"}, method = RequestMethod.POST)
	public String saveGenre(@Valid @ModelAttribute ("genre")Genre genre, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors 
	        model.addAttribute("errors", bindingResult.getAllErrors());
			return "addgenre";  // return back to form

		} else { // no validation errors

		genreRepository.save(genre);
		
		return "redirect:/genres";
		}
	}
	
	@RequestMapping(value= "/genres/delete/{genre_id}", method = RequestMethod.GET)
	public String deleteGenre (@PathVariable("genre_id") Long genreId, Model model) {
		genreRepository.deleteById(genreId);
		
		return "redirect:/genres";
	}

	@RequestMapping(value = "/genres/edit/{genre_id}")
	public String showModGenre(@PathVariable("genre_id") Long genreID, Model model) {
		model.addAttribute("genre", genreRepository.findById(genreID));
		
		return "editgenre";
	}
	
	@GetMapping("/genres/search") 
    public String searchGenres(@RequestParam("query") String query, Model model){
	   List<Genre> results = genreRepository.search(query);
	    model.addAttribute("genres", results); 
	    return "genrelist";
    }
 
}

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

import hh.sof3as3.Concerts.domain.ConcertType;
import hh.sof3as3.Concerts.domain.ConcertTypeRepository;
import jakarta.validation.Valid;

@Controller
public class ConcertTypeController {
	
	@Autowired
	ConcertTypeRepository concertTypeRepository;
	
	@RequestMapping(value = "/concerttypes", method = RequestMethod.GET)
	public String getConcertTypes(Model model) {
	List<ConcertType> concertTypes = (List<ConcertType>)concertTypeRepository.findAll();
	
	model.addAttribute("concertTypes",concertTypes);
	return "concerttypelist";
	}
	
	@RequestMapping(value ="concerttypes/{concertType_id}", method = RequestMethod.GET)
	public @ResponseBody Optional<ConcertType> getOneConcertType(@PathVariable("concertType_id") Long concertTypeId){
		return concertTypeRepository.findById(concertTypeId);
	}

	@RequestMapping(value = "/newconcerttype", method = RequestMethod.GET)
	public String getNewConcertTypeForm(Model model) {
		model.addAttribute("concertType", new ConcertType());
		return "addconcerttype";
	}
	
	@RequestMapping(value = {"/saveconcerttype", "/concerttypes/saveconcerttype"}, method = RequestMethod.POST)
	public String saveConcertType(@Valid @ModelAttribute ("concertType") ConcertType concertType, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) { // validation errors 
	        model.addAttribute("errors", bindingResult.getAllErrors());
		
			return "addconcerttype";  // return back to form

		} else { // no validation errors

		concertTypeRepository.save(concertType);
		
		return "redirect:/concerttypes";
		}
	}

	@RequestMapping(value= "/concerttypes/delete/{concertType_id}", method = RequestMethod.GET)
	public String deleteConcertType (@PathVariable("concertType_id") Long concertTypeId, Model model) {
		concertTypeRepository.deleteById(concertTypeId);
		
		return "redirect:/concerttypes";
	}

	@RequestMapping(value = "/concerttypes/edit/{concertType_id}")
	public String showModArtist(@PathVariable("concertType_id") Long concertTypeID, Model model) {
		model.addAttribute("concertType", concertTypeRepository.findById(concertTypeID));
		
		return "editconcerttype";
	}
	
	@GetMapping("/concerttypes/search") 
    public String searchConcerttypes(@RequestParam("query") String query, Model model){
	   List<ConcertType> results = concertTypeRepository.search(query);
	    model.addAttribute("concertTypes", results); 

	    return "concerttypelist";
	    
    }
 
}

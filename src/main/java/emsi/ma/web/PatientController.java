package emsi.ma.web;





import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import emsi.ma.entities.Patient;
import emsi.ma.repositories.PatientRepository;

@Controller
public class PatientController {
	@Autowired
 private PatientRepository patientRepository;
	
	@GetMapping(path = "/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(path ="/patients")
	public String list(Model model, 
	
		@RequestParam(name="page",defaultValue="0")int page, // va dans la requette utilise l'objet request getparameter(page) et l'affect a int page
		@RequestParam(name="size",defaultValue="5")int size,
		@RequestParam(name="keyword",defaultValue="")String mc) {
		Page<Patient> pagePatients = patientRepository.findByNameContains(mc,PageRequest.of(page, size)); // retourne une page des liste des patients
		//List<Patient> patients = patientRepository.findAll();
		model.addAttribute("patients",pagePatients.getContent()); // liste des patients 
		
		model.addAttribute("pages",new int[pagePatients.getTotalPages()]); // tableau[nombretotalepage]
		model.addAttribute("currentpage",page);
		model.addAttribute("keyword",mc);
		return "patients"; 
	}
	
	@GetMapping(path = "/deletePatient")
	public String delete(Long id) {
		patientRepository.deleteById(id);
		return "redirect:/patients";
	}
	
	@GetMapping(path = "/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formPatient";
	}
	

	@PostMapping(path = "/savePatient")
	public String savePatient(@Valid Patient patient,BindingResult bidingResult) {
		if(bidingResult.hasErrors()) return "formPatient";
		patientRepository.save(patient);
		return "formPatient";
	}
	
	
	@GetMapping(path = "/editPatient")
	public String editPatient(Model model,Long id) {
		Patient p=patientRepository.findById(id).get();
		model.addAttribute("patient", p);
		return "formPatient";
	}
	
	
	
	
	
	
}

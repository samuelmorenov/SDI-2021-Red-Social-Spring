package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorsService;
import com.uniovi.validators.ProfessorValidator;

@Controller
public class ProfessorsController {

	@Autowired // Inyectar el servicio
	private ProfessorsService professorsService;

	@Autowired
	private ProfessorValidator professorValidator;

	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorList", professorsService.getProfessors());
		return "professor/list";
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@ModelAttribute Professor professor, BindingResult result, Model model) {
		professorValidator.validate(professor, result);
		if (result.hasErrors()) {
			return "professor/add";
		}
		professorsService.addProfessor(professor);
		return "redirect:/professor/list";
	}

	@RequestMapping(value = "/professor/add")
	public String getProfessor() {
		return "professor/add";
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorsService.getProfessor(id));
		return "professor/details";
	}

	@RequestMapping("/professor/delete/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		professorsService.deleteProfessor(id);
		return "redirect:/professor/list";
	}

	@RequestMapping(value = "/professor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorsService.getProfessor(id));
		return "professor/edit";
	}

	@RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Professor professor) {
		professor.setId(id);
		professorsService.addProfessor(professor);
		return "redirect:/professor/details/" + id;
	}

}

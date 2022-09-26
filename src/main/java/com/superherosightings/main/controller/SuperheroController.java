package com.superherosightings.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.superherosightings.main.dao.SuperheroDao;
import com.superherosightings.main.dto.Superhero;

@Controller
public class SuperheroController {
	
	@Autowired
	SuperheroDao superheroDao;

	
	@GetMapping("/create_superhero")
	public String viewCreateSuperhero() {
		return "create_superhero";
	}
	
	@PostMapping("/createNewSuperhero")
	public String createNewSuperhero(Model model, HttpServletRequest request) {
		String superheroName = request.getParameter("name");
		String superheroDesc = request.getParameter("description");
		Superhero superhero = new Superhero();
		superhero.setName(superheroName);
		superhero.setDescription(superheroDesc);
		superheroDao.createSuperhero(superhero);
		model.addAttribute("successMessage", "The superhero/supervillain has been successfully created");
		return viewSuperheroes(model);
	}
	
	@GetMapping("/view_superheroes")
	public String viewSuperheroes(Model model) {
		List<Superhero> superheroes = superheroDao.getAllSuperheroes();
		model.addAttribute("superheroes", superheroes);
		return "view_superheroes";
	}
	
	@GetMapping("/edit_superheroes/{superheroId}")
	public String viewEditSuperhero(@PathVariable int superheroId, Model model) {
		model.addAttribute("superheroId", ""+superheroId);
		return "edit_superheroes";
	}
	
	@PostMapping("/edit_superheroes/editSuperhero/{superheroId}")
	public String editSuperhero(@PathVariable int superheroId, Model model, HttpServletRequest request) {
		
		Superhero superhero;
		String superheroName = request.getParameter("name");
		String superheroDesc = request.getParameter("description");
		
		superhero = superheroDao.getSuperheroById(superheroId);
		superhero.setName(superheroName);
		superhero.setDescription(superheroDesc);
		superheroDao.updateSuperhero(superhero);
		model.addAttribute("successMessage", "The superhero/supervillain has been successfully updated");
		return viewSuperheroes(model);
	}
	
	@PostMapping("/deleteSuperhero/{superheroId}")
	public String deleteSuperhero(@PathVariable int superheroId, Model model, HttpServletRequest request) {
		superheroDao.deleteSuperheroById(superheroId);
		return viewSuperheroes(model);
	}
	
}

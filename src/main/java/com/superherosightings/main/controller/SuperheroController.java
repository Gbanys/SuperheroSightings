package com.superherosightings.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		return "create_superhero";
	}
	
	@GetMapping("/view_superheroes")
	public String viewSuperheroes() {
		return "view_superheroes";
	}
	
	@GetMapping("/viewAllSuperheroes")
	public String displaySuperheroes(Model model) {
		
		List<Superhero> superheroes = superheroDao.getAllSuperheroes();
		model.addAttribute("superheroes", superheroes);
		return "view_superheroes";
	}
	
	@GetMapping("/viewSuperheroById")
	public String displaySuperheroById(Model model, HttpServletRequest request) {
		
		int superheroId;
		
		try {
		superheroId = Integer.parseInt(request.getParameter("superheroId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "view_superheroes";
		}
		
		try {
		Superhero superhero = superheroDao.getSuperheroById(superheroId);
		model.addAttribute("superheroById", superhero.toString());
		return "view_superheroes";
		}
		catch(NoSuchElementException e) {
			model.addAttribute("NoSuchSuperheroError", "Sorry but a superhero with this ID does not exist");
			return "view_superheroes";
		}
	}
	
	@GetMapping("/edit_superheroes")
	public String viewEditSuperhero() {
		return "edit_superheroes";
	}
	
	@PostMapping("/editSuperhero")
	public String editSuperhero(Model model, HttpServletRequest request) {
		
		int superheroId;
		Superhero superhero;
		
		try {
		superheroId = Integer.parseInt(request.getParameter("superheroId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "edit_superheroes";
		}
		String superheroName = request.getParameter("name");
		String superheroDesc = request.getParameter("description");
		
		try {
			superhero = superheroDao.getSuperheroById(superheroId);
		}
		catch(NoSuchElementException e) {
			model.addAttribute("NoSuchSuperheroError", "Sorry but a superhero with this ID does not exist");
			return "edit_superheroes";
		}
		
		superhero.setName(superheroName);
		superhero.setDescription(superheroDesc);
		superheroDao.updateSuperhero(superhero);
		model.addAttribute("successMessage", "The superhero/supervillain has been successfully updated");
		return "edit_superheroes";
	}
	
	@GetMapping("/delete_superheroes")
	public String viewDeleteSuperhero() {
		return "delete_superheroes";
	}
	
	@PostMapping("/deleteSuperhero")
	public String deleteSuperhero(Model model, HttpServletRequest request) {
		int superheroId;
		
		try {
		superheroId = Integer.parseInt(request.getParameter("superheroId"));
		}
		catch(NumberFormatException e) {
			model.addAttribute("NumberFormatError", "Please enter a number into the ID field, no letters or symbols are allowed!");
			return "delete_superheroes";
		}
		
		try {
		superheroDao.deleteSuperheroById(superheroId);
		model.addAttribute("successMessage", "The superhero has been successfully deleted");
		return "delete_superheroes";
		}
		catch(EmptyResultDataAccessException e) {
			model.addAttribute("NoSuchSuperheroError", "Sorry but a superhero with this ID does not exist");
			return "delete_superheroes";
		}
	}
	
}

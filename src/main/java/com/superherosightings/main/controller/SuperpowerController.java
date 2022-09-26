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

import com.superherosightings.main.dao.SuperpowerDao;
import com.superherosightings.main.dto.Superhero;
import com.superherosightings.main.dto.Superpower;

@Controller
public class SuperpowerController {
	
	@Autowired
	SuperpowerDao superpowerDao;

	@GetMapping("/create_superpower")
	public String viewCreateSuperpower() {
		return "create_superpower";
	}
	
	@PostMapping("/createNewSuperpower")
	public String createNewSuperpower(Model model, HttpServletRequest request) {
		String superpowerName = request.getParameter("name");
		Superpower superpower = new Superpower();
		superpower.setName(superpowerName);
		superpowerDao.createSuperpower(superpower);
		model.addAttribute("successMessage", "The superpower has been successfully created");
		return viewSuperpowers(model);
	}
	
	@GetMapping("/view_superpowers")
	public String viewSuperpowers(Model model) {
		List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
		model.addAttribute("superpowers", superpowers);
		return "view_superpowers";
	}
	
	@GetMapping("/edit_superpowers/{superpowerId}")
	public String viewEditSuperpower(@PathVariable int superpowerId, Model model) {
		model.addAttribute("superheroId", ""+superpowerId);
		return "edit_superpowers";
	}
	
	@PostMapping("/edit_superpower/editSuperpower/{superpowerId}")
	public String editSuperpower(@PathVariable int superpowerId, Model model, HttpServletRequest request) {
		
		String superpowerName = request.getParameter("name");
		Superpower superpower = superpowerDao.getSuperpowerById(superpowerId);
		superpower.setName(superpowerName);
		superpowerDao.updateSuperpower(superpower);
		model.addAttribute("successMessage", "The superpower has been successfully updated");
		return viewSuperpowers(model);
	}
	
	@PostMapping("/delete_superpowers/{superpowerId}")
	public String viewDeleteSuperpower() {
		return "delete_superpowers";
	}
	
	@PostMapping("/deleteSuperpower/{superpowerId}")
	public String deleteSuperpower(@PathVariable int superpowerId, Model model, HttpServletRequest request) {
		
		superpowerDao.deleteSuperpowerById(superpowerId);
		model.addAttribute("successMessage", "The superpower has been successfully deleted");
		return viewSuperpowers(model);
		
	}
}

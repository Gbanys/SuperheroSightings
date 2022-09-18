package com.superherosightings.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		return "create_superpower";
	}
	
	@GetMapping("/view_superpowers")
	public String viewSuperpowers() {
		return "view_superpowers";
	}
	
	@GetMapping("/viewAllSuperpowers")
	public String displaySuperpowers(Model model) {
		List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
		model.addAttribute("superpowers", superpowers);
		return "view_superpowers";
	}
	
	@GetMapping("/viewSuperpowerById")
	public String displaySuperpowerById(Model model, HttpServletRequest request) {
		int superpowerId = Integer.parseInt(request.getParameter("superpowerId"));
		Superpower superpower = superpowerDao.getSuperpowerById(superpowerId);
		model.addAttribute("superpowerById", superpower.toString());
		return "view_superpowers";
	}
	
	@GetMapping("/edit_superpowers")
	public String viewEditSuperpower() {
		return "edit_superpowers";
	}
	
	@PostMapping("/editSuperpower")
	public String editSuperpower(Model model, HttpServletRequest request) {
		int superpowerId = Integer.parseInt(request.getParameter("superpowerId"));
		String superpowerName = request.getParameter("name");
		Superpower superpower = superpowerDao.getSuperpowerById(superpowerId);
		superpower.setName(superpowerName);
		superpowerDao.updateSuperpower(superpower);
		model.addAttribute("successMessage", "The superpower has been successfully updated");
		return "edit_superpowers";
	}
	
	@GetMapping("/delete_superpowers")
	public String viewDeleteSuperpower() {
		return "delete_superpowers";
	}
	
	@PostMapping("/deleteSuperpower")
	public String deleteSuperpower(Model model, HttpServletRequest request) {
		int superpowerId = Integer.parseInt(request.getParameter("superpowerId"));
		superpowerDao.deleteSuperpowerById(superpowerId);
		model.addAttribute("successMessage", "The superpower has been successfully deleted");
		return "delete_superpowers";
	}
}

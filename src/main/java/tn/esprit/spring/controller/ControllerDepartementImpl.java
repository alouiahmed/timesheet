package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.services.IDepartementService;

@Controller
public class ControllerDepartementImpl {
	
	@Autowired
	IDepartementService ideptService;
	

	public int ajouterDept(Departement dep) {
		ideptService.ajouterDepartement(dep);
		return dep.getId();
}
	
}
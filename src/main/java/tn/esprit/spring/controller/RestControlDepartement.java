package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEntrepriseService;

@RestController
public class RestControlDepartement {
	
	@Autowired
	IDepartementService iDeptService ;
	@Autowired
	IEntrepriseService ientrepriseservice;

	@PostMapping("/ajouterDept")
	@ResponseBody
	public Departement ajouterDept(@RequestBody Departement dep)
	{
		iDeptService.ajouterDepartement(dep);
		return dep;
	}
	 @DeleteMapping("/deleteDepartement/{iddept}") 
		@ResponseBody 
		public void deleteDepartementById(@PathVariable("iddept") int depId) {
		 iDeptService.deleteDepartementById(depId);

		}
	 @GetMapping(value = "getDeptById/{iddept}")
	    @ResponseBody
		public Departement getDepartement(@PathVariable("iddept") int idDep) {
			return iDeptService.getDepBytId(idDep);
		}
	 @GetMapping(value = "/getAllDept")
	    @ResponseBody
		public List<Departement> getAllDept() {
			
			return iDeptService.getAllDepartements();
		}
}

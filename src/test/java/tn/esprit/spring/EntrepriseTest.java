package tn.esprit.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.RestControlEntreprise;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
@SpringBootTest()
@RunWith(SpringRunner.class)
public class EntrepriseTest {

	@Autowired
	RestControlEntreprise controlEntreprise;
	@Autowired
	IEntrepriseService ientrepriseservice;
	
	@Test
	 public void testajouterEntreprise(){
		 Entreprise entreprise= new Entreprise (10,"Sagem", "Sagemcom");
		 controlEntreprise.ajouterEntreprise(entreprise); 
	 }
	 
	 @Test
	 public void testGetEntrepriseById(){
		 controlEntreprise.getEntrepriseById(10);
		 
	 }
	 
	 @Test
	 public void testAjouterDepartement(){
		 Departement departement = new Departement (10,"Informatique");
		 controlEntreprise.ajouterDepartement(departement);
	 }
	 
	 @Test
	 public void testAffecterDepartementAEntreprise(){
		 controlEntreprise.affecterDepartementAEntreprise(5, 10); //idDep,idEnt
	 }
	
	 @Test
	 public void testGetAllDepartementsNamesByEntreprise(){
		 controlEntreprise.getAllDepartementsNamesByEntreprise(10);
	 }
	 
	 @Test
	 public void testDeleteDepartementById(){
		 controlEntreprise.deleteDepartementById(6);
	 }
	 
	 @Test
	 public void testDeleteEntrepriseById(){
		 controlEntreprise.deleteEntrepriseById(11);
	 }
}

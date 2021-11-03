package tn.esprit.spring;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.RestControlEntreprise;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
@SpringBootTest()
@RunWith(SpringRunner.class)
public class EntrepriseTest {

	@Autowired
	RestControlEntreprise controlEntreprise;
	
	private static final Logger l = Logger.getLogger(EntrepriseTest.class);
	
	@Test
	 public void testajouterEntreprise(){
		 Entreprise entreprise= new Entreprise (10,"Sagem", "Sagemcom");
			try{
				l.debug("Je vais  lancer lancer test ajout de l'entreprise");
				 controlEntreprise.ajouterEntreprise(entreprise); 
				l.info("Entreprise est ajouté avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans testajouterEntreprise()!!"+ e);
			}
			finally{
				l.info("la méthode testajouterEntreprise() est finie");
			}		 
	 }
	 
	 @Test
	 public void testGetEntrepriseById(){
		 
		 try{
				l.debug("lancement testgetEntrepriseById()");			
				 controlEntreprise.getEntrepriseById(10);
				l.info("la méthode testgetEntrepriseById() est réalisée avec succès");

			}
			catch(Exception e){
				l.error("Erreur dans la méthode getEntrepriseById()!!"+ e);
			}
			finally{
				l.info("la méthode testgetEntrepriseById() est finie");
			}
		 		 
	 }
	 
	 @Test
	 public void testAjouterDepartement(){
		 Departement departement = new Departement (10,"Informatique");	 
			try{
				l.debug("Je vais  lancer lancer test ajout d'une département");
				 controlEntreprise.ajouterDepartement(departement);
				l.info("Département est ajoutée avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testAjouterDepartement()()!!"+ e);
			}
			finally{
				l.info("la méthode testAjouterDepartement() est finie");
			}
	 }
	 
	 @Test
	 public void testAffecterDepartementAEntreprise(){
			try{
				l.debug("Je vais  lancer testAffecterDepartementAEntreprise");
				
				 controlEntreprise.affecterDepartementAEntreprise(5, 10); //idDep,idEnt

				l.info("Département est affectée à une entreprise avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testAffecterDepartementAEntreprise()!!"+ e);
			}
			finally{
				l.info("la méthode testAffecterDepartementAEntreprise() est finie");
			}		 
		 
		 
		 
	 }
	
	 @Test
	 public void testGetAllDepartementsNamesByEntreprise(){		 
			try{
				l.debug("Je vais lancer testGetAllDepartementsNamesByEntreprise()");
				 controlEntreprise.getAllDepartementsNamesByEntreprise(10);
				l.info("la méthode testGetAllDepartementsNamesByEntreprise() est réalisé avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testGetAllDepartementsNamesByEntreprise()!!"+ e);
			}
			finally{
				l.info("la méthode testGetAllDepartementsNamesByEntreprise() est finie");
			}
	 }
	 
	 @Test
	 public void testDeleteDepartementById(){
			try{
				l.debug("lancement testDeleteDepartementById()");			
				
				 controlEntreprise.deleteDepartementById(7);
				
				l.info("la méthode testDeleteDepartementById() est réalisée avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testDeleteDepartementById()!!"+ e);
			}
			finally{
				l.info("la méthode testDeleteDepartementById() est finie");
			}
	 }
	 
	 @Test
	 public void testDeleteEntrepriseById(){		 
			try{
				l.debug("lancement testDeleteEntrepriseById()");			
				
				 controlEntreprise.deleteEntrepriseById(12);
				
				l.info("la méthode testDeleteEntrepriseById() est réalisée avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testDeleteEntrepriseById()!!"+ e);
			}
			finally{
				l.info("la méthode testDeleteEntrepriseById() est finie");
			}
	 }
}

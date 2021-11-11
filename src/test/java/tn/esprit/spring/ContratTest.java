package tn.esprit.spring;

import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.RestControlEmploye;
import tn.esprit.spring.entities.Contrat;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ContratTest {
	
	
	private static final Logger l = LogManager.getLogger(ContratTest.class);	
	
	@Autowired
	RestControlEmploye restControlEmploye;
	
	
	@Test
	public void testAjouterContrat()
	{
		Contrat contrat=new Contrat("stage", 452156, 2000);
		try {
			l.info("Tester l'ajout d'un contrat");
			restControlEmploye.ajouterContrat(contrat);
			l.info("Contrat ajouté avec succés");
			
			l.info("Test reference not null");
			assertNotNull(contrat.getReference());
			
			l.info("test salaire superieur a 1000");
			assertTrue(contrat.getSalaire() >1000);
			
			l.info("test longueur de type contrat superieur a 3");
			assertTrue(contrat.getTypeContrat().length() >3);
			
		}
		catch (Exception e)
		{ l.error(()->"Erreur dans testAjouterContrat() : " + e); }
	}
	
	@Test
	public void testaffecterContratAEmploye()
	{
		try {
			l.info("Tester l'affectation d'un contrat a employe");
			restControlEmploye.affecterContratAEmploye(8, 1);
			l.info("affectation employe/contrat avec succés");
			
		}
		catch (Exception e)
		{ l.error(()->"Erreur dans testaffecterContratAEmploye() : " + e); }
	}
	
	 @Test
	 public void testDeleteContratById(){		 
			try{
				l.debug("lancement testDeleteContratById()");			
				
				restControlEmploye.deleteContratById(9);
				
				l.info("contrat supprimé avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testDeleteContratById()!!"+ e);
			}
			finally{
				l.info("la méthode testDeleteContratById() est finie");
			}
	 }
	 
	 @Test
	 public void testDeleteAllContrat(){		 
			try{
				l.debug("lancement testDeleteAllContrat()");			
				
				restControlEmploye.deleteAllContratJPQL();
				
				l.info("testDeleteAllContrat avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testDeleteAllContrat()!!"+ e);
			}
			finally{
				l.info("la méthode testDeleteAllContrat() est finie");
			}
	 }
	
	
}








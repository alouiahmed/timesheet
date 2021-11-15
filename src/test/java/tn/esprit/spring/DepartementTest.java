package tn.esprit.spring;

import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.controller.RestControlDepartement;
import tn.esprit.spring.entities.Departement;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class DepartementTest {
	
	
	private static final Logger l = LogManager.getLogger(DepartementTest.class);	
	
	@Autowired
	RestControlDepartement restControlDep;
	
	
	@Test
	public void testAjouterContrat()
	{
		Departement dep=new Departement("Dep dev");
		try {
			l.info("Tester l'ajout d'un departement");
			restControlDep.ajouterDept(dep);
			l.info("departement ajouté avec succés");
			
			l.info("Test nom not null");
			assertNotNull(dep.getName());
			
		}
		catch (Exception e)
		{ l.error(()->"Erreur dans testAjouterDepartement() : " + e); }
	}
	
	
	
	 @Test
	 public void testDeleteDepartementById(){		 
			try{
				l.debug("lancement testDeleteDepartementById()");			
				
				restControlDep.deleteDepartementById(9);
				
				l.info("departement supprimé avec succès");
			}
			catch(Exception e){
				l.error("Erreur dans la méthode testDeleteDepartementById()!!"+ e);
			}
			finally{
				l.info("la méthode testDeleteDepartementById() est finie");
			}
	 }
	 
	
	
}








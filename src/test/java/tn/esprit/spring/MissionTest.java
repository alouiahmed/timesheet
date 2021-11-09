package tn.esprit.spring;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionTest {
	
	@Autowired
	ITimesheetService service1;
	
	@Autowired
	IEntrepriseService service2;
	
	@Autowired
	DepartementRepository drep;
	
	@Autowired
	MissionRepository rep;
	
	private static final Logger l = LogManager.getLogger(MissionTest.class);	
	
	
	@Test
	public void testAjouterMission()
	{
		try {
			l.info("In testAjouterMission():");
			Mission mission=new Mission("testAjout", "testAjout");
			int id=service1.ajouterMission(mission);
			assertNotNull(id);
			assertTrue(mission.getName().length() >0);
			assertTrue(mission.getDescription().length() >0);
			ArrayList <Mission> liste1 =(ArrayList<Mission>) rep.findAll();
			int size1=liste1.size();
			l.info(()->"nombre de missions avant l'ajout: " +size1);
			l.info("Je vais ajouter une mission.");
			ArrayList <Mission> liste2 =(ArrayList<Mission>) rep.findAll();
			int size2=liste2.size();
			l.info(()->"nombre de missions apres l'ajout: " +size2);
			l.info("comparaison size avant et apres.");
			assertTrue(size2==size1+1);
			l.info("Out testAjouterEntreprise() sans erreurs.");
		}
		catch (Exception e)
		{ l.error(()->"Erreur dans testAjouterEntreprise() : " + e); }
	}
	
	
	@Test
	public void testAffecterMission()
	{
		try {
			l.info("In testAffecterMission():");
			l.info("Je vais creer un Departement.");
			Departement departement=new Departement("Ressources Humaines.");
			l.info("Je vais creer une mission.");
			Mission mission=new Mission("testAjout", "testAjout");
			l.info("Je vais ajouter le departement.");
			int ajouterdepartement=service2.ajouterDepartement(departement);
			l.info("Je vais ajouter la mission.");
			int d=service1.ajouterMission(mission);
			l.info("Je vais affecter la mission au departement.");
			service1.affecterMissionADepartement(d, ajouterdepartement);
			l.info("Je vais reprendre la mission depuis la base de donnée.");
			Mission findbyid=rep.findById(d).orElseThrow(RuntimeException::new);
			l.info("Je vais tester si le departement_id du mission est égale a l'id de departement auquel je l'ai affecté.");
			assertTrue(findbyid.getDepartement().getId()==ajouterdepartement);
			l.info("Out testAffecterDepartement() sans erreurs.");
		}catch (Exception e) { l.error(()-> "Erreur dans testAffecterDepartement() : " + e); }
	}}






















	
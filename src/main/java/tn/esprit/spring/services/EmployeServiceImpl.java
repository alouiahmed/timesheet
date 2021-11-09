package tn.esprit.spring.services;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	
	static Logger log = Logger.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Optional<Employe> employe = employeRepository.findById(employeId);
		if(employe.isPresent()) {
			employe.get().setEmail(email);
			employeRepository.save(employe.get());
		}
		

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

		if(depManagedEntity.isPresent() && employeManagedEntity.isPresent())
		{
			if(depManagedEntity.get().getEmployes() == null){

				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity.get());
				depManagedEntity.get().setEmployes(employes);
			}else{

				depManagedEntity.get().getEmployes().add(employeManagedEntity.get());
			}

			// à ajouter? 
			deptRepoistory.save(depManagedEntity.get()); 
		}
		

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Optional<Departement> dep = deptRepoistory.findById(depId);

		if (dep.isPresent()){
			int employeNb = dep.get().getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dep.get().getEmployes().get(index).getId() == employeId){
					dep.get().getEmployes().remove(index);
					break;//a revoir
				}
			}
		}
		
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		try {
			
		
		log.debug("Je vais  lancer l'ajout du contrat");
		contratRepoistory.save(contrat);
		log.info("contrat ajouté avec succés");
		return contrat.getReference();
		}
	catch(Exception e){
		log.error("Erreur ajout contrat!!"+ e);
		return 0;
	}
	finally{
		log.info("la méthode ajouterContrat() est finie");
	}}

	public void affecterContratAEmploye(int contratId, int employeId) {
		try {
			
		
		log.debug("Je vais  lancer l'affectation du contrat a l'employe");
		Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

		if (contratManagedEntity.isPresent() && employeManagedEntity.isPresent()) {
			contratManagedEntity.get().setEmploye(employeManagedEntity.get());
			contratRepoistory.save(contratManagedEntity.get());
			log.info("affectation du contrat a l'employe avec succés");
		}}
		catch(Exception e){
			log.error("Erreur affecterContratAEmploye!!"+ e);
		}
		finally{
			log.info("la méthode affecterContratAEmploye() est finie");
		}
		

	}

	public String getEmployePrenomById(int employeId) {
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		if (employeManagedEntity.isPresent()) {
			return employeManagedEntity.get().getPrenom();	
		}
		else
			return "il n'ya pas un employee sous cet id";
		
	}
	 
	public void deleteEmployeById(int employeId)
	{
		Optional<Employe> employe = employeRepository.findById(employeId);

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		if (employe.isPresent()) {
			for(Departement dep : employe.get().getDepartements()){
				dep.getEmployes().remove(employe.get());
			}

			employeRepository.delete(employe.get());
		}
		
	}

	public void deleteContratById(int contratId) {
		try {
			log.debug("Je vais  lancer la suppression du contrat");
			Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
			if (contratManagedEntity.isPresent())
			{
				contratRepoistory.delete(contratManagedEntity.get());
				log.info("contrat supprimé avec succés");	
			}
		}catch(Exception e){
			log.error("Erreur deleteContratById!!"+ e);
		}
		finally{
			log.info("la méthode deleteContratById() est finie");
		}
		
		

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		try {
			log.debug("Je vais  lancer la suppression de tous les contrats");
			employeRepository.deleteAllContratJPQL();
			log.info("tous les contrats sont supprimés avec succés");
		}catch(Exception e){
			log.error("Erreur deleteAllContratJPQL!!"+ e);
		}
		finally{
			log.info("la méthode deleteAllContratJPQL() est finie");
		}
		
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}

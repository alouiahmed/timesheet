package tn.esprit.spring.services;

import java.util.ArrayList;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
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
		try{
			l.debug("lancement de l'ajout ou update d'une employee");
			employeRepository.save(employe);	
			l.info("employee est ajouté ou update avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans l'ajout de la employee addOrUpdateEmploye()!!"+ e);
		}
		finally{
			l.info("la méthode addOrUpdateEmploye() est finie");
		}
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try{
			l.debug("lancement de mettreAjourEmailByEmployeId d'une employee");
			Optional<Employe> employeOPt = employeRepository.findById(employeId);

			if (employeOPt.isPresent() )
			{
				Employe employe=employeOPt.get();
				employe.setEmail(email);
				employeRepository.save(employe);	
			}
			l.info("employee est ajour avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans a jour de la employee !!"+ e);
		}
		finally{
			l.info("la méthode mettreAjourEmailByEmployeId() est finie");
		}
		
		

	

	}

	@Transactional	
	public void affecterEmployeADepartement(int employeId, int depId) {
		
		Optional<Employe> employeOPt = employeRepository.findById(employeId);
		Employe employeManagedEntity =null;
		if (employeOPt.isPresent() )
		{
			 employeManagedEntity=employeOPt.get();
		
		}
		
		Optional<Departement> depManagedEntityOPt = deptRepoistory.findById(depId);
		Departement depManagedEntity =null;
		if (depManagedEntityOPt.isPresent() )
		{
			depManagedEntity=depManagedEntityOPt.get();

			if(depManagedEntity.getEmployes() == null){

				List<Employe> employes = new ArrayList<>();
				employes.add(employeManagedEntity);
				depManagedEntity.setEmployes(employes);
			}else{

				depManagedEntity.getEmployes().add(employeManagedEntity);
			}

			// à ajouter? 
			deptRepoistory.save(depManagedEntity); 
		}
	

	}
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		
		Optional<Departement> depManagedEntityOPt = deptRepoistory.findById(depId);
		Departement dep =null;
		if (depManagedEntityOPt.isPresent() )
		{
			dep=depManagedEntityOPt.get();
			int employeNb = dep.getEmployes().size();
			for(int index = 0; index < employeNb; index++){
				if(dep.getEmployes().get(index).getId() == employeId){
					dep.getEmployes().remove(index);
					break;//a revoir
				}
			}
		
		}
	
		
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
	
		Optional<Contrat> ContratOPt = contratRepoistory.findById(contratId);
		Contrat contratManagedEntity =null;
		if (ContratOPt.isPresent() )
		{
			contratManagedEntity=ContratOPt.get();
		
		}
		Optional<Employe> employeOPt = employeRepository.findById(employeId);
		Employe employeManagedEntity =null;
		if (employeOPt.isPresent() )
		{
			employeManagedEntity=employeOPt.get();
		
		}
		
		if (employeManagedEntity != null & contratManagedEntity != null)
		{
			
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
		}
	

	}

	public String getEmployePrenomById(int employeId) {
	
		Optional<Employe> employeOPt = employeRepository.findById(employeId);
		Employe employeManagedEntity =null;
		if (employeOPt.isPresent() )
		{
			employeManagedEntity=employeOPt.get();
			return employeManagedEntity.getPrenom();
		}
		return null;
	}
	 
	public void deleteEmployeById(int employeId)
	{
		
		Optional<Employe> employeOPt = employeRepository.findById(employeId);
		Employe employe =null;
		if (employeOPt.isPresent() )
		{
			employe=employeOPt.get();
			//Desaffecter l'employe de tous les departements
			//c'est le bout master qui permet de mettre a jour
			//la table d'association
			for(Departement dep : employe.getDepartements()){
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
		
		}
		
	}

	public void deleteContratById(int contratId) {
		try{
			l.debug("lancement de deleteContratById");
					Optional<Contrat> ContratOPt = contratRepoistory.findById(contratId);
			Contrat contratManagedEntity =null;
			if (ContratOPt.isPresent() )
			{
				contratManagedEntity=ContratOPt.get();
			
			}
			contratRepoistory.delete(contratManagedEntity);
			l.info("deleteContratById avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans deleteContratById !!"+ e);
		}
		finally{
			l.info("la méthode deleteContratById() est finie");
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
		try{
			l.debug("lancement de Delete ou update d'une employee");
			employeRepository.deleteAllContratJPQL();
			l.info("all contrat est ajouté ou update avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans deleteAllContratJPQL()!!"+ e);
		}
		finally{
			l.info("la méthode deleteAllContratJPQL() est finie");
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

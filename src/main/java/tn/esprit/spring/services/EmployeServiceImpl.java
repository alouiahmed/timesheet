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

@Service
public class EmployeServiceImpl implements IEmployeService {

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
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);


		if (contratManagedEntity.isPresent() && employeManagedEntity.isPresent()) {
			contratManagedEntity.get().setEmploye(employeManagedEntity.get());
			contratRepoistory.save(contratManagedEntity.get());
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
		Optional<Contrat> contratManagedEntity = contratRepoistory.findById(contratId);
		if (contratManagedEntity.isPresent()){
		contratRepoistory.delete(contratManagedEntity.get());
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
		employeRepository.deleteAllContratJPQL();
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

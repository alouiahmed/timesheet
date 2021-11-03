package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

	
	public int ajouterEntreprise(Entreprise entreprise) {
		try{
			l.debug("Je vais  lancer lancer l'ajout de l'entreprise");
			entrepriseRepoistory.save(entreprise);
			l.info("Entreprise est ajouté avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans l'ajout de l'entreprise ajouterEntreprise()!!"+ e);
		}
		finally{
			l.info("la méthode ajouterEntreprise() est finie");
		}
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		try{
			l.debug("Je vais  lancer lancer l'ajout d'une département");
			deptRepoistory.save(dep);
			l.info("Département est ajoutée avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans la méthode ajouterDepartement()!!"+ e);
		}
		finally{
			l.info("la méthode ajouterDepartement() est finie");
		}
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
		//donc il faut rajouter l'entreprise a departement 
		// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
		//Rappel : la classe qui contient mappedBy represente le bout Slave
		//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.

		try{
			l.debug("Je vais  lancer affecterDepartementAEntreprise");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			Departement depManagedEntity = deptRepoistory.findById(depId).get();
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(depManagedEntity);
			
			l.info("Département est affectée à une entreprise avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans la méthode affecterDepartementAEntreprise()!!"+ e);
		}
		finally{
			l.info("la méthode affecterDepartementAEntreprise() est finie");
		}

				

		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		List<String> depNames = new ArrayList<>();
		try{
			l.debug("Je vais lancer getAllDepartementsNamesByEntreprise()");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
				depNames.add(dep.getName());
			}
			
			l.info("la méthode getAllDepartementsNamesByEntreprise est réalisé avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans la méthode getAllDepartementsNamesByEntreprise()!!"+ e);
		}
		finally{
			l.info("la méthode getAllDepartementsNamesByEntreprise() est finie");
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		try{
			l.debug("lancement deleteEntrepriseById()");			
			
			entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
			
			l.info("la méthode deleteEntrepriseById() est réalisée avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans la méthode deleteEntrepriseById()!!"+ e);
		}
		finally{
			l.info("la méthode deleteEntrepriseById() est finie");
		}
		
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		try{
			l.debug("lancement deleteDepartementById()");			
			
			deptRepoistory.delete(deptRepoistory.findById(depId).get());	

			l.info("la méthode deleteDepartementById() est réalisée avec succès");
		}
		catch(Exception e){
			l.error("Erreur dans la méthode deleteDepartementById()!!"+ e);
		}
		finally{
			l.info("la méthode deleteDepartementById() est finie");
		}
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		Entreprise entreprise = null ;
		try{
			l.debug("lancement getEntrepriseById()");			
			entreprise = entrepriseRepoistory.findById(entrepriseId).get();
			l.info("la méthode getEntrepriseById() est réalisée avec succès");

		}
		catch(Exception e){
			l.error("Erreur dans la méthode getEntrepriseById()!!"+ e);
		}
		finally{
			l.info("la méthode getEntrepriseById() est finie");
		}
		return entreprise;
	}

}

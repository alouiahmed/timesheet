package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

@Service
public class DepartementServiceImpl implements IDepartementService {


	@Autowired
	DepartementRepository deptRepoistory;


	public List<Departement> getAllDepartements() {
		return (List<Departement>) deptRepoistory.findAll();
	}

	@Override
	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}

	@Override
	public void deleteDepartementById(int depId) {
		Optional<Departement> depatementManagedEntity = deptRepoistory.findById(depId);
		
		if (depatementManagedEntity.isPresent()){
			deptRepoistory.delete(depatementManagedEntity.get());
		}
			
		
	}

	@Override
	public Departement getDepBytId(int depId) {
		Optional<Departement> depatementManagedEntity = deptRepoistory.findById(depId);
		if (depatementManagedEntity.isPresent()){
			return depatementManagedEntity.get();	
		}
		return depatementManagedEntity.get();	

	}

}

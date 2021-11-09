package tn.esprit.spring.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	
	static Logger log = Logger.getLogger(ContratServiceImpl.class);


	public List<Contrat> getAllContrats() {
		log.debug("Je vais  afficher la liste des contrats");
		return (List<Contrat>) contratRepository.findAll();
	}

}

package apap.tugaspraktikum.sipes.service;

import java.util.List;

import apap.tugaspraktikum.sipes.model.*;

public interface PenerbanganService {
	
	List<PenerbanganModel> getAllPenerbangan();
	
	PenerbanganModel getPenerbanganById(Long idPenerbangan);
	
	void addPenerbangan(PenerbanganModel penerbangan);
	
	PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);
	
	Boolean deletePenerbanganById(Long idPenerbangan);
	
	

}

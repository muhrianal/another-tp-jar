package apap.tugaspraktikum.sipes.service;

import apap.tugaspraktikum.sipes.model.*;
import apap.tugaspraktikum.sipes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {

	@Autowired
	PenerbanganDb penerbanganDb;
	
	@Override
	public List<PenerbanganModel> getAllPenerbangan(){
		return penerbanganDb.findAll();
	}
	
	@Override
	public PenerbanganModel getPenerbanganById(Long idPenerbangan) {
		return penerbanganDb.findById(idPenerbangan).get();
	}
	
	@Override
	public 	void addPenerbangan(PenerbanganModel penerbangan) {
		penerbanganDb.save(penerbangan);
	}
	
	@Override
	public 	PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {
		PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getIdPenerbangan()).get();
		
		try {
			targetPenerbangan.setNomorPenerbangan(penerbangan.getNomorPenerbangan());
			targetPenerbangan.setKodeBandaraAsal(penerbangan.getKodeBandaraAsal());
			targetPenerbangan.setKodeBandaraTujuan(penerbangan.getKodeBandaraTujuan());
			targetPenerbangan.setWaktuBerangkat(penerbangan.getWaktuBerangkat());
			
			penerbanganDb.save(targetPenerbangan);
			return targetPenerbangan;
		} catch (Exception e) {
			return null;
		}
	
	}
	
	@Override
	public Boolean deletePenerbanganById(Long idPenerbangan) {
		PenerbanganModel penerbangan = penerbanganDb.findById(idPenerbangan).get();
		if (penerbangan != null) {
			penerbanganDb.deleteById(idPenerbangan);
			return true;
		} return false;
	}
}

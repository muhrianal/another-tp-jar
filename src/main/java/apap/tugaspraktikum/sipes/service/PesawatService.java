package apap.tugaspraktikum.sipes.service;

import java.util.Date;
import java.util.List;

import apap.tugaspraktikum.sipes.model.*;
import apap.tugaspraktikum.sipes.model.TipeModel;

public interface PesawatService {
//	String generateNoSeri(int jenisPesawat, String tipePesawat, 
//			Date tanggalDi) 
	List<PesawatModel> getAllPesawat();
	
	void addPesawat(PesawatModel pesawat);
	
	String getNomorSeri(String jenisPesawat, TipeModel tipe, 
			Date tanggalDibuat);
	
	PesawatModel getPesawatById(Long id);
	
	PesawatModel updatePesawat(PesawatModel pesawat);
	
//	List<PesawatModel> getPesawatByPenerbanganTipeTeknisi(
//			Long idPenerbangan, Long idTipe, Long idTeknisi);
	
	List<PesawatModel> getPesawatMoreThanTenYears();
	
	PesawatModel assignPenerbangan(PenerbanganModel penerbangan, Long idPesawat);
	
	List<String> getUsiaPesawat(List<PesawatModel> listPesawat);
	
	List<PesawatModel> getPesawatTeknisiByIdTeknisi(Long idTeknisi);
	
	List<PesawatModel> filterPesawat(Long idTeknisi, Long idPenerbangan, Long idTipe);
	
	List<Integer> getListJumlahTeknisi(List<PesawatModel> listPesawat);
}


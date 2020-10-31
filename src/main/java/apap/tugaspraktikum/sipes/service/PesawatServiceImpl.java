package apap.tugaspraktikum.sipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tugaspraktikum.sipes.repository.*;
import apap.tugaspraktikum.sipes.model.*;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
	
	@Autowired
	PesawatDb pesawatDb;
	@Autowired
	PenerbanganDb penerbanganDb;
	
	@Override
	public List<PesawatModel> getAllPesawat(){
		
		return pesawatDb.findAll();
		
	}
	
	@Override
	public 	void addPesawat(PesawatModel pesawat) {
		pesawat.setNomorSeri(getNomorSeri(pesawat.getJenisPesawat(), pesawat.getTipe(), pesawat.getTanggalDibuat()));
		pesawatDb.save(pesawat);
	}
	
	@Override
	public PesawatModel getPesawatById(Long id) {
		return pesawatDb.findById(id).get();
	}
	
	@Override
	public String getNomorSeri(String jenisPesawat, TipeModel tipe, 
			Date tanggalDibuat) {
		
		StringBuilder sb = new StringBuilder(String.valueOf(tanggalDibuat.getYear() + 1900));
		
		String first = jenisPesawat;
		String second = "XX";
		
		switch(tipe.getNamaTipe()) {
			case "Boeing": second = "BO"; break;
			case "ATR": second = "AT"; break;
			case "Airbus": second = "AB"; break;
			case "Bombardier": second = "BB"; break;
		}
		
		String third = sb.reverse().toString();
		String fourth = String.valueOf(tanggalDibuat.getYear()+1908);
		Random rd = new Random();
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		char letterOne = abc.charAt(rd.nextInt(abc.length()));
		char letterTwo = abc.charAt(rd.nextInt(abc.length()));
		
		return first + second + third + fourth + letterOne + letterTwo;
		
	}
	
	
	@Override
	public PesawatModel updatePesawat(PesawatModel pesawat) {
		PesawatModel targetPesawat = pesawatDb.findById(pesawat.getIdPesawat()).get();
		
		try {
			targetPesawat.setMaskapai(pesawat.getMaskapai());
			targetPesawat.setTanggalDibuat(pesawat.getTanggalDibuat());
			targetPesawat.setTempatDibuat(pesawat.getTempatDibuat());
			targetPesawat.setJenisPesawat(pesawat.getJenisPesawat());
			targetPesawat.setNomorSeri(getNomorSeri(pesawat.getJenisPesawat(), targetPesawat.getTipe(), pesawat.getTanggalDibuat()));
			
			pesawatDb.save(targetPesawat);
			
			return targetPesawat;
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	@Override
	public 	PesawatModel assignPenerbangan(PenerbanganModel penerbangan, Long idPesawat) {
		PesawatModel targetPesawat = pesawatDb.findById(idPesawat).get();
		PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getIdPenerbangan()).get();
		
		try {
			targetPesawat.getListPenerbangan().add(penerbangan);
			targetPenerbangan.setPesawat(targetPesawat);
			pesawatDb.save(targetPesawat);
			penerbanganDb.save(targetPenerbangan);
			return targetPesawat;
		} catch(Exception e) {
			return null;
		}
		
	}
	
	
	@Override
	public 	List<String> getUsiaPesawat(List<PesawatModel> listPesawat){
		List<String> listUsia = new ArrayList<>();
		
		Date date = new Date();
		
		for (PesawatModel pesawat: listPesawat) {
			listUsia.add(date.getYear() - pesawat.getTanggalDibuat().getYear() + " tahun");
			
		}
		
		return listUsia;
	}
	
	
	@Override
	public 	List<PesawatModel> getPesawatMoreThanTenYears(){
		Date date = new Date();
		date.setYear(date.getYear() - 10);
		return pesawatDb.findByTanggalDibuatBefore(date);
	}
	
	//test filter
	
	@Override
	public List<PesawatModel> getPesawatTeknisiByIdTeknisi(Long idTeknisi){
		return pesawatDb.findByListTeknisi_IdTeknisi(idTeknisi);
	}
	
	
	// filter beneran
	@Override
	public List<PesawatModel> filterPesawat(Long idTeknisi, Long idPenerbangan, Long idTipe){
		if (idTeknisi == null && idPenerbangan == null && idTipe == null) return pesawatDb.findAll();
		else if(idTeknisi == null && idPenerbangan == null) return pesawatDb.findByTipe_IdTipe(idTipe);
		else if (idPenerbangan == null && idTipe == null) return pesawatDb.findByListTeknisi_IdTeknisi(idTeknisi);
		else if (idTeknisi == null && idTipe == null) return pesawatDb.findByListPenerbangan_IdPenerbangan(idPenerbangan);
		else if (idTipe == null) pesawatDb.findByListTeknisi_IdTeknisiAndListPenerbangan_IdPenerbangan(idTeknisi, idPenerbangan);
		else if (idTeknisi == null) pesawatDb.findByListPenerbangan_IdPenerbanganAndTipe_IdTipe(idPenerbangan, idTipe);
		return pesawatDb.findByListTeknisi_IdTeknisiAndTipe_idTipe(idTeknisi, idTipe);
	}
	
	@Override
	public 	List<Integer> getListJumlahTeknisi(List<PesawatModel> listPesawat){
		List<Integer> result = new ArrayList<>();
		
		for (PesawatModel pesawat: listPesawat) {
			result.add(pesawat.getListTeknisi().size());
		}
		
		return result;
	}
	
	
}
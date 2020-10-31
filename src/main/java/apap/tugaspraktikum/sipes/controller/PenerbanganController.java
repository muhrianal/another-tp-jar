package apap.tugaspraktikum.sipes.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugaspraktikum.sipes.model.PenerbanganModel;
import apap.tugaspraktikum.sipes.service.PenerbanganService;

import java.time.LocalTime;

@Controller
public class PenerbanganController {
	
	@Autowired
	private PenerbanganService penerbanganService;
	
	@RequestMapping("/penerbangan")
	public String viewAllPenerbangan(Model model) {
		List<PenerbanganModel> listPenerbangan = penerbanganService.getAllPenerbangan();
		model.addAttribute("listPenerbangan", listPenerbangan);
		
		return "view-all-penerbangan";
	}
	
	@GetMapping("/penerbangan/{idPenerbangan}")
	public String viewDetailPenerbangan(  
			@PathVariable Long idPenerbangan,
			Model model
	) {
		PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
		model.addAttribute("penerbangan", penerbangan);
		
		String nomorSeri = penerbangan.getPesawat() != null ? penerbangan.getPesawat().getNomorSeri() : "Belum di-assign pesawat";
		
		model.addAttribute("nomorSeri", nomorSeri);
		
		return "view-detail-penerbangan";
	}
		
	@GetMapping("/penerbangan/tambah")
	public String addPenerbanganFormPage(Model model) {
		model.addAttribute("penerbangan", new PenerbanganModel());
		
		return "form-add-penerbangan";
	}
	
	@PostMapping("/penerbangan/tambah")
	public String addPenerbanganSubmit(  
			@ModelAttribute PenerbanganModel penerbangan,
			Model model
	) {
		penerbanganService.addPenerbangan(penerbangan);
		model.addAttribute("kodePenerbangan", penerbangan.getNomorPenerbangan());
		
		return "add-penerbangan-success";
		
	}
	
	@GetMapping("/penerbangan/ubah/{idPenerbangan}")
	public String ubahPenerbanganFormPage( 
			@PathVariable Long idPenerbangan,
			Model model
	) {
		PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
		model.addAttribute("penerbangan", penerbangan);
		
		return "form-ubah-penerbangan";
	}
	
	@PostMapping("/penerbangan/ubah")
	public String ubahPenerbanganSubmit(
			@ModelAttribute PenerbanganModel penerbangan,
			Model model
	) {
		penerbanganService.updatePenerbangan(penerbangan);
		model.addAttribute("kodePenerbangan", penerbangan.getNomorPenerbangan());
		return "ubah-penerbangan-success";
	}
	
	@GetMapping("/penerbangan/hapus/{idPenerbangan}")
	public String hapusPenerbangan( 
			@PathVariable Long idPenerbangan,
			Model model
	) {
		try {
			PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
			Boolean condition = penerbanganService.deletePenerbanganById(idPenerbangan);
			
			model.addAttribute("nomorPenerbangan", penerbangan.getNomorPenerbangan());
			if (condition) return "success-hapus-penerbangan";
			return "error-hapus-penerbangan";
			
		}catch (Exception e) {
			return "error-hapus-penerbangan";
		}
	}
	
}

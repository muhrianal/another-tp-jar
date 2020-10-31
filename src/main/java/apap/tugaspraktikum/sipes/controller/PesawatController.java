package apap.tugaspraktikum.sipes.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import apap.tugaspraktikum.sipes.service.*;

import apap.tugaspraktikum.sipes.model.*;

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

import java.time.LocalTime;

@Controller
public class PesawatController {
	
	@Autowired
	private PesawatService pesawatService;
	
	@Autowired 
	private TipeService tipeService;

	@Autowired 
	private TeknisiService teknisiService;
	
	@Autowired
	private PenerbanganService penerbanganService;
	
	@GetMapping("/")
	private String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/pesawat")
	public String viewAllPesawat(Model model) {
		List<PesawatModel> listPesawat = pesawatService.getAllPesawat();
		
		model.addAttribute("listPesawat", listPesawat);
		
		return "view-all-pesawat";
	}
	
	@RequestMapping("/pesawat/pesawat-tua")
	public String viewPesawatTua(Model model) {
		List<PesawatModel> listPesawat = pesawatService.getPesawatMoreThanTenYears();
		List<String> listUsia = pesawatService.getUsiaPesawat(listPesawat);
		
		model.addAttribute("listPesawat", listPesawat);
		model.addAttribute("listUsia", listUsia);
		
		return "view-pesawat-tua";
	}
	
	
	@GetMapping("/pesawat/tambah")
	public String addPesawatFormPage(Model model) {
		
		PesawatModel dummyPesawat = new PesawatModel();
		
		List<TeknisiModel> dummyTeknisiList= new ArrayList<>();
		
		dummyTeknisiList.add(new TeknisiModel());
		
		dummyPesawat.setListTeknisi(dummyTeknisiList);
		
		model.addAttribute("pesawat", dummyPesawat);
		model.addAttribute("listTipe", tipeService.getAllTipe());
		model.addAttribute("listAllTeknisi", teknisiService.getAllTeknisi());
		return "form-add-pesawat";
	}
	
	@PostMapping(value="/pesawat/tambah/", params="submit")
	public String addPesawatSubmit(
			@ModelAttribute PesawatModel pesawat,
			Model model
	) {
		
		
		pesawatService.addPesawat(pesawat);
		model.addAttribute("nomorSeri", pesawat.getNomorSeri());
		model.addAttribute("namaPesawat", pesawat.getMaskapai());
		
		return "add-pesawat-success";
	}
	
	
	// add teknisi in add pesawat
	@PostMapping(value="/pesawat/tambah", params="addRow")
	public String addTeknisiInAddPesawat(
			@ModelAttribute PesawatModel pesawat,
			Model model
	
	) {
		pesawat.getListTeknisi().add(new TeknisiModel());
		model.addAttribute("pesawat", pesawat);
		model.addAttribute("listTipe", tipeService.getAllTipe());
		model.addAttribute("listAllTeknisi", teknisiService.getAllTeknisi());
		return "form-add-pesawat";
		
	}
	
	@GetMapping("/pesawat/{idPesawat}")
	public String viewPesawat( 
			@PathVariable Long idPesawat,
			Model model
	) {
		PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
		
		model.addAttribute("pesawat", pesawat);
		model.addAttribute("listTeknisi", pesawat.getListTeknisi());
		model.addAttribute("listPenerbangan", pesawat.getListPenerbangan());
		
		model.addAttribute("response", "");
		
		List<PenerbanganModel> availablePenerbangan = penerbanganService.getAllPenerbangan();
		
		model.addAttribute("allAvailablePenerbangan", availablePenerbangan);
		
		
		return "assign-penerbangan";
		
	}
	
	@GetMapping("/pesawat/ubah/{idPesawat}")
	public String ubahPesawatFormPage( 
			@PathVariable Long idPesawat,
			Model model
			
	){ 
		PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
		model.addAttribute("pesawat", pesawat);
		
		return "form-ubah-pesawat";
	}
	
	
	@PostMapping("/pesawat/ubah")
	public String ubahPesawatSubmit(  
			@ModelAttribute PesawatModel pesawat,
			Model model
			
	) {
		pesawat = pesawatService.updatePesawat(pesawat);
		model.addAttribute("pesawat", pesawat);
		
		return "ubah-pesawat-success";
	}
	
	@GetMapping("/pesawat/{idPesawat}/tambah-penerbangan")
	public String assignPenerbangan(
			@PathVariable Long idPesawat,
			Model model
	) {
		PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
		
		model.addAttribute("pesawat", pesawat);
		model.addAttribute("listTeknisi", pesawat.getListTeknisi());
		model.addAttribute("listPenerbangan", pesawat.getListPenerbangan());
		
		model.addAttribute("response", "");
		
		List<PenerbanganModel> availablePenerbangan = penerbanganService.getAllPenerbangan();
		
		model.addAttribute("allAvailablePenerbangan", availablePenerbangan);
		
		
		return "assign-penerbangan";
	}
	
	
	
	@PostMapping("/pesawat/{idPesawat}/tambah-penerbangan")
	public String assignPenerbanganSubmit(
			@ModelAttribute PesawatModel pesawat,
			Model model
	) {
		String nomorPenerbangan = pesawat.getListPenerbangan().get(0).getNomorPenerbangan();
		PesawatModel updatedPesawat = pesawatService.assignPenerbangan(pesawat.getListPenerbangan().get(0), pesawat.getIdPesawat());
	
		model.addAttribute("pesawat", updatedPesawat);
		
		model.addAttribute("listTeknisi", updatedPesawat.getListTeknisi());
		model.addAttribute("listPenerbangan", updatedPesawat.getListPenerbangan());
		
		model.addAttribute("response", "Penerbangan dengan nomor " + nomorPenerbangan + " berhasil ditambahkan !");
		
		List<PenerbanganModel> availablePenerbangan = penerbanganService.getAllPenerbangan();
		
		model.addAttribute("allAvailablePenerbangan", availablePenerbangan);
		
		
		return "assign-penerbangan";
		
	}
	
	
	
	
	@GetMapping("/pesawat/filter")
	public String filterPesawat(
			@RequestParam(required = false) Long idPenerbangan,
			@RequestParam(required = false) Long idTipe,
			@RequestParam(required = false) Long idTeknisi,
			Model model
			
	){	
		List<PenerbanganModel> listPenerbangan = penerbanganService.getAllPenerbangan();
		List<TipeModel> listTipe = tipeService.getAllTipe();
		List<TeknisiModel> listTeknisi = teknisiService.getAllTeknisi();
		
		List<PesawatModel> listPesawat = pesawatService.filterPesawat(idTeknisi, idPenerbangan, idTipe);
		
		
		model.addAttribute("listPenerbangan", listPenerbangan);
		model.addAttribute("listTipe", listTipe);
		model.addAttribute("listTeknisi", listTeknisi);
		model.addAttribute("listPesawat", listPesawat);
		
		return "filter-pesawat";
	}
	
	@RequestMapping("/pesawat/jumlah-teknisi")
	public String viewJumlahTeknisi(Model model) {
		List<PesawatModel> listPesawat = pesawatService.getAllPesawat();
		
		List<Integer> listJumlahTeknisi = pesawatService.getListJumlahTeknisi(listPesawat);
		
		model.addAttribute("listPesawat", listPesawat);
		model.addAttribute("listJumlahTeknisi", listJumlahTeknisi);
		
		return "view-jumlah-teknisi";
	}
	
	

}
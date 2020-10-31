package apap.tugaspraktikum.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pesawat")
public class PesawatModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPesawat;
	
	@NotNull
	@Size(max=255)
	@Column(name="maskapai", nullable=false)
	private String maskapai;
	
	@NotNull
	@Size(max=255)
	@Column(name="nomorSeri", unique=true, nullable=false)
	private String nomorSeri;
	
	@NotNull
	@Size(max=255)
	@Column(name="tempatDibuat", nullable=false)
	private String tempatDibuat;
	
	@NotNull
	@Column(name="tanggalDibuat")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date tanggalDibuat;
	
	@NotNull
	@Size(max=255)
	@Column(name="jenisPesawat", nullable=false)
	private String jenisPesawat;
	
	@OneToMany(mappedBy = "pesawat", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PenerbanganModel> listPenerbangan;
	
	@ManyToMany
	@JoinTable(
	  name = "teknisiPesawat", 
	  joinColumns = @JoinColumn(name = "idPesawat"), 
	  inverseJoinColumns = @JoinColumn(name = "idTeknisi"))
	
	List<TeknisiModel> listTeknisi;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "tipeId", referencedColumnName = "idTipe", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private TipeModel tipe;

	public Long getIdPesawat() {
		return idPesawat;
	}

	public void setIdPesawat(Long idPesawat) {
		this.idPesawat = idPesawat;
	}

	public String getMaskapai() {
		return maskapai;
	}

	public void setMaskapai(String maskapai) {
		this.maskapai = maskapai;
	}

	public String getNomorSeri() {
		return nomorSeri;
	}

	public void setNomorSeri(String nomorSeri) {
		this.nomorSeri = nomorSeri;
	}

	public String getTempatDibuat() {
		return tempatDibuat;
	}

	public void setTempatDibuat(String tempatDibuat) {
		this.tempatDibuat = tempatDibuat;
	}

	public Date getTanggalDibuat() {
		return tanggalDibuat;
	}

	public void setTanggalDibuat(Date tanggalDibuat) {
		this.tanggalDibuat = tanggalDibuat;
	}

	public String getJenisPesawat() {
		return jenisPesawat;
	}

	public void setJenisPesawat(String jenisPesawat) {
		this.jenisPesawat = jenisPesawat;
	}

	public List<PenerbanganModel> getListPenerbangan() {
		return listPenerbangan;
	}

	public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
		this.listPenerbangan = listPenerbangan;
	}

	public List<TeknisiModel> getListTeknisi() {
		return listTeknisi;
	}

	public void setListTeknisi(List<TeknisiModel> listTeknisi) {
		this.listTeknisi = listTeknisi;
	}

	public TipeModel getTipe() {
		return tipe;
	}

	public void setTipe(TipeModel tipe) {
		this.tipe = tipe;
	}
	
	@Override
	public String toString() {
		return this.maskapai;
	}
	
	
}

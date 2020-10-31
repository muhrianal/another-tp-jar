package apap.tugaspraktikum.sipes.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;


@Entity
@Table(name="tipe")
public class TipeModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipe;
	
    @OneToMany(mappedBy = "tipe", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PesawatModel> listPesawat;
    
    @NotNull
	@Size(max=255)
	@Column(name="namaTipe", nullable=false)
	private String namaTipe;
    
    @NotNull
	@Size(max=255)
	@Column(name="deskripsiTipe", nullable=false)
	private String deskripsiTipe;

	public Long getIdTipe() {
		return idTipe;
	}

	public void setIdTipe(Long idTipe) {
		this.idTipe = idTipe;
	}

	public List<PesawatModel> getListPesawat() {
		return listPesawat;
	}

	public void setListPesawat(List<PesawatModel> listPesawat) {
		this.listPesawat = listPesawat;
	}

	public String getNamaTipe() {
		return namaTipe;
	}

	public void setNamaTipe(String namaTipe) {
		this.namaTipe = namaTipe;
	}

	public String getDeskripsiTipe() {
		return deskripsiTipe;
	}

	public void setDeskripsiTipe(String deskripsiTipe) {
		this.deskripsiTipe = deskripsiTipe;
	}

    
}

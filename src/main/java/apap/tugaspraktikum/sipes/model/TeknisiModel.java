package apap.tugaspraktikum.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="teknisi")
public class TeknisiModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTeknisi;
	
	@NotNull
	@Size(max=255)
	@Column(name="namaTeknisi", nullable=false)
	private String namaTeknisi;
	
	@NotNull
	@Column(name="nomorTelepon", nullable=false)
	private Long nomorTelepon;
	
	@ManyToMany(mappedBy = "listTeknisi")
	List<PesawatModel> listPesawat;

	public Long getIdTeknisi() {
		return idTeknisi;
	}

	public void setIdTeknisi(Long idTeknisi) {
		this.idTeknisi = idTeknisi;
	}

	public String getNamaTeknisi() {
		return namaTeknisi;
	}

	public void setNamaTeknisi(String namaTeknisi) {
		this.namaTeknisi = namaTeknisi;
	}

	public Long getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(Long nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}

	public List<PesawatModel> getListPesawat() {
		return listPesawat;
	}

	public void setListPesawat(List<PesawatModel> listPesawat) {
		this.listPesawat = listPesawat;
	}
	
	@Override
	public String toString() {
		return this.namaTeknisi;
	}
	
}

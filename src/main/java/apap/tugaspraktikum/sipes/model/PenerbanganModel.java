package apap.tugaspraktikum.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Date;
import java.io.Serializable;

@Entity
@Table(name="penerbangan")
public class PenerbanganModel implements Serializable{
	public Long getIdPenerbangan() {
		return idPenerbangan;
	}

	public void setIdPenerbangan(Long idPenerbangan) {
		this.idPenerbangan = idPenerbangan;
	}

	public String getKodeBandaraAsal() {
		return kodeBandaraAsal;
	}

	public void setKodeBandaraAsal(String kodeBandaraAsal) {
		this.kodeBandaraAsal = kodeBandaraAsal;
	}

	public String getKodeBandaraTujuan() {
		return kodeBandaraTujuan;
	}

	public void setKodeBandaraTujuan(String kodeBandaraTujuan) {
		this.kodeBandaraTujuan = kodeBandaraTujuan;
	}

	public Date getWaktuBerangkat() {
		return waktuBerangkat;
	}

	public void setWaktuBerangkat(Date waktuBerangkat) {
		this.waktuBerangkat = waktuBerangkat;
	}

	public String getNomorPenerbangan() {
		return nomorPenerbangan;
	}

	public void setNomorPenerbangan(String nomorPenerbangan) {
		this.nomorPenerbangan = nomorPenerbangan;
	}

	public PesawatModel getPesawat() {
		return pesawat;
	}

	public void setPesawat(PesawatModel pesawat) {
		this.pesawat = pesawat;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPenerbangan;
	
	@NotNull
	@Size(max=255)
	@Column(name="kodeBandaraAsal", nullable=false)
	private String kodeBandaraAsal;
	
	@NotNull
	@Size(max=255)
	@Column(name="kodeBandaraTujuan", nullable=false)
	private String kodeBandaraTujuan;
	
	@NotNull
	@Column(name="waktuBerangkat")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date waktuBerangkat;
	
	
	@NotNull
	@Size(max=255)
	@Column(name="nomorPenerbangan", nullable=false, unique=true)
	private String nomorPenerbangan;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name= "pesawatId", referencedColumnName = "idPesawat", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private PesawatModel pesawat;
	
	public String toString() {
		return this.nomorPenerbangan;
	}
	
}

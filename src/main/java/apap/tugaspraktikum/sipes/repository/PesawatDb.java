package apap.tugaspraktikum.sipes.repository;


import apap.tugaspraktikum.sipes.model.PesawatModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long>{
	Optional<PesawatModel> findById(Long id);
	
	List<PesawatModel> findByTanggalDibuatBefore(Date todayTenYearsAgo);
	
	List<PesawatModel> findByListTeknisi_IdTeknisi(Long idTeknisi);
	
	List<PesawatModel> findByTipe_IdTipe(Long idTipe);
	
	List<PesawatModel> findByListPenerbangan_IdPenerbangan(Long idPenerbangan);
	
	List<PesawatModel> findByListTeknisi_IdTeknisiAndTipe_idTipe(Long idTeknisi, Long idTipe);
	
	List<PesawatModel> findByListTeknisi_IdTeknisiAndListPenerbangan_IdPenerbangan(Long idTeknisi, Long idPenerbangan);

	List<PesawatModel> findByListPenerbangan_IdPenerbanganAndTipe_IdTipe(Long idPenerbangan, Long idTipe);
	
	List<PesawatModel> findByListTeknisi_IdTeknisiAndListPenerbangan_IdPenerbanganAndTipe_IdTipe(Long idTeknisi, Long idPenerbangan, Long idTipe);

}

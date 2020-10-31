package apap.tugaspraktikum.sipes.repository;


import apap.tugaspraktikum.sipes.model.PenerbanganModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenerbanganDb extends JpaRepository<PenerbanganModel, Long>{
	Optional<PenerbanganModel> findById(Long idPenerbangan);
	

}

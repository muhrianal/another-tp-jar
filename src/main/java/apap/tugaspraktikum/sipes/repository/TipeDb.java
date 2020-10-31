package apap.tugaspraktikum.sipes.repository;

import apap.tugaspraktikum.sipes.model.TipeModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long> {
	Optional<TipeModel> findById(Long idTipe);

}

package apap.tugaspraktikum.sipes.repository;

import apap.tugaspraktikum.sipes.model.TeknisiModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long> {

	Optional<TeknisiModel> findById(Long idTeknisi);
}

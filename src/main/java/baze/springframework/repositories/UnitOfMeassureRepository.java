package baze.springframework.repositories;

import baze.springframework.domain.UnitOfMeassure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeassureRepository extends CrudRepository<UnitOfMeassure, Long> {

    Optional<UnitOfMeassure> findByDescription(String description);

}

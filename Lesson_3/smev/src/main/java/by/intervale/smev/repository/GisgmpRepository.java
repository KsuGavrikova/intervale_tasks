package by.intervale.smev.repository;

import by.intervale.smev.model.ResponseGisgmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GisgmpRepository extends
        JpaRepository<ResponseGisgmp, UUID> {
    Optional<ResponseGisgmp> getByStsInn(String stsInn);
}

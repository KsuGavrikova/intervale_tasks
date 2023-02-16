package by.intervale.smev.repository;

import by.intervale.smev.model.ResponseGisgmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GisgmpRepository extends
        JpaRepository<ResponseGisgmp, Long> {
    Optional<ResponseGisgmp> getByStsInn(String stsInn);
}

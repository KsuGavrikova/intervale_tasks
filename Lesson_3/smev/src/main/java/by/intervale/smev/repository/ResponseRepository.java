package by.intervale.smev.repository;

import by.intervale.smev.model.Response;
import by.intervale.smev.model.ResponseGisgmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends
        JpaRepository<Response, Long> {
}

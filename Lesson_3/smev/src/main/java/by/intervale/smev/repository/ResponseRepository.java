package by.intervale.smev.repository;

import by.intervale.smev.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseRepository extends
        JpaRepository<Response, UUID> {
}

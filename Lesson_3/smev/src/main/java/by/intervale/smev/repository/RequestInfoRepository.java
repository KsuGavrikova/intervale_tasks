package by.intervale.smev.repository;

import by.intervale.smev.model.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestInfoRepository extends
        JpaRepository<RequestInfo, UUID> {
}

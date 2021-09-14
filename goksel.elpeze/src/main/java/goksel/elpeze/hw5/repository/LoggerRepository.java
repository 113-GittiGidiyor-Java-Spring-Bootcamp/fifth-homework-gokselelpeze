package goksel.elpeze.hw5.repository;

import goksel.elpeze.hw5.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LoggerRepository extends JpaRepository<Logger, Integer> {

    List<Logger> findAllById(int id);

    @Query("SELECT l FROM Logger l WHERE l.requestTime >= ?1 order by l.requestTime")
    List<Logger> findAllByLogDate(LocalDate requestDate);
}

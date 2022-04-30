package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainsAndPrenomContainsAndScoreGreaterThanEqual(String kw, String pr, int sc, Pageable pageable);

}

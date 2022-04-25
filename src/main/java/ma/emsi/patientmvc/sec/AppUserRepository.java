package ma.emsi.patientmvc.sec;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    //AppUser findBy
}

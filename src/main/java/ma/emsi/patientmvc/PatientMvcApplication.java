package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entities.Medecin;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import ma.emsi.patientmvc.repositories.PatientRepository;
import ma.emsi.patientmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientMvcApplication.class, args);
    }
    /*
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     */

    //Pour le médecin
    @Bean
    CommandLineRunner commandLineRunner(MedecinRepository medecinRepository){
        return args -> {
            medecinRepository.save(
                    new Medecin(null,"Hassan","Sbai",new Date(),"BK989"));
            medecinRepository.save(
                    new Medecin(null,"Nizar","Sbai",new Date(),"BK7189"));
            medecinRepository.save(
                    new Medecin(null,"Yasmine","Berrada",new Date(),"BK12344"));
            medecinRepository.save(
                    new Medecin(null,"Dounia","MTR",new Date(),"BK54321"));




            medecinRepository.findAll().forEach(p->
                    System.out.println(p.getNom()));
        };

    }




    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"Hassan","Sbai",new Date(),false,122));
            patientRepository.save(
                    new Patient(null,"Mohammed","Tarik",new Date(),true,321));
            patientRepository.save(
                    new Patient(null,"Yasmine","Berrada",new Date(),true,165));
            patientRepository.save(
                    new Patient(null,"Hanaa","Fadal",new Date(),false,132));
            patientRepository.save(
                    new Patient(null,"Hanaa","Hanaa",new Date(),false,132));/**/



            patientRepository.findAll().forEach(p->
                System.out.println(p.getNom()));
            };

    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            //securityService.saveNewUser("nizar","1234","1234");
            securityService.saveNewUser("youssef","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed","USER");
            //securityService.addRoleToUser("nizar","ADMIN");
            //securityService.addRoleToUser("saad","ADMIN");
            //securityService.addRoleToUser("nizar","USER");

            securityService.addRoleToUser("youssef","USER");
            securityService.addRoleToUser("hassan","USER");
        };
    }
}

package ma.emsi.patientmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entities.Medecin;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class MedecinController {
    private MedecinRepository medecinRepository;
    @GetMapping(path = "/user/index2")
    public String medecins(Model model,
                           @RequestParam(name="page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword,
                           @RequestParam(name = "prenom", defaultValue = "") String prenom
                           ){
        Page<Medecin> pageMedecins = medecinRepository.findByNomContainsAndPrenomContains(keyword,prenom, PageRequest.of(page,size));
        model.addAttribute("listMedecins", pageMedecins.getContent());
        model.addAttribute("pages",new int[pageMedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prenom",prenom);
        //model.addAttribute("cin",CIN);
        List<Medecin> medecins=medecinRepository.findAll();
        model.addAttribute("listMedecins",medecins);
        return "medecins";
    }

    @GetMapping(path="/user/medecins")
    @ResponseBody
    public List<Medecin> listMedecins(){
        return medecinRepository.findAll();
    }

}

package ma.emsi.patientmvc.web;

import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name="page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword,
                           @RequestParam(name = "prenom", defaultValue = "") String prenom,
                           //@RequestParam(name = "malade", defaultValue = "") boolean malade,
                           @RequestParam(name = "score", defaultValue = "10") int score){
        Page<Patient> pagePatients = patientRepository.findByNomContainsAndPrenomContainsAndScoreGreaterThanEqual(keyword,prenom,score,PageRequest.of(page,size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prenom",prenom);
        model.addAttribute("score",score);
        //model.addAttribute("malade",malade);
        return "patients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword,String prenom, int score, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword+"&score="+score+"&prenom="+prenom;
    }

    @GetMapping(path = "/")
    public String home(){
        return "home";
    }
    @GetMapping(path="/user/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    @GetMapping(path="/admin/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "") String prenom,
                       //@RequestParam(defaultValue = "") boolean malade,
                       @RequestParam(name = "score", defaultValue = "") int score){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword+"&score="+score+"&prenom="+prenom;
    }

    @GetMapping("/admin/editPatient")
    public String editPatients(Model model, Long id, String keyword, String prenom, int score, int page){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prenom",prenom);
        model.addAttribute("score",score);
        //model.addAttribute("malade",malade);
        return "editPatient";
    }

}

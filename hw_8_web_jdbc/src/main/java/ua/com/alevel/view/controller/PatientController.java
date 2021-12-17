package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.view.dto.request.PatientRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

@Controller
@RequestMapping("/patients")
public class PatientController extends AbstractController {

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("first name", "firstname", "first_name"),
                new HeaderName("last name", "lastname", "first_name"),
                new HeaderName("age", "age", "age"),
                new HeaderName("created", "created", "created"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private final PatientFacade patientFacade;

    public PatientController(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

        @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<PatientResponseDto> response = patientFacade.findAll(request);
        initDataTable(response, getColumnTitles(), model);
        model.addAttribute("createUrl", "/patients/all");
        model.addAttribute("cardHeader", "All Patients");
        return "pages/patient/patient_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "patients");
    }

    @GetMapping("/new")
    public String redirectToNewPatientPage(Model model) {
        model.addAttribute("patient", new PatientRequestDto());
        return "pages/patient/patient_new";
    }

    @PostMapping("/new")
    public String createNewPatient(@ModelAttribute("patient") PatientRequestDto patientRequestDto) {
        patientFacade.create(patientRequestDto);
        return "redirect:/patients";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute("patient") PatientRequestDto patientRequestDto) {
        patientFacade.update(patientRequestDto, id);
        return "redirect:/patients";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PatientResponseDto patientResponseDto = patientFacade.findById(id);
        model.addAttribute("patient", patientResponseDto);
        return "pages/patient/patient_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        patientFacade.delete(id);
        return "redirect:/patients";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        PatientResponseDto patientResponseDto = patientFacade.findById(id);
        model.addAttribute("patient", patientResponseDto);
        return "pages/patient/patient_details";
    }
}
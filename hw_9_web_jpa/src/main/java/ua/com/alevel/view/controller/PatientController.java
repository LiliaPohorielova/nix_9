package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.view.dto.request.PatientRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/patients")
public class PatientController extends AbstractController {

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("last name", "lastname", "last_name"),
                new HeaderName("first name", "firstname", "first_name"),
                new HeaderName("age", "age", "age"),
                new HeaderName("created", "created", "created"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;

    public PatientController(DoctorFacade doctorFacade, PatientFacade patientFacade) {
        this.doctorFacade = doctorFacade;
        this.patientFacade = patientFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<PatientResponseDto> response = patientFacade.findAll(request);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/patients/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Patients");
        return "pages/patient/patient_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/patients", model);
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

    @GetMapping("/update/{id}")
    public String redirectToUpdatePatientPage(@PathVariable Long id, Model model) {
        PatientResponseDto patientResponseDto = patientFacade.findById(id);
        model.addAttribute("patient", patientResponseDto);
        return "pages/patient/patient_update";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@ModelAttribute("patient") PatientRequestDto patientRequestDto, @PathVariable Long id) {
        patientFacade.update(patientRequestDto, id);
        return "redirect:/patients";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<DoctorResponseDto> doctors = patientFacade.getDoctors(id);
        model.addAttribute("patient", patientFacade.findById(id));
        model.addAttribute("doctors", doctors);
        return "pages/patient/patient_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        List<DoctorResponseDto> doctors = patientFacade.getDoctors(id);
        for (DoctorResponseDto doctor: doctors) {
            doctorFacade.removePatient(doctor.getId(), id);
        }
        patientFacade.delete(id);
        return "redirect:/patients";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddPatientPage(@PathVariable Long id, Model model, WebRequest request) {
        List<PatientResponseDto> patients = patientFacade.findAll(request).getItems();
        model.addAttribute("patients", patients);
        model.addAttribute("doctor", doctorFacade.findById(id));
        return "pages/patient/patient_add";
    }

    @GetMapping("/doctor/{patientId}/{doctorId}")
    public String addPatient(@PathVariable Long patientId, @PathVariable Long doctorId, Model model) {
        doctorFacade.addPatient(doctorId,patientId);
        List<PatientResponseDto> patients = doctorFacade.getPatients(doctorId);
        model.addAttribute("doctor", doctorFacade.findById(doctorId));
        model.addAttribute("patients", patients);
        return "pages/doctor/doctor_details";
    }

    @GetMapping("/delete/doctor/{patientId}/{doctorId}")
    public String deletePatientFromDoctor(@PathVariable Long patientId, @PathVariable Long doctorId, Model model) {
        doctorFacade.removePatient(doctorId,patientId);
        List<PatientResponseDto> patients = doctorFacade.getPatients(doctorId);
        model.addAttribute("doctor", doctorFacade.findById(doctorId));
        model.addAttribute("patients", patients);
        return "pages/doctor/doctor_details";
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<PatientResponseDto> response) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnTitles) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getDbName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        return headerDataList;
    }
}
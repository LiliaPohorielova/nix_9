package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.DeclarationFacade;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.view.dto.request.DeclarationRequestDto;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/doctors")
public class DoctorController extends AbstractController {

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("lastname", "lastname", "last_name"),
                new HeaderName("firstname", "firstname", "first_name"),
                new HeaderName("middle name", "middleName", "middle_name"),
                new HeaderName("patient count", "patientCount", "patientCount"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;
    private final DeclarationFacade declarationFacade;

    public DoctorController(DoctorFacade doctorFacade, PatientFacade patientFacade, DeclarationFacade declarationFacade) {
        this.doctorFacade = doctorFacade;
        this.patientFacade = patientFacade;
        this.declarationFacade = declarationFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<DoctorResponseDto> response = doctorFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/doctors/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Doctors");
        return "pages/doctor/doctor_all";

    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/doctors", model);
    }

    @GetMapping("/new")
    public String redirectToNewDoctorPage(Model model) {
        model.addAttribute("doctor", new DoctorRequestDto());
        return "pages/doctor/doctor_new";
    }

    @PostMapping("/new")
    public String createNewDoctor(@ModelAttribute("doctor") DoctorRequestDto dto) {
        doctorFacade.create(dto);
        return "redirect:/doctors";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateDoctorPage(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/doctor/doctor_update";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@ModelAttribute("doctor") DoctorRequestDto doctorRequestDto, @PathVariable Long id) {
        doctorFacade.update(doctorRequestDto, id);
        return "redirect:/doctors";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        List<PatientResponseDto> patients = declarationFacade.findByDoctorId(id);
        model.addAttribute("doctor", doctorFacade.findById(id));
        model.addAttribute("patients", patients);
        return "pages/doctor/doctor_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        List<PatientResponseDto> patients = declarationFacade.findByDoctorId(id);
        patients.stream()
                .map(patient -> declarationFacade.findByDoctorIdAndPatientId(patient.getId(), id).getId())
                .forEach(declarationFacade::delete);
        doctorFacade.delete(id);
        return "redirect:/doctors";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddDoctorPage(@PathVariable Long id, Model model, WebRequest request) {
        List<DoctorResponseDto> doctors = doctorFacade.findAll(request).getItems();
        model.addAttribute("doctors", doctors);
        model.addAttribute("patient", patientFacade.findById(id));
        return "pages/doctor/doctor_add";
    }

    @GetMapping("/doctor/{patientId}/{doctorId}")
    public String addPatient(@PathVariable Long patientId, @PathVariable Long doctorId, Model model) {
        DeclarationRequestDto declarationRequestDto = new DeclarationRequestDto();
        declarationRequestDto.setPatientId(patientId);
        declarationRequestDto.setDoctorId(doctorId);
        declarationFacade.create(declarationRequestDto);
        List<DoctorResponseDto> doctors = declarationFacade.findByPatientId(patientId);
        model.addAttribute("patient", patientFacade.findById(patientId));
        model.addAttribute("doctors", doctors);
        return "pages/patient/patient_details";
    }

    @GetMapping("/delete/doctor/{patientId}/{doctorId}")
    public String deletePatientFromDoctor(@PathVariable Long patientId, @PathVariable Long doctorId, Model model) {
        Long id = declarationFacade.findByDoctorIdAndPatientId(patientId, doctorId).getId();
        declarationFacade.delete(id);
        List<DoctorResponseDto> doctors = declarationFacade.findByPatientId(patientId);
        model.addAttribute("patient", patientFacade.findById(patientId));
        model.addAttribute("doctors", doctors);
        return "pages/patient/patient_details";
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<DoctorResponseDto> response) {
        List<HeaderData> headerDataList = new ArrayList<>();

        for (HeaderName headerName : columnTitles) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getTableName())) {
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
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
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

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

    public DoctorController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
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

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") DoctorRequestDto doctorRequestDto) {
        doctorFacade.update(doctorRequestDto, id);
        return "redirect:/doctors";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/doctor/doctor_update";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/doctor/doctor_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        doctorFacade.delete(id);
        return "redirect:/doctors";
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
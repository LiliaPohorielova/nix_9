package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.PatientRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PatientFacadeImpl implements PatientFacade {

    private final PatientService patientService;

    public PatientFacadeImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void create(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setFirstname(patientRequestDto.getFirstname());
        patient.setLastname(patientRequestDto.getLastname());
        patient.setAge(patientRequestDto.getAge());
        patientService.create(patient);
    }

    @Override
    public void update(PatientRequestDto patientRequestDto, Long id) {
    Patient patient = patientService.findById(id);
    patient.setFirstname(patientRequestDto.getFirstname());
    patient.setLastname(patientRequestDto.getLastname());
    patient.setAge(patientRequestDto.getAge());
    patientService.update(patient);
    }

    @Override
    public void delete(Long id) {
    patientService.delete(id);
    }

    @Override
    public PatientResponseDto findById(Long id) {
        return new PatientResponseDto(patientService.findById(id));
    }

    @Override
    public PageData<PatientResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setOrder(sortData.getOrder());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setPageSize(dataTableRequest.getPageSize());

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            String[] params = request.getParameterMap().get("doctorId");
            if (params != null) {
                Long doctorId = Long.parseLong(params[0]);
                System.out.println("doctorId = " + doctorId);
                dataTableRequest.getQueryMap().put("doctorId", doctorId);
            }
        }

        DataTableResponse<Patient> all = patientService.findAll(dataTableRequest);

        List<PatientResponseDto> items = all.getItems()
                .stream().map(PatientResponseDto::new)
                .collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = new PageData<>();
        pageData.setItems(items);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}
package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.PatientFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.PatientRequestDto;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFacadeImpl implements PatientFacade {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public PatientFacadeImpl(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public List<DoctorResponseDto> getDoctors(Long id) {
        List<Doctor> doctors = patientService.getDoctors(id);
        List<DoctorResponseDto> list = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorResponseDto doctorResponseDto = new DoctorResponseDto(doctor);
            list.add(doctorResponseDto);
        }
        return list;
    }

    @Override
    public void create(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setLastname(patientRequestDto.getLastname());
        patient.setFirstname(patientRequestDto.getFirstname());
        patient.setAge(patientRequestDto.getAge());
        patientService.create(patient);
    }

    @Override
    public void update(PatientRequestDto patientRequestDto, Long id) {
        Patient patient = patientService.findById(id).get();
        patient.setLastname(patientRequestDto.getLastname());
        patient.setFirstname(patientRequestDto.getFirstname());
        patient.setAge(patientRequestDto.getAge());
        patientService.update(patient);
    }

    @Override
    public void delete(Long id) {
        patientService.delete(id);
    }

    @Override
    public PatientResponseDto findById(Long id) {
        return new PatientResponseDto(patientService.findById(id).get());
    }

    @Override
    public PageData<PatientResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Patient> all = patientService.findAll(dataTableRequest);

        List<PatientResponseDto> list = all.getItems().
                stream().
                map(PatientResponseDto::new).
                collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());

        return pageData;
    }

    @Override
    public List<PatientResponseDto> findAll() {
        List<Patient> all = patientService.findAll();
        List<PatientResponseDto> items = all.stream().map(PatientResponseDto::new).collect(Collectors.toList());
        return items;
    }

   /* private List<PatientResponseDto> convertToDtoByEntity(List<Patient> patients) {
        return patients.stream()
                .map(PatientResponseDto::new)
                .collect(Collectors.toList());
    }*/
}

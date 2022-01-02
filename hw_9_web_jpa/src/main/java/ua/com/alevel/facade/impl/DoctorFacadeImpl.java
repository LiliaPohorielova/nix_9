package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorFacadeImpl implements DoctorFacade {

    private final DoctorService doctorService;

    public DoctorFacadeImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void addPatient(Long doctorId, Long patientId) {
        doctorService.addPatient(doctorId, patientId);
    }

    @Override
    public void removePatient(Long doctorId, Long patientId) {
        doctorService.removePatient(doctorId, patientId);
    }

    @Override
    public List<PatientResponseDto> getPatients(Long id) {
        List<Patient> patients = doctorService.getPatients(id);
        List<PatientResponseDto> list = new ArrayList<>();
        for (Patient patient : patients) {
            PatientResponseDto patientResponseDto = new PatientResponseDto(patient);
            list.add(patientResponseDto);
        }
        return list;
    }

    @Override
    public void create(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor();
        doctor.setLastname(doctorRequestDto.getLastname());
        doctor.setFirstname(doctorRequestDto.getFirstname());
        doctor.setMiddleName(doctorRequestDto.getMiddleName());
        doctor.setSpecialization(doctorRequestDto.getSpecialization());
        doctorService.create(doctor);
    }

    @Override
    public void update(DoctorRequestDto doctorRequestDto, Long id) {
        Doctor doctor = doctorService.findById(id);
        doctor.setLastname(doctorRequestDto.getLastname());
        doctor.setFirstname(doctorRequestDto.getFirstname());
        doctor.setMiddleName(doctorRequestDto.getMiddleName());
        doctor.setSpecialization(doctorRequestDto.getSpecialization());
        doctorService.update(doctor);
    }

    @Override
    public void delete(Long id) {
        doctorService.delete(id);
    }

    @Override
    public DoctorResponseDto findById(Long id) {
        Doctor doctor = doctorService.findById(id);
        return new DoctorResponseDto(doctor);
    }

    @Override
    public PageData<DoctorResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Doctor> all = doctorService.findAll(dataTableRequest);

        List< DoctorResponseDto> list = all.getItems().
                stream().
                map( DoctorResponseDto::new).
                collect(Collectors.toList());

        PageData< DoctorResponseDto> pageData = new PageData<>();
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
    public List<DoctorResponseDto> findAll() {
        List<Doctor> all = doctorService.findAll();
        List<DoctorResponseDto> items = all.stream().map(DoctorResponseDto::new).collect(Collectors.toList());
        return items;
    }
}

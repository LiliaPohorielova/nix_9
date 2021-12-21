package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DeclarationFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Declaration;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.DeclarationService;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.DeclarationRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.DeclarationResponseDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarationFacadeImpl implements DeclarationFacade {

    private final DeclarationService declarationService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public DeclarationFacadeImpl(DeclarationService declarationService, PatientService patientService, DoctorService doctorService) {
        this.declarationService = declarationService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    public void create(DeclarationRequestDto declarationRequestDto) {
        Declaration declaration = new Declaration();
        declaration.setDoctorId(declarationRequestDto.getDoctorId());
        declaration.setPatientId(declarationRequestDto.getPatientId());
        declarationService.create(declaration);
    }

    @Override
    public void update(DeclarationRequestDto declarationRequestDto, Long id) {
        Declaration declaration = declarationService.findById(id);
        declaration.setDoctorId(declarationRequestDto.getDoctorId());
        declaration.setPatientId(declarationRequestDto.getPatientId());
        declarationService.update(declaration);
    }

    @Override
    public void delete(Long id) {
        declarationService.delete(id);
    }

    @Override
    public DeclarationResponseDto findById(Long id) {
        Declaration declaration = declarationService.findById(id);
        return new DeclarationResponseDto(declaration);
    }

    @Override
    public List<PatientResponseDto> findByDoctorId(Long doctorId) {
        List<Declaration> declarations;
        List<PatientResponseDto> patients = new ArrayList<>();
        declarations = declarationService.findByDoctorId(doctorId);
        Patient patient;
        PatientResponseDto patientResponseDto;
        for (Declaration declaration : declarations) {
            if (declaration.getDoctorId().equals(doctorId)) {
                patient = patientService.findById(declaration.getPatientId());
                patientResponseDto = new PatientResponseDto(patient);
                patients.add(patientResponseDto);
            }
        }
        return patients;
    }

    @Override
    public List<DoctorResponseDto> findByPatientId(Long patientId) {
        List<Declaration> declarations;
        List<DoctorResponseDto> doctors = new ArrayList<>();
        declarations = declarationService.findByPatientId(patientId);
        Doctor doctor;
        DoctorResponseDto doctorResponseDto;
        for (Declaration declaration : declarations) {
            if (declaration.getPatientId().equals(patientId)) {
                doctor = doctorService.findById(declaration.getDoctorId());
                doctorResponseDto = new DoctorResponseDto(doctor);
                doctors.add(doctorResponseDto);
            }
        }
        return doctors;
    }

    @Override
    public DeclarationResponseDto findByDoctorIdAndPatientId(Long patientId, Long doctorId) {
        Declaration declaration = declarationService.findByDoctorIdAndPatientId(patientId, doctorId);
        return new DeclarationResponseDto(declaration);
    }

    @Override
    public PageData<DeclarationResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Declaration> all = declarationService.findAll(dataTableRequest);

        List<DeclarationResponseDto> list = all.getItems().
                stream().
                map(DeclarationResponseDto::new).
                collect(Collectors.toList());

        PageData<DeclarationResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}

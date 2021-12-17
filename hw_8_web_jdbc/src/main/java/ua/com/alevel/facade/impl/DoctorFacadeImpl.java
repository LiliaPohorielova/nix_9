package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DoctorFacadeImpl implements DoctorFacade {

    private final DoctorService doctorService;

    public DoctorFacadeImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void create(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor();
        doctor.setLastname(doctorRequestDto.getLastname());
        doctor.setFirstname(doctorRequestDto.getFirstname());
        doctor.setMiddleName(doctorRequestDto.getMiddleName());
        doctorService.create(doctor);
    }

    @Override
    public void update(DoctorRequestDto doctorRequestDto, Long id) {
        Doctor doctor = doctorService.findById(id);
        doctor.setLastname(doctorRequestDto.getLastname());
        doctor.setFirstname(doctorRequestDto.getFirstname());
        doctor.setMiddleName(doctorRequestDto.getMiddleName());
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

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            System.out.println("parameterMap = " + parameterMap);
        }

        DataTableResponse<Doctor> all = doctorService.findAll(dataTableRequest);
        List<DoctorResponseDto> list = all.getItems().
                stream().
                map(DoctorResponseDto::new).
                peek(dto -> dto.setPatientCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());

        return pageData;
    }
}
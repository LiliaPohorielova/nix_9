package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.DeclarationDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Declaration;
import ua.com.alevel.service.DeclarationService;

import java.util.List;

@Service
public class DeclarationServiceImpl implements DeclarationService {

    private final DeclarationDao declarationDao;

    public DeclarationServiceImpl(
            DeclarationDao declarationDao) {
        this.declarationDao = declarationDao;
    }

    @Override
    public void create(Declaration declaration) {
        declarationDao.create(declaration);
    }

    @Override
    public void update(Declaration declaration) {
        declarationDao.update(declaration);
    }

    @Override
    public void delete(Long id) {
        if (declarationDao.existById(id)) {
            declarationDao.delete(id);
        }
    }

    @Override
    public Declaration findById(Long id) {
        return declarationDao.findById(id);
    }

    @Override
    public List<Declaration> findByDoctorId(Long doctorId) {
        return declarationDao.findByDoctorId(doctorId);
    }

    @Override
    public List<Declaration> findByPatientId(Long patientId) {
        return declarationDao.findByPatientId(patientId);
    }

    @Override
    public Declaration findByDoctorIdAndPatientId(Long patientId, Long doctorId) {
        return declarationDao.findByDoctorIdAndPatientId(patientId, doctorId);
    }

    @Override
    public DataTableResponse<Declaration> findAll(DataTableRequest request) {
        DataTableResponse<Declaration> dataTableResponse = declarationDao.findAll(request);
        long count = declarationDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}

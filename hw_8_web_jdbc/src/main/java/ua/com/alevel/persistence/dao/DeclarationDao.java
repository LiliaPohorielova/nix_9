package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Declaration;

import java.util.List;

public interface DeclarationDao extends BaseDao<Declaration> {

        List<Declaration> findByDoctorId(Long doctorId);

        List<Declaration> findByPatientId(Long patientId);

        Declaration findByDoctorIdAndPatientId(Long patientId, Long doctorId);
}

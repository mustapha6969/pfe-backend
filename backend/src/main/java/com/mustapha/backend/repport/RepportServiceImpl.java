package com.mustapha.backend.repport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepportServiceImpl implements IRepportService {

    private final RepportRepository repportRepository;

    @Autowired
    public RepportServiceImpl(RepportRepository repportRepository) {
        this.repportRepository = repportRepository;
    }

    @Override
    public Repport createRepport(Repport repport) {
        return repportRepository.save(repport);
    }

    @Override
    public Optional<Repport> getRepportById(Integer id) {
        return repportRepository.findById(id);
    }

    @Override
    public List<Repport> getAllRepports() {
        return repportRepository.findAll();
    }

    @Override
    public Repport updateRepport(Integer id, Repport updatedRepport) {
        return repportRepository.findById(id).map(repport -> {
            repport.setProject(updatedRepport.getProject());
            repport.setSecurityConsultant(updatedRepport.getSecurityConsultant());
            repport.setPdfReport(updatedRepport.getPdfReport());
            repport.setValidated(updatedRepport.isValidated());
            return repportRepository.save(repport);
        }).orElseThrow(() -> new RuntimeException("Repport not found with id " + id));
    }

    @Override
    public void deleteRepport(Integer id) {
        repportRepository.deleteById(id);
    }

    @Override
    public List<Repport> getRepportsBySecurityConsultantId(Integer consultantId) {
        return repportRepository.findBySecurityConsultantId(consultantId);
    }

    @Override
    public List<Repport> getRepportsByProjectId(Integer projectId) {
        return repportRepository.findByProjectId(projectId);
    }
}

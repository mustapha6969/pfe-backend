package com.mustapha.backend.repport;

import java.util.List;
import java.util.Optional;

public interface IRepportService {
    Repport createRepport(Repport repport);

    Optional<Repport> getRepportById(Integer id);

    List<Repport> getAllRepports();

    Repport updateRepport(Integer id, Repport repport);

    void deleteRepport(Integer id);

    List<Repport> getRepportsBySecurityConsultantId(Integer consultantId);

    List<Repport> getRepportsByProjectId(Integer projectId);
}

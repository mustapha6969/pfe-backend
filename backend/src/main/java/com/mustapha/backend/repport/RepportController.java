package com.mustapha.backend.repport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repports")
public class RepportController {

    private final IRepportService repportService;

    @Autowired
    public RepportController(IRepportService repportService) {
        this.repportService = repportService;
    }

    @PostMapping
    public ResponseEntity<Repport> createRepport(@RequestBody Repport repport) {
        Repport createdRepport = repportService.createRepport(repport);
        return new ResponseEntity<>(createdRepport, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repport> getRepportById(@PathVariable Integer id) {
        Optional<Repport> repport = repportService.getRepportById(id);
        return repport.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Repport>> getAllRepports() {
        List<Repport> repports = repportService.getAllRepports();
        return new ResponseEntity<>(repports, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repport> updateRepport(@PathVariable Integer id, @RequestBody Repport repport) {
        try {
            Repport updatedRepport = repportService.updateRepport(id, repport);
            return new ResponseEntity<>(updatedRepport, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepport(@PathVariable Integer id) {
        repportService.deleteRepport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/consultant/{consultantId}")
    public ResponseEntity<List<Repport>> getRepportsBySecurityConsultantId(@PathVariable Integer consultantId) {
        List<Repport> repports = repportService.getRepportsBySecurityConsultantId(consultantId);
        return new ResponseEntity<>(repports, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Repport>> getRepportsByProjectId(@PathVariable Integer projectId) {
        List<Repport> repports = repportService.getRepportsByProjectId(projectId);
        return new ResponseEntity<>(repports, HttpStatus.OK);
    }
}

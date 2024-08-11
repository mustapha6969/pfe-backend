package com.mustapha.backend.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final IFormService formService;

    @Autowired
    public FormController(IFormService formService) {
        this.formService = formService;
    }

    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        Form createdForm = formService.createForm(form);
        return new ResponseEntity<>(createdForm, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Integer id) {
        Optional<Form> form = formService.getFormById(id);
        return form.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Form>> getAllForms() {
        List<Form> forms = formService.getAllForms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable Integer id, @RequestBody Form form) {
        try {
            Form updatedForm = formService.updateForm(id, form);
            return new ResponseEntity<>(updatedForm, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Integer id) {
        formService.deleteForm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/developer/{developerId}")
    public ResponseEntity<List<Form>> getFormsByDeveloperId(@PathVariable Integer developerId) {
        List<Form> forms = formService.getFormsByDeveloperId(developerId);
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Form>> getFormsByProjectId(@PathVariable Integer projectId) {
        List<Form> forms = formService.getFormsByProjectId(projectId);
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }
}

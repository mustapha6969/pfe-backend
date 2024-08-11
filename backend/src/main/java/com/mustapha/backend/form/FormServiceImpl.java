package com.mustapha.backend.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormServiceImpl implements IFormService {

    private final FormRepository formRepository;

    @Autowired
    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    @Override
    public Optional<Form> getFormById(Integer id) {
        return formRepository.findById(id);
    }

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form updateForm(Integer id, Form updatedForm) {
        return formRepository.findById(id).map(form -> {
            form.setProject(updatedForm.getProject());
            form.setDeveloper(updatedForm.getDeveloper());
            form.setSubmissionDate(updatedForm.getSubmissionDate());
            form.setPdfForm(updatedForm.getPdfForm());
            return formRepository.save(form);
        }).orElseThrow(() -> new RuntimeException("Form not found with id " + id));
    }

    @Override
    public void deleteForm(Integer id) {
        formRepository.deleteById(id);
    }

    @Override
    public List<Form> getFormsByDeveloperId(Integer developerId) {
        return formRepository.findByDeveloperId(developerId);
    }

    @Override
    public List<Form> getFormsByProjectId(Integer projectId) {
        return formRepository.findByProjectId(projectId);
    }
}

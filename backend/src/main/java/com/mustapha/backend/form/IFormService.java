package com.mustapha.backend.form;

import java.util.List;
import java.util.Optional;

public interface IFormService {
    Form createForm(Form form);

    Optional<Form> getFormById(Integer id);

    List<Form> getAllForms();

    Form updateForm(Integer id, Form form);

    void deleteForm(Integer id);

    List<Form> getFormsByDeveloperId(Integer developerId);

    List<Form> getFormsByProjectId(Integer projectId);
}

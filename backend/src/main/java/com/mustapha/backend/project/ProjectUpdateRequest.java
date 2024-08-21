package com.mustapha.backend.project;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectUpdateRequest {
    private String name;
    private String description;
    private List<Integer> userIdsToAdd;
    private List<Integer> userIdsToRemove;
}

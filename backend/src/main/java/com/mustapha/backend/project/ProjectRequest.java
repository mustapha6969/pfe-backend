package com.mustapha.backend.project;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProjectRequest {
    private String name;
    private String description;
    private List<Integer> userIds;

}


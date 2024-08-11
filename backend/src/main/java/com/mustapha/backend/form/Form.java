package com.mustapha.backend.form;

import com.mustapha.backend.project.Project;
import com.mustapha.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private User developer;

    @Column(nullable = false)
    private LocalDate submissionDate;

    @Column(length = 500)
    private String pdfForm; // Storing PDF as a string (could be a file path or base64 string)
}

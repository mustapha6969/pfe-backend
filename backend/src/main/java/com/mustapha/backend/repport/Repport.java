package com.mustapha.backend.repport;

import com.mustapha.backend.project.Project;
import com.mustapha.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Repport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "consultant_id", nullable = false)
    private User securityConsultant;

    @Column(length = 500)
    private String pdfReport; // Storing PDF as a string (could be a file path or base64 string)

    @Column(nullable = false)
    private boolean validated;
}

package com.example.document_manager.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 6, message = "Name must be at least 6 characters long")
    @Column(nullable = false)
    private String name;

    @Lob
    @Size(min = 16, message = "Content must be at least 16 characters long")
    private String content;

    @NotNull(message = "Creation date is required")
    @Column(nullable = false)
    private LocalDate creationDate;

    @NotNull(message = "Last modified date is required")
    @Column(nullable = false)
    private LocalDate lastModifiedDate;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;

    @NotNull(message = "Validity date is required")
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate validityDate;

    // Constructors
    public Document(String name, String content, LocalDate creationDate, LocalDate lastModifiedDate, DocumentStatus status, LocalDate validityDate) {
        this.name = name;
        this.content = content;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.status = status;
        this.validityDate = validityDate;
    }

    protected Document() {} // No-args constructor

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }
}

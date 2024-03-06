package com.example.document_manager.service;

import com.example.document_manager.entity.Document;
import com.example.document_manager.exception.DocumentNotFoundException;
import com.example.document_manager.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found with id: " + id));
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document updateDocument(Long id, Document updatedDocument) {
        Document existingDocument = getDocumentById(id);
        if (existingDocument == null) {
            throw new IllegalArgumentException("Error: Document with id " + id + " does not exist.");
        }
        // Update existing document properties
        existingDocument.setName(updatedDocument.getName());
        existingDocument.setContent(updatedDocument.getContent());
        existingDocument.setLastModifiedDate(LocalDate.now());
        existingDocument.setStatus(updatedDocument.getStatus());
        existingDocument.setValidityDate(updatedDocument.getValidityDate());
        return documentRepository.save(existingDocument);
    }

    public String deleteDocument(Long id) {
        Document document = getDocumentById(id);
        if (document == null) {
            return "Error: Document with id " + id + " does not exist.";
        }
        documentRepository.delete(document);
        return "Document with id: " + id + " deleted successfully.";
    }

    public List<Document> searchDocuments(Document searchCriteria) {
        List<Document> allDocuments = documentRepository.findAll();

        return allDocuments.stream()
                .filter(document -> matchesCriteria(document, searchCriteria))
                .collect(Collectors.toList());
    }

    public List<Document> searchDocumentsByDate(LocalDate fromDate, LocalDate toDate) {
        Specification<Document> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("creationDate"), fromDate, toDate);

        return documentRepository.findAll(specification);
    }

    private boolean matchesCriteria(Document document, Document searchCriteria) {
        // Check if each attribute in the document matches the corresponding attribute in the search criteria
        return (searchCriteria.getId() == null || document.getId().equals(searchCriteria.getId())) &&
                (searchCriteria.getName() == null || document.getName().contains(searchCriteria.getName())) &&
                (searchCriteria.getContent() == null || document.getContent().contains(searchCriteria.getContent())) &&
                (searchCriteria.getCreationDate() == null || document.getCreationDate().equals(searchCriteria.getCreationDate())) &&
                (searchCriteria.getLastModifiedDate() == null || document.getLastModifiedDate().equals(searchCriteria.getLastModifiedDate())) &&
                (searchCriteria.getStatus() == null || document.getStatus().equals(searchCriteria.getStatus())) &&
                (searchCriteria.getValidityDate() == null || document.getValidityDate().equals(searchCriteria.getValidityDate()));
    }
}

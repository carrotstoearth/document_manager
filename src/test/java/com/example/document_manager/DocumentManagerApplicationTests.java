package com.example.document_manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.document_manager.service.DocumentService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DocumentManagerApplicationTests {

	@Autowired
	private DocumentService documentService;

	@Test
	void contextLoads() {
		// Verify that the application context loads successfully
	}

	@Test
	void documentServiceIsNotNull() {
		assertNotNull(documentService, "DocumentService should be not null");
	}

	// Add more test methods to verify other parts of your application
}

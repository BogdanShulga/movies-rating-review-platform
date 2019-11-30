package com.edu.movies_rating_review_platform;

import com.edu.movies_rating_review_platform.controller.MigrationController;
import com.edu.movies_rating_review_platform.service.MigrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import  static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class migrationControllerContextLessTests {

    @Mock
    private MigrationService migrationServiceMock;

    @InjectMocks
    private MigrationController migrationController;


    @Test
    public void migrationControllerTest() {

        String expectedResult = "Added 5 documents to Movies collection and 10 documents to Reviews collection!";

        when(migrationServiceMock.migrate()).thenReturn("Added 5 documents to Movies collection and 10 documents to Reviews collection!");

        ResponseEntity<String> responseEntity = migrationController.migrate();

        verify(migrationServiceMock, times(1)).migrate();

        assertEquals(responseEntity.getBody(), expectedResult);
    }
}

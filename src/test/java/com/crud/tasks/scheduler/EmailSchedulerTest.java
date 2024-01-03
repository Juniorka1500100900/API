package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailSchedulerTest {

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Captor
    private ArgumentCaptor<Mail> mailArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void sendInformationEmail_shouldHandleSingleTaskWordCorrectly() {
        // Given
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail capturedMail = mailArgumentCaptor.getValue();

        assertEquals("Currently in database you have: 1 task", capturedMail.getMessage());
    }

    @Test
    void sendInformationEmail_shouldHandlePluralTaskWordCorrectly() {
        // Given
        when(taskRepository.count()).thenReturn(0L);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail capturedMail = mailArgumentCaptor.getValue();

        assertEquals("Currently in database you have: 0 tasks", capturedMail.getMessage());
    }
}

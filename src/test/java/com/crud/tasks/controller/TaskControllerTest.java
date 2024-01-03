package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTasks_shouldReturnTaskDtoList() {
        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test Task", "Test Content"));
        when(dbService.getAllTasks()).thenReturn(tasks);

        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "Test Task", "Test Content"));
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        // When
        ResponseEntity<List<TaskDto>> responseEntity = taskController.getTasks();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDtos, responseEntity.getBody());
    }

    @Test
    void getTask_shouldReturnTaskDto() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Test Task", "Test Content");
        when(dbService.getTask(1L)).thenReturn(task);

        TaskDto taskDto = new TaskDto(1L, "Test Task", "Test Content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.getTask(1L);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDto, responseEntity.getBody());
    }

    @Test
    void deleteTask_shouldReturnNoContent() throws TaskNotFoundException {
        // When
        ResponseEntity<Void> responseEntity = taskController.deleteTask(1L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    void updateTask_shouldReturnUpdatedTaskDto() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated Content");
        Task task = new Task(1L, "Updated Task", "Updated Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When
        ResponseEntity<TaskDto> responseEntity = taskController.updateTask(taskDto);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(taskDto, responseEntity.getBody());
    }

    @Test
    void createTask_shouldReturnOk() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "New Task", "New Content");
        Task task = new Task(1L, "New Task", "New Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        // When
        ResponseEntity<Void> responseEntity = taskController.createTask(taskDto);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(dbService, times(1)).saveTask(task);
    }
}

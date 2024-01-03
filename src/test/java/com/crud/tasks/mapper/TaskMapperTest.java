package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        taskMapper = new TaskMapper();
    }

    @Test
    void mapToTask_shouldMapToTaskCorrectly() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "TestTask", "TaskContent");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(1L, task.getId());
        assertEquals("TestTask", task.getTitle());
        assertEquals("TaskContent", task.getContent());
    }

    @Test
    void mapToTaskDto_shouldMapToTaskDtoCorrectly() {
        // Given
        Task task = new Task(1L, "TestTask", "TaskContent");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(1L, taskDto.getId());
        assertEquals("TestTask", taskDto.getTitle());
        assertEquals("TaskContent", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoList_shouldMapToTaskDtoListCorrectly() {
        // Given
        List<Task> taskList = Arrays.asList(
                new Task(1L, "TestTask1", "TaskContent1"),
                new Task(2L, "TestTask2", "TaskContent2")
        );

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertEquals(2, taskDtoList.size());
        assertEquals(1L, taskDtoList.get(0).getId());
        assertEquals("TestTask1", taskDtoList.get(0).getTitle());
        assertEquals("TaskContent1", taskDtoList.get(0).getContent());
    }
}

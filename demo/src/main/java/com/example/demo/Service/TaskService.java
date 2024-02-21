package com.example.demo.Service;

import com.example.demo.Entity.DTO.TaskRequestDto;
import com.example.demo.Entity.DTO.TaskResponseDto;
import com.example.demo.Exception.EntityNotFoundException;

import java.util.List;

public interface TaskService {
    List<TaskResponseDto> getAllTasks();

    TaskResponseDto createTask(TaskRequestDto taskDto);
    TaskResponseDto getTaskById(Long id) throws EntityNotFoundException;
    //TaskResponseDto updateTask() throws Exception;

    TaskResponseDto updateTask(TaskRequestDto taskDto) throws EntityNotFoundException;

    void deleteTask(Long id) throws EntityNotFoundException;

}
package com.example.demo.Service;

import com.example.demo.Entity.DTO.TaskRequestDto;
import com.example.demo.Entity.DTO.TaskResponseDto;
import com.example.demo.Exception.EntityNotFoundException;
import com.example.demo.Exception.TaskInputNotValidException;
import com.example.demo.Repository.TaskRepo;
import com.example.demo.Utils.InputValidatorUtils;
import com.example.demo.Utils.MappingProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepo taskRepo;

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepo.findAll().stream()
                .map(MappingProfile::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskDto) {
        if(InputValidatorUtils.isStringEmpty(taskDto.getTitle()))
            throw new TaskInputNotValidException("Title is null");
        //ici
        if( InputValidatorUtils.isValidStatus(taskDto.getStatus()))
            throw new TaskInputNotValidException("Status is null");
        if( InputValidatorUtils.isFueDateIsValid(taskDto.getDueDate()))
            throw new TaskInputNotValidException("Date not valid");
        var task = MappingProfile.mapToEntity(taskDto);
        return MappingProfile.mapToDto(taskRepo.save(task));
    }

    @Override
    public TaskResponseDto getTaskById(Long id)  throws EntityNotFoundException {
        var task = taskRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        return MappingProfile.mapToDto(task);
    }



    @Override
    public TaskResponseDto updateTask(TaskRequestDto taskDto) throws EntityNotFoundException {
        var task = taskRepo.findById(taskDto.getId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setId(taskDto.getId());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(task.getDueDate());
        return MappingProfile.mapToDto(taskRepo.save(task));
    }

    @Override
    public void deleteTask(Long id) throws EntityNotFoundException {
        var task = taskRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        taskRepo.delete(task);
    }
}

package com.example.demo.Service;

import com.example.demo.Entity.DTO.UserRequestDto;
import com.example.demo.Entity.DTO.UserResponseDto;
import com.example.demo.Entity.Status;
import com.example.demo.Entity.User;
import com.example.demo.Exception.EntityNotFoundException;
import com.example.demo.Exception.TaskInputNotValidException;
import com.example.demo.Exception.UserInputNotValidException;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Utils.InputValidatorUtils;
import com.example.demo.Utils.MappingProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<UserResponseDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(MappingProfile::mapToUserDto).toList();
    }

    public UserResponseDto createUser(UserRequestDto userDto) {
        if(!InputValidatorUtils.isValidEmail(userDto.getEmail()))
            throw new UserInputNotValidException("Email not Valid");
        if( InputValidatorUtils.isStringEmpty(userDto.getFirstName()))
            throw new UserInputNotValidException("firstName  is null");
        if( InputValidatorUtils.isStringEmpty(userDto.getLastName()))
            throw new UserInputNotValidException("LastName not valid");
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepo.save(user));
    }

    public Object getUserById(Long id) throws EntityNotFoundException {
        var user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new Object(){
            public Long id = user.getId();
            public String fullName = user.getLastName().toUpperCase() + ", " + user.getFirstName();
            public String email = user.getEmail();

            public List<Object> tasks = Collections.singletonList(user.getTasks().stream().map(task -> new Object() {
                public Long id = task.getId();
                public String title = task.getTitle();
                public String description = task.getDescription();
                public Status status = task.getStatus();
                public String dueDate = task.getDueDate().toString();
                public String createdAt = task.getCreatedAt().toString();
                public String updatedAt = task.getUpdatedAt().toString();
            }).toList());
        };
    }

    public UserResponseDto addUser(UserRequestDto userDto) {
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepo.save(user));
    }

    public UserResponseDto updateUser(Long id, User userDto) throws Exception {
        var user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return MappingProfile.mapToUserDto(userRepo.save(user));

    }
    public  UserResponseDto deleteUser(Long id)throws Exception{
        var user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepo.delete(user);
        return MappingProfile.mapToUserDto(user);
    }
}

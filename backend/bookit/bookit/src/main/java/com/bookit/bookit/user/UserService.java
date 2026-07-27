package com.bookit.bookit.user;

import com.bookit.bookit.user.dto.UpdateUserDTO;
import com.bookit.bookit.user.dto.UserDTO;
import com.bookit.bookit.user.dto.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with this id: " + id));
        return UserMapper.toDto(user);
    }

    @Transactional
    public void putUserById(int id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with this id: " + id));
        User updatedUser = UserMapper.toEntity(updateUserDTO, user);
        userRepository.save(updatedUser);
    }
}

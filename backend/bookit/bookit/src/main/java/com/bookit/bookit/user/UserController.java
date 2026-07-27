package com.bookit.bookit.user;

import com.bookit.bookit.user.dto.UpdateUserDTO;
import com.bookit.bookit.user.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        userService.putUserById(id, updateUserDTO);
        return ResponseEntity.ok().build();
    }
}

package ac2_project.example.ac2_ca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac2_project.example.ac2_ca.dto.UserDTO;
import ac2_project.example.ac2_ca.entity.User;
import ac2_project.example.ac2_ca.entity.User_Email;
import ac2_project.example.ac2_ca.repository.User_Repository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private  User_Repository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getName());
        user.setEmail(new User_Email(userDTO.getEmail()));
        return userRepository.save(user);
    }

    public  List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setName(user.getUsername());
                    dto.setEmail(user.getEmail().getEmailAddress());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
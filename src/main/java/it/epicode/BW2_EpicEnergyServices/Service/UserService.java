package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.UserDto;
import it.epicode.BW2_EpicEnergyServices.Entity.User;
import it.epicode.BW2_EpicEnergyServices.Enums.Role;
import it.epicode.BW2_EpicEnergyServices.Exceptions.UserNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAvatar(userDto.getAvatar());

        user.setRole(Role.USER);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);

        return "L'utente with id " + user.getUserId() + " correctly saved!";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException("Utente with email=" + email + " not found!"); //ERRORE UTENTE NON TROVATO
        }
    }

    public User updateUser(int id, UserDto userDto) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setAvatar(userDto.getAvatar());

            user.setRole(Role.USER);

            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("Utente with id=" + id + " not found!");
        }
    }

    public String deleteUser(int id) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return "User with id=" + id + " correctly deleted!";
        } else {
            throw new UserNotFoundException("Utente with id=" + id + " not found!");
        }
    }
}

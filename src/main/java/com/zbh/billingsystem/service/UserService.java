package com.zbh.billingsystem.service;

import com.zbh.billingsystem.entity.User;
import com.zbh.billingsystem.exception.ResourceNotFoundException;
import com.zbh.billingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ZinBhoneHtut
 */
@Service
@RequiredArgsConstructor
public class UserService implements CrudService<User> {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(long id, User user) throws ResourceNotFoundException {
        User oldUser = this.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setRole(user.getRole());
        this.save(oldUser);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findByName(userName);
    }

    public void saveAll(Iterable<User> userList) {
        this.userRepository.saveAll(userList);
    }

}

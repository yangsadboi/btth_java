package com.example.btth_java.services;


import com.example.btth_java.entity.User;
import com.example.btth_java.repository.IRoleRepository;
import com.example.btth_java.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != null && userId != null) {
            userRepository.addRoleToUser(userId, roleId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Các phương thức bổ sung cho quản lý vai trò có thể được thêm vào đây
}

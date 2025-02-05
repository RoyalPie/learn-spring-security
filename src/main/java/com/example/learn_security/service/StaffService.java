package com.example.learn_security.service;

import com.example.learn_security.entity.Staff;
import com.example.learn_security.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record StaffService(StaffRepository staffRepository, PasswordEncoder passwordEncoder) {
    public String addStaff(Staff staff) {
        if(staff.getId() != null){throw new IllegalStateException("ID already exists!");}
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staffRepository.save(staff);
        return "Thêm staff thành công!";
    }
    public String deleteUser(Long id){
        staffRepository.deleteById(id);
        return "Xóa staff thành công";
    }
    public String updateUser(Long id, Staff staff){
        Staff existingUser = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found!"));
        if (staff.getUsername() != null && !staff.getUsername().isEmpty()) {
            existingUser.setUsername(staff.getUsername());
        }
        if (staff.getAge() != null) {
            existingUser.setAge(staff.getAge());
        }
        if (staff.getRoles() != null && !staff.getRoles().isEmpty()) {
            existingUser.setRoles(staff.getRoles());
        }
        if (staff.getPassword() != null && !staff.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(staff.getPassword()));
        }
        staffRepository.save(existingUser);
        return "Cập nhật staff thành công";
    }
}

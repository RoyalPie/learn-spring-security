package com.example.learn_security.controller;

import com.example.learn_security.entity.Staff;
import com.example.learn_security.repository.StaffRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepository staffRepository;
    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        return ResponseEntity.ok(staffList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable("id") Long id) {
        return staffRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

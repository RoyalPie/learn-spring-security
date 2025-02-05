package com.example.learn_security.controller;

import com.example.learn_security.entity.Staff;
import com.example.learn_security.repository.StaffRepository;

import com.example.learn_security.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepository staffRepository;
    private final StaffService staffService;
    public StaffController(StaffRepository staffRepository, StaffService staffService) {
        this.staffRepository = staffRepository;
        this.staffService = staffService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        return ResponseEntity.ok(staffList);
    }
    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String addStaff(@RequestBody @Valid Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Validation failed";
        }
        return staffService.addStaff(staff);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable("id") Long id) {
        return staffRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        return staffService.deleteUser(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@PathVariable Long id,@RequestBody @Valid Staff staff) {
        return staffService.updateUser(id,staff);
    }
}

package com.bhoomika.universitymanagementsystem.controller;

import com.bhoomika.universitymanagementsystem.api.ApiModels.StudentData;
import com.bhoomika.universitymanagementsystem.model.Department;
import com.bhoomika.universitymanagementsystem.model.Student;
import com.bhoomika.universitymanagementsystem.repository.DepartmentRepository;
import com.bhoomika.universitymanagementsystem.repository.StudentRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository students;
    private final DepartmentRepository departments;

    public StudentController(StudentRepository students, DepartmentRepository departments) {
        this.students = students;
        this.departments = departments;
    }

    @GetMapping
    public List<StudentData> getAll() {
        return students.findAll().stream().map(StudentController::toData).toList();
    }

    @GetMapping("/{id}")
    public StudentData getById(@PathVariable Integer id) {
        return toData(findStudent(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentData create(@Valid @RequestBody StudentData data) {
        if (students.existsById(data.studentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student ID already exists");
        }
        Student student = new Student(data.studentId(), data.name(), data.city(), data.admissionYear(),
                data.cgpa(), findDepartmentOrNull(data.deptId()));
        return toData(students.save(student));
    }

    @PutMapping("/{id}")
    public StudentData update(@PathVariable Integer id, @Valid @RequestBody StudentData data) {
        Student student = findStudent(id);
        student.setName(data.name());
        student.setCity(data.city());
        student.setAdmissionYear(data.admissionYear());
        student.setCgpa(data.cgpa());
        student.setDepartment(findDepartmentOrNull(data.deptId()));
        return toData(students.save(student));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        students.delete(findStudent(id));
    }

    private Student findStudent(Integer id) {
        return students.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    private Department findDepartmentOrNull(Integer id) {
        if (id == null) return null;
        return departments.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    }

    private static StudentData toData(Student student) {
        return new StudentData(student.getStudentId(), student.getName(), student.getCity(),
                student.getAdmissionYear(), student.getCgpa(),
                student.getDepartment() == null ? null : student.getDepartment().getDeptId());
    }
}

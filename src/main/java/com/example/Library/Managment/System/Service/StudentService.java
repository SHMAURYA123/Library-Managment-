package com.example.Library.Managment.System.Service;

import com.example.Library.Managment.System.Entities.Student;
import com.example.Library.Managment.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
@Autowired
   StudentRepository studentRepository;
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student has been saved to DB";
    }
}

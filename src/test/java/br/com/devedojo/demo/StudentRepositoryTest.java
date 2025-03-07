package br.com.devedojo.demo;

import br.com.devedojo.demo.model.Student;
import br.com.devedojo.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void createShouldPersistData() {
        Student student = new Student("William", "william@devdojo.com.br");
        this.studentRepository.save(student);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getName()).isEqualTo("William");
        assertThat(student.getEmail()).isEqualTo("william@devdojo.com.br");
    }

    @Test
    public void deleteShouldRemoveData() {
        Student student = new Student("William", "william@devdojo.com.br");
        this.studentRepository.save(student);
        studentRepository.delete(student);
        assertThat(studentRepository.findById(student.getId())).isEmpty();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
        Student student = new Student("William", "william@devdojo.com.br");
        this.studentRepository.save(student);
        student.setName("William222");
        student.setEmail("william222@devdojo.com.br");
        this.studentRepository.save(student);
        student = this.studentRepository.findById(student.getId()).orElse(null);
        assertThat(student).isNotNull();
        assertThat(student.getName()).isEqualTo("William222");
        assertThat(student.getEmail()).isEqualTo("william222@devdojo.com.br");
    }

    @Test
    public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
        Student student = new Student("William", "william@devdojo.com.br");
        Student student2 = new Student("william", "william222@devdojo.com.br");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);
        List<Student> studentList = studentRepository.findByNameIgnoreCaseContaining("william");
        assertThat(studentList.size()).isEqualTo(2);
    }

    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            this.studentRepository.save(new Student());
        });
        assertThat(thrown.getMessage()).contains("O campo nome do estudante é obrigatório");
    }

    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException() {
        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            Student student = new Student();
            student.setName("William");
            this.studentRepository.save(student);
        });
    }

    @Test
    public void createWhenEmailIsNotValidShouldThrowConstraintViolationException() {
        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            Student student = new Student();
            student.setName("William");
            student.setEmail("William");
            this.studentRepository.save(student);
        });
        assertThat(thrown.getMessage()).contains("Digite um email válido");
    }
}
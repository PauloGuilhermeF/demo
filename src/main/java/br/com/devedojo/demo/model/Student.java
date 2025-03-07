package br.com.devedojo.demo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Entity
public class Student extends AbstractEntity {
    @NotEmpty(message = "O campo nome do estudante é obrigatório")
    private String name;
    @NotEmpty
    @Email(message = "Digite um email válido")
    private String email;

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public Student(Long id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

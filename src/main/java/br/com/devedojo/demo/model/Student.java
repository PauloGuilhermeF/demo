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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

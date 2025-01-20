package br.com.devedojo.demo.model;

import jakarta.persistence.Entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student extends AbstractEntity {
    @NotEmpty(message = "O campo nome do estudante é obrigatório")
    private String name;
    @NotEmpty
    @Email(message = "Digite um email válido")
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

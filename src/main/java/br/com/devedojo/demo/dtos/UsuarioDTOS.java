package br.com.devedojo.demo.dtos;

public record   UsuarioDTOS(
        String name,
        String password,
        String role
) {

    public String passwordHash() {
        return "";
    }
}

package br.com.devedojo.demo.Service.imp;

import br.com.devedojo.demo.Service.UsuarioService;
import br.com.devedojo.demo.dtos.UsuarioDTOS;
import br.com.devedojo.demo.model.User;
import br.com.devedojo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends UsuarioService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTOS salvar(UsuarioDTOS usuarioDTOS) {
        User usuariojaExiste = userRepository.findByName(usuarioDTOS.name());
        if (usuariojaExiste != null) {
            throw new RuntimeException("Usuario ja existe");
        }

        var passwordHash = passwordEncoder.encode(usuarioDTOS.password());


        User entity = new User(usuarioDTOS.name(), usuarioDTOS.role(), usuarioDTOS.passwordHash());
        User novoUsuario = userRepository.save(entity);
        return new UsuarioDTOS(novoUsuario.getName(), novoUsuario.getLogin(), novoUsuario.getPassword());
    }
}

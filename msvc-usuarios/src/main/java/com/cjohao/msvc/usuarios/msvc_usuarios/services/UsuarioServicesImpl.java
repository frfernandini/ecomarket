package com.cjohao.msvc.usuarios.msvc_usuarios.services;

import com.cjohao.msvc.usuarios.msvc_usuarios.exceptions.UsuarioExceptions;
import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import com.cjohao.msvc.usuarios.msvc_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioServicesImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuarios> findAll() {return usuarioRepository.findAll();}

    @Override
    public Usuarios findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioExceptions("El usuario con id "+id+" no existe")
        );
    }

    @Override
    public Usuarios findByRun(String run) {
        return usuarioRepository.findByRun(run).orElseThrow(
                () -> new UsuarioExceptions("El usuario con run "+run+" no existe")
        );
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        if(usuarioRepository.findByRun(usuario.getRun()).isPresent()){
            throw new UsuarioExceptions("El usuario con este RUN ya existe");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {usuarioRepository.deleteById(id);}

    @Override
    public Usuarios update(Long id, Usuarios usuarios) {
        return usuarioRepository.findById(id).map(u->{
            u.setRun(usuarios.getRun());
            u.setNombresUsuario(usuarios.getNombresUsuario());
            u.setApellidosUsuario(usuarios.getApellidosUsuario());
            u.setCorreoUsuario(usuarios.getCorreoUsuario());
            u.setContraseña(usuarios.getContraseña());
            u.setRegistroUsuario(usuarios.getRegistroUsuario());
            return usuarioRepository.save(u);
        }).orElseThrow(
                () -> new UsuarioExceptions("El usuario con id "+id+" no existe")
        );
    }
}

package com.cjohao.msvc.usuarios.msvc_usuarios.services;

import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;

import java.util.List;

public interface UsuarioService {
    List<Usuarios> findAll();
    Usuarios findById(Long id);
    Usuarios findByRun(String run);
    Usuarios save(Usuarios usuarios);
    void deleteById(Long id);
    Usuarios update(Long id,Usuarios usuarios);
}

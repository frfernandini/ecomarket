package com.cjohao.msvc.usuarios.msvc_usuarios.repositories;

import com.cjohao.msvc.usuarios.msvc_usuarios.models.Usuarios;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByRun(String run);
}

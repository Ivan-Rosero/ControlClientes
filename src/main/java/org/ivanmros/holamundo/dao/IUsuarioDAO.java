package org.ivanmros.holamundo.dao;

import org.ivanmros.holamundo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}

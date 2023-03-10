package org.ivanmros.holamundo.dao;

import org.ivanmros.holamundo.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPersonaDAO extends JpaRepository<Persona, Long> {


}

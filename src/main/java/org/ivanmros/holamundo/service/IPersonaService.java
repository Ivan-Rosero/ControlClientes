package org.ivanmros.holamundo.service;

import org.ivanmros.holamundo.domain.Persona;

import java.util.List;

public interface IPersonaService {
    public List<Persona> listaPersonas();

    public void guardar(Persona persona);
    public void eliminar(Persona persona);
    public Persona encontrarPersona(Persona persona);
}

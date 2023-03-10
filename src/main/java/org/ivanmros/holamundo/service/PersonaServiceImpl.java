package org.ivanmros.holamundo.service;

import org.springframework.transaction.annotation.Transactional;
import org.ivanmros.holamundo.dao.IPersonaDAO;
import org.ivanmros.holamundo.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService{

    @Autowired
    private IPersonaDAO iPersonaDAO;
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listaPersonas() {
        return (List<Persona>) iPersonaDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
         iPersonaDAO.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        iPersonaDAO.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return iPersonaDAO.findById(persona.getIdPersona()).orElse(null);
    }
}

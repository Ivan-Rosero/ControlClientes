package org.ivanmros.holamundo.service;

import lombok.extern.slf4j.Slf4j;
import org.ivanmros.holamundo.dao.IUsuarioDAO;
import org.ivanmros.holamundo.domain.Rol;
import org.ivanmros.holamundo.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioDAO iUsuarioDAO;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioDAO.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }

//        recorrerá los roles de los usuarios
        var roles = new ArrayList<GrantedAuthority>();

        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        //Se recupera el usuario usando el User de tipo Spring
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}

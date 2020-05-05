package com.security.codexy.app.repositories;

import com.security.codexy.app.entities.Authority;
import org.springframework.data.repository.CrudRepository;

/**
 * Esta interfaz define consultas genericas para realizar las operaciones CRUD de la BD,
 * con ayuda de la interfaz CrudRepository.
 */
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
} // end repository

package com.security.codexy.app.services;

import com.security.codexy.app.entities.Authority;

import java.util.List;

/**
 * Define los métodos CRUD que podrá usar la clase de servicio,
 * Practicamente emvuelve los métodos del CrudRepository con un mejor nombre
 * ademas de poderlos implementar en la clase de servicio
 */
public interface IAuthorityService {

    /**
     * Busca y retorna todos los registros que haya en ld tabla Authorities
     * @return Regresa una lista con objetos de tipo Authority
     */
    List<Authority> findAllAuthorities();

    /**
     * Retorna la autorización dependiendo el id dado
     * @param idAuthority requiere un id para identificar que autoridad buscara
     * @return regresa un objeto de tipo Authority
     */
    Authority findAuthorityById(int idAuthority);

    /**
     * Guarda un objeto de tipo Authority en la BD, reemplazando los valores
     * @param authority Requiere el objeto authority para guardarlo
     */
    void saveAuthority(Authority authority);

    /**
     * Eliminar de la BD la autoridad tanto de la table Authority como de la tabla transitiva
     * @param idAuthority Requiere un parámetro de tipo <b>String</b>
     */
    void deleteAuthority(int idAuthority);

} // end interface service

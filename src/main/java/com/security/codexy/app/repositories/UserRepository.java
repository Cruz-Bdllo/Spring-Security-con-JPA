package com.security.codexy.app.repositories;

import com.security.codexy.app.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Esta interfaz define consultas genericas y personalizadas para realizar un CRUD de la BD,
 * con ayuda de la interfaz CrudRepository.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Definimos una consulta personalizada para buscar un registro dado un campo diferente al id
     * @param rfc requiere un párametro de tipo String
     * @return puede retornar un onjeto <b>User</b> de encontrarlo en la BD de lo contrario regresara <b>null</b>
     */
    @Query("SELECT u FROM User u WHERE u.rfc = ?1")
    Optional<User> findUserByRfc(String rfc);

    /**
     * Méthodo para eliminar un registro de la BD dado el RFC del usuario
     * @param rfc requiere un parámetro de tipo String siendo este la primary key de la tabla
     */
    @Query("DELETE FROM User u WHERE u.rfc = ?1")
    void deleteByRfc(String rfc);

} // end repository

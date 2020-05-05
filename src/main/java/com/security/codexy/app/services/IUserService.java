package com.security.codexy.app.services;

import com.security.codexy.app.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Definir los métodos CRUD que podrá usar la clase de servicio,
 * Practicamente emvuelve los métodos del CrudRepository con un mejor nombre
 * ademas de poderlos implementar en la clase de servicio
 */
public interface IUserService {

    /**
     * @return Devuelve una lista de todos los usuarios encontrados en la BD
     */
    List<User> findAllUsers();

    /**
     * @param rfc requiere un parametro de tipo String, siendo este la primary key de la tabla
     * @return Retorna un <b>User</b> de lo contrario regresara <b>null</b>
     */
    Optional<User> findUserByRfc(String rfc);

    /**
     * El cual guardara el contenido del objeto en la BD
     * @param user requiere un parametro de tipo <b>User</b>
     */
    void saveUser(User user);

    /**
     * Elimina tanto de la tabla <b>users</b> como de la transitiva el registro que pertenesca al rfc dado
     * @param rfc requiere un parámetro de tipo String
     */
    void deleteUserByRfc(String rfc);

} // end service

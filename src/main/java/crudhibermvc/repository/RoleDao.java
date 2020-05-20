package crudhibermvc.repository;

import crudhibermvc.entity.Role;

/**
 * @author Pavel Tokarev, 20.05.2020
 */

public interface RoleDao {

    Role findById(Long id);
}

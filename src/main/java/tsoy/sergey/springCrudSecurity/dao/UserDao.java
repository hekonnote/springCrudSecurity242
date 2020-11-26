package tsoy.sergey.springCrudSecurity.dao;

import tsoy.sergey.springCrudSecurity.models.Role;
import tsoy.sergey.springCrudSecurity.models.User;

import java.util.List;

//В приложении должна быть страница,
//  - на которую выводятся все юзеры с возможностью
//  - добавлять,
//  - удалять
//  - изменять юзера.

public interface UserDao {

    List<User> getAll();

    void add(User user);

    void delete(Long id);

    User getById(Long id);

    User getByName(String name);

    void update(Long id, User updatedUser);

}

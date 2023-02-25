package org.sapient.tbs.repository;

import org.sapient.tbs.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

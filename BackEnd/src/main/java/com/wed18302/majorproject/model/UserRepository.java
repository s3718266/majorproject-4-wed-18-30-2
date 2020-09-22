package com.wed18302.majorproject.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.ID=(:pId)")
    public User findByID(@Param("pId") int id);
	public User findByEMAIL(String email);
}

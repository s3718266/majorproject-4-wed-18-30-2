package com.wed18302.majorproject.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEMAIL(String email);
}

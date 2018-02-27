package com.color_it.backend.repositories;

import com.color_it.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<UserEntity, Long> {

}

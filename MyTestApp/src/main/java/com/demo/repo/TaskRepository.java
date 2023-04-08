package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.TaskEntity;
import com.demo.entity.UserEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{

}

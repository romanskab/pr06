package com.oktenweb.pr06.dao;

import com.oktenweb.pr06.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDAO extends JpaRepository<Application, Integer> {
}

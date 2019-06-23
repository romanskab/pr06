package com.oktenweb.pr06.dao;

import com.oktenweb.pr06.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Integer> {
}

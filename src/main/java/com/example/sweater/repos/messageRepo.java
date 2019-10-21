package com.example.sweater.repos;

import com.example.sweater.domain.message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface messageRepo extends CrudRepository<message, Integer> {
    List<message> findByTag(String tag);
}



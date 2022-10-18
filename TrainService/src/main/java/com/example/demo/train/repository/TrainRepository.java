package com.example.demo.train.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.train.model.Train;

@Repository
public interface TrainRepository extends MongoRepository<Train, Integer>{

}


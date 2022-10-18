package com.example.demo.train.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.train.controller.TrainController;
import com.example.demo.train.exception.NoProperDataException;
import com.example.demo.train.exception.TrainNotFoundException;
import com.example.demo.train.model.Train;
import com.example.demo.train.repository.TrainRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainRepository trainRepository;
	Logger logger = LoggerFactory.getLogger(TrainController.class);

	@Override
	public List<Train> getAllTrains() throws TrainNotFoundException {
		
		List<Train> trains= trainRepository.findAll();
		logger.debug("Trains are :{}");
		
		return trains;
		
	}

	@Override
	public Train getTrainById(int id) throws TrainNotFoundException {
		
		logger.info("start");
		return trainRepository.findById(id)
				.orElseThrow(()->  new TrainNotFoundException("Train with the id "+id+" doesn't exist"));
	}

	@Override
	public Train addTrain(Train train) throws NoProperDataException {
		logger.info("start");
		if(train!=null) 
		{
			trainRepository.save(train);
			logger.debug("added train");
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		return train;
	}
	

	@Override
	public String deleteTrain(Integer id) throws TrainNotFoundException {
		logger.info("start");
		Optional<Train> pop=trainRepository.findById(id);
		
		if(!pop.isPresent()) {
			throw new TrainNotFoundException("Train with the id "+id+" doesn't exist");
		}else {
			trainRepository.deleteById(id);
			logger.debug(" train deleted successfully {}");
		}
		logger.info("End");
		return id+ " train  deleted successfully....";
	}
	

	@Override
	public Train updateTrain(Train trains, int trainId) throws TrainNotFoundException {
		Train train=trainRepository.findById(trainId).orElseThrow(()-> new TrainNotFoundException("train not "+trainId));
		trains.setTrainId(trains.getTrainId());
		trains.setTrainName(trains.getTrainName());
     	trains.setTrainTime(trains.getTrainTime());
		trains.setTrainAvailable(trains.getTrainAvailable());
		trains.setTrainNumber(trains.getTrainNumber());
		trains.setTrainCost(trains.getTrainCost());
		
		final Train updatedTrain = trainRepository.save(trains);
		return updatedTrain;
	}

	
	
	}





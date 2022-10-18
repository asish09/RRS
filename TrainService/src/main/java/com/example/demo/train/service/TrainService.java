package com.example.demo.train.service;

import java.util.List;

import com.example.demo.train.exception.NoProperDataException;
import com.example.demo.train.exception.TrainNotFoundException;
import com.example.demo.train.model.Train;


public interface TrainService {
	public  List<Train> getAllTrains() throws  TrainNotFoundException;
	public Train getTrainById( int trainId) throws TrainNotFoundException;
	public Train addTrain( Train train)  throws NoProperDataException;
	public String deleteTrain(Integer id) throws TrainNotFoundException;
	public Train updateTrain(Train trains, int trainId) throws TrainNotFoundException;
}

package com.example.demo.train.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.train.exception.NoProperDataException;
//import com.cg.opna.train.exception.NoProperDataException;
import com.example.demo.train.exception.TrainNotFoundException;
import com.example.demo.train.model.Train;
import com.example.demo.train.repository.TrainRepository;

@SpringBootTest

public class TrainServiceTest {
	

	@InjectMocks
	private TrainServiceImpl trainServiceImpl;
	
	@Mock
	private TrainRepository trainRepository;
	
	@Test
	void testServiceNotNull() {
		assertThat(trainServiceImpl).isNotNull();
	}
	
	@Test
	void testRepositoryNotNull() {
		assertThat(trainRepository).isNotNull();
	}
	
	@Test
	void testGetAllTrains() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);
		Train p2= new Train(2001,10, "brown","round",20, 100);
		List<Train> trainlist=new ArrayList<Train>();
		trainlist.add(p1);
		trainlist.add(p2);
		when(trainRepository.findAll()).thenReturn(trainlist);
		assertEquals(trainlist,trainServiceImpl.getAllTrains());
		
	}
	
	@Test
	void testGetTrainById() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);
		when(trainRepository.findById(101)).thenReturn(Optional.of(p1));
		assertEquals(p1,trainServiceImpl.getTrainById(101));
	}
	
	@Test
	void testGetTrainByInvalidId() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);
		when(trainRepository.findById(2000)).thenReturn(Optional.of(p1));
		try {
			assertThat(trainServiceImpl.getTrainById(2001)).as("Train with the id 2001 doesn't exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddTrainException() throws TrainNotFoundException {
		
		Train p1= null;//new Train(2000,9, "red","oval",10, 90);
		when(trainRepository.findById(2000)).thenReturn(Optional.of(p1));
	try {
		trainServiceImpl.addTrain(p1);
	}catch(Exception e) {
		assertEquals("Please fill fields", e.getMessage());
	}
	}
	
	@Test
	void testAddTrain() throws TrainNotFoundException, NoProperDataException {
		Train p1= new Train(2000,9, "Rajyarani","yes",10001, 90);
		when(trainRepository.findById(2000)).thenReturn(Optional.of(p1));
		Train t1=trainServiceImpl.addTrain(p1);
		System.out.println(t1.getTrainName() + "  " +t1.get_id());
		assertEquals("Rajyarani",t1.getTrainName());
	
	}	
	
	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	@Test
	void testAddTrainAlreadyExists() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);
		when(trainRepository.findById(101)).thenReturn(Optional.of(p1));
	try {
		((List<Train>) assertThat(trainServiceImpl.addTrain(p1)))
		.contains("Train with the id "+p1.get_id()+" already exist");
	}catch(Exception e) {
		
	}
	}
//	
	@Test
	void testupdateTrain() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);	
		when(trainRepository.findById(2000)).thenReturn(Optional.of(p1));
		assertThat(trainServiceImpl.updateTrain(p1))
		.contains("updated successfully....");
	
	}
	
	@Test
	void testupdateTrainDoesnotExists() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);	
		when(trainRepository.findById(10)).thenReturn(Optional.of(p1));
	try {
		assertThat(trainServiceImpl.updateTrain(p1))
		.contains("Train with the id "+p1.get_id() +" doesn't exist for update");
	}catch(Exception e) {
		
	}
	}
	
	@Test
	void testDeleteTrainById() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);	

		when(trainRepository.findById(101)).thenReturn(Optional.of(p1));
		assertThat(trainServiceImpl.deleteTrain(101))
		.contains("deleted successfully....");
	}
	
	@Test
	void testDeleteTrainByInvalidId() throws TrainNotFoundException {
		Train p1= new Train(2000,9, "red","oval",10, 90);	
		when(trainRepository.findById(101)).thenReturn(Optional.of(p1));
		try {
			assertThat(trainServiceImpl.deleteTrain(2000))
			.contains("Train with the id "+p1.get_id()+" doesn't exist");
		}catch(Exception e) {
			
		}
	}
	
	
	}

	



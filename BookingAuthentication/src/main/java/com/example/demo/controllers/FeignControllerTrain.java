package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.FeignClientUtilTrain;
import com.example.demo.exception.NoProperDataException;
import com.example.demo.exception.TrainNotFoundException;
import com.example.demo.models.Train;
import com.example.demo.services.SequenceGeneratorService;


@RestController
@RequestMapping("/train")
public class FeignControllerTrain  {
	
	@Autowired
	private FeignClientUtilTrain feigntrain;
	
	
	@Autowired
	private SequenceGeneratorService service;
	
	@GetMapping("/alltrains") 
	public ResponseEntity<List<Train>> getAllTrain(@RequestHeader("Authorization") String token) throws TrainNotFoundException
	{
		
		return feigntrain.getAllTrain(token);
		
	}
	
	@GetMapping("/trains/{id}")
	public ResponseEntity<Train> getTrainById(@Valid @RequestHeader("Authorization") String token, @PathVariable  Integer id)
	throws TrainNotFoundException{
		return feigntrain.getTrainById(token,id);
	}
	
	@PostMapping("/addtrains") 
	public ResponseEntity<Train> addTrain(@Valid @RequestBody Train train)  throws NoProperDataException
	{
		
		train.setTrainId(service.getSequenceNumberForTrain(Train.SEQUENCE_NAME));
		return feigntrain.addTrain(train);
	}


   @PutMapping("/updatetrain")
    public String updateTrain(@Valid  @RequestHeader("Authorization") String token,@RequestBody Train train) throws TrainNotFoundException{
	     return feigntrain.updateTrain(token,train);
   }
	@DeleteMapping(path="/trains/{id}")
	public ResponseEntity<String> deleteTrain(@Valid @RequestHeader("Authorization") String token, @PathVariable int id) throws TrainNotFoundException {
			return feigntrain.deleteTrain(token,id);
	}

}

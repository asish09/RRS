package com.example.demo.train.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.train.exception.NoProperDataException;
import com.example.demo.train.exception.TrainNotFoundException;
import com.example.demo.train.model.Train;
import com.example.demo.train.service.SequenceGeneratorService;
import com.example.demo.train.service.TrainServiceImpl;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/train")
@Slf4j
public class TrainController {
	@Autowired
	private TrainServiceImpl trainServiceimpl;

	@Autowired
	private SequenceGeneratorService service;

	
	@GetMapping("/alltrains") 
	public ResponseEntity<List<Train>> getAllTrains() throws TrainNotFoundException
	{
		List<Train> trains=trainServiceimpl.getAllTrains();
		log.info("starting  of get mapping");
	
		if(trains.size()>0) {
			log.debug("trains are {}"
					+ trains);
		 return new  ResponseEntity<>(trains,HttpStatus.OK); 
		}
		else {
			log.debug(" no trains found ");
			 return new  ResponseEntity<>(trains,HttpStatus.NO_CONTENT); 
		}
		
	}
	
	@GetMapping("/trains/{id}")
	public ResponseEntity<Train> getTrainById(@PathVariable  Integer id)
	throws TrainNotFoundException{
		
		Train trains= trainServiceimpl.getTrainById(id);
		if(trains!=null){
		  return ResponseEntity.ok().body(trains);
		}
		  else {
			return new   ResponseEntity<Train>(trains,HttpStatus.NOT_FOUND);
		  }

	}
	
	@PostMapping("/addtrains") 
	public ResponseEntity<Train> addTrain(@Valid @RequestBody Train train)  throws NoProperDataException
	{
		 if(train!=null) {
			
			train.setTrainId(service.getSequenceNumberForTrain(Train.SEQUENCE_NAME));
			trainServiceimpl.addTrain(train);
			log.error("added train");
			return new ResponseEntity<>(train,HttpStatus.CREATED);
			
		}
		 else
		 {
			 throw new NoProperDataException("Please fill fields");
			
	}
		
		
	}


	
	@DeleteMapping(path="/trains/{id}")
	public ResponseEntity<String> deleteTrain(@PathVariable int id) throws TrainNotFoundException {
		int count=1;
		for(int i=1;i>=count;count++)
		{
			if(count==1)
			{
			try {
				trainServiceimpl.deleteTrain(id);
			} catch (TrainNotFoundException e) {
				throw new TrainNotFoundException("Train with the id "+id+" doesn't exist");
				//log.error(e.getMessage());
			}
			}
			else
			{
				
				log.info("id not found");
			}
			
		}
		
		
			return ResponseEntity.ok(" deleted operation done ");
	
	}
	


@PutMapping("/updatetrain/{id}")
public Train updateTrain(@Valid @RequestBody Train train,@PathVariable int trainId) throws TrainNotFoundException{
	
	Train trains=trainServiceimpl.updateTrain(train,trainId);
	return trains;
}

}
	
	




	




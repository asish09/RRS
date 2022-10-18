package com.example.demo.util;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.models.Train;


@FeignClient(value="Train-Service",url="http://localhost:8085/train")
public interface FeignClientUtilTrain {
	
	@GetMapping("/alltrains") 
	public ResponseEntity<List<Train>> getAllTrain(@RequestHeader("Authorization") String token);
	
	@GetMapping("/trains/{id}")
	public ResponseEntity<Train> getTrainById(@RequestHeader("Authorization") String token,Integer id);
	
	
	@PostMapping("/addtrains") 
	public ResponseEntity<Train> addTrain(Train train);

	
	@DeleteMapping(path="/trains/{id}")
	public ResponseEntity<String> deleteTrain( @RequestHeader("Authorization") String token,int id);


    @PutMapping("/updatetrain")
          public String updateTrain(@RequestHeader("Authorization") String token,Train train);

	


}

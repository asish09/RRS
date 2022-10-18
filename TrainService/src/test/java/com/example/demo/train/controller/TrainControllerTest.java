package com.example.demo.train.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.train.model.Train;
import com.example.demo.train.service.TrainServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainControllerTest {
	
	@MockBean
	private TrainServiceImpl trainServiceImpl;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	void testTrainServiceNotNull() {
		assertThat(trainServiceImpl).isNotNull();
	}
	
	@Test
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	
	@SuppressWarnings({ "unchecked", "null" })
	@Test
	void testShowTrains() throws Exception {
		Train p1= new Train(2000,9, "red","oval",10, 90);
		Object p2= new Train(2001,10, "brown","round",20, 100);
		List<Train> trainList=new ArrayList<Train>();
		List<com.example.demo.train.controller.Train> train = null;
		train.addAll((Collection<? extends com.example.demo.train.controller.Train>) p1);
		train.add((com.example.demo.train.controller.Train) p2);
		when(trainServiceImpl.getAllTrains()).thenReturn(trainList);
	mockMvc.perform(get("/train/alltrains"))
	.andExpect(status().isOk())
	.andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$[*]", hasSize(2)))
	.andExpect(jsonPath("$[0].trainId").value(2000))
	.andExpect(jsonPath("$[0].trainTime").value(9))
	.andExpect(jsonPath("$[0].trainAvailable").value("oval"))
	.andExpect(jsonPath("$[0].trainNumber").value(10))
	.andExpect(jsonPath("$[0].trainName").value("red"))
	.andExpect(jsonPath("$[0].trainCost").value(90));
		
	}
	
	

//	@Test
//	void testShowTrainsById() throws Exception {
//		Train p2= new Train(2001,10, "brown","round",20, 100);
//			when(trainServiceImpl.getTrainById(2001)).thenReturn(p2);
//	mockMvc.perform(get("/train/trains/2001"))
//	.andExpect(status().isOk())
//	.andExpect(content().contentType("application/json"))
//	.andExpect(jsonPath("$[0].trainId").value(2001))
//	.andExpect(jsonPath("$[0].trainTime").value(10))
//	.andExpect(jsonPath("$[0].trainAvailable").value("round"))
//	.andExpect(jsonPath("$[0].trainNumber").value(20))
//	.andExpect(jsonPath("$[0].trainName").value("brown"))
//	.andExpect(jsonPath("$[0].trainCost").value(100));
//		
//	}
	
	
	@Test
	void testShowTrainInvalidId() throws Exception {
		Train p1= new Train(2000,9, "red","oval",10, 90);
	when(trainServiceImpl.getTrainById(2000)).thenReturn(p1);
	MvcResult result=mockMvc.perform(get("/train/trains/2"))
	.andExpect(status().isOk())
	.andReturn();
	assertThat(result.getResponse().toString())
	.as("Train with the id 2 doesn't exist");
		
	}
	
	
	@Test
	void testDeleteTrainById() throws Exception {
	
		new Train(2000,9, "red","oval",10, 90);
		String s="deleted successfully....";
	when(trainServiceImpl.deleteTrain(2000)).thenReturn(s);
	mockMvc.perform(delete("/train/trains/2000"))
	.andExpect(status().isOk());
		
	}

	@Test
	void testdeleteTrainInvalidId() throws Exception {
		new Train(2000,9, "red","oval",10, 90);
		String s="deleted successfully....";
		when(trainServiceImpl.deleteTrain(20)).thenReturn(s);
	MvcResult result=mockMvc.perform(delete("/train/Trains/20"))
	.andExpect(status().isOk())
	.andReturn();
	assertThat(result.getResponse().toString())
	.as("Train with the id 20 doesn't exist");
		
	}
	
	

	
	@Test
	void testAddTrain() throws Exception {
		Train p1 = new Train(2002,9, "red","oval",10, 90);
		when(trainServiceImpl.addTrain(p1)).thenReturn(p1);
//		pdto.setTrainId(service.getSequenceNumberForTrain(Train.SEQUENCE_NAME));
//		trainServiceimpl.addTrain(pdto);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
		String reqstr=writer.writeValueAsString(p1);
	mockMvc.perform(post("/train/addtrains/")
			.contentType("application/json")
			.content(reqstr))
	.andExpect(status().isCreated());
	
		
	}
	
	@Test
	void testUpdateTrain() throws Exception {
		Train p1 = new Train(2002,9, "red","oval",10, 90);
		String s="updated successfully....";
		when(trainServiceImpl.updateTrain(p1)).thenReturn(s);
		
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
		String reqstr=writer.writeValueAsString(p1);
	mockMvc.perform(put("/train/updatetrain/")
			.contentType("application/json")
			.content(reqstr))
	.andExpect(status().isOk());
//	.andExpect(content().string(s));
		
	}
}

	




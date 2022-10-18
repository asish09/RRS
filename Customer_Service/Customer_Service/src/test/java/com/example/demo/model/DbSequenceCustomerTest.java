package com.example.demo.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.DbSequenceCustomer;


public class DbSequenceCustomerTest {


	@BeforeEach
	public void after() {
		DbSequenceCustomer db = new DbSequenceCustomer();
		
		db.setId("500");
		db.setSeq(500);
		
	}
	@Test
	public void Seq()
	{
		DbSequenceCustomer db = new DbSequenceCustomer();
		DbSequenceCustomer db1 = new DbSequenceCustomer();
		assertNotEquals(db, db1);
	}
	@Test
	public void testGetIdAndSequence()
	{
		DbSequenceCustomer db = new DbSequenceCustomer();
		db.setId("500");
		db.setSeq(500);
		assertEquals("500",db.getId());
		assertEquals(500, db.getSeq());
	}
}
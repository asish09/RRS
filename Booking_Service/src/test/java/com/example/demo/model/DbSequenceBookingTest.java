package com.example.demo.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DbSequenceBookingTest {


	@BeforeEach
	public void after() {
		DbSequenceBooking db = new DbSequenceBooking();
		
		db.setId("500");
		db.setSeq(500);
		
	}
	@Test
	public void Seq()
	{
		DbSequenceBooking db = new DbSequenceBooking();
		DbSequenceBooking db1 = new DbSequenceBooking();
		assertNotEquals(db, db1);
	}
	@Test
	public void testGetIdAndSequence()
	{
		DbSequenceBooking db = new DbSequenceBooking();
		db.setId("500");
		db.setSeq(500);
		assertEquals("500",db.getId());
		assertEquals(500, db.getSeq());
	}
}
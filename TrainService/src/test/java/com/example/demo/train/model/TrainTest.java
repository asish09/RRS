package com.example.demo.train.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TrainTest {
    

        Train p1;
        @BeforeEach
        public void before() {
            @SuppressWarnings("unused")
			Train p1 = new Train(2000,9, "red","oval",10, 90);
        }
        
        
        @AfterEach
        public void after() {
            p1=null;
        }
        
//      @Test
//      void testGetTrainId() {
//          assertEquals(2000, p1.getTrainId());
//      }
//
//      @Test
//      void testGetTrainTime() {
//          assertEquals(9, p1.getTrainTime());
//      }
//
//      @Test
//      void testGetTrainName() {
//          assertEquals("red", p1.getTrainName());
//      }
//      
//      @Test
//         void testGetTrainAvailable() {
//          assertEquals("oval",p1.getTrainAvailable());
//      }
//      
//      @Test
//      void testGetTrainCost() {
//          assertEquals(90, p1.getTrainCost());
//      }
//
//      @Test
//      void testGetTrainNumber() {
//          assertEquals(10, p1.getTrainNumber());
//      }
//      
//      
//      @Test
//      void testSetTrainId() {
//          p1.setTrainId(2001);
//          assertEquals(2000, p1.getTrainId());
//      }
//
//      @Test
//      void testSetTrainTime() {
//          p1.setTrainTime(6);
//          assertEquals(6, p1.getTrainTime());
//      }
//
//      @Test
//      void testSetTrainAvailable() {
//          p1.setTrainAvailable("round");
//          assertEquals("round",p1.getTrainAvailable());
//      }
//
//      @Test
//      void testSetTrainName() {
//          p1.setTrainAvailable("red");
//          assertEquals("red",p1.getTrainName());
//      }
//      @Test
//      void testSetTrainCost() {
//          p1.setTrainCost(100);
//          assertEquals(100, p1.getTrainCost());
//      }
//      
//      @Test
//      void testSetTrainNumber() {
//          p1.setTrainNumber(18);
//          assertEquals(18, p1.getTrainNumber());
//      }

    }




/*
 * 
 * Copyright 2014 LATAM Tech Office <maltron at redhat dot com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.redhat.lto.testdrive.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mauricio
 */
public class SurveyTest {
    
    public SurveyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInstanceCreation() {
        
        Answer answer1 = new Answer(1, "John");
        Answer answer2 = new Answer(2, "Melany");
        Answer answer3 = new Answer(3, "Paul");
        Question question1 = new Question(1, "What is your name ?", QuestionType.SINGLE,
            new Answer[] {answer1, answer2, answer3});
        
        
        Answer answer4 = new Answer(1, "Male");
        Answer answer5 = new Answer(2, "Female");
        Question question2 = new Question(2, "What is your sex ?", QuestionType.RANK,
                new Answer[] {answer4, answer5});
        
        Survey survey = new Survey("Basic Question", new Question[] { question1, question2 });
        
        
        System.out.printf(">>> Answer 1: %s\n", answer1.toString());
        System.out.printf(">>> Question1: %s\n", question1);
        System.out.printf(">>> Survey: %s\n", survey);
    }

//    /**
//     * Test of getID method, of class Survey.
//     */
//    @Test
//    public void testGetID() {
//        System.out.println("getID");
//        Survey instance = new Survey();
//        ObjectId expResult = null;
//        ObjectId result = instance.getID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setID method, of class Survey.
//     */
//    @Test
//    public void testSetID() {
//        System.out.println("setID");
//        ObjectId ID = null;
//        Survey instance = new Survey();
//        instance.setID(ID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getName method, of class Survey.
//     */
//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        Survey instance = new Survey();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setName method, of class Survey.
//     */
//    @Test
//    public void testSetName() {
//        System.out.println("setName");
//        String name = "";
//        Survey instance = new Survey();
//        instance.setName(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getQuestion method, of class Survey.
//     */
//    @Test
//    public void testGetQuestion() {
//        System.out.println("getQuestion");
//        Survey instance = new Survey();
//        Question expResult = null;
//        Question result = instance.getQuestion();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setQuestion method, of class Survey.
//     */
//    @Test
//    public void testSetQuestion() {
//        System.out.println("setQuestion");
//        Question question = null;
//        Survey instance = new Survey();
//        instance.setQuestion(question);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAnswers method, of class Survey.
//     */
//    @Test
//    public void testGetAnswers() {
//        System.out.println("getAnswers");
//        Survey instance = new Survey();
//        Answer[] expResult = null;
//        Answer[] result = instance.getAnswers();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAnswers method, of class Survey.
//     */
//    @Test
//    public void testSetAnswers() {
//        System.out.println("setAnswers");
//        Answer[] answers = null;
//        Survey instance = new Survey();
//        instance.setAnswers(answers);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hashCode method, of class Survey.
//     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        Survey instance = new Survey();
//        int expResult = 0;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of equals method, of class Survey.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Survey instance = new Survey();
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Survey.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Survey instance = new Survey();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toJSON method, of class Survey.
//     */
//    @Test
//    public void testToJSON() {
//        System.out.println("toJSON");
//        Survey instance = new Survey();
//        JsonObjectBuilder expResult = null;
//        JsonObjectBuilder result = instance.toJSON();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}

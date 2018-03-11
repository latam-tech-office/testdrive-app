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
 * Testing a specific Survey Object
 * 
 * @author Mauricio "Maltron" Leal <maltron at redhat dot com>
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
        
        Survey one = new Survey("One", new Question[] {
            new Question(1, "First question ?", QuestionType.SINGLE, new Answer[] {
                new Answer(1, "First Answer"),
                new Answer(2, "Second Answer")
            }),
            new Question(2, "Second question ?", QuestionType.MULTIPLE, new Answer[] {
                new Answer(1, "First Answer"),
                new Answer(2, "Second Answer")
            })
        });
        System.out.printf(">>> One:  %s\n", one);
        
        Survey survey = new Survey("TestDrive OpenShift", new Question[] { 
            new Question(1, "What is your experience with Container technology ?", 
                QuestionType.SINGLE, new Answer[] {
                    new Answer(1, "Experienced"),
                    new Answer(2, "Just playing"),
                    new Answer(3, "I heard about it"),
                    new Answer(4, "Never heard of it")
                }),
            new Question(2, "What container technology are you using currently ?", 
                QuestionType.MULTIPLE, new Answer[] {
                    new Answer(1, "Docker"),
                    new Answer(2, "Rocket"),
                    new Answer(3, "CRI-O")
                }),
            new Question(3, "What do you consider the most important ?", 
                QuestionType.RANK, new Answer[] {
                    new Answer(1, "Knowledge"),
                    new Answer(2, "Experience"),
                    new Answer(3, "Having a specialist around"),
            }),
            new Question(4,
                "Please tell us where we should improve", 
                QuestionType.OPEN)            
        });
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

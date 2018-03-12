//
//  ViewController.swift
//  survey2
//
//  Created by Mauricio Leal on 3/11/18.
//  Copyright © 2018 Mauricio "Maltron" Leal. All rights reserved.
//

import UIKit

class SurveyJSON: Codable {
    let _id: String?
    let name: String
    var questions: [QuestionJSON]
    let numberOfQuestions: Int
}

class QuestionJSON: Codable, Comparable {
    let order: Int
    let question: String
    let type: TypeJSON
    var answers: [AnswerJSON]?
    
    static func == (lhs: QuestionJSON, rhs: QuestionJSON) -> Bool {
        return  lhs.order == rhs.order
    }
    
    static func < (lhs: QuestionJSON, rhs: QuestionJSON) -> Bool {
        return lhs.order < rhs.order
    }
}

enum TypeJSON: String, Codable {
    case single = "SINGLE"
    case multiple = "MULTIPLE"
    case rank = "RANK"
    case open = "OPEN"
}

class AnswerJSON: Codable, Comparable {
    let order: Int
    let answer: String
    
    static func == (lhs: AnswerJSON, rhs: AnswerJSON) -> Bool {
        return  lhs.order == rhs.order
    }
    
    static func < (lhs: AnswerJSON, rhs: AnswerJSON) -> Bool {
        return lhs.order < rhs.order
    }
}

protocol QuestionDelegate {
    func jumpNextQuestion()
    func doneSurvey()
}

class QuestionManager {
    static let shared: QuestionManager = QuestionManager()
    
    func parseJSONContent(data: Data?) -> SurveyJSON? {
        guard let data = data else { return nil }
        
        do {
            return try JSONDecoder().decode(SurveyJSON.self, from: data)
//            if !survey.questions.isEmpty {
//                // Go over each question
//                for question in survey.questions {
//                    // Each question does it have an answer ?
//                    if let answers = question.answers {
//                        // Make sure all the answers are ordered
//                        question.answers = answers.sorted()
//                    }
//                }
//
//                // Finally, make sure every question is sorted as well
//                survey.questions = survey.questions.sorted()
//            }
        } catch let decodeErr {
            print("### parseJSONContent() DECODE FAILED:", decodeErr)
        }
        
        return nil
    }
}

class SurveyViewController: UIViewController, QuestionDelegate {
    
    let JSON_CONTENT: String = "{\"name\":\"TestDrive OpenShift\",\"numberOfQuestions\":4,\"questions\":[{\"order\":1,\"question\":\"What is your experience with Container technology ?\",\"type\":\"SINGLE\",\"answers\":[{\"order\":1,\"answer\":\"Experienced\"},{\"order\":2,\"answer\":\"Just playing\"},{\"order\":3,\"answer\":\"I heard about it\"},{\"order\":4,\"answer\":\"Never heard of it\"}]},{\"order\":2,\"question\":\"What container technology are you using currently ?\",\"type\":\"MULTIPLE\",\"answers\":[{\"order\":1,\"answer\":\"Docker\"},{\"order\":2,\"answer\":\"Rocket\"},{\"order\":3,\"answer\":\"CRI-O\"}]},{\"order\":3,\"question\":\"What do you consider the most important ?\",\"type\":\"RANK\",\"answers\":[{\"order\":1,\"answer\":\"Knowledge\"},{\"order\":2,\"answer\":\"Experience\"},{\"order\":3,\"answer\":\"Having a specialist around\"}]},{\"order\":4,\"question\":\"Please tell us where we should improve\",\"type\":\"OPEN\"}]}"
    var survey: SurveyJSON?
    
    var currentQuestion: Int = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.title = "Surveys"
        
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handleRunSurvey))
        
        survey = QuestionManager.shared.parseJSONContent(data: JSON_CONTENT.data(using: .utf8))
    }
    
    @objc func handleRunSurvey() {
        print(">>> SurveyViewController handleSimulate()")
        pushQuestionViewController()
    }
    
    private func pushQuestionViewController() {
        let questionViewController = QuestionViewController()
        questionViewController.question = survey?.questions[currentQuestion]
        questionViewController.delegate = self
        if let numberOfQuestions = survey?.numberOfQuestions {
            questionViewController.nextQuestion(currentQuestion == numberOfQuestions - 1)
        }
        
        navigationController?.pushViewController(questionViewController, animated: true)
    }
    
    // QUESTION DELEGATE QUESTION DELEGATE QUESTION DELEGATE QUESTION DELEGATE QUESTION DELEGATE
    //   QUESTION DELEGATE QUESTION DELEGATE QUESTION DELEGATE QUESTION DELEGATE QUESTION DELEGATE
    
    func jumpNextQuestion() {
        currentQuestion += 1
        pushQuestionViewController()
    }
    
    func doneSurvey() {
        print(">>> Sending Response")
        navigationController?.popToViewController(self, animated: true)
    }
}


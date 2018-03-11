//
//  ViewController.swift
//  survey2
//
//  Created by Mauricio Leal on 3/11/18.
//  Copyright © 2018 Mauricio "Maltron" Leal. All rights reserved.
//

import UIKit

class SurveyJSON: Codable {
    let name: String
    let questions: [QuestionJSON]
    let numberOfQuestions: Int
}

class QuestionJSON: Codable {
    let order: Int
    let question: String
    let type: TypeJSON
    let answers: [AnswerJSON]
}

enum TypeJSON: String, Codable {
    case single = "SINGLE"
    case multiple = "MULTIPLE"
    case rank = "RANK"
    case open = "OPEN"
}

class AnswerJSON: Codable {
    let order: Int
    let answer: String
}

class QuestionManager {
    static let shared: QuestionManager = QuestionManager()
    
    func parseJSONContent(data: Data?) -> SurveyJSON? {
        guard let data = data else { return nil }
        
        do {
            return try JSONDecoder().decode(SurveyJSON.self, from: data)
        } catch let decodeErr {
            print("### parseJSONContent() DECODE FAILED:", decodeErr)
        }
        
        return nil
    }
    
    func fetchQuestion(from survey: SurveyJSON, order: Int) -> QuestionJSON? {
        for question in survey.questions {
            if question.order == order {
                return question
            }
        }
        
        return nil
    }
    
    func fetchAnswer(from question: QuestionJSON, order: IndexPath) -> AnswerJSON? {
        for answer in question.answers {
            if answer.order == order.row {
                return answer
            }
        }
        
        return nil
    }
}

class SurveyViewController: UIViewController {
    
    let JSON_CONTENT: String = "{\"name\":\"TestDrive OpenShift\",\"numberOfQuestions\":4,\"questions\":[{\"order\":1,\"question\":\"What is your experience with Container technology ?\",\"type\":\"SINGLE\",\"answers\":[{\"order\":1,\"answer\":\"Experienced\"},{\"order\":2,\"answer\":\"Just playing\"},{\"order\":3,\"answer\":\"I heard about it\"},{\"order\":4,\"answer\":\"Never heard of it\"}]},{\"order\":2,\"question\":\"What container technology are you using currently ?\",\"type\":\"MULTIPLE\",\"answers\":[{\"order\":1,\"answer\":\"Docker\"},{\"order\":2,\"answer\":\"Rocket\"},{\"order\":3,\"answer\":\"CRI-O\"}]},{\"order\":3,\"question\":\"What do you consider the most important ?\",\"type\":\"RANK\",\"answers\":[{\"order\":1,\"answer\":\"Knowledge\"},{\"order\":2,\"answer\":\"Experience\"},{\"order\":3,\"answer\":\"Having a specialist around\"}]},{\"order\":4,\"question\":\"Please tell us where we should improve\",\"type\":\"OPEN\"}]}"
    var survey: SurveyJSON?
    
    var currentQuestion: Int = 1

    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.title = "Surveys"
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handleSimulate))
        
        survey = QuestionManager.shared.parseJSONContent(data: JSON_CONTENT.data(using: .utf8))
    }
    
    @objc func handleSimulate() {
        print(">>> SurveyViewController handleSimulate()")
        
        let questionViewController = QuestionViewController()
        questionViewController.question = QuestionManager.shared.fetchQuestion(from: survey!, order: currentQuestion)
        navigationController?.popToViewController(questionViewController, animated: true)
    }
    

}


//
//  ViewController.swift
//  survey2
//
//  Created by Mauricio Leal on 3/11/18.
//  Copyright © 2018 Mauricio "Maltron" Leal. All rights reserved.
//

import UIKit

class SurveyJSON: Codable {
    var name: String
    var questions: [QuestionJSON]
}

class QuestionJSON: Codable {
    var order: Int
    var question: String
    var type: TypeJSON
}

enum TypeJSON: String, Codable {
    case single = "SINGLE"
    case multiple = "MULTIPLE"
    case rank = "RANK"
    case open = "OPEN"
}

class AnswerJSON: Codable {
    var order: Int
    var answer: String
}

class SurveyViewController: UIViewController {
    
    let JSON_CONTENT: String = "{\"name\":\"Basic Question\",\"questions\":[{\"order\":1,\"question\":\"What is your name ?\",\"type\":\"SINGLE\",\"answers\":[{\"order\":1,\"answer\":\"John\"},{\"order\":2,\"answer\":\"Melany\"},{\"order\":3,\"answer\":\"Paul\"}]},{\"order\":2,\"question\":\"What is your sex ?\",\"type\":\"RANK\",\"answers\":[{\"order\":1,\"answer\":\"Male\"},{\"order\":2,\"answer\":\"Female\"}]}]}"
    var survey: SurveyJSON?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.title = "Surveys"
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handleSimulate))
    }
    
    @objc func handleSimulate() {
        
    }



}


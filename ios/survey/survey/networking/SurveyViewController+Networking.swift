//
//  SurveyViewController+Networking.swift
//  survey
//
//  Created by Mauricio Leal on 3/9/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit

class SurveyJSON: Codable {
    let _id: String
    let name: String
    let questions: [QuestionJSON]
    
    init(_id: String, name: String, questions: [QuestionJSON]) {
        self._id = _id
        self.name = name
        self.questions = questions
    }
}

class QuestionJSON: Codable {
    let order: Int
    let question: String
    let type: QuestionType
    let answers: [AnswerJSON]
    
    init(order: Int, question: String, type: QuestionType, answers: [AnswerJSON]) {
        self.order = order
        self.question = question
        self.type = type
        self.answers = answers
    }
}

enum QuestionType: String, Codable {
    case single = "SINGLE"
    case multiple = "MULTIPLE"
    case rank = "RANK"
    case open = "OPEN"
}

class AnswerJSON: Codable {
    let order: Int
    let answer: String
    
    init(order: Int, answer: String) {
        self.order = order
        self.answer = answer
    }
}

extension SurveyViewController {
    
    @objc func handleRefresh() {
        print("handleRefresh")
        
        var request: URLRequest = URLRequest(url: URL(string: "/api/v1/survey", relativeTo: serverURL)!)
        request.httpMethod = "GET"
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        dataTask?.cancel()
        dataTask = URLSession.shared.dataTask(with: request, completionHandler: { (data, response, error) in
            if let error = error {
                print("### handleRefresh() NETWORK FAILURE:", error)
                DispatchQueue.main.async {
                    // Network failure
                    self.refreshControl?.endRefreshing()
                    self.showNetworkFailure()
                }
            } else if let httpResponse = response as? HTTPURLResponse {
                // First, end refresh control
                DispatchQueue.main.async {
                    self.refreshControl?.endRefreshing()
                    self.emptyCoreData()
                    
                }
                if httpResponse.statusCode == 200 { // 200 - Ok
                    DispatchQueue.main.async {
                        self.refreshControl?.endRefreshing()
                        self.updateAllSurveys(data: data)
                    }
                    
                } else if httpResponse.statusCode == 204 { // 204 - No Content
                    DispatchQueue.main.async {
                        self.refreshControl?.endRefreshing()
                        self.emptyCoreData()
                    }
                }
            }
        })
        dataTask?.resume()
    }
    
    func showNetworkFailure() {
        let alert = UIAlertController(title: "Network Failure", message: "Unable to connect to Server", preferredStyle: .alert)
        let action = UIAlertAction(title: "Ok", style: .default, handler: nil)
        
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
    }
}

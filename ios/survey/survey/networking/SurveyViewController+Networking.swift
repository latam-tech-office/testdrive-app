//
//  SurveyViewController+Networking.swift
//  survey
//
//  Created by Mauricio Leal on 3/9/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit

class SurveyJSON: Codable {
    let id: String
    let name: String
    let questions: [QuestionJSON]
    
    init(id: String, name: String, questions: [QuestionJSON]) {
        self.id = id
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
    case single
    case multiple
    case rank
    case open
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
    
    func showNetworkFailure() {
        let alert = UIAlertController(title: "Network Failure", message: "Unable to connect to Server", preferredStyle: .alert)
        let action = UIAlertAction(title: "Ok", style: .default, handler: nil)
        
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
    }

    @objc func handleRefresh() {
        print("handleRefresh")
        
        var request: URLRequest = URLRequest(url: URL(fileURLWithPath: "/api/v1/survey", relativeTo: serverURL))
        request.httpMethod = "GET"
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        dataTask?.cancel()
        dataTask = URLSession.shared.dataTask(with: request, completionHandler: { (data, response, error) in
            if let _ = error {
                DispatchQueue.main.async {
                    // Network failure
                    self.refresh.endRefreshing()
                    self.showNetworkFailure()
                }
            } else if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode == 200 { // 200 - Ok
                    DispatchQueue.main.async {
                        self.refresh.endRefreshing()
                        self.emptyCoreData()
                        
                    }
                    
                } else if httpResponse.statusCode == 204 { // 204 - No Content
                    DispatchQueue.main.async {
                        self.refresh.endRefreshing()
                        self.emptyCoreData()
                    }
                }
            }
        })
        dataTask?.resume()
    }

    
}

//
//  SurveyViewController.swift
//  survey
//
//  Created by Mauricio Leal on 3/8/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit
import CoreData

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

class SurveyViewController: UITableViewController {
    
    // CORE DATA CORE DATA CORE DATA CORE DATA CORE DATA CORE DATA CORE DATA
    //   CORE DATA CORE DATA CORE DATA CORE DATA CORE DATA CORE DATA CORE DATA
    lazy var persistentContainer: NSPersistentContainer = {
        let container: NSPersistentContainer = NSPersistentContainer(name: "survey")
        container.loadPersistentStores(completionHandler: { (description, error) in
            if let error = error as NSError? {
                fatalError("### persistentContainer UNABLE TO LOAD DATA: \(error)")
            }
        })
    
        return container
    }()
    
    var fetchedResultsController: NSFetchedResultsController<Survey>!
    
    // NETWORK NETWORK NETWORK NETWORK NETWORK NETWORK NETWORK NETWORK
    //   NETWORK NETWORK NETWORK NETWORK NETWORK NETWORK NETWORK NETWORK
    let serverURL: URL? = URL(string: "survey-app-survey.cloudapps.maltron.solutionarchitectsredhat.com.br")
    var dataTask: URLSessionDataTask!
    
    let refresh: UIRefreshControl = {
        let refreshControl: UIRefreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(handleRefresh), for: .valueChanged)

        return refreshControl
    }()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Setup some Toolbar's buttons
        let items: [UIBarButtonItem] = [
            UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handlePlay))
        ]
        setToolbarItems(items, animated: true)
        
        // Refresh: Fetch new data and insert into CoreData
        self.refreshControl = refresh
        
        navigationItem.title = "Surveys"
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TheCell")
        tableView.tableFooterView = UIView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        navigationController?.isToolbarHidden = false
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        navigationController?.isToolbarHidden = true
    }
    
    @objc func handlePlay() {
        print("handlePlay")
    }
    
    @objc func handleRefresh() {
        print("handleRefresh")
        
        var request: URLRequest = URLRequest(url: URL(fileURLWithPath: "/api/v1/survey", relativeTo: serverURL))
        request.httpMethod = "GET"
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        dataTask?.cancel()
        dataTask = URLSession.shared.dataTask(with: request, completionHandler: { (data, response, error) in
            if let error = error {
                DispatchQueue.main.async {
                    // Network failure
                    self.refresh.endRefreshing()
                    print("### NETWORK FAILURE:", error)
                }
            } else if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode == 200 { // 200 - Ok
                    
                } else if httpResponse.statusCode == 204 { // 204 - No Content
                }
            }
        })
        dataTask?.resume()
    }
    
    override func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        let headerView = view as! UITableViewHeaderFooterView
        headerView.textLabel?.text = "My String"
    }
    
    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "titleForHeaderInSection"
    }
    
    override func tableView(_ tableView: UITableView, titleForFooterInSection section: Int) -> String? {
        return "titleForFooterInSection"
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TheCell", for: indexPath)
        if indexPath.section == 0 {
            if indexPath.row == 0 {
                cell.textLabel?.text = "One"
            } else {
                cell.textLabel?.text = "Two"
            }
        } else {
            if indexPath.row == 0 {
                cell.textLabel?.text = "Three"
            } else {
                cell.textLabel?.text = "Four"
            }
        }
        
        return cell;
    }
    
}


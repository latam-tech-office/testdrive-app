//
//  QuestionViewController.swift
//  survey
//
//  Created by Mauricio Leal on 3/9/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit
import CoreData

class QuestionViewController: UITableViewController, NSFetchedResultsControllerDelegate {
    
    weak var context: NSManagedObjectContext!
    var fetchedResultsController: NSFetchedResultsController<Question>!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // TableView
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TheQuestionCell")
        
        // Navigation
        navigationItem.title = "Questions"
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(handleAddQuestion))
        
        // Core Data
        loadCoreData()
    }
    
    @objc func handleAddQuestion() {
        
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let sections: [NSFetchedResultsSectionInfo] = fetchedResultsController.sections else {
            return 0
        }
        
        return sections[section].numberOfObjects
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "TheQuestionCell", for: indexPath)
        let question: Question = fetchedResultsController.object(at: indexPath)
        if let questionPerSe = question.question {
            cell.textLabel?.text = "\(question.order): \(questionPerSe)"
        }
        
        return cell
    }
}

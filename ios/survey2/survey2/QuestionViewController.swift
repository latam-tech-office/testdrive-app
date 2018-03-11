//
//  QuestionViewController.swift
//  survey2
//
//  Created by Mauricio Leal on 3/11/18.
//  Copyright © 2018 Mauricio "Maltron" Leal. All rights reserved.
//

import UIKit

class QuestionViewController: UITableViewController {
    
    var question: QuestionJSON? {
        didSet {
            navigationItem.title = question?.question
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TheQuestionCell")
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (question?.answers.count)!
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "TheQuestionCell", for: indexPath)
        cell.textLabel?.text = QuestionManager.shared.fetchAnswer(from: question!, order: indexPath)?.answer
        
        return cell
    }
}

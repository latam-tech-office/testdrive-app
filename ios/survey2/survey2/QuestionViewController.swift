//
//  QuestionViewController.swift
//  survey2
//
//  Created by Mauricio Leal on 3/11/18.
//  Copyright © 2018 Mauricio "Maltron" Leal. All rights reserved.
//

import UIKit

class QuestionViewController: UITableViewController {
    
    var delegate: QuestionDelegate!
    
    var question: QuestionJSON? {
        didSet {
            print(">>> Question: \(question?.question ?? "")")
            self.labelTitle.text = question?.question
            navigationItem.titleView = self.labelTitle
        }
    }
    
    let labelTitle: UILabel = {
        let label: UILabel = UILabel()
        label.numberOfLines = 0
        
        return label
    }()
    
    deinit {
        print("QuestionViewController deinit()")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TheQuestionCell")
    }
    
    @objc func handleNextQuestion() {
        delegate.jumpNextQuestion()
    }
    
    @objc func handleDoneSurvey() {
        delegate.doneSurvey()
    }
    
    func nextQuestion(_ value: Bool) {
        navigationItem.rightBarButtonItem = value ? UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(handleDoneSurvey)) : UIBarButtonItem(title: "Next", style: .plain, target: self, action: #selector(handleNextQuestion))
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let count = question?.answers?.count else { return 0 }
        
        return count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "TheQuestionCell", for: indexPath)
        if let answer = question?.answers![indexPath.row] {
            cell.textLabel?.text = answer.answer
        }
        
        return cell
    }
}

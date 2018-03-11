//
//  LongTextViewController.swift
//  survey
//
//  Created by Mauricio Leal on 3/10/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit

class AnswerViewController: UIViewController {

    var answer: Answer? {
        didSet {
            navigationItem.title = "Answer #\(answer?.order ?? Int16(0))"
            navigationItem.leftBarButtonItem = UIBarButtonItem(barButtonSystemItem: .cancel, target: self, action: #selector(handleCancel))
            navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .done, target: self, action: #selector(handleDone))
        }
    }
    
    let textView: UITextView = {
        let textView: UITextView = UITextView()
        
        return textView
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.title = "New Answer"
        
        // Setup UI using Auto Layout
        view.addSubview(textView)
        NSLayoutConstraint(item: textView, attribute: .top, relatedBy: .equal, toItem: self.view, attribute: .top, multiplier: 1.0, constant: 0.0).isActive = true
        NSLayoutConstraint(item: textView, attribute: .bottom, relatedBy: .equal, toItem: self.view, attribute: .bottom, multiplier: 1.0, constant: 0.0).isActive = true
        NSLayoutConstraint(item: textView, attribute: .trailing, relatedBy: .equal, toItem: self.view, attribute: .trailing, multiplier: 1.0, constant: 0.0).isActive = true
        NSLayoutConstraint(item: textView, attribute: .leading, relatedBy: .equal, toItem: self.view, attribute: .leading, multiplier: 1.0, constant: 0.0).isActive = true
    }
    
    @objc func handleCancel() {
        print(">>> AnswerViewController.handleCancel()")
    }
    
    @objc func handleDone() {
        print(">>> AnswerViewController.handleDone()")
    }
    
}

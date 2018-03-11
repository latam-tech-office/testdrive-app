//
//  SurveyTitleViewController.swift
//  survey
//
//  Created by Mauricio Leal on 3/10/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit

class SurveyTitleViewController: UITableViewController {
    
    var survey: Survey?
    
    var hasChanges: Bool {
        guard survey != nil else { return false }
        
        return survey?.name != cellSurveyTitle.textfield.text
    }
    
    var cellSurveyTitle: TextCell {
        return tableView.cellForRow(at: IndexPath(row: 0, section: 0)) as! TextCell
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.register(TextCell.self, forCellReuseIdentifier: "TheSurveyTitleCell")
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        if (!(self.navigationController?.viewControllers.contains(self))!) {
            print(">>> SurveyTitleViewController viewWillDisappear BACK")
            if hasChanges {
                print(">>> SurveyTitleViewController viewWillDisappear hasChanges")
                survey?.name = cellSurveyTitle.textfield.text
//                delegate?.update(person!)
            }
        }
        
        super.viewWillDisappear(animated)
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: TextCell = tableView.dequeueReusableCell(withIdentifier: "TheSurveyTitleCell", for: indexPath) as! TextCell
        
        return cell
    }
}

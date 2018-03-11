//
//  SurveyViewController+TableView.swift
//  survey
//
//  Created by Mauricio Leal on 3/9/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit
import CoreData

extension SurveysViewController {

//    override func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
//        let headerView = view as! UITableViewHeaderFooterView
//        headerView.textLabel?.text = "My String"
//    }
//
//    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
//        return "titleForHeaderInSection"
//    }
//
//    override func tableView(_ tableView: UITableView, titleForFooterInSection section: Int) -> String? {
//        return "titleForFooterInSection"
//    }
    
    // REQUIRED
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let sections: [NSFetchedResultsSectionInfo] = fetchedResultsController.sections else { return 0 }
        
        return sections[section].numberOfObjects
    }
    
    // REQUIRED
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "TheCell", for: indexPath)
        let survey: Survey = fetchedResultsController.object(at: indexPath)
        
        if let surveyName = survey.name {
            cell.textLabel?.text = surveyName
        }
        
        return cell;
    }
    
    override func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        let label: UILabel = UILabel()
        label.textAlignment = .center
        label.text = "No surveys available"
        
        return label
    }
    
    override func tableView(_ tableView: UITableView, heightForFooterInSection section: Int) -> CGFloat {
        guard let sections: [NSFetchedResultsSectionInfo] = fetchedResultsController.sections else { return 0 }
        
        return sections[section].numberOfObjects == 0 ? 150 : 0
    }
}

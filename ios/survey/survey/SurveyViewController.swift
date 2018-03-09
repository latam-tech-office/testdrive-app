//
//  SurveyViewController.swift
//  survey
//
//  Created by Mauricio Leal on 3/8/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit
import CoreData

class SurveyViewController: UITableViewController, NSFetchedResultsControllerDelegate {
    
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
    
    let simulateSurvey: UIBarButtonItem = {
        let button: UIBarButtonItem = UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handleSimulateSurvey))
        button.isEnabled = false
        
        return button
    }()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Setup some Toolbar's buttons
        setToolbarItems([simulateSurvey], animated: true)
        
        // Refresh: Fetch new data and insert into CoreData
        self.refreshControl = refresh
        
        navigationItem.title = "Surveys"
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TheCell")
        
        // Setup Core Data
        loadCoreData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        navigationController?.isToolbarHidden = false
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        navigationController?.isToolbarHidden = true
    }
    
    @objc func handleSimulateSurvey() {
        print("handleSimulateSurvey")
    }
    
    
    
}


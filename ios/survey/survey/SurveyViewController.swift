//
//  SurveyViewController.swift
//  survey
//
//  Created by Mauricio Leal on 3/8/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit
import CoreData

struct MenuOption {
    let name: String
    let description: String
}

class SurveyViewController: UITableViewController {
    
    var mytoolbar: UIToolbar!
    var mytableView: UITableView!
    
    let o: [[MenuOption]] = [
        [ MenuOption(name: "hello", description: "desc") ]
    ]
    
    let options: [[String]] = [
        // Section 0
        [ "Admin Users"],
        // Secion 1
        [ "Create", "Simulate"]
    ]
    
    let toolbar: UIToolbar = {
        var toolbar: UIToolbar = UIToolbar()
        toolbar.translatesAutoresizingMaskIntoConstraints = false
        toolbar.items?.append(UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handlePlay)))
        
        return toolbar
    }()
    
//    override init(style: UITableViewStyle) {
//        super.init(style: style)
//
//        navigationController?.isToolbarHidden = false
//    }
//
//    required init?(coder aDecoder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
    
//    // CoreData: PersistentContainer
//    lazy var persistentContainer: NSPersistentContainer = {
//        let container: NSPersistentContainer = NSPersistentContainer(name: "survey")
//        container.loadPersistentStores(completionHandler: { (description, error) in
//            if let error = error as NSError? {
//                fatalError("### persistentContainer UNABLE TO LOAD DATA: \(error)")
//            }
//        })
//
//        return container
//    }()
//
//    var fetchedResultsController: NSFetchedResultsController<Survey>!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        mytoolbar = UIToolbar()
//        mytoolbar.translatesAutoresizingMaskIntoConstraints = false
//        var items = [UIBarButtonItem]()
//        items.append(UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handlePlay)))
//        mytoolbar.setItems(items, animated: true)
//        view.addSubview(mytoolbar)
//
//        NSLayoutConstraint(item: mytoolbar, attribute: .bottom, relatedBy: .equal, toItem: self.view, attribute: .bottom, multiplier: 1.0, constant: 0).isActive = true
//        NSLayoutConstraint(item: mytoolbar, attribute: .leading, relatedBy: .equal, toItem: self.view, attribute: .leading, multiplier: 1.0, constant: 0).isActive = true
//        NSLayoutConstraint(item: mytoolbar, attribute: .trailing, relatedBy: .equal, toItem: self.view, attribute: .trailing, multiplier: 1.0, constant: 0).isActive = true
        
        navigationController?.isToolbarHidden = false
        let items: [UIBarButtonItem] = [
            UIBarButtonItem(barButtonSystemItem: .play, target: self, action: #selector(handlePlay))
        ]
        setToolbarItems(items, animated: true)
        
        navigationItem.title = "TheCell"
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TheCell")
        tableView.tableFooterView = UIView()

        let refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(handleRefresh), for: .valueChanged)
        self.refreshControl = refreshControl
    }
    
    @objc func handlePlay() {
        print("handlePlay")
    }
    
    @objc func handleRefresh() {
        print("handleRefresh")
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


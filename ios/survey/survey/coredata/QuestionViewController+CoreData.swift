//
//  QuestionViewController+CoreData.swift
//  survey
//
//  Created by Mauricio Leal on 3/9/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import CoreData

extension QuestionViewController {
    
    func loadCoreData() {
        // Step 1: Prepare key variables
        let fetchRequest: NSFetchRequest<Question> = NSFetchRequest(entityName: "Question")
        fetchRequest.sortDescriptors = [NSSortDescriptor(key: "order", ascending: true),
                                    NSSortDescriptor(key: "question", ascending: true)]
        
        // Step 2: Create a NSFetchedResultsController instance and attach to a delegate
        fetchedResultsController = NSFetchedResultsController(fetchRequest: fetchRequest, managedObjectContext: context, sectionNameKeyPath: nil, cacheName: nil)
        fetchedResultsController.delegate = self
        
        // Step 3: Perform fetch
        do {
            try fetchedResultsController.performFetch()
        } catch let performErr {
            print("### QuestionViewController.loadCoreData() UNABLE TO PERFORM FETCH:", performErr)
        }
    }

}

//
//  SurveyViewController+CoreData.swift
//  survey
//
//  Created by Mauricio Leal on 3/9/18.
//  Copyright © 2018 Mauricio Leal. All rights reserved.
//

import UIKit
import CoreData

extension SurveyViewController {
    
    func updateAllSurveys(data: Data?) {
        guard let data = data else { return }
        
        let context: NSManagedObjectContext = persistentContainer.viewContext
        // Step #1: Decode all the components
        do {
            for surveyjson in try JSONDecoder().decode([SurveyJSON].self, from: data) {
                let entity: NSEntityDescription = NSEntityDescription.entity(forEntityName: "Survey", in: context)!
                let newSurvey: Survey = Survey(entity: entity, insertInto: context)
                newSurvey.id = surveyjson._id
                newSurvey.name = surveyjson.name
            }
        } catch let decodeJSONErr {
            print("### updateAllSurveys() UNABLE TO DECODE CONTENT:", decodeJSONErr)
        }

        // Step #2: Save
        do {
            try context.save()
        } catch let saveErr {
            print("### updateAllSurveys() UNABLE TO SAVE DATA:", saveErr)
        }
    }
    
    func emptyCoreData() {
        let context: NSManagedObjectContext = persistentContainer.viewContext
        
        // PENDING: Something that works better
//        let deleteBatch: NSBatchDeleteRequest = NSBatchDeleteRequest(fetchRequest: Survey.fetchRequest())
//        do {
//            try context.execute(deleteBatch)
//        } catch let deleteBatchErr {
//            print("### emptyCoreData() UNABLE TO BATCH DELETE ALL:", deleteBatchErr)
//        }
        let fetchRequest: NSFetchRequest<Survey> = NSFetchRequest(entityName: "Survey")
        do {
            for existingSurvey in try context.fetch(fetchRequest) {
                context.delete(existingSurvey)
            }
        } catch let deleteErr {
            print("### emptyCoreData() UNABLE TO DELETE:", deleteErr)
        }
    }
    
    func loadCoreData() {
        // Step 1: Prepare key variables
        let context: NSManagedObjectContext = persistentContainer.viewContext
        let fetchRequest: NSFetchRequest<Survey> = NSFetchRequest(entityName: "Survey")
        fetchRequest.sortDescriptors = [NSSortDescriptor(key: "name", ascending: true)]
        
        // Step 2: Create a NSFetchedResultsController instance and attach to a delegate
        fetchedResultsController = NSFetchedResultsController(fetchRequest: fetchRequest, managedObjectContext: context, sectionNameKeyPath: nil, cacheName: nil)
        fetchedResultsController.delegate = self
        
        // Step 3: Perform fetch
        do {
            try fetchedResultsController.performFetch()
        } catch let performErr {
            print("### loadCoreData() UNABLE TO PERFORM FETCH:", performErr)
        }
    }
    
    func controllerWillChangeContent(_ controller: NSFetchedResultsController<NSFetchRequestResult>) {
        print(">>> controllRerWillChangeContent()")
        tableView.beginUpdates()
    }
    
    func controller(_ controller: NSFetchedResultsController<NSFetchRequestResult>, didChange anObject: Any, at indexPath: IndexPath?, for type: NSFetchedResultsChangeType, newIndexPath: IndexPath?) {
        print(">>> controller(didChange)")
        
        switch type {
        // INSERT/CREATE
        case NSFetchedResultsChangeType.insert:
            if let insertIndexPath = newIndexPath {
                tableView.insertRows(at: [insertIndexPath], with: .automatic)
            }
        // DELETE
        case NSFetchedResultsChangeType.delete:
            if let deleteIndexPath = indexPath {
                tableView.deleteRows(at: [deleteIndexPath], with: .automatic)
            }
            
        // UPDATE
        case NSFetchedResultsChangeType.update:
            if let updateIndexPath = indexPath {
                let cell = tableView.cellForRow(at: updateIndexPath)
                let survey: Survey = fetchedResultsController.object(at: updateIndexPath)
                
                if let surveyName = survey.name {
                    cell?.textLabel?.text = "\(surveyName)"
                }
            }
            
        // MOVE = DELETE + INSERT
        case NSFetchedResultsChangeType.move:
            if let deleteIndexPath = indexPath {
                tableView.deleteRows(at: [deleteIndexPath], with: .automatic)
            }
            
            if let insertIndexPath = newIndexPath {
                tableView.insertRows(at: [insertIndexPath], with: .automatic)
            }
        }
    }
    
    func controller(_ controller: NSFetchedResultsController<NSFetchRequestResult>, didChange sectionInfo: NSFetchedResultsSectionInfo, atSectionIndex sectionIndex: Int, for type: NSFetchedResultsChangeType) {
        print(">>> controller(didChange:sectionInfo")
        
        switch type {
        // INSERT
        case .insert:
            let sectionIndexSet = IndexSet(integer: sectionIndex)
            tableView.insertSections(sectionIndexSet, with: .automatic)
        // DELETE
        case .delete:
            let sectionIndexSet = IndexSet(integer: sectionIndex)
            tableView.deleteSections(sectionIndexSet, with: .automatic)
        default:
            break
        }
    }
    
    func controllerDidChangeContent(_ controller: NSFetchedResultsController<NSFetchRequestResult>) {
        print(">>> controllerDidChangeContent")
        tableView.endUpdates()
    }
}

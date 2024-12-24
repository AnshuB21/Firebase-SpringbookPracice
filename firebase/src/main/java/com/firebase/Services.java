package com.firebase;

import com.google.api.core.ApiFuture; // Import for handling asynchronous calls in Firestore
import com.google.cloud.firestore.DocumentReference; // Represents a reference to a Firestore document
import com.google.cloud.firestore.DocumentSnapshot; // Represents a snapshot of a Firestore document
import com.google.cloud.firestore.Firestore; // Represents the Firestore database
import com.google.cloud.firestore.WriteResult; // Represents the result of a Firestore write operation
import com.google.firebase.cloud.FirestoreClient; // Provides access to the Firestore database
import org.springframework.stereotype.Service; // Marks this class as a Spring service

import java.util.concurrent.ExecutionException; // Exception thrown when a task is interrupted or fails

@Service // Marks this class as a Spring service for dependency injection
public class Services {

    // Method to create or add a new document to the Firestore collection
    public String createCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); // Get Firestore database instance
        // Add or update a document in the "user" collection with the ID from crud.getDocument_id()
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("user").document(crud.getDocument_id()).set(crud);
        // Return the update time of the created/updated document
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    // Method to retrieve a document from the Firestore collection by its document ID
    public CRUD getCRUD(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); // Get Firestore database instance
        // Get a reference to the document with the given ID in the "user" collection
        DocumentReference documentReference = dbFirestore.collection("user").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get(); // Retrieve the document asynchronously
        DocumentSnapshot document = future.get(); // Wait for and get the document snapshot
        CRUD crud; // Object to hold the retrieved data
        if (document.exists()) { // Check if the document exists
            // Map the Firestore document fields to a CRUD object
            crud = document.toObject(CRUD.class);
            return crud; // Return the retrieved CRUD object
        }
        return null; // Return null if the document doesn't exist
    }

    // Method to update an existing document in the Firestore collection
    public String updateCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); // Get Firestore database instance
        // Update the document in the "user" collection with the ID from crud.getDocument_id()
        ApiFuture<WriteResult> collectiondb = dbFirestore.collection("user").document(crud.getDocument_id()).set(crud);
        return "Successful"; // Return a success message
    }

    // Method to delete a document from the Firestore collection by its document ID
    public String deleteCRUD(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore(); // Get Firestore database instance
        // Delete the document with the given ID from the "user" collection
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("user").document(documentId).delete();
        return "Successfully deleted " + documentId; // Return a success message
    }
}

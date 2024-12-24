package com.firebase;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping
public class Controller {
 public Services crudService;

 public Controller(Services crudService){
     this.crudService =crudService;
 }
 @PostMapping("/create")
 public String createCRUD(@RequestBody CRUD crud) throws  InterruptedException, ExecutionException{
     return crudService.createCRUD(crud);
 }

 @GetMapping("/get")
    public CRUD getCRUD(@RequestParam String documentID) throws  InterruptedException, ExecutionException{
        return crudService.getCRUD(documentID);
    }

    @PutMapping ("/update")
    public String updateCRUD(@RequestBody CRUD crud) throws  InterruptedException, ExecutionException{
        return crudService.updateCRUD(crud);
    }

    @DeleteMapping("/delete")
    public String deleteCRUD(@RequestParam String documentID) throws  InterruptedException, ExecutionException{
        return crudService.deleteCRUD(documentID);
    }

}

package com.example.bakery.controllers;

import com.example.bakery.models.Receivers;
import com.example.bakery.services.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receivers")
public class ReceiverController {

    private final ReceiverService receiverService;

    @Autowired
    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @GetMapping
    public ResponseEntity<List<Receivers>> getAllReceivers() {
        List<Receivers> receivers = receiverService.getAllReceivers();
        return ResponseEntity.ok(receivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receivers> getReceiverById(@PathVariable Integer id) {
        return receiverService.getReceiverById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Receivers> createReceiver(@RequestBody Receivers receiver) {
        Receivers createdReceiver = receiverService.createReceiver(receiver);
        return ResponseEntity.ok(createdReceiver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receivers> updateReceiver(@PathVariable Integer id, @RequestBody Receivers receiver) {
        return receiverService.updateReceiver(id, receiver)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceiver(@PathVariable Integer id) {
        boolean deleted = receiverService.deleteReceiver(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

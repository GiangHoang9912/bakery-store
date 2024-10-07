package com.example.bakery.services;

import com.example.bakery.models.Receivers;
import com.example.bakery.repositories.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiverService {

    private final ReceiverRepository receiverRepository;

    @Autowired
    public ReceiverService(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    public List<Receivers> getAllReceivers() {
        return receiverRepository.findAll();
    }

    public Optional<Receivers> getReceiverById(Integer id) {
        return receiverRepository.findById(id);
    }

    public Receivers createReceiver(Receivers receiver) {
        receiver.setCreatedAt(LocalDateTime.now());
        receiver.setUpdatedAt(LocalDateTime.now());
        return receiverRepository.save(receiver);
    }

    public Optional<Receivers> updateReceiver(Integer id, Receivers receiverDetails) {
        return receiverRepository.findById(id)
                .map(existingReceiver -> {
                    existingReceiver.setName(receiverDetails.getName());
                    existingReceiver.setPhone(receiverDetails.getPhone());
                    existingReceiver.setAddress(receiverDetails.getAddress());
                    existingReceiver.setEmail(receiverDetails.getEmail());
                    existingReceiver.setUpdatedAt(LocalDateTime.now());
                    return receiverRepository.save(existingReceiver);
                });
    }

    public boolean deleteReceiver(Integer id) {
        return receiverRepository.findById(id)
                .map(receiver -> {
                    receiverRepository.delete(receiver);
                    return true;
                })
                .orElse(false);
    }
}

package com.microservices.demo.service;

import com.microservices.demo.repository.ClientRepository;
import com.microservices.demo.config.RabbitConfig;
import com.microservices.demo.dto.ClientMessage;
import com.microservices.demo.model.Client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Client clientCreate(Client client){
        Client clientSave = clientRepository.save(client);

        //Rabbit
        ClientMessage message = new ClientMessage();
        message.setIdClient(clientSave.getId());
        message.setName(clientSave.getName());
        message.setDni(clientSave.getDni());

        rabbitTemplate.convertAndSend(
            RabbitConfig.EXCHANGE,
            RabbitConfig.ROUTING_KEY,
            message
        );
        return clientSave;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<Client>getClientById(Long id){
        return clientRepository.findById(id);
    }

    public Client updateClient(Long id, Client clientDetail){
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()){
            Client client = clientOptional.get();
            client.setName(clientDetail.getName());
            client.setDni(clientDetail.getDni());
            client.setGender(clientDetail.getGender());
            client.setAge(clientDetail.getAge());
            client.setAddress(clientDetail.getAddress());
            client.setPhone(clientDetail.getPhone());
            client.setPassword(clientDetail.getPassword());
            client.setState(clientDetail.getState());
            return clientRepository.save(client);
        }
        throw new RuntimeException("Client not found.");
    }

    public boolean deleteCliente(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

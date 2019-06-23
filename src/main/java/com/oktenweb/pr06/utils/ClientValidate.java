package com.oktenweb.pr06.utils;

import com.oktenweb.pr06.dao.ClientDAO;
import com.oktenweb.pr06.entity.Client;

import java.util.List;

public class ClientValidate {

    public static boolean isPhoneInBase(Client client1, List<Client> clientList){
        return clientList.stream().anyMatch(client2 -> client2.getPhone().equals(client1.getPhone()));
    }

    public static Client findByPhone(Client client1, List<Client> clientList){
        return clientList.stream().filter(client2 -> client2.getPhone().equals(client1.getPhone())).findAny().get();
    }

    public static boolean isEmailInBase(Client client1, List<Client> clientList){
        return clientList.stream().anyMatch(client2 -> client2.getEmail().equals(client1.getEmail()));
    }

    public static Client findByEmail(Client client1, List<Client> clientList){
        return clientList.stream().filter(client2 -> client2.getEmail().equals(client1.getEmail())).findAny().get();
    }
}

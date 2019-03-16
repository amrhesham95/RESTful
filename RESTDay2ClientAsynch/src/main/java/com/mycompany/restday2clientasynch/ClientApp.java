/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restday2clientasynch;

import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.async.TypeListener;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.non.blocking.NonBlockingClient;
import com.sun.jersey.client.non.blocking.config.DefaultNonBlockingClientConfig;
import com.sun.jersey.client.non.blocking.config.NonBlockingClientConfig;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author AmrHesham
 */
public class ClientApp {

    public static void main(String args[]) {
        try {

            //obtain an instance of the interface
            ClientConfig cc = new DefaultNonBlockingClientConfig();

            cc.getProperties().put(NonBlockingClientConfig.PROPERTY_THREADPOOL_SIZE, 10);
            Client client = NonBlockingClient.create(cc);
            //fill the client with the target coming from this service
            String getUrl = "http://localhost:8084/RESTDay2/rest/employees/88";
            AsyncWebResource asyncWebResource = client.asyncResource(getUrl);

            //create request based on the target so i've choose xml because the service produce xml :D
            AsyncWebResource.Builder builder = asyncWebResource.accept(MediaType.APPLICATION_JSON);

            builder.get(new TypeListener<ClientResponse>(ClientResponse.class) {
                @Override
                public void onComplete(Future<ClientResponse> future) throws InterruptedException {
                    try {
                        ClientResponse response = future.get();
                        if (response.getStatus() != 200) {
                            throw new RuntimeException("HTTP ERROR:" + response.getStatus());
                        }

                        Employee employee = response.getEntity(Employee.class);
                        System.out.println("response from server");
                        System.out.println(employee);
                        //To change body of generated methods, choose Tools | Templates.
                    } catch (ExecutionException ex) {
                        Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            String postUrl = "http://localhost:8084/RESTDay2/rest/employees";
            AsyncWebResource webResourcePost = client.asyncResource(postUrl);

            //create request based on the target so i've choose xml because the service produce xml :D
            AsyncWebResource.Builder builderPost = webResourcePost.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
            Employee newEmployee = new Employee("88", "arafa", "90000", "500000", "officeBoy");
            ObjectMapper mapper = new ObjectMapper();
            String request = mapper.writeValueAsString(newEmployee);
            builderPost.post(new TypeListener<ClientResponse>(ClientResponse.class) {
                @Override
                public void onComplete(Future<ClientResponse> future) throws InterruptedException {
                    try {
                        ClientResponse responsePost = future.get();
                        System.out.println(responsePost.getStatus());
                    } catch (ExecutionException ex) {
                        Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, request);
        } catch (IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

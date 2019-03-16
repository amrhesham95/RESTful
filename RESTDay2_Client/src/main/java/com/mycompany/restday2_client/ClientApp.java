/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restday2_client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author AmrHesham
 */


public class ClientApp {
    public static void main(String args[]){
        try {
            //obtain an instance of the interface
            Client client=Client.create();
            
            //fill the client with the target coming from this service
            String getUrl="http://localhost:8084/RESTDay2/rest/employees/1";
            WebResource webResource=client.resource(getUrl);
            
            //create request based on the target so i've choose xml because the service produce xml :D
            WebResource.Builder builder=webResource.accept(MediaType.APPLICATION_JSON);
            
            ClientResponse response=builder.get(ClientResponse.class);
            
            if(response.getStatus() !=200){
                throw new RuntimeException("HTTP ERROR:"+response.getStatus());
            }
            
            Employee employee=response.getEntity(Employee.class);
            System.out.println("response from the server");
            System.out.println(employee);
            
            String postUrl="http://localhost:8084/RESTDay2/rest/employees";
            WebResource webResourcePost=client.resource(postUrl);
            
            //create request based on the target so i've choose xml because the service produce xml :D
            WebResource.Builder builderPost=webResourcePost.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
            
//            ClientResponse responsePost=builderPost.post(ClientResponse.class);
            
//            if(responsePost.getStatus() !=200){
//                throw new RuntimeException("HTTP ERROR:"+responsePost.getStatusInfo());
//            }
            Employee newEmployee=new Employee("99", "hamada", "90000", "500000", "officeBoy");
            ObjectMapper mapper=new ObjectMapper();
            String request=mapper.writeValueAsString(newEmployee);
            
            ClientResponse responsePost=builderPost.post(ClientResponse.class,request);
            System.out.println(responsePost.getStatus());
        } catch (IOException ex) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

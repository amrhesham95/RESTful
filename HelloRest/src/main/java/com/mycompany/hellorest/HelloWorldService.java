/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellorest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author AmrHesham
 */
@Path("/hello")
public class HelloWorldService {
    @GET
    public String sayHello(){return ("HelloWorld!!!");}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellorest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author AmrHesham
 */
@Path("/calculator")
public class Calculator {
    
    @GET
    @Path("/add")
    public float add(@QueryParam("num1") String num1,@QueryParam("num2") String num2){
        return Float.parseFloat(num1)+Float.parseFloat(num2);
    }
    
    @GET
    @Path("/sub")
    public float sub(@QueryParam("num1") String num1,@QueryParam("num2") String num2){
        return Float.parseFloat(num1)-Float.parseFloat(num2);
    }
    
    @GET
    @Path("/mul")
    public float mul(@QueryParam("num1") String num1,@QueryParam("num2") String num2){
        return Float.parseFloat(num1)*Float.parseFloat(num2);
    }
    
    @GET
    @Path("/divide")
    public float divide(@QueryParam("num1") String num1,@QueryParam("num2") String num2){
        return Float.parseFloat(num1)/Float.parseFloat(num2);
    }
}

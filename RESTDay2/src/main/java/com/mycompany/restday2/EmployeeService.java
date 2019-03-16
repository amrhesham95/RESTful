/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restday2;

import java.awt.PageAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author AmrHesham
 */
@Path("/employees")
public class EmployeeService {

   
  static Map <String, Employee> employeesMap = new HashMap<String, Employee>();
   public EmployeeService() {
        Employee emp=new Employee("1", "AmrHesham", "90000", "500000", "officeBoy");
       employeesMap.put(emp.getId(), emp);
    }
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Employee addEmployee(Employee emp){
      System.out.println("added"+emp.getName());
     employeesMap.put(emp.getId(), emp);
     return emp;
  }
  
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<Employee> getEmployees(){
      List<Employee> list = new ArrayList<Employee>(employeesMap.values());
      return list;
  }
  
  @GET
  @Path("/{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public Employee getEmployee(@PathParam("id") String id){
      return employeesMap.get(id);
  }
  
  @PUT
  @Path("/{id}")
  @Produces({MediaType.APPLICATION_XML})
  public Employee update(@PathParam("id") String id){
      employeesMap.get(id).setName("updated");
     return employeesMap.get(id);
  }
  
  @DELETE
  @Path("/{id}")
  @Produces({MediaType.APPLICATION_XML})
  public String delete(@PathParam("id") String id){
    String msg=employeesMap.get(id).getName()+"deleted";
    employeesMap.remove(id);
    return msg;
    
  }
  
}

package com.oraclechallenge.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

import com.google.gson.Gson;

import java.math.BigInteger;
import com.fasterxml.jackson.annotation.JsonProperty;

class returnValue 
{
    List<BigInteger> fibonacci;
    List<BigInteger> sorted;
    returnValue(){}

    public List<BigInteger> getFibList(){return this.fibonacci;}
    public List<BigInteger> getSortedList(){return this.sorted;}
    public void setFib(List<BigInteger> newList){this.fibonacci = newList;}
    public void setSorted(List<BigInteger> newList){this.sorted = newList;}
}

@Path("/fibonacci")
public class fibonacciFunction{
    public fibonacciFunction (){}

    //The private static class is to bind the JSON body parameters into the function
    public static class handler
    {
        @JsonProperty("elements")
        public int elements;
    }
    
    //The only way to accomplish this that I could do when using React is to convert the GET method into a POST
    //By sending raw json in Postman, the GET method would work, but in React, the environment throws an error that GET does not support body
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
  //  public Response getFibonacciSequence( @PathParam("elements") String elements  ) 
    public Response getFibonacciSequence( handler handle  ) 
    {
        
        //initialising two stacks that will help create the final list of sorted values, a fibonacci list, as well as a list of the final sorted values
        //BigIntegers have to be used as the scope of the challenge requires up to a input of 100, which will cause calculated values to exceed what Integer and Long are able to hold without overflowing
        Stack<BigInteger> oddStack = new Stack<BigInteger>();
        Stack<BigInteger> evenStack = new Stack<BigInteger>();
        List<BigInteger> fibonacciList = new LinkedList<BigInteger>();
        List<BigInteger> sortedFibonacciList = new LinkedList<BigInteger>();

        //initialising variables to hold the fibonacci values during loop
        BigInteger firstNumber= BigInteger.valueOf(0), secondNumber=BigInteger.valueOf(1), result;

        //int count = newDecoder.getElements();
        int count = handle.elements;

        //0 counts as even, for values of 1 and 2, their fibonacci values are already fixed, hence their positions 
        //in the non sorted and sorted lists are also fixed
        if(count == 1)
        {
            fibonacciList.add(BigInteger.valueOf(0));
            sortedFibonacciList.add(BigInteger.valueOf(0));
        }
        else if(count == 2)
        {
            fibonacciList.add(BigInteger.valueOf(0));
            fibonacciList.add(BigInteger.valueOf(1));
            sortedFibonacciList.add(BigInteger.valueOf(0));
            sortedFibonacciList.add(BigInteger.valueOf(1));
        }

        else
        {
            //first two values are pre-added
            fibonacciList.add(BigInteger.valueOf(0));
            fibonacciList.add(BigInteger.valueOf(1));
            evenStack.push(BigInteger.valueOf(0));
            oddStack.push(BigInteger.valueOf(1));

            //for anything past 2, fibonacci calculation
            for( int  i = 2;  i < count; i++)
            {
                //continue the fibonacci pattern of new number being sum of last two
                result = firstNumber.add(secondNumber);
                //add the result to the fibonacci list
                fibonacciList.add(result);

                //modulo 2 checks if the result is odd or even, and add them to the appropriate stack
                if(result.mod(BigInteger.valueOf(2)) == BigInteger.valueOf(0) )
                    evenStack.push(result);
                else
                    oddStack.push(result);

                //update the values for the fibonacci pattern
                firstNumber = secondNumber;
                secondNumber = result;
            }
            //after calculating all fibonacci numbers, create the sorted list by first popping the even stack, then the odd stack
            //by using a stack, the popping ensures that the largest number always get popped first
            while( !evenStack.isEmpty())
            {
                BigInteger toPop = evenStack.peek();
                sortedFibonacciList.add(toPop);
                evenStack.pop();
            
            }
            while( !oddStack.isEmpty())
            {
                BigInteger toPop = oddStack.peek();
                sortedFibonacciList.add(toPop);
                oddStack.pop();
            
            }
        }
        //Set both lists into attributes on the holding class before converting them into JSON format
        returnValue newReturn = new returnValue();
        newReturn.setFib(fibonacciList);
        newReturn.setSorted(sortedFibonacciList);

        String gson = new Gson().toJson(newReturn);

        //return the response as a json with both the lists
        return Response
        .status(Response.Status.OK)
        .entity(gson)
        .type(MediaType.APPLICATION_JSON)
        .build();

    }
}



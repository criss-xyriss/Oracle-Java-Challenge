package com.oraclechallenge;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import com.oraclechallenge.resources.fibonacciFunction;

public class fibonacciApplication extends Application<fibonacciConfiguration> {

    public static void main(final String[] args) throws Exception {
        new fibonacciApplication().run(args);
    }

    @Override
    public String getName() {
        return "fibonacci";
    }

    @Override
    public void initialize(final Bootstrap<fibonacciConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final fibonacciConfiguration configuration,final Environment environment) 
    {
       fibonacciFunction fibonacciHandler = new fibonacciFunction();
       environment.jersey().register(fibonacciHandler);
    }

}

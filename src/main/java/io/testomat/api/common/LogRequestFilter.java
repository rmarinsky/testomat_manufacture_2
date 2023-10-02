package io.testomat.api.common;

import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogRequestFilter implements OrderedFilter {

    private static final Logger logger = LoggerFactory.getLogger(LogRequestFilter.class);

    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        logger.info(requestSpec.getMethod().toUpperCase() + " " + requestSpec.getURI());
        if (requestSpec.getBody() != null)
            logger.info("Request body:\n" + requestSpec.getBody().toString());


        Response response = ctx.next(requestSpec, responseSpec);

        logger.info(response.getStatusCode() + " " + requestSpec.getURI());

        if (needToLogResponse()) {
            logger.info(
                    "Content-Type: " + response.getHeader("Content-Type") + "\n" +
                            response.asPrettyString());
        }

        return response;
    }

    private boolean needToLogResponse() {
        //        Properties properties = new Properties();
        // Assuming you load properties from file/classpath/env
        return true/*Boolean.parseBoolean(properties.getProperty("log.response"))*/;
    }

    public int getOrder() {
        return Integer.MAX_VALUE;
    }

}

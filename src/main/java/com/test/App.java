package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.test.Student;
import com.test.Request; 
/**
 * 
 * AWS Lambda function with API Gateway trigger
 * 
 * @author AlperOzdamar
 *
 */
public class App {
	
	static final Logger log  = LoggerFactory.getLogger(App.class);
	
	public static Object handleRequest(Request request, Context context) throws ResourceNotFoundException {		
		
		log.debug("HandleRequest is invoked!"); 
		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);	
		Student student = null;				
		switch(request.getHttpMethod()) {
		case "GET":
			student = mapper.load(Student.class,request.getId());
			if(student==null) {
				throw new ResourceNotFoundException("Resource not found:"+request.getId());
			}		
			return student;
		case "POST":
			student = request.getStudent();			
			mapper.save(student);
			return student;			
			
		default:
				//Throw exception if called method is not implemented
			break;			
		}		
		return null;		
	}
	
	

}

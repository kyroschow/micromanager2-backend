package project.ucsd.micromanager2.dynamodb

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import io.ktor.application.Application

var ddb:DynamoDB? = null

fun Application.installDynamoDB(){
    val dynamoDBClient = AmazonDynamoDBAsyncClientBuilder.standard()
        .withRegion(Regions.US_WEST_2)
        .build()
    ddb = DynamoDB(dynamoDBClient)
}
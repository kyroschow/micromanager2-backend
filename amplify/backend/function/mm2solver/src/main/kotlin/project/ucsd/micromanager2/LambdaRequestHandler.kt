/* Amplify Params - DO NOT EDIT
	ENV
	REGION
	STORAGE_MM2DB_ARN
	STORAGE_MM2DB_NAME
Amplify Params - DO NOT EDIT */
package project.ucsd.micromanager2

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

class LambdaRequestHandler : RequestHandler<RequestClass, ResponseClass> {
    override fun handleRequest(
        request: RequestClass,
        context: Context
    ): ResponseClass {
        val greetingString = String.format("Hello %s %s!", request.firstName, request.lastName)
        return ResponseClass(greetingString)
    }
}
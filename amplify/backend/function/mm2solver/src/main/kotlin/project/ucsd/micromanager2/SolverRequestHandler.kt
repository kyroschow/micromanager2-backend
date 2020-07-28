/* Amplify Params - DO NOT EDIT
	ENV
    REGION
    STORAGE_MM2DB_ARN
    STORAGE_MM2DB_NAME
Amplify Params - DO NOT EDIT */
package project.ucsd.micromanager2

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

@Suppress("unused")
class SolverRequestHandler : RequestHandler<ScheduleId, ScheduleId> {
    override fun handleRequest(
        request: ScheduleId,
        context: Context
    ): ScheduleId {
        return request
    }
}
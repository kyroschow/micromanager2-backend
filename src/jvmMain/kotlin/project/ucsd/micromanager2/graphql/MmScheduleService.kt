package project.ucsd.micromanager2.graphql

import io.ktor.application.Application
import project.ucsd.micromanager2.dynamodb.ddb
import project.ucsd.micromanager2.model.MmSchedule

class MmScheduleQuery(val application: Application) {
    val TABLE_NAME = application.environment.config.property("aws.dynamodb.table_name").toString()
    fun scheduleById(
        id: String
    ): MmSchedule {
        val table = ddb!!.getTable(TABLE_NAME)
        val item = table!!.getItem("id", id)
        val schedule = MmSchedule(id = id, ownerId = item.getString("ownerId"),
            name = item.getString("name"), progress = item.getFloat("progress"),
            events = item.getList("events"))
        return schedule
    }

    fun schedules(
        page: Int,
        size: Int = 10
    ): List<MmSchedule> {
        TODO()
    }

    // TODO: remove after testing
    fun test(): MmSchedule = MmSchedule("12345", "Alan", "Schedule", 0f, listOf())
}

class MmScheduleMutation {
    fun solve(
        schedule: MmSchedule
    ): MmSchedule {
        // insert new schedule into dynamodb table
        // get the id back
        // start the aws lambda and send in the id of the schedule to aws lambda
        // lambda processes the schedule
        // lambda puts the processed schedule back into dynamodb
        return schedule
    }
}

class MmScheduleSubscription {
    fun scheduleStatus(
        id: String
    ): Unit = TODO()
}
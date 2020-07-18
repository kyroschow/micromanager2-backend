package project.ucsd.micromanager2.graphql

import project.ucsd.micromanager2.model.MmSchedule

class MmScheduleQuery {
    fun scheduleById(
        id: String
    ): MmSchedule {
        TODO()
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
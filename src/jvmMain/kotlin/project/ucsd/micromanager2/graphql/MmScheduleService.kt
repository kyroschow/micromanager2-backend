package project.ucsd.micromanager2.graphql

import project.ucsd.micromanager2.model.MmSchedule

class MmScheduleQuery {
    fun scheduleById(id: String): MmSchedule {
        TODO()
    }

    // TODO: remove after testing
    fun test(): MmSchedule = MmSchedule("12345", "Alan", "Schedule", listOf())
}

class MmScheduleMutation {
    fun solve(schedule: MmSchedule): Int {
        return 0
    }
}
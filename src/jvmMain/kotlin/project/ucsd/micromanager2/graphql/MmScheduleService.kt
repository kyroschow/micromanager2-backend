package project.ucsd.micromanager2.graphql

import project.ucsd.micromanager2.model.MmSchedule

class MmScheduleQuery {
    fun scheduleById(id: String): MmSchedule {
        TODO()
    }
}

class MmScheduleMutation {
    fun solve(schedule: MmSchedule): Int {
        return 0
    }
}
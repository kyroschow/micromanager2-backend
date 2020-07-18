package project.ucsd.micromanager2.model

data class MmSchedule(
    val id: String,
    val ownerId: String,
    val name: String,
    val progress: Float, // solve progress of this schedule, between 0.0 to 1.0 inclusive
    val events: List<MmEvent>
)
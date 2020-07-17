package project.ucsd.micromanager2.model

data class MmSchedule(
    val id: String,
    val ownerId: String,
    val name: String,
    val events: List<MmEvent>
)
package project.ucsd.micromanager2.model

data class MmEvent(
    val name: String,
    val startTime: Long?,
    val endTime: Long?,
    val duration: Long?
)

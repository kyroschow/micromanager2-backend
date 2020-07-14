package project.ucsd.micromanager2

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"
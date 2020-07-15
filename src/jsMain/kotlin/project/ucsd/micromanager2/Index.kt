package project.ucsd.micromanager2

import react.child
import react.dom.render
import kotlin.browser.*

fun main() {
    render(document.getElementById("root")) {
        child(App)
    }
}
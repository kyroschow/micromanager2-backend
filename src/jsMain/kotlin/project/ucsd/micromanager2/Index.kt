package project.ucsd.micromanager2

import react.child
import react.dom.render
import kotlin.browser.*

fun main() {
    document.getElementById("root")
        ?.let { root ->
            render(root) {
                child(App)
            }
        }
}
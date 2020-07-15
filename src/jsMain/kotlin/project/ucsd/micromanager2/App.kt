package project.ucsd.micromanager2

import react.RProps
import react.dom.h1
import react.dom.h2
import react.functionalComponent

val App = functionalComponent<RProps> {
    h1 {
        +"Welcome to Micromanager2"
    }
    h2 {
        +"Hello!"
    }
}
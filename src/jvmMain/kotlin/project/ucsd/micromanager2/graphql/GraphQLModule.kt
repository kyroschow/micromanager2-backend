package project.ucsd.micromanager2.graphql

import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema
import graphql.ExecutionInput
import graphql.GraphQL
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun Application.installGraphQL() {
    val config = SchemaGeneratorConfig(listOf("project.ucsd.micromanager2"))
    val schema = toSchema(
        config = config,
        queries = topLevelObjects(MmScheduleQuery(this)),
        mutations = topLevelObjects(MmScheduleMutation())
    )
    val graphQL = GraphQL.newGraphQL(schema).build()

    suspend fun ApplicationCall.executeQuery() {
        val request = receive<GraphQLRequest>()
        val executionInput = ExecutionInput.newExecutionInput()
            .query(request.query)
            .operationName(request.operationName)
            .variables(request.variables)
            .build()

        val output = graphQL.execute(executionInput)
        respond(output)
    }

    routing {
        get("/graphql") {
            call.executeQuery()
        }
        post("/graphql") {
            call.executeQuery()
        }
    }
}

private fun topLevelObjects(vararg elements: Any): List<TopLevelObject> =
    elements.map { TopLevelObject(it) }
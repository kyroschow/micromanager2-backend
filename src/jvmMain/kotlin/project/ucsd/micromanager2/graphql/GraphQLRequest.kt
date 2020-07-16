package project.ucsd.micromanager2.graphql

data class GraphQLRequest(
    val query: String,
    val operationName: String?,
    val variables: Map<String, Any>?
)
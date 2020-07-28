package project.ucsd.micromanager2.graphql

import com.amazonaws.services.dynamodbv2.document.PrimaryKey
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap
import io.ktor.application.Application
import project.ucsd.micromanager2.dynamodb.ddb
import project.ucsd.micromanager2.model.MmSchedule

class MmScheduleQuery(val application: Application) {
    val TABLE_NAME = application.environment.config.property("aws.dynamodb.table_name").toString()

    fun scheduleById(
        ownerId: String,
        id: String
    ): MmSchedule {
        val table = ddb!!.getTable(TABLE_NAME)
        val key = PrimaryKey()
        key.addComponent("ownerId", ownerId)
        key.addComponent("id", id)
        val item = table!!.getItem(key)
        return MmSchedule(id = id, ownerId = ownerId,
            name = item.getString("name"), progress = item.getFloat("progress"),
            events = item.getList("events"))
    }

    fun schedules(
        page: Int,
        size: Int = 10,
        ownerId: String
    ): List<MmSchedule> { //Figure out how to sort query results (global secondary index?)
        var numItems = 0
        val table = ddb!!.getTable(TABLE_NAME)
        val spec = QuerySpec()
            .withKeyConditionExpression("ownerId = :oid")
            .withValueMap(
                ValueMap().withString(":oid", ownerId)
            )
            .withMaxPageSize(size)
        val items = table.query(spec)
        val iterator = items.iterator()
        val itemList = mutableListOf<MmSchedule>()
        while(iterator.hasNext()){
            val item = iterator.next()
            itemList.add(MmSchedule(id = item.getString("id"), ownerId = ownerId,
                name = item.getString("name"), progress = item.getFloat("progress"),
                events = item.getList("events")))
        }
        return itemList.toList()
    }

    // TODO: remove after testing
    fun test(): MmSchedule = MmSchedule("12345", "Alan", "Schedule", 0f, listOf())
}

class MmScheduleMutation {
    fun solve(
        schedule: MmSchedule
    ): MmSchedule {
        // insert new schedule into dynamodb table
        // get the id back
        // start the aws lambda and send in the id of the schedule to aws lambda
        // lambda processes the schedule
        // lambda puts the processed schedule back into dynamodb
        return schedule
    }
}

class MmScheduleSubscription {
    fun scheduleStatus(
        id: String
    ): Unit = TODO()
}
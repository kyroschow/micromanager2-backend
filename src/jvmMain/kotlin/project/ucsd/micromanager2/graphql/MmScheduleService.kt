package project.ucsd.micromanager2.graphql

import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PrimaryKey
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap
import com.amazonaws.services.dynamodbv2.model.ReturnValue
import io.ktor.application.Application
import project.ucsd.micromanager2.dynamodb.ddb
import project.ucsd.micromanager2.model.MmSchedule

class MmScheduleQuery(val application: Application) {
    val TABLE_NAME = application.environment.config.property("aws.dynamodb.table_name").toString()

    fun scheduleById(
        ownerId: String,
        updateTime: String
    ): MmSchedule {
        val table = ddb!!.getTable(TABLE_NAME)
        val key = PrimaryKey()
        key.addComponent("ownerId", ownerId)
        key.addComponent("updateTime", updateTime)
        val item = table!!.getItem(key)
        return MmSchedule(updateTime = updateTime, ownerId = ownerId,
            name = item.getString("name"), progress = item.getFloat("progress"),
            events = item.getList("events"))
    }

    fun schedules(
        size: Int = 10,
        ownerId: String
    ): List<MmSchedule> {
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
            itemList.add(MmSchedule(updateTime = item.getString("updateTime"), ownerId = ownerId,
                name = item.getString("name"), progress = item.getFloat("progress"),
                events = item.getList("events")))
        }
        return itemList.toList()
    }

    // TODO: remove after testing
    fun test(): MmSchedule = MmSchedule("12345", "Alan", "Schedule", 0f, listOf())
}

class MmScheduleMutation(val application: Application) {
    val TABLE_NAME = application.environment.config.property("aws.dynamodb.table_name").toString()

    fun create(
        schedule: MmSchedule
    ){
        val table = ddb!!.getTable(TABLE_NAME)
        table.putItem(Item()
            .withPrimaryKey("ownerId", schedule.ownerId, "updateTime", schedule.updateTime)
            .withString("name", schedule.name)
            .withList("events", schedule.events)
        )
    }

    fun delete(
        ownerId: String,
        updateTime: String
    ){
        val table = ddb!!.getTable(TABLE_NAME)
        val spec = DeleteItemSpec()
            .withPrimaryKey("ownerId", ownerId, "updateTime", updateTime)
        table.deleteItem(spec)
    }

    fun solve(
        schedule: MmSchedule
    ): MmSchedule {
        // insert new schedule into dynamodb table
        // get the id back
        // start the aws lambda and send in the id of the schedule to aws lambda
        // lambda processes the schedule
        // lambda puts the processed schedule back into dynamodb
        val table = ddb!!.getTable(TABLE_NAME)
        val origUT = schedule.updateTime
        val spec = UpdateItemSpec()
            .withPrimaryKey("ownerId", schedule.ownerId, "updateTime", origUT)
            .withUpdateExpression("set updateTime=:u, events=:e")
            .withValueMap(
                ValueMap()
                    .withString(":u", schedule.updateTime)
                    .withList(":e", schedule.events)
            )
            .withReturnValues(ReturnValue.UPDATED_NEW)
        val outcome = table.updateItem(spec)
        return schedule
    }
}

class MmScheduleSubscription {
    fun scheduleStatus(
        id: String
    ): Unit = TODO()
}
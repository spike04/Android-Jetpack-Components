package com.rubin.jetpackcomponents.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_workmanager_main.*

class WorkManagerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager_main)

        val workManager = WorkManager.getInstance()

        val powerConstraint = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val taskData = Data.Builder()
            .putString(MESSAGE_STATUS, "Message Sent Successfully")
            .build()

        val request =
            OneTimeWorkRequest.Builder(SendWorker::class.java).setConstraints(powerConstraint).setInputData(taskData)
                .build()

        btnSend.setOnClickListener {
            workManager.enqueue(request)
        }

        workManager.getWorkInfoByIdLiveData(request.id).observe(this@WorkManagerMainActivity, Observer { workInfo ->
            workInfo?.let {

                if (it.state.isFinished) {
                    val outputData = it.outputData
                    val taskResult = outputData.getString(SendWorker.WORK_RESULT)
                    txtStatus.append(taskResult + "\n")
                }

                val state = workInfo.state
                txtStatus.append(state.toString() + "\n")
            }
        })
    }

    companion object {
        const val MESSAGE_STATUS = "message_status"
    }
}
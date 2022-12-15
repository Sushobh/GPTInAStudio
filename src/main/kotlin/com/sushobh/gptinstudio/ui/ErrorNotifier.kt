package com.sushobh.gptinstudio.ui


import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

object ErrorNotifier {



    fun apiError(
        project: Project?
    ) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("org.sushobh.gptinstudio.apierrors")
            .createNotification("Hey , Api failed, you might want to check the Open AI token. " +
                    "\n Settings -> Tools -> GPTInStudioSettings", NotificationType.ERROR)
            .notify(project)
    }

    fun incompatibleApiCallError(
        project: Project?
    ) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("org.sushobh.gptinstudio.apierrors")
            .createNotification("GPTInStudio currently works only with Kotlin functions :( \n " +
                    "Right click on function name to get started", NotificationType.ERROR)
            .notify(project)
    }
}
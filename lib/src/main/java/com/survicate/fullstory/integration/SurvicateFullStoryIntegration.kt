package com.survicate.fullstory.integration

import com.fullstory.FS
import com.survicate.surveys.IntegrationListener
import com.survicate.surveys.IntegrationPayload
import com.survicate.surveys.QuestionAnsweredEvent

class SurvicateFullStoryIntegration : IntegrationListener() {
    override val providerName: String = "fullstory"
    internal val eventName = "Survicate question answered"

    override fun onWillSendAnswer(): Map<String, IntegrationPayload> {
        val sessionUrl: String = FS.getCurrentSessionURL() ?: ""
        return mapOf("fullstory_url" to IntegrationPayload(sessionUrl))
    }

    override fun onQuestionAnswered(event: QuestionAnsweredEvent) {
        val eventArgs = event.toFullStoryEventProperties()
        FS.event(eventName, eventArgs)
        FS.setUserVars(event.toFullStoryUserVars())
    }
}

internal fun QuestionAnsweredEvent.toFullStoryEventProperties(): Map<String, Any> {
    return mapOf(
        "answer_str" to answer.value.orEmpty(),
        "question_str" to questionText,
        "survey_name_str" to surveyName,
    )
}

internal fun QuestionAnsweredEvent.toFullStoryUserVars(): Map<String, Any> {
    return mapOf(
        "survey_id" to surveyId,
        "survicate_response_uuid_str" to responseUuid,
        "survicate_visitor_uuid_str" to visitorUuid
    )
}

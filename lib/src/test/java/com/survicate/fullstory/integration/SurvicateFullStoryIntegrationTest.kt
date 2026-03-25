package com.survicate.fullstory.integration

import com.survicate.surveys.QuestionAnsweredEvent
import com.survicate.surveys.SurvicateAnswer
import com.survicate.surveys.surveys.CtaSurveyAnswerType
import com.survicate.surveys.surveys.QuestionSurveyAnswerType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SurvicateFullStoryIntegrationTest {

    @Test
    fun `has correct provider name`() {
        assertEquals("fullstory", SurvicateFullStoryIntegration().providerName)
    }

    @Test
    fun `has correct event name`() {
        val integration = SurvicateFullStoryIntegration()
        assertEquals("Survicate question answered", integration.eventName)
    }

    @Test
    fun `creates FullStory event properties from Survicate question answered event`() {
        val event = QuestionAnsweredEvent(
            responseUuid = SAMPLE_RESPONSE_UUID,
            visitorUuid = SAMPLE_VISITOR_UUID,
            surveyId = "testSurveyId",
            surveyName = "Test Survey",
            questionId = 1,
            questionText = "Question text",
            answer = SurvicateAnswer(
                type = QuestionSurveyAnswerType.SINGLE,
                id = 2,
                ids = null,
                value = "Test answer",
            ),
            panelAnswerUrl = "https://test.com"
        )

        val fsProps = event.toFullStoryEventProperties()

        assertEquals("Test answer", fsProps["answer_str"])
        assertEquals("Question text", fsProps["question_str"])
        assertEquals("Test Survey", fsProps["survey_name_str"])
    }

    @Test
    fun `creates FullStory user vars from Survicate question answered event`() {
        val event = QuestionAnsweredEvent(
            responseUuid = SAMPLE_RESPONSE_UUID,
            visitorUuid = SAMPLE_VISITOR_UUID,
            surveyId = "testSurveyId",
            surveyName = "Test Survey",
            questionId = 1,
            questionText = "Question text",
            answer = SurvicateAnswer(
                type = QuestionSurveyAnswerType.SINGLE,
                id = 2,
                ids = null,
                value = "Test answer",
            ),
            panelAnswerUrl = "https://test.com"
        )

        val fsUserVars = event.toFullStoryUserVars()

        assertEquals("testSurveyId", fsUserVars["survey_id"])
        assertEquals(SAMPLE_RESPONSE_UUID, fsUserVars["survicate_response_uuid_str"])
        assertEquals(SAMPLE_VISITOR_UUID, fsUserVars["survicate_visitor_uuid_str"])
    }

    @Test
    fun `creates FullStory event properties with empty string as answer if SurvicateAnswer value is null`() {
        val event = QuestionAnsweredEvent(
            responseUuid = SAMPLE_RESPONSE_UUID,
            visitorUuid = SAMPLE_VISITOR_UUID,
            surveyId = "testSurveyId",
            surveyName = "Test Survey",
            questionId = 1,
            questionText = "Lorem ipsum",
            answer = SurvicateAnswer(
                type = CtaSurveyAnswerType.BUTTON_CLOSE,
                id = null,
                ids = null,
                value = null,
            ),
            panelAnswerUrl = "https://test.com"
        )

        val fsProps = event.toFullStoryEventProperties()

        assertEquals("", fsProps["answer_str"])
    }

    companion object Companion {
        private const val SAMPLE_RESPONSE_UUID = "00000000-0000-0000-0000-000000000000"
        private const val SAMPLE_VISITOR_UUID = "10000000-0000-0000-0000-000000000001"
    }
}


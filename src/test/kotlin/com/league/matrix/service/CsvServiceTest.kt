package com.league.matrix.service

import com.league.matrix.exception.BadRequestException
import com.league.matrix.exception.CsvImportException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import java.io.File

class CsvServiceTest {
    private val csvService = CsvService()

    @Test
    fun test_upload_when_is_ok() {
        // GIVEN
        val matrix = Matrix(3, 3, listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))
        val fileResourceOk = File(
            "src/test/kotlin/com/league/matrix/mock/matrix.csv"
        )
        val fileOk = MockMultipartFile(
            "file", fileResourceOk.name,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            fileResourceOk.inputStream()
        )

        // WHEN
        val response = csvService.getMatrixFromFile(fileOk)

        // THEN
        Assertions.assertEquals(matrix, response)
    }

    @Test
    fun test_upload_when_is_empty() {
        // GIVEN
        val fileResourceEmpty = File(
            "src/test/kotlin/com/league/matrix/mock/matrix-empty.csv"
        )
        val file = MockMultipartFile(
            "file", fileResourceEmpty.name,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            fileResourceEmpty.inputStream()
        )

        // WHEN
        val response = assertThrows<BadRequestException> { csvService.getMatrixFromFile(file) }

        // THEN
        Assertions.assertEquals("Empty file", response.message)
    }

    @Test
    fun test_upload_when_is_not_square() {
        // GIVEN
        val fileResourceEmpty = File(
            "src/test/kotlin/com/league/matrix/mock/matrix-not-square.csv"
        )
        val file = MockMultipartFile(
            "file", fileResourceEmpty.name,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            fileResourceEmpty.inputStream()
        )

        // WHEN
        val response = assertThrows<CsvImportException> { csvService.getMatrixFromFile(file) }

        // THEN
        Assertions.assertEquals("Error during csv import", response.message)
    }

    @Test
    fun test_upload_when_is_not_ok() {
        // GIVEN
        val fileResourceEmpty = File(
            "src/test/kotlin/com/league/matrix/mock/matrix-wrong-values.csv"
        )
        val file = MockMultipartFile(
            "file", fileResourceEmpty.name,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            fileResourceEmpty.inputStream()
        )

        // WHEN
        val response = assertThrows<CsvImportException> { csvService.getMatrixFromFile(file) }

        // THEN
        Assertions.assertEquals("Error during csv import", response.message)
    }
}
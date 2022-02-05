package com.league.matrix.controller

import com.league.matrix.service.CsvService
import com.league.matrix.service.Matrix
import com.league.matrix.service.MatrixService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import java.io.File


class MatrixControllerTest {
    private val matrixService: MatrixService = mockk()
    private val csvService: CsvService = mockk()
    private val matrixController: MatrixController = MatrixController(csvService, matrixService)

    companion object {
        val matrix = Matrix(3, 3, listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))
        val fileResource = File(
            "src/test/kotlin/com/league/matrix/mock/matrix.csv"
        )
        val file = MockMultipartFile(
            "file", fileResource.name,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            fileResource.inputStream()
        )
    }

    @Test
    fun test_echo_when_is_ok() {
        // GIVEN
        val result = """
            1,2,3
            4,5,6
            7,8,9
        """.trimIndent()

        every { csvService.getMatrixFromFile(any()) }.answers { matrix }
        every { matrixService.echo(any()) }.answers { result }

        // WHEN
        val response = matrixController.echo(file)

        // THEN
        Assertions.assertEquals(result, response)
    }

    @Test
    fun test_invert_when_is_ok() {
        // GIVEN
        val result = """
            1,4,7
            2,5,8
            3,6,9
        """.trimIndent()

        every { csvService.getMatrixFromFile(any()) }.answers { matrix }
        every { matrixService.invert(any()) }.answers { result }

        // WHEN
        val response = matrixController.invert(file)

        // THEN
        Assertions.assertEquals(result, response)
    }

    @Test
    fun test_flatten_when_is_ok() {
        // GIVEN
        val result = "1,2,3,4,5,6,7,8,9"

        every { csvService.getMatrixFromFile(any()) }.answers { matrix }
        every { matrixService.flatten(any()) }.answers { result }

        // WHEN
        val response = matrixController.flatten(file)

        // THEN
        Assertions.assertEquals(result, response)
    }

    @Test
    fun test_sum_when_is_ok() {
        // GIVEN
        val result = "45"

        every { csvService.getMatrixFromFile(any()) }.answers { matrix }
        every { matrixService.sum(any()) }.answers { result }

        // WHEN
        val response = matrixController.sum(file)

        // THEN
        Assertions.assertEquals(result, response)
    }

    @Test
    fun test_product_when_is_ok() {
        // GIVEN
        val result = "362880"

        every { csvService.getMatrixFromFile(any()) }.answers { matrix }
        every { matrixService.product(any()) }.answers { result }

        // WHEN
        val response = matrixController.product(file)

        // THEN
        Assertions.assertEquals(result, response)
    }
}
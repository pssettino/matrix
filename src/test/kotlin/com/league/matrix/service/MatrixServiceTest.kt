package com.league.matrix.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import java.io.File

class MatrixServiceTest {
    private val matrixService: MatrixService = MatrixService()

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

        // WHEN
        val response = matrixService.echo(matrix)

        // THEN
        Assertions.assertEquals(result.trim(), response.trim())
    }

    @Test
    fun test_invert_when_is_ok() {
        // GIVEN
        val result = """
            1,4,7
            2,5,8
            3,6,9
        """.trimIndent()

        // WHEN
        val response = matrixService.invert(matrix)

        // THEN
        Assertions.assertEquals(result.trim(), response.trim())
    }

    @Test
    fun test_flatten_when_is_ok() {
        // GIVEN
        val result = "1,2,3,4,5,6,7,8,9"

        // WHEN
        val response = matrixService.flatten(matrix)

        // THEN
        Assertions.assertEquals(result, response)
    }

    @Test
    fun test_sum_when_is_ok() {
        // GIVEN
        val result = "45"

        // WHEN
        val response = matrixService.sum(matrix)

        // THEN
        Assertions.assertEquals(result, response)
    }

    @Test
    fun test_product_when_is_ok() {
        // GIVEN
        val result = "362880"

        // WHEN
        val response = matrixService.product(matrix)

        // THEN
        Assertions.assertEquals(result, response)
    }
}
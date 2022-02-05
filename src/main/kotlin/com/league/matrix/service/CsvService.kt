package com.league.matrix.service

import com.league.matrix.exception.BadRequestException
import com.league.matrix.exception.CsvImportException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Service
class CsvService {

    fun getMatrixFromFile(file: MultipartFile): Matrix {
        throwIfFileEmpty(file)
        var fileReader: BufferedReader? = null
        try {
            fileReader = BufferedReader(InputStreamReader(file.inputStream))
            var line: String? = "";
            val matrix = mutableListOf<List<Int>>()
            var row = 0
            var column = 0
            while (line != null) {
                line = fileReader.readLine()
                if (line != null) {
                    val value = line.split(",")
                    column = value.size
                    val array = value.map {
                        it.toInt()
                    }
                    matrix.add(array)
                    row += 1
                }
            }

            if (row != column) throw CsvImportException("")

            return Matrix(row, column, matrix)
        } catch (ex: Exception) {
            throw CsvImportException("Error during csv import")
        } finally {
            closeFileReader(fileReader)
        }
    }

    private fun throwIfFileEmpty(file: MultipartFile) {
        if (file.isEmpty)
            throw BadRequestException("Empty file")
    }

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException("Error during csv import")
        }
    }
}
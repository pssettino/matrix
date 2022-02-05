package com.league.matrix.controller

import com.league.matrix.service.CsvService
import com.league.matrix.service.MatrixService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/")
class MatrixController @Autowired constructor(
    val csvService: CsvService,
    val matrixService: MatrixService
) {
    @GetMapping("echo")
    fun echo(
        @RequestParam("file") file: MultipartFile
    ): String {
        // get matrix object from csv file
        val importedEntries = csvService.getMatrixFromFile(file)

        // Return the matrix as a string in matrix format.
        return matrixService.echo(importedEntries)
    }

    @GetMapping("invert")
    fun invert(
        @RequestParam("file") file: MultipartFile
    ): String {
        // get matrix object from csv file
        val importedEntries = csvService.getMatrixFromFile(file)

        // Return the matrix as a string in matrix format where the columns and rows are inverted
        return matrixService.invert(importedEntries)
    }

    @GetMapping("flatten")
    fun flatten(
        @RequestParam("file") file: MultipartFile
    ): String {
        // get matrix object from csv file
        val importedEntries = csvService.getMatrixFromFile(file)

        // Return the matrix as a 1 line string, with values separated by commas.
        return matrixService.flatten(importedEntries)
    }

    @GetMapping("sum")
    fun sum(
        @RequestParam("file") file: MultipartFile
    ): String {
        // get matrix object from csv file
        val importedEntries = csvService.getMatrixFromFile(file)

        // Return the sum of the integers in the matrix
        return matrixService.sum(importedEntries)
    }


    @GetMapping("product")
    fun product(
        @RequestParam("file") file: MultipartFile
    ): String {
        // get matrix object from csv file
        val importedEntries = csvService.getMatrixFromFile(file)

        // Return the product of the integers in the matrix
        return matrixService.product(importedEntries)
    }
}
package com.league.matrix.service

import org.springframework.stereotype.Service

@Service
class MatrixService {
    fun echo(matrix: Matrix): String {
        // I go through the N x N matrix, so accumulate the values of the same in a variable of type String
        var response = ""
        for (i in 0 until matrix.row) {
            for (j in 0 until matrix.column) {
                response += if(j == matrix.column - 1) {
                    "${matrix.values[i][j]}"
                } else {
                    "${matrix.values[i][j]},"
                }
            }
            response += "\n"
        }

        return response
    }

    fun invert(matrix: Matrix): String {
        // Go through the N x N matrix inverting the indices i by j to obtain the inverse matrix and accumulate
        // the values of the matrix in a variable of type String
        var response = ""
        for (i in 0 until matrix.column) {
            for (j in 0 until matrix.row) {
                response += if(j == matrix.row - 1) {
                    "${matrix.values[j][i]}"
                } else {
                    "${matrix.values[j][i]},"
                }
            }
            response += "\n"
        }

        return response
    }

    fun flatten(matrix: Matrix): String {
        // Replace line breaks and remove last comma
        val response = echo(matrix).replace("\n", ",")

        return response.take(response.length - 1)
    }

    fun sum(matrix: Matrix): String {
        // Replace line breaks, remove last comma, and split by comma then perform a sum of a list.
        val response = echo(matrix).replace("\n", ",")

        val values = response.take(response.length - 1).split(",").toList()

        return values.sumOf { it.toInt() }.toString()
    }

    fun product(matrix: Matrix): String {
        // Replace line breaks, remove last comma, and split by comma then perform a product of a list.
        val response = echo(matrix).replace("\n", ",")

        val values = response.take(response.length - 1).split(",").toList()

        var product = 1
        values.forEach {
            product *= it.toInt()
        }

        return product.toString()
    }
}
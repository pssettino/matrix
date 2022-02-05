package com.league.matrix.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

// When something inside the csv is wrong, then throw exception.
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class CsvImportException(msg: String) : RuntimeException(msg)
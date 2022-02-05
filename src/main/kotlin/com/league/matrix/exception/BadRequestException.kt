package com.league.matrix.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

// When file is empty, then throw exception.
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(msg: String) : RuntimeException(msg)
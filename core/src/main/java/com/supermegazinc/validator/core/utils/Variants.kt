package com.supermegazinc.validator.core.utils

import com.supermegazinc.validator.core.InputValidator
import com.supermegazinc.validator.core.ValidatedInput

typealias ValidatedString = ValidatedInput<String>
typealias ValidatedInt = ValidatedInput<Int>

typealias StringValidator = InputValidator<String>
typealias IntValidator = InputValidator<Int>

typealias StringValidatorBuilder = InputValidatorBuilder<String>
typealias IntValidatorBuilder = InputValidatorBuilder<Int>
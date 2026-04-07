package com.supermegazinc.validator.core.utils

import com.supermegazinc.validator.core.InputValidator
import com.supermegazinc.validator.core.InputValidators

fun <T>Set<InputValidator<T>>.asInputValidators() = InputValidators(this)
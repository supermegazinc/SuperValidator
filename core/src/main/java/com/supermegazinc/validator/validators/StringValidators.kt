package com.supermegazinc.validator.validators

import com.supermegazinc.validator.core.utils.StringValidator
import com.supermegazinc.validator.core.utils.StringValidatorBuilder

object StringValidators {

	val EmptyField: StringValidator = StringValidatorBuilder { it.isNotEmpty() }

}
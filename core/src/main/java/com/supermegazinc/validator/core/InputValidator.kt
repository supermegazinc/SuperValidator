package com.supermegazinc.validator.core

interface InputValidator<T> {
	fun validate(input: T): Boolean
}
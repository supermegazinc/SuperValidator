package com.supermegazinc.validator.compose.example.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExampleScreen() {

	val vm = viewModel<ExampleScreenVM>()
	val form = vm.exampleFormReactive.compose()

	var username by form.username.inputState
	var password by form.password.inputState
	var password2 by form.password2.inputState
	val enabled by vm.isReady.collectAsStateWithLifecycle()

	Surface {
		Column(
			modifier = Modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			TextField(
				placeholder = { Text("Username") },
				value = username,
				onValueChange = { username = it },
				isError = form.username.failedValidator != null
			)
			TextField(
				placeholder = { Text("Password") },
				value = password,
				onValueChange = { password = it },
				isError = form.password.failedValidator != null
			)
			TextField(
				placeholder = { Text("Confirm password") },
				value = password2,
				onValueChange = { password2 = it },
				isError = form.password2.failedValidator != null
			)
			Button(
				onClick = {},
				enabled = enabled,
				content = {
					Text("Continue")
				}
			)
		}
	}
}

@Composable
@Preview
fun ExampleScreenPreview() {
	ExampleScreen()
}
package com.example.recipes.screens.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipes.R.string as AppText
import com.example.recipes.common.composable.*
import com.example.recipes.common.ext.basicButton
import com.example.recipes.common.ext.fieldModifier

@Composable
fun SignUpScreen(
  openAndPopUp: (String, String) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignUpViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState
  val fieldModifier = Modifier.fieldModifier()

  BasicToolbar(AppText.create_account)

  Column(
    modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
    PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
    RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

    BasicButton(AppText.create_account, Modifier.basicButton()) {
      viewModel.onSignUpClick(openAndPopUp)
    }
  }
}

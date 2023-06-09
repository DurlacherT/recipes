package com.example.recipes.screens.settings

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipes.R.drawable as AppIcon
import com.example.recipes.R.string as AppText
import com.example.recipes.common.composable.*
import com.example.recipes.common.ext.card
import com.example.recipes.common.ext.spacer

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
  restartApp: (String) -> Unit,
  openScreen: (String) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SettingsViewModel = hiltViewModel()
) {
  val uiState by viewModel.uiState.collectAsState(initial = SettingsUiState(false))

  Column(
    modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    BasicToolbar(AppText.settings)

    Spacer(modifier = Modifier.spacer())

    if (uiState.isAnonymousAccount) {
      RegularCardEditor(AppText.sign_in, AppIcon.ic_sign_in, "", Modifier.card()) {
        viewModel.onLoginClick(openScreen)
      }

      RegularCardEditor(AppText.create_account, AppIcon.ic_create_account, "", Modifier.card()) {
        viewModel.onSignUpClick(openScreen)
      }
    } else {
      SignOutCard { viewModel.onSignOutClick(restartApp) }
      DeleteMyAccountCard { viewModel.onDeleteMyAccountClick(restartApp) }
    }
    val contextForToast = LocalContext.current.applicationContext

    var checkedvege by remember {
      mutableStateOf(true)
    }
    var checkedvega by remember {
      mutableStateOf(true)
    }
    var checkedall by remember {
      mutableStateOf(true)
    }

    Column(

    ) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
      Checkbox(
        checked = checkedvege,
        onCheckedChange = { checked_ ->
          checkedvege = checked_
          Toast.makeText(contextForToast, "checked_ = $checked_", Toast.LENGTH_SHORT).show()
        }
      )

      Text(
        modifier = Modifier.padding(start = 2.dp),
        text = "Vegetarian"
      )
    }
    Row(verticalAlignment = Alignment.CenterVertically,  horizontalArrangement = Arrangement.Start) {
      Checkbox(
        checked = checkedvega,
        onCheckedChange = { checked_ ->
          checkedvega = checked_
          Toast.makeText(contextForToast, "checked_ = $checked_", Toast.LENGTH_SHORT).show()
        }
      )

      Text(
        modifier = Modifier.padding(start = 2.dp),
        text = "Vegan"
      )}

      Row(verticalAlignment = Alignment.CenterVertically,  horizontalArrangement = Arrangement.Start) {
        Checkbox(
          checked = checkedall,
          onCheckedChange = { checked_ ->
            checkedall = checked_
            Toast.makeText(contextForToast, "checked_ = $checked_", Toast.LENGTH_SHORT).show()
          }
        )

        Text(
          modifier = Modifier.padding(start = 2.dp),
          text = "Food Allergies"
        )
      }}
  }
}

@ExperimentalMaterialApi
@Composable
private fun SignOutCard(signOut: () -> Unit) {
  var showWarningDialog by remember { mutableStateOf(false) }

  RegularCardEditor(AppText.sign_out, AppIcon.ic_exit, "", Modifier.card()) {
    showWarningDialog = true
  }

  if (showWarningDialog) {
    AlertDialog(
      title = { Text(stringResource(AppText.sign_out_title)) },
      text = { Text(stringResource(AppText.sign_out_description)) },
      dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
      confirmButton = {
        DialogConfirmButton(AppText.sign_out) {
          signOut()
          showWarningDialog = false
        }
      },
      onDismissRequest = { showWarningDialog = false }
    )
  }
}

@ExperimentalMaterialApi
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
  var showWarningDialog by remember { mutableStateOf(false) }

  DangerousCardEditor(
    AppText.delete_my_account,
    AppIcon.ic_delete_my_account,
    "",
    Modifier.card()
  ) {
    showWarningDialog = true
  }

  if (showWarningDialog) {
    AlertDialog(
      title = { Text(stringResource(AppText.delete_account_title)) },
      text = { Text(stringResource(AppText.delete_account_description)) },
      dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
      confirmButton = {
        DialogConfirmButton(AppText.delete_my_account) {
          deleteMyAccount()
          showWarningDialog = false
        }
      },
      onDismissRequest = { showWarningDialog = false }
    )
  }
}



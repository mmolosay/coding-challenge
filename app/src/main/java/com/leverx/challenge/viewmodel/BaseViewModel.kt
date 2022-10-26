package com.leverx.challenge.viewmodel

import androidx.lifecycle.ViewModel

/**
 * Wrapper for [ViewModel] to be used as superclass of all other `ViewModel`.
 *
 * Introducing such a "base" classs allows to have mutable code-wise common ancestor and
 * tweak behaviour of all inheritors easily.
 */
abstract class BaseViewModel : ViewModel()
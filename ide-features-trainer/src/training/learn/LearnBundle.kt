// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package training.learn

import com.intellij.DynamicBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
private const val BUNDLE = "training.learn.LearnBundle"

open class BundlePlace(val bundleAppendix: String)

object LearnBundle : DynamicBundle(BUNDLE) {

  fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String = getMessage(key, *params)

  fun messageInPlace(@PropertyKey(resourceBundle = BUNDLE) key: String, bundlePlace: BundlePlace, vararg params: Any): String =
    getMessage(key + bundlePlace.bundleAppendix, *params)
}
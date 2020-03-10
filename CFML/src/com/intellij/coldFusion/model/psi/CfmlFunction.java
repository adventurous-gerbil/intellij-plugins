// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.coldFusion.model.psi;

import com.intellij.coldFusion.model.CfmlUtil;
import com.intellij.coldFusion.model.files.CfmlFile;
import com.intellij.coldFusion.model.info.CfmlFunctionDescription;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CfmlFunction extends PsiNameIdentifierOwner {
  CfmlFunction[] EMPTY_ARRAY = new CfmlFunction[0];

  @NotNull
  String getParametersAsString();

  CfmlParameter @NotNull [] getParameters();

  @Nullable
  default PsiType getReturnType() {
    return CfmlUtil.getTypeFromName(getContainingFile(), getReturnTypeName(), getProject());
  }

  @Nullable
  String getReturnTypeName();

  @Nullable
  CfmlFile getContainingFile();

  @Nullable
  String getDescription();

  @Override
  @NotNull
  String getName();

  @NotNull
  CfmlFunctionDescription getFunctionInfo();
}

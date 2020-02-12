// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.coldFusion.UI.editorActions;

import com.intellij.coldFusion.model.CfmlUtil;
import com.intellij.coldFusion.model.psi.CfmlComponent;
import com.intellij.coldFusion.model.psi.CfmlFunction;
import com.intellij.coldFusion.model.psi.CfmlProperty;
import com.intellij.coldFusion.model.psi.CfmlTag;
import com.intellij.coldFusion.model.psi.impl.CfmlAttributeImpl;
import com.intellij.coldFusion.model.psi.impl.CfmlAttributeNameImpl;
import com.intellij.coldFusion.model.psi.impl.CfmlTagImpl;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Lera Nikolaenko
 */
public class CfmlDocumentProvider implements DocumentationProvider {

  @Override
  public String generateDoc(PsiElement element, PsiElement originalElement) {
    if (element instanceof CfmlComponent) {
      return CfmlUtil.getComponentDescription((CfmlComponent)element, element.getProject());
    }
    else if (element instanceof CfmlFunction) {
      return CfmlUtil.getFunctionDescription((CfmlFunction)element, element.getProject());
    }
    else if (element instanceof CfmlProperty) {
      return CfmlUtil.getPropertyDescription((CfmlProperty)element, element.getProject());
    }
    else if (element instanceof CfmlAttributeImpl && element.getParent() instanceof CfmlTag) {
      String tagName = StringUtil.toLowerCase(((CfmlTag)element.getParent()).getTagName());
      String attributeName = (element instanceof CfmlAttributeNameImpl) ?
                             "name" :
                             StringUtil.notNullize(((CfmlAttributeImpl)element).getName());
      return CfmlUtil.getAttributeDescription(tagName, attributeName, element.getProject());
    }
    else if (element instanceof CfmlTag) {
      String name = StringUtil.toLowerCase(((CfmlTag)element).getTagName());
      if (CfmlUtil.isStandardTag(name, element.getProject())) {
        return CfmlUtil.getTagDescription(name, element.getProject());
      }
    }
    return null;
  }

  @Nullable
  @Override
  public PsiElement getCustomDocumentationElement(@NotNull Editor editor,
                                                  @NotNull PsiFile file,
                                                  @Nullable PsiElement contextElement,
                                                  int targetOffset) {
    if (contextElement == null) return null;
    if (contextElement.getParent() instanceof CfmlTagImpl) return contextElement.getParent();
    if (contextElement.getParent() instanceof CfmlAttributeImpl) {
      return contextElement.getParent();
    }
    else {
      return null;
    }
  }
}

package com.intellij.coldFusion.model.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CfmlArrayAccessExpression extends CfmlCompositeElement implements CfmlExpression, CfmlTypedElement {
  public CfmlArrayAccessExpression(@NotNull ASTNode node) {
    super(node);
  }

  @Nullable
  public CfmlTypedElement getInnerExpression() {
    return findChildByClass(CfmlTypedElement.class);
  }

  @Nullable
  public PsiType getExternalType() {
    final CfmlTypedElement innerExpression = getInnerExpression();
    if (innerExpression != null) {
      return innerExpression.getPsiType();
    }
    return null;
  }

  @Override
  @Nullable
  public PsiType getPsiType() {
    PsiType type = getExternalType();

    if (type instanceof PsiArrayType) {
      return ((PsiArrayType)type).getComponentType();
    }
    return null;
  }
}

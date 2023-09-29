package com.jetbrains.support;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.codeEditor.printing.HTMLTextPainter;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.awt.RelativePoint;

import java.awt.*;


public class HelloWorldAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        String myCode = """
    public static Integer fibonacci(Integer n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) return 1;
        else return (fibonacci(n - 1) + fibonacci(n - 2));
    }               
                """;

        Project project = ProjectManager.getInstance().getDefaultProject();

        PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
        PsiFile psiFile = psiFileFactory.createFileFromText("MyDummyFile.java", myCode);

        String htmlFragment = "<html><body>" +
                com.intellij.codeEditor.printing.HTMLTextPainter.convertCodeFragmentToHTMLFragmentWithInlineStyles(psiFile, myCode) +
                "</html></body>";
        // TODO: Display the HTML fragment in an IntelliJ window or dialog.


        PopupDialog popupDialog = new PopupDialog();
        popupDialog.setPreferredSize(new Dimension(500, 500));
        popupDialog.setTooltip(myCode);
        popupDialog.setVisible(true);
    }
}

package com.jetbrains.support.example.htmlfunctest;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.codeEditor.printing.HTMLTextPainter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

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

// Alternatively, you can get the project for a specific open project by name.
// Replace "YourProjectName" with the name of the project you want to obtain.
        Project specificProject = ProjectManager.getInstance().getOpenProjects()[0];//   .findProjectByName("YourProjectName");

        PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
        PsiFile psiFile = psiFileFactory.createFileFromText("MyDummyFile.java", myCode);

        String htmlFragment = com.intellij.codeEditor.printing.HTMLTextPainter.convertCodeFragmentToHTMLFragmentWithInlineStyles(psiFile, myCode);
        // TODO: Display the HTML fragment in an IntelliJ window or dialog.
        int i = 1;
    }
}

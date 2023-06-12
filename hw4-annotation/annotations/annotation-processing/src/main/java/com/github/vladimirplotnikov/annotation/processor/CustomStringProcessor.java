package com.github.vladimirplotnikov.annotation.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.github.vladimirplotnikov.annotation.processor.CustomToString")
@AutoService(Processor.class)
public class CustomStringProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (Element element :  roundEnv.getElementsAnnotatedWith(CustomToString.class)) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("HELLO FROM CUSTOMSTRINGPORCESSOR");
            String className = String.valueOf(element.getSimpleName());
            System.out.println("И имя классу="+className);
            String packageName = element.getEnclosingElement().toString();
            System.out.println("И имя пакету="+packageName);

            try {
                writeBuilderFile(className,packageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private void writeBuilderFile(String className,String packageName) throws IOException {
        //String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }
        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = className + "Interface";
        String builderSimpleClassName = builderClassName.substring(lastDot + 1);

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }
            out.print("interface ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    public void toString( ");
            out.print(simpleClassName);
            out.print(" myClass); ");
            out.println();

            out.println("}");
        }
    }
}
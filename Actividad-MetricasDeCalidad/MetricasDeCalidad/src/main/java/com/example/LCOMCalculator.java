package com.example;

import java.util.*;

public class LCOMCalculator {
    public static class ClassInfo {
        List<String> methods = new ArrayList<>();
        Map<String, Set<String>> methodAttributes = new HashMap<>();
        Set<String> attributes = new HashSet<>();

        public void addMethod(String methodName, Set<String> attrs) {
            if (methodName == null || methodName.isEmpty() || attrs == null) {
                throw new IllegalArgumentException("Invalid method name or attributes");
            }
            methods.add(methodName);
            methodAttributes.put(methodName, attrs);
            attributes.addAll(attrs);
        }
    }

    public static void main(String[] args) {
        ClassInfo classInfo = new ClassInfo();
        // Simulación de entrada de métodos y sus accesos a atributos
        classInfo.addMethod("method1", new HashSet<>(Arrays.asList("attr1", "attr2")));
        classInfo.addMethod("method2", new HashSet<>(Arrays.asList("attr2")));
        classInfo.addMethod("method3", new HashSet<>(Arrays.asList("attr3")));
        classInfo.addMethod("method4", new HashSet<>(Arrays.asList("attr4","attr5","attr6")));
        classInfo.addMethod("method5", new HashSet<>(Arrays.asList("attr5","attr1")));
        classInfo.addMethod("method6", new HashSet<>(Arrays.asList("attr6","attr2")));
        classInfo.addMethod("method7", new HashSet<>(Arrays.asList("attr7","attr3")));
        classInfo.addMethod("method8", new HashSet<>(Arrays.asList("attr8","attr4")));
        int lcom = calculateLCOM(classInfo);
        System.out.println("LCOM = " + lcom);
    }

    public static int calculateLCOM(ClassInfo classInfo) {
        int p = 0, q = 0;
        List<String> methods = classInfo.methods;
        for (int i = 0; i < methods.size(); i++) {
            for (int j = i + 1; j < methods.size(); j++) {
                String method1 = methods.get(i);
                String method2 = methods.get(j);
                Set<String> attrs1 = classInfo.methodAttributes.get(method1);
                Set<String> attrs2 = classInfo.methodAttributes.get(method2);
                // Calculamos si comparten atributos
                Set<String> intersection = new HashSet<>(attrs1);
                intersection.retainAll(attrs2);
                if (intersection.isEmpty()) {
                    p++; // No comparten atributos
                    System.out.println("No comparten atributos: " + method1 + " y " + method2);
                } else {
                    q++; // Comparten al menos un atributo
                    System.out.println("Comparten atributos: " + method1 + " y " + method2);
                }
            }
        }
        return Math.max(p - q, 0);
    }
}
package org.example.contexts.retornoEvento;

public enum Paths {
    SUBJECT_1("src/main/resources/aalegra-test-subject.xml"),
    SUBJECT_2("src/main/resources/snoh-test-subject.xml"),
    SUBJECT_3("src/main/resources/xuxa-test-subject.xml");

    private final String path;
    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

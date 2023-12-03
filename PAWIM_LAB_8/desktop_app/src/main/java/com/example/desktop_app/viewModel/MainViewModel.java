package com.example.desktop_app.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel {
    private StringProperty textArea = new SimpleStringProperty("");

    private StringProperty fieldTextArea = new SimpleStringProperty("");

    private StringProperty fieldName = new SimpleStringProperty("");

    public StringProperty textAreaProperty() {
        return textArea;
    }

    public StringProperty fieldTextAreaProperty() {
        return fieldTextArea;
    }

    public StringProperty fieldNameProperty() { return  fieldName;}

    public String getFieldTextArea() {
        return fieldTextArea.get();
    }

    public String getFieldName() { return  fieldName.get();}

    public void setTextArea(String textArea) {
        this.textArea.set(textArea);
    }
}

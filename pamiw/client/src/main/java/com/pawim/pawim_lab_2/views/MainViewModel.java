package com.pawim.pawim_lab_2.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel {
    private StringProperty textArea = new SimpleStringProperty("");

    private StringProperty fieldTextArea = new SimpleStringProperty("");

    public StringProperty textAreaProperty() {
        return textArea;
    }

    public StringProperty fieldTextAreaProperty() {
        return fieldTextArea;
    }

    public String getFieldTextArea() {
        return fieldTextArea.get();
    }


    public void setTextArea(String textArea) {
        this.textArea.set(textArea);
    }
}

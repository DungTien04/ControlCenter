package com.example.controlcenter.viewMd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class M002MD extends CommonMD{
    private final MutableLiveData<String> selectedItem = new MutableLiveData<>();

    public void selectItem(String item) {
        selectedItem.setValue(item);
    }

    public LiveData<String> getSelectedItem() {
        return selectedItem;
    }
}

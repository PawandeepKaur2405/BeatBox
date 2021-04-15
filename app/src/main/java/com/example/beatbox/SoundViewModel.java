package com.example.beatbox;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SoundViewModel extends BaseObservable {

    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox)
    {
        mBeatBox = beatBox;
    }

    @Bindable
    public String getTitle()
    {
        return mSound.getName();
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    public void onButtonClicked() {
        mBeatBox.play(mSound);
    }
}

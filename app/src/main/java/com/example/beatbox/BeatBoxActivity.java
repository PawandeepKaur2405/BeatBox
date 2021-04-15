package com.example.beatbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class BeatBoxActivity extends SingleFragmentActivity {

    
    protected Fragment createFragment()
    {
        return BeatBoxFragment.newInstance();
    }
}
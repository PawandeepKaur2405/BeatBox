package com.example.beatbox;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SoundViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class);
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle()
    {
        Assert.assertThat(mSubject.getTitle() , is(mSound.getName()));
    }
    @Test
    public void callsBeatBoxPlayButtonOnClicked()
    {
        mSubject.onButtonClicked();
        verify(mBeatBox).play(mSound);
    }
}
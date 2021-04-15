package com.example.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {

    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;
    public static final float MIN_PLAYBACK_SPEED = 0.5f;
    public static final float MAX_PLAYBACK_SPEED = 2.0f;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;
    private float mPlaybackSpeed;


    public BeatBox (Context context)
    {
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS , AudioManager.STREAM_MUSIC , 0);
        mPlaybackSpeed = 1.0f;
        loadSounds();
    }


    public void play(Sound sound)   //to play sounds from soundPool
    {
        Integer soundId = sound.getSoundId();
        if(soundId == null)
        {
            return;
        }
        mSoundPool.play(soundId , 1.0f , 1.0f , 1 , 0 ,mPlaybackSpeed);
    }

    private void loadSounds()
    {
        String soundNames[];

        try
        {
            soundNames = mAssets.list(SOUND_FOLDER);
            Log.i( TAG, "Found " + soundNames.length + " sounds");
        }
        catch (Exception e)
        {
            Log.e(TAG, "Could not list assets", e);
            return;
        }

        for(String filename : soundNames)
        {

            try
            {
                String assetPath =  SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);    //loading sounds in soundPool
                mSounds.add(sound); //adding sound titles to list
            }
            catch (IOException e)
            {
                Log.e(TAG, "Could not load sound " + filename, e);
            }

        }
    }

    private void load(Sound sound) throws IOException
    {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath()); //declaring object of AssetFileDescriptor
        int soundId = mSoundPool.load(afd , 1); //return an id
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds()
    {
        return mSounds; //returning list of sounds
    }

    public void release()
    {
        mSoundPool.release();
    }

    public float getPlaybackSpeed() {
        return mPlaybackSpeed;
    }

    public void setPlaybackSpeed(float playbackSpeed) {
        if (playbackSpeed > MAX_PLAYBACK_SPEED) {
            mPlaybackSpeed = MAX_PLAYBACK_SPEED;
        } else if (playbackSpeed < MIN_PLAYBACK_SPEED) {
            mPlaybackSpeed = MIN_PLAYBACK_SPEED;
        } else {
            mPlaybackSpeed = playbackSpeed;
        }

    }


}

package com.example.beatbox;

public class Sound
{

    private String mAssetPath;
    private String mName;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    private Integer mSoundId;   //to keep track of sounds loaded into SoundPool

    public Sound(String assetPath)
    {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav","");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}

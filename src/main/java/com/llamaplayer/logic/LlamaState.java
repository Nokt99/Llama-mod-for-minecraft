package com.llamaplayer.logic;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class LlamaState {

    private static final String TAG_LLAMA = "LlamaPlayerState";

    public enum Mood {
        CHILL,
        ANGY
    }

    private int practiceChances = 5;
    private int angyScore = 0;
    private Mood mood = Mood.CHILL;
    private int deathsUsed = 0;
    private boolean finalSlapTriggered = false;

    public static LlamaState get(Player player) {
        CompoundTag persistent = player.getPersistentData();
        CompoundTag data = persistent.getCompound(TAG_LLAMA);
        LlamaState state = new LlamaState();
        state.load(data);
        return state;
    }

    public void saveTo(Player player) {
        CompoundTag persistent = player.getPersistentData();
        CompoundTag data = new CompoundTag();
        this.save(data);
        persistent.put(TAG_LLAMA, data);
    }

    public void copyFrom(LlamaState other) {
        this.practiceChances = other.practiceChances;
        this.angyScore = other.angyScore;
        this.mood = other.mood;
        this.deathsUsed = other.deathsUsed;
        this.finalSlapTriggered = other.finalSlapTriggered;
    }

    public void load(CompoundTag tag) {
        if (tag.contains("PracticeChances")) {
            this.practiceChances = tag.getInt("PracticeChances");
        }
        this.angyScore = tag.getInt("AngyScore");
        String moodStr = tag.getString("Mood");
        this.mood = moodStr.isEmpty() ? Mood.CHILL : Mood.valueOf(moodStr);
        this.deathsUsed = tag.getInt("DeathsUsed");
        this.finalSlapTriggered = tag.getBoolean("FinalSlapTriggered");
    }

    public void save(CompoundTag tag) {
        tag.putInt("PracticeChances", practiceChances);
        tag.putInt("AngyScore", angyScore);
        tag.putString("Mood", mood.name());
        tag.putInt("DeathsUsed", deathsUsed);
        tag.putBoolean("FinalSlapTriggered", finalSlapTriggered);
    }

    public int getPracticeChances() {
        return practiceChances;
    }

    public void decrementPractice() {
        if (practiceChances > 0) practiceChances--;
    }

    public boolean isPracticeOver() {
        return practiceChances <= 0;
    }

    public int getAngyScore() {
        return angyScore;
    }

    public void addAngy(int amount) {
        this.angyScore += amount;
        if (this.angyScore < 0) this.angyScore = 0;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public int getDeathsUsed() {
        return deathsUsed;
    }

    public void incrementDeaths() {
        this.deathsUsed++;
    }

    public boolean isFinalSlapTriggered() {
        return finalSlapTriggered;
    }

    public void setFinalSlapTriggered(boolean finalSlapTriggered) {
        this.finalSlapTriggered = finalSlapTriggered;
    }
}

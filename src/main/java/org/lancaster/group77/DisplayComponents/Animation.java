package org.lancaster.group77.DisplayComponents;

import java.util.Objects;

public class Animation extends DisplayComponentBase {
    private String animation_name = null;
    private int duration;

    public Animation() {
        super("animation");
    }

    public Animation(String animation_name, int duration) {
        super("animation");
        this.animation_name = animation_name;
        this.duration = duration;
    }

    public String getAnimation_name() {
        return animation_name;
    }

    public void setAnimation_name(String animation_name) {
        this.animation_name = animation_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Animation animation = (Animation) object;
        return getDuration() == animation.getDuration() && Objects.equals(getAnimation_name(), animation.getAnimation_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAnimation_name(), getDuration());
    }
}

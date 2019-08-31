package za.co.jaredfishy.mslights.application.domain.command;

public enum CommandEffect {
    SUDDEN("sudden"),
    SMOOTH("smooth");

    String effect;

    CommandEffect(String effect) {
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }
}

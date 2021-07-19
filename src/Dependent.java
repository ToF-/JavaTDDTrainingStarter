// a class depending on a dependency to function
public class Dependent {
    private final Dependency dependency;

    public Dependent(Dependency dependency) {
        this.dependency = dependency;
    }

    public String getMessage() {
        return "It's been " + Long.toString(dependency.getDependentValue()) + " milliseconds..";
    }
}

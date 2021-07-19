// a class with an external dependency, like data source, network, time...
public class Dependency {
    // a real external source dependent value
    public long getDependentValue() {
        return System.currentTimeMillis();
    }
}

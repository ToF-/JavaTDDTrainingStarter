import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DummyMockingTest {
    @Mock
    Dependency dependencyMock;

    @Test
    public void aDummyTestThatShouldMockADependency() {
       dependencyMock = Mockito.mock(Dependency.class);
       when(dependencyMock.getDependentValue()).thenReturn(42L);
       Dependent dependent = new Dependent(dependencyMock);
       Assert.assertEquals("It's been 42 milliseconds..", dependent.getMessage());
    }
}

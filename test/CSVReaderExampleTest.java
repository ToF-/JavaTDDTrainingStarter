import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

public class CSVReaderExampleTest {
    @Test
    public void canReadACsvFile() throws Exception {
        String string = "item,quantity\npaper,42\n";
        StringReader reader = new StringReader(string);
        List<String[]> result = CSVReaderExample.readAll(reader);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("item", result.get(0)[0]);
        Assert.assertEquals("quantity", result.get(0)[1]);
        Assert.assertEquals("paper", result.get(1)[0]);
        Assert.assertEquals("42", result.get(1)[1]);

    }
}

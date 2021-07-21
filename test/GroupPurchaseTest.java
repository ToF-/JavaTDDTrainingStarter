import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class GroupPurchaseTest {
    @Test
    public void givenOneOrderWithoutShippingFeeTheAmountOnTheBillIsTheOrderTotal() {
        // arrangement
        List <Order> orders = new ArrayList<>();
        orders.add(new Order(0.50, 20, "Bertrand"));
        GroupPurchaseCalculator groupPurchaseCalculator = new GroupPurchaseCalculator(orders);
        // action
        List<Bill> bills = groupPurchaseCalculator.calculateBills();
        // assertion
        Bill firstBill = bills.get(0);
        Assert.assertEquals(10.0, firstBill.getAmount(),0.00001);
        Assert.assertEquals("Bertrand", firstBill.getBuyer());
    }
    @Test
    public void givenAnyOrderWithoutShippingFeeTheAmountOnTheBillIsTheOrderTotal() {
        // arrangement
        List <Order> orders = new ArrayList<>();
        orders.add(new Order(1.50, 10, "Alice"));
        GroupPurchaseCalculator groupPurchaseCalculator = new GroupPurchaseCalculator(orders);
        // action
        List<Bill> bills = groupPurchaseCalculator.calculateBills();
        // assertion
        Bill firstBill = bills.get(0);
        Assert.assertEquals(15.0, firstBill.getAmount(),0.00001);
        Assert.assertEquals("Alice", firstBill.getBuyer());
    }
    @Test
    public void givenSeveralOrdersForOneBuyerWithoutShippingFeeTheAmountOnTheBillIsTheTotalOOrders() {
        // arrangement
        List <Order> orders = new ArrayList<>();
        orders.add(new Order(5.00, 100, "Clara"));
        orders.add(new Order(42.00, 1, "Clara"));
        GroupPurchaseCalculator groupPurchaseCalculator = new GroupPurchaseCalculator(orders);
        // action
        List<Bill> bills = groupPurchaseCalculator.calculateBills();
        // assertion
        Bill firstBill = bills.get(0);
        Assert.assertEquals(5.00*100 + 42.0, firstBill.getAmount(),0.00001);
        Assert.assertEquals("Clara", firstBill.getBuyer());
    }
    @Test
    public void givenSeveralOrdersForSeveralBuyersWithoutShippingFeeTheAmountOfEachBillisEachBuyersTotal() {
        // arrangement
        List <Order> orders = new ArrayList<>();
        orders.add(new Order(0.50, 20, "Bertrand"));
        orders.add(new Order(1.50, 10, "Alice"));
        orders.add(new Order(5.00, 100, "Clara"));
        orders.add(new Order(42.00, 1, "Clara"));
        GroupPurchaseCalculator groupPurchaseCalculator = new GroupPurchaseCalculator(orders);
        // action
        List<Bill> bills = groupPurchaseCalculator.calculateBills();
        // assertion
        Assert.assertEquals(3, bills.size());
        Assert.assertEquals(5.00*100 + 42.0, bills.get(0).getAmount(),0.00001);
        Assert.assertEquals(1.50 * 10, bills.get(1).getAmount(),0.00001);
        Assert.assertEquals(0.50 * 20, bills.get(2).getAmount(),0.00001);
    }

    @Test
    public void givenACsvContainingOrdersTheResultingBillsShouldCorrespondToTheOrdersInTheCsv() throws IOException, CsvException {

        StringReader stringReader = new StringReader("unit price, quantity, total, buyer\n0.50,20,10.0,Bertrand\n1.50,10,15.0,Alice\n5.0,100,500.0,Clara\n42.0,1,42,Clara\n");
        CSVReader csvReader = new CSVReader(stringReader);
        GroupPurchaseCalculator groupPurchaseCalculator = new GroupPurchaseCalculator(csvReader);
        // action
        List<Bill> bills = groupPurchaseCalculator.calculateBills();
        // assertion
        Assert.assertEquals(3, bills.size());
        Assert.assertEquals(5.00*100 + 42.0, bills.get(0).getAmount(),0.00001);
        Assert.assertEquals(1.50 * 10, bills.get(1).getAmount(),0.00001);
        Assert.assertEquals(0.50 * 20, bills.get(2).getAmount(),0.00001);

    }
    @Test
    public void givenOrdersTheResultingCsvWillContainTheCorrespondingBills() {
        // arrangement
        List <Order> orders = new ArrayList<>();
        orders.add(new Order(0.50, 20, "Bertrand"));
        orders.add(new Order(1.50, 10, "Alice"));
        orders.add(new Order(5.00, 100, "Clara"));
        orders.add(new Order(42.00, 1, "Clara"));
        GroupPurchaseCalculator groupPurchaseCalculator = new GroupPurchaseCalculator(orders);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        // action
        groupPurchaseCalculator.ouputCsv(csvWriter);
        // assertion
        Assert.assertEquals("\"amount\",\"buyer\"\n\"542.0\",\"Clara\"\n\"15.0\",\"Alice\"\n\"10.0\",\"Bertrand\"\n", stringWriter.toString());

    }
}

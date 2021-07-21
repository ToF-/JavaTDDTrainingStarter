import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

public class GroupPurchaseCalculator {
    List <Order> orders;
    public GroupPurchaseCalculator(List<Order> orders) {
        this.orders = orders;
    }

    public GroupPurchaseCalculator(CSVReader csvReader) throws IOException, CsvException {
        orders = new ArrayList<>();
        List<String[]> fields = new ArrayList<>();
        fields = csvReader.readAll();
        csvReader.close();
        for(int line = 1; line < fields.size(); line++) {
            double unitPrice = Double.valueOf(fields.get(line)[0]);
            int quantity = Integer.valueOf((fields.get(line)[1]));
            // ignoring field[2] which order total because we calculate it ourselves
            String buyer = fields.get(line)[3];
            Order order = new Order(unitPrice, quantity, buyer);
            orders.add(order);
        }
    }

    public List<Bill> calculateBills() {
        HashMap<String, Double> purchases = new HashMap<>();
        List<Bill> bills = new ArrayList<>();
        for(Order order:  orders)  {
            String buyer = order.getBuyer();
            double amount = order.getTotalAmount();
            double currentTotalForBuyer = purchases.getOrDefault(buyer, 0.0);
            double newTotalForBuyer = currentTotalForBuyer + amount;
            purchases.put(buyer, newTotalForBuyer);
        }
        for (String buyer : purchases.keySet()) {
            double billTotalForBuyer = purchases.get(buyer);
            bills.add(new Bill(billTotalForBuyer,buyer));
        }
        return bills;
    }

    public void ouputCsv(CSVWriter csvWriter) {
        List<Bill> bills = calculateBills();
        String[] headerRecord = {"amount","buyer"};
        csvWriter.writeNext(headerRecord);
        for(Bill bill: bills) {
            String[] record = new String[] { String.valueOf(bill.getAmount()), bill.getBuyer() };
            csvWriter.writeNext(record);
        }
    }
}

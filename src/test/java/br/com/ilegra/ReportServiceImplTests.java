package br.com.ilegra;

import br.com.ilegra.domain.SalesData;
import br.com.ilegra.domain.customer.Customer;
import br.com.ilegra.domain.sale.ItemSale;
import br.com.ilegra.domain.sale.Sale;
import br.com.ilegra.domain.seller.Seller;
import br.com.ilegra.service.report.ReportService;
import br.com.ilegra.service.report.ReportServiceFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReportServiceImplTests {

	static final ReportService service = ReportServiceFactory.create();

	@BeforeAll
	static void loadSalesData() {
		SalesData salesData = new SalesData();

		salesData.addSeller(new Seller("1234567891234", "Pedro", 50000));
		salesData.addSeller(new Seller("3245678865434", "Paulo", 40000.99));
		salesData.addCustomer(new Customer("2345675434544345", "Jose da Silva", "Rural"));
		salesData.addCustomer(new Customer("2345675433444345", "Eduardo Pereira", "Rural"));

		List<ItemSale> itemList1 = new ArrayList<>();
		itemList1.add(new ItemSale("1", 10, 100));
		itemList1.add(new ItemSale("2", 30, 2.50));
		itemList1.add(new ItemSale("3", 40, 3.10));
		salesData.addSale(new Sale("10", itemList1, "Pedro"));

		List<ItemSale> itemList2 = new ArrayList<>();
		itemList2.add(new ItemSale("1", 34, 10));
		itemList2.add(new ItemSale("2", 33, 1.50));
		itemList2.add(new ItemSale("3", 40, 0.10));
		salesData.addSale(new Sale("08", itemList1, "Paulo"));

		service.setSalesData(salesData);
	}

	@Test
	void expensiveSale() {
		assertThat(service.getExpensiveSaleId()).isEqualTo("10");
	}

	@Test
	void numberCustomers() {
		assertThat(service.getNumberCustomers()).isEqualTo(2);
	}

	@Test
	void numberSellers() {
		assertThat(service.getNumberSellers()).isEqualTo(2);
	}

	@Test
	void worstSeller() {
		assertThat(service.getWorstSeller()).isEqualTo("Paulo");
	}

}

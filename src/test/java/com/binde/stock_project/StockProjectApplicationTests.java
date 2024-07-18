package com.binde.stock_project;

import com.binde.stock_project.dto.requestdto.StockRequest;
import com.binde.stock_project.dto.response.StockResponse;
import com.binde.stock_project.model.Stock;
import com.binde.stock_project.repository.StockRepository;
import com.binde.stock_project.service.implementation.StockServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static javax.management.Query.eq;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockProjectApplicationTests {

	@InjectMocks
	private StockServiceImpl stockService;
	@Mock
	private StockRepository stockRepository;
	@Mock
	private ModelMapper modelMapper;
	@Test
	public void testCreateStock() {

		StockRequest request = new StockRequest();
		request.setName("Stock1");
		request.setCurrentPrice(BigDecimal.valueOf(100.0));


		Stock savedStock = new Stock();
		savedStock.setName("Stock1");
		savedStock.setCurrentPrice(BigDecimal.valueOf(100.0));


		StockResponse stockResponse = new StockResponse();
		stockResponse.setName("Stock1");
		stockResponse.setCurrentPrice(BigDecimal.valueOf(100.0));

//		when(stockRepository.save(any(Stock.class))).thenReturn(savedStock);
//		when(modelMapper.map(any(Stock.class), eq(StockResponse.class))).thenReturn(stockResponse);

		StockResponse result = stockService.createStock(request);


		assertEquals(stockResponse.getName(), result.getName());
		assertEquals(stockResponse.getCurrentPrice(), result.getCurrentPrice());
	}
	@Test
	public void testGetListOfStocks() {
		int pageNo = 0;
		int pageSize = 10;
		String sortBy = "id";

		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Stock> mockedPage = new PageImpl<>(Collections.emptyList());

		when(stockRepository.findAll(pageRequest)).thenReturn(mockedPage);

		Page<Stock> result = stockService.getListOfStocks(pageNo, pageSize, sortBy);

		assertEquals(mockedPage, result);
	}
	@Test
	public void testGetStockById() {
		Long stockId = 1L;
		Stock stock = new Stock();
		stock.setName("Stock1");
		stock.setCurrentPrice(BigDecimal.valueOf(100.0));

		when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

		StockResponse stockResponse = new StockResponse();
		stockResponse.setName("Stock1");
		stockResponse.setCurrentPrice(BigDecimal.valueOf(100.0));
		when(modelMapper.map(stock, StockResponse.class)).thenReturn(stockResponse);
		StockResponse result = stockService.getStockById(stockId);

		assertEquals(stockResponse.getName(), result.getName());
		assertEquals(stockResponse.getCurrentPrice(), result.getCurrentPrice());
	}
	@Test
	public void testUpdateStock() {
		Long stockId = 1L;
		StockRequest request = new StockRequest();
		request.setName("Updated Stock1");
		request.setCurrentPrice(BigDecimal.valueOf(150.0));

		Stock existingStock = new Stock();
		existingStock.setId(stockId);
		existingStock.setName("Stock1");
		existingStock.setCurrentPrice(BigDecimal.valueOf(100.0));

		when(stockRepository.findById(stockId)).thenReturn(Optional.of(existingStock));

		Stock updatedStock = new Stock();
		updatedStock.setId(stockId);
		updatedStock.setName("Updated Stock1");
		updatedStock.setCurrentPrice(BigDecimal.valueOf(150.0));

		when(stockRepository.save(existingStock)).thenReturn(updatedStock);

		StockResponse stockResponse = new StockResponse();
		stockResponse.setName("Updated Stock1");
		stockResponse.setCurrentPrice(BigDecimal.valueOf(150.0));

		when(modelMapper.map(updatedStock, StockResponse.class)).thenReturn(stockResponse);

		StockResponse result = stockService.updateStock(stockId, request);

		assertEquals(stockResponse.getName(), result.getName());
		assertEquals(stockResponse.getCurrentPrice(), result.getCurrentPrice());
	}

}

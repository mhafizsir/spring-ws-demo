package id.mhafizsir.fgswsdemo.controller.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.mhafizsir.fgswsdemo.dto.StockPrice;
import id.mhafizsir.fgswsdemo.dto.StockRequest;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class StockPriceController {

  private final SimpMessagingTemplate template;

    public StockPriceController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // Simulate stock price updates. In a real application, this would be fetched from a service.
  @MessageMapping("/stock")
  @SendTo("/topic/price")
  public String getStockPrice(StockRequest stockRequest) throws Exception {
    // Simulate a delay
    Thread.sleep(1000); // Simulating delay

    // Return a random stock price for the requested symbol
    return new ObjectMapper()
        .writeValueAsString(new StockPrice(stockRequest.getSymbol(), Math.random() * 1000));
  }

  @Scheduled(fixedRate = 1000)
  public void sendStockPrice() throws JsonProcessingException {
    // send stock price to topic
    template.convertAndSend("/topic/price", new ObjectMapper().writeValueAsString(new StockPrice("AAPL", Math.random() * 1000)));
  }
}

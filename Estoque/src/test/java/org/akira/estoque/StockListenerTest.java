package org.akira.estoque;

import org.akira.estoque.models.Order;
import org.akira.estoque.services.StockListener;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class StockListenerTest {
    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private StockListener stockListener;

    @Test
    public void testOnNewOrder() {
        // Implemente o teste unitário para o método onNewOrder da classe StockListener
        Order order = new Order();
        order.setId(1L);
        order.setDescription("Pedido 1");
        order.setQuantity(50);

        // Chama o método de processar pedido
        stockListener.onNewOrder(order);

        // Verifica se o pedido foi enviado para a fila "order-validated"
        verify(jmsTemplate, times(1)).convertAndSend("orders-confirmed", order);
    }

    @Test
    public void testOnNewOrderWithoutStock() {
        // Implemente o teste unitário para o método onNewOrder da classe StockListener
        Order order = new Order();
        order.setId(2L);
        order.setDescription("Pedido 2");
        order.setQuantity(100);

        // Chama o método de processar pedido
        stockListener.onNewOrder(order);

        // Verifica se o pedido foi enviado para a fila "order-validated"
        verify(jmsTemplate, never()).convertAndSend("orders-confirmed", order);
    }
}

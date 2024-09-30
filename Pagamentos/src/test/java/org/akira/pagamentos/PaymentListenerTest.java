package org.akira.pagamentos;

import org.akira.pagamentos.models.Order;
import org.akira.pagamentos.services.PaymentListener;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentListenerTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private PaymentListener paymentListener;

    @Test
    public void testProcessarPagamentoAprovado() {
        Order order = new Order();
        order.setId(1L);
        order.setDescription("Produto Teste");
        order.setQuantity(50);

        // Chama o método de processar pagamento
        paymentListener.onOrderConfirmed(order);

        // Verifica se o pagamento foi aprovado e enviado para a fila "payment-confirmed"
        verify(jmsTemplate, times(1)).convertAndSend("payment-confirmed", order);
    }
}

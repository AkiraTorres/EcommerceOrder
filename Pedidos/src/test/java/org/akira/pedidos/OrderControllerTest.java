package org.akira.pedidos;

import org.akira.pedidos.controllers.OrderController;
import org.akira.pedidos.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JmsTemplate jmsTemplate;

    @Test
    public void testCreateOrder() throws Exception {
        // Configura o comportamento do mock do JmsTemplate
        doNothing().when(jmsTemplate).convertAndSend("new-orders", new Order());

        // Realiza a requisição para o endpoint
        mockMvc.perform(post("/pedidos/novo")
            .contentType("application/json")
            .content("{\"id\": 1, \"description\": \"Pedido 1\", \"quantity\": 10}"))
            .andExpect(status().isOk());
    }
}

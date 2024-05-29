package Laboratorio4com.example.demo.service;

import Laboratorio4com.example.demo.controller.PreferenceMP;
import Laboratorio4com.example.demo.entities.Pedido;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {

    public static final String TITULO_PEDIDO = "Compra de instrumentos";
    public static final String DESCRIPCION_PEDIDO = "Pedido realizado desde el carrito de compras";
    public static final String MERCADOPAGO_ACCESS_TOKEN = "TEST-3341755492218288-052918-10e02fa5ffe8770eec415a31ceaa572a-1835742044";

    public PreferenceMP crearPreferencia(Pedido pedido) {

        try {
            MercadoPagoConfig.setAccessToken(MERCADOPAGO_ACCESS_TOKEN);

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedido.getId().toString())
                    .title(TITULO_PEDIDO)
                    .description(DESCRIPCION_PEDIDO)
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(BigDecimal.valueOf(pedido.getTotalPedido()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/mpsuccess")
                    .pending("http://localhost:5173/mppending").failure("http://localhost:5173/mpfailure").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}

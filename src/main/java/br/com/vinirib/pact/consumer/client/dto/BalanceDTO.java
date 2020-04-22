package br.com.vinirib.pact.consumer.client.dto;

import lombok.*;

import javax.money.MonetaryAmount;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BalanceDTO implements Serializable {

    private Integer clientId;
    private Integer accountId;
    private MonetaryAmount balance;

}
package com.example.demo.transection;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionDTO {

    public long transferAmount;
    public String senderIBAN;
    public String receiverIBAN;
}

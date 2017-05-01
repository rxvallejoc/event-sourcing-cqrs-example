package com.thoughtworks.customers.command.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressRequestBody {
    String nickName;
    String location;
}

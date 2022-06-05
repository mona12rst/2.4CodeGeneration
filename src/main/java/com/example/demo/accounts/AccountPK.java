
package com.example.demo.accounts;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountPK implements Serializable
{
    private String IBAN;
    private long accountId;

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        AccountPK pk = (AccountPK) o;
        return Objects.equals( IBAN, pk.IBAN ) &&
                Objects.equals( accountId, pk.accountId );
    }

    @Override
    public int hashCode() {
        return Objects.hash( IBAN, accountId );
    }

}


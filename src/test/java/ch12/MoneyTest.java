package ch12;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MoneyTest {

    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        /**
         * Money 연산을 Expression 에 위임(Expression 은 Money 의 계산식으로만 취급)
         * $5 + $5
         */
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        /**
         * Money 의 계산식, 즉 Expression 을 받고, currency Type 을 받는다
         * 이 뜻은 Money 의 계산식을 currency Type 에 맞게 계산하는것을
         * Bank 가 위임받아서 하고있다는 것을 의미한다.
         *
         * Ex. Expression : $5 + $5
         *     Bank : $5 + $5 를 주어진 currency Type 에 맞춘 금액은? => bank.reduce($5 + $5, currency)
         */
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

}

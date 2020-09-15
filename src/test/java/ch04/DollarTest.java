package ch04;

import org.junit.Test;

import static org.junit.Assert.*;

public class DollarTest {

    /**
     * 이전의 테스트 코드들과는 달리
     * Dollar 의 프로퍼티를 직접 사용하지 않음으로써
     * 코드 - 테스트 간 결합도를 낮췄다.
     *
     * 명심하자 항상.. 결합도는 낮게, 응집도는 높게!!!
     */
    @Test
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }

}

TDD
=======

# 1. 다중 통화를 지원하는 Money 객체
  ## TODO
  * $5 + 10CHF = $10(환율이 2:1 일 경우)  
  * **$5 X 2 = $10**  
  * amount 를 private 으로 만들기
  * Dollar Side Effect
  * Money 반올림
  
  ## 기능
  * 통화가 다른 두 금액을 더해서 주어진 환율에 맞게 변환된 금액을 얻을 수 있어야 함.
  * 금액 X 숫자에 대한 결과를 얻을 수 있어야 함.
  
  ## 주의
  위의 기능을 구현하기 위해 어떤 객체를 만들어야 할까? X
  위의 기능을 구현하기 위해 어떤 테스트가 필요할까? O
  
  ## 주기
  ### 1. 작은 테스트를 하나 추가한다.
  ### 2. 모든 테스트를 실행해서 테스트가 실패하는 것을 본다.
  ### 3. 조금 수정한다.
  ### 4. 모든 테스트를 실행해서 테스트가 성공하는 것을 본다.
  ### 5. 중복을 제거하기 위해 리팩토링을 한다.
  
  Dollar.java
  <pre>
    <code>
    public class Dollar {
    
        public int amount;
    
        public Dollar(int amount) {
            this.amount = amount;
        }
    
        public void times(int multiplier) {
            amount *= multiplier;
        }
        
    }
    </code>
  </pre>
  
  DollarTest.java
  <pre>
    <code>
    public class DollarTest {
    
        @Test
        public void testMultiplication() {
            Dollar five = new Dollar(5);
            five.times(2);
            assertEquals(10, five.amount);
        }
        
    }
    </code>
  </pre>
  
# 2. 타락한 객체
  ## TODO
  * $5 + 10CHF = $10(환율이 2:1 일 경우)  
  * ~~$5 X 2 = $10~~  
  * amount 를 private 으로 만들기
  * **Dollar Side Effect**
  * Money 반올림
  
  Dollar.java
  <pre>
    <code>
    public class Dollar {
    
        public int amount;
    
        public Dollar(int amount) {
            this.amount = amount;
        }
    
        public Dollar times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    
    }
    </code>
  </pre>
  
  DollarTest.java
  <pre>
      <code>
      public class DollarTest {
      
          @Test
          public void testMultiplication() {
              Dollar five = new Dollar(5);
              Dollar product = five.times(2);
              assertEquals(10, product.amount);
              product = five.times(3);
              assertEquals(15, product.amount);
          }
      
      }
      </code>
 </pre>
  
# 3. 모두를 위한 평등
  ## TODO
  * $5 + 10CHF = $10(환율이 2:1 일 경우)  
  * ~~$5 X 2 = $10~~  
  * amount 를 private 으로 만들기
  * ~~Dollar Side Effect~~
  * Money 반올림
  * **equals()**
  * hashCode()
  * Equal null
  * Equal object
  
    Dollar.java
    <pre>
      <code>
      public class Dollar {
      
          public int amount;
      
          public Dollar(int amount) {
              this.amount = amount;
          }
      
          public Dollar times(int multiplier) {
              return new Dollar(amount * multiplier);
          }
      
          public boolean equals(Object object) {
              Dollar dollar = (Dollar) object;
              return this.amount == dollar.amount;
          }
      
      }
      </code>
    </pre>
    
    DollarTest.java
    <pre>
        <code>
        public class DollarTest {
        
            @Test
            public void testMultiplication() {
                Dollar five = new Dollar(5);
                Dollar product = five.times(2);
                assertEquals(10, product.amount);
                product = five.times(3);
                assertEquals(15, product.amount);
            }
        
            @Test
            public void testEquality() {
                assertTrue(new Dollar(5).equals(new Dollar(5)));
                assertFalse(new Dollar(5).equals(new Dollar(6)));
            }
        
        }
        </code>
   </pre>
   
 # 4. 프라이버시
   ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * **amount 를 private 으로 만들기**
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
  
  Dollar.java
  <pre>
    <code>
    public class Dollar {
    
        private int amount;
    
        public Dollar(int amount) {
            this.amount = amount;
        }
    
        public Dollar times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    
        public boolean equals(Object object) {
            Dollar dollar = (Dollar) object;
            return this.amount == dollar.amount;
        }
    }
    </code>
  </pre>
  
  DollarTest.java
  <pre>
    <code>
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
    </code>
  </pre>
    
# 5. 솔직히 말하자면
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * **5CHF X 2 = 10CHF**
   
  ## 주기 (리마인드)
  ### 1. 테스트 작성
  ### 2. 컴파일되게 하기
  ### 3. 실패하는지 확인하기 위해 실행
  ### 4. 성공하게 만들기
  ### 5. 중복 제거
  
  Dollar.java
  <pre>
    <code>
    public class Dollar {
    
        private int amount;
    
        public Dollar(int amount) {
            this.amount = amount;
        }
    
        public Dollar times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    
        public boolean equals(Object object) {
            Dollar dollar = (Dollar) object;
            return this.amount == dollar.amount;
        }
        
    }
    </code>
  </pre>
  
  Franc.java
  <pre>
    <code>
    public class Franc {
    
        private int amount;
    
        public Franc(int amount) {
            this.amount = amount;
        }
    
        public Franc times(int multiplier) {
            return new Franc(amount * multiplier);
        }
    
        public boolean equals(Object object) {
            Franc franc = (Franc) object;
            return this.amount == franc.amount;
        }
        
    }
    </code>
  </pre>
  
  DollarFrancTest.java
  <pre>
    <code>
    public class DollarFrancTest {
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
    
        @Test
        public void testFrancMultiplication() {
            Franc five = new Franc(5);
            assertEquals(new Franc(10), five.times(2));
            assertEquals(new Franc(15), five.times(3));
        }
    }
    </code>
  </pre>

   
   
# 6. 돌아온 '모두를 위한 평등'
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * ~~5CHF X 2 = 10CHF~~
   * Dollar / Franc 중복 코드
   * **공용 equals**
   * 공용 times

Money.java
<pre>
    <code>
    public class Money {
    
        protected int amount;
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount;
        }
    
    }
    </code>
</pre>

Dollar.java
<pre>
    <code>
    public class Dollar extends Money {
    
        public Dollar(int amount) {
            this.amount = amount;
        }
    
        public Dollar times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    
    }
    </code>
</pre>

Franc.java
<pre>
    <code>
    public class Franc extends Money {
    
        public Franc(int amount) {
            this.amount = amount;
        }
    
        public Franc times(int multiplier) {
            return new Franc(amount * multiplier);
        }
    
    }
    </code>
</pre>

DollarFrancTest.java
<pre>
    <code>
    public class DollarFrancTest {
    
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
            assertTrue(new Franc(5).equals(new Franc(5)));
            assertFalse(new Franc(5).equals(new Franc(6)));
        }
    
        @Test
        public void testFrancMultiplication() {
            Franc five = new Franc(5);
            assertEquals(new Franc(10), five.times(2));
            assertEquals(new Franc(15), five.times(3));
        }
    
    }
    </code>
</pre>

# 7. 사과와 오렌지
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * ~~5CHF X 2 = 10CHF~~
   * Dollar / Franc 중복 코드
   * ~~공용 equals~~
   * 공용 times
   * **Franc 과 Dollar 비교하기**
   
   Money.java
   <pre>
        <code>
        public class Money {
        
            protected int amount;
        
            public boolean equals(Object object) {
                Money money = (Money) object;
                return this.amount == money.amount && getClass().equals(money.getClass());
            }
        
        }
        </code>
   </pre>
   
   Dollar.java
   <pre>
        <code>
        public class Dollar extends Money {
        
            public Dollar(int amount) {
                this.amount = amount;
            }
        
            public Dollar times(int multiplier) {
                return new Dollar(amount * multiplier);
            }
        
        }
        </code>
   </pre>
   
   Franc.java
   <pre>
        <code>
        public class Franc extends Money {
        
            public Franc(int amount) {
                this.amount = amount;
            }
        
            public Franc times(int multiplier) {
                return new Franc(amount * multiplier);
            }
        
        }
        </code>
   </pre>
   
   DollarFrancTest.java
   <pre>
        <code>
        public class DollarFrancTest {
            
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
                assertTrue(new Franc(5).equals(new Franc(5)));
                assertFalse(new Franc(5).equals(new Franc(6)));
                assertFalse(new Franc(5).equals(new Dollar(5)));
            }
        
            @Test
            public void testFrancMultiplication() {
                Franc five = new Franc(5);
                assertEquals(new Franc(10), five.times(2));
                assertEquals(new Franc(15), five.times(3));
            }
        
        }
        </code>
   </pre>

# 8. 객체 만들기
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * ~~5CHF X 2 = 10CHF~~
   * **Dollar / Franc 중복 코드**
   * ~~공용 equals~~
   * 공용 times
   * ~~Franc 과 Dollar 비교하기~~
   
Money.java
<pre>
    <code>
    public abstract class Money {
    
        protected int amount;
    
        public static Money dollar(int amount) {
            return new Dollar(amount);
        }
    
        public static Money franc(int amount) {
            return new Franc(amount);
        }
    
        abstract Money times(int multiplier);
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && getClass().equals(money.getClass());
        }
    
    }
    </code>
</pre>

Dollar.java
<pre>
    <code>
    public class Dollar extends Money {
    
        public Dollar(int amount) {
            this.amount = amount;
        }
    
        public Money times(int multiplier) {
            return new Dollar(amount * multiplier);
        }
    
    }
    </code>
</pre>

Franc.java
<pre>
    <code>
    public class Franc extends Money {
    
        public Franc(int amount) {
            this.amount = amount;
        }
    
        public Money times(int multiplier) {
            return new Franc(amount * multiplier);
        }
    
    }
    </code>
</pre>

DollarFrancTest.java
<pre>
    <code>
    /**
     * Factory Method Pattern 을 사용하여
     * 객체 생성에 대한 구체적인 부분을 추상화 함
     */
    public class DollarFrancTest {
    
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
            assertTrue(Money.franc(5).equals(Money.franc(5)));
            assertFalse(Money.franc(5).equals(Money.franc(6)));
            assertFalse(Money.franc(5).equals(new Dollar(5)));
        }
    
        @Test
        public void testFrancMultiplication() {
            Money five = Money.franc(5);
            assertEquals(Money.franc(10), five.times(2));
            assertEquals(Money.franc(15), five.times(3));
        }
    
    }
    </code>
</pre>

   
   
# 9. 우리가 사는 시간
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * ~~5CHF X 2 = 10CHF~~
   * Dollar / Franc 중복 코드(아직 더 할게 있음)
   * ~~공용 equals~~
   * 공용 times
   * ~~Franc 과 Dollar 비교하기~~
   * **통화?**
   * testFrancMultiplication 제거
   
Money.java
<pre>
    <code>
    public abstract class Money {
    
        protected int amount;
        protected String currency;
    
        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }
    
        public static Money dollar(int amount) {
            return new Dollar(amount, "USD");
        }
    
        public static Money franc(int amount) {
            return new Franc(amount, "CHF");
        }
    
        abstract Money times(int multiplier);
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && getClass().equals(money.getClass());
        }
    
        public String currency() {
            return this.currency;
        }
    
    }
    </code>
</pre>

Dollar.java
<pre>
    <code>
    public class Dollar extends Money {
    
        public Dollar(int amount, String currency) {
            super(amount, currency);
        }
    
        @Override
        public Money times(int multiplier) {
            return Money.dollar(amount * multiplier);
        }
    
    }
    </code>
</pre>

Franc.java
<pre>
    <code>
    public class Franc extends Money {
    
        public Franc(int amount, String currency) {
            super(amount, currency);
        }
    
        @Override
        public Money times(int multiplier) {
            return Money.franc(amount * multiplier);
        }
    
    }
    </code>
</pre>

DollarFrancTest.java
<pre>
    <code>
    /**
     * Factory Method Pattern 을 사용하여
     * 객체 생성에 대한 구체적인 부분을 추상화 함
     */
    public class DollarFrancTest {
    
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
            assertTrue(Money.franc(5).equals(Money.franc(5)));
            assertFalse(Money.franc(5).equals(Money.franc(6)));
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
    
    }
    </code>
</pre>

# 10. 흥미로운 시간
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * ~~5CHF X 2 = 10CHF~~
   * Dollar / Franc 중복 코드(아직 더 할게 있음)
   * ~~공용 equals~~
   * **공용 times**
   * ~~Franc 과 Dollar 비교하기~~
   * ~~통화?~~
   * testFrancMultiplication 제거

Money.java
<pre>
    <code>
    public class Money {
    
        protected int amount;
        protected String currency;
    
        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }
    
        public static Money dollar(int amount) {
            return new Dollar(amount, "USD");
        }
    
        public static Money franc(int amount) {
            return new Franc(amount, "CHF");
        }
    
        public Money times(int multiplier) {
            return new Money(amount * multiplier, currency);
        }
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && currency.equals(money.currency());
        }
    
        public String currency() {
            return this.currency;
        }
    
        @Override
        public String toString() {
            return "Money{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    }
    </code>
</pre>

Dollar.java
<pre>
    <code>
    public class Dollar extends Money {
    
        public Dollar(int amount, String currency) {
            super(amount, currency);
        }
    
    }
    </code>
</pre>

Franc.java
<pre>
    <code>
    public class Franc extends Money {
    
        public Franc(int amount, String currency) {
            super(amount, currency);
        }
    
    }
    </code>
</pre>

DollarFrancTest.java
<pre>
    <code>
    public class DollarFrancTest {
    
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
            assertTrue(Money.franc(5).equals(Money.franc(5)));
            assertFalse(Money.franc(5).equals(Money.franc(6)));
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
        public void testDifferentClassEquality() {
            assertTrue(new Money(10, "CHF").equals(new Franc(10, "CHF")));
        }
    
    }
    </code>
</pre>

# 11. 모든 악의 근원
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * ~~$5 X 2 = $10~~  
   * ~~amount 를 private 으로 만들기~~
   * ~~Dollar Side Effect~~
   * Money 반올림
   * ~~equals()~~
   * hashCode()
   * Equal null
   * Equal object
   * ~~5CHF X 2 = 10CHF~~
   * **Dollar / Franc 중복 코드(아직 더 할게 있음)**
   * ~~공용 equals~~
   * ~~공용 times~~
   * ~~Franc 과 Dollar 비교하기~~
   * ~~통화?~~
   * testFrancMultiplication 제거
  
Money.java
<pre>
    <code>
    public class Money {
    
        protected int amount;
        protected String currency;
    
        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }
    
        public static Money dollar(int amount) {
            return new Money(amount, "USD");
        }
    
        public static Money franc(int amount) {
            return new Money(amount, "CHF");
        }
    
        public Money times(int multiplier) {
            return new Money(amount * multiplier, currency);
        }
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && currency.equals(money.currency());
        }
    
        public String currency() {
            return this.currency;
        }
    
        @Override
        public String toString() {
            return "Money{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    }
    </code>
</pre>

MoneyTest.java
<pre>
    <code>
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
    
    }
    </code>
</pre>

# 12. 드디어, 더하기
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * **$5 + $5 = 10$**
   
Expression.java
<pre>
    <code>
    public interface Expression {
    
    }
    </code>
</pre>

Money.java
<pre>
    <code>
    public class Money implements Expression{
    
        protected int amount;
        protected String currency;
    
        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }
    
        public static Money dollar(int amount) {
            return new Money(amount, "USD");
        }
    
        public static Money franc(int amount) {
            return new Money(amount, "CHF");
        }
    
        public Money times(int multiplier) {
            return new Money(amount * multiplier, currency);
        }
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && currency.equals(money.currency());
        }
    
        public String currency() {
            return this.currency;
        }
    
        @Override
        public String toString() {
            return "Money{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    
        public Expression plus(Money money) {
            return new Money(this.amount + money.amount, this.currency);
        }
    }
    </code>
</pre>

Bank.java
<pre>
    <code>
    public class Bank {
    
        public Money reduce(Expression sum, String usd) {
            return Money.dollar(10);
        }
    }
    </code>
</pre>

MoneyTest.java
<pre>
    <code>
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
    </code>
</pre>

   
   
# 13. 진짜로 만들기
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * **$5 + $5 = 10$(중복이 있음)**
   * $5 + $5 에서 Money 반환하기
   * Bank.reduce(Money)
   * Money 에 대한 통화 변환을 수행하는 Reduce
   * Reduce(Bank, String)
   
 Expression.java
 <pre>
    <code>
    public interface Expression {
    
        Money reduce(String to);
    }
    </code>
 </pre>
 
 Bank.java
  <pre>
     <code>
     public class Bank {
     
         public Money reduce(Expression source, String to) {
             return source.reduce(to);
         }
     }
     </code>
  </pre>
  
 Money.java
  <pre>
     <code>
     public class Money implements Expression {
     
         protected int amount;
         protected String currency;
     
         public Money(int amount, String currency) {
             this.amount = amount;
             this.currency = currency;
         }
     
         public static Money dollar(int amount) {
             return new Money(amount, "USD");
         }
     
         public static Money franc(int amount) {
             return new Money(amount, "CHF");
         }
     
         public Money times(int multiplier) {
             return new Money(amount * multiplier, currency);
         }
     
         public boolean equals(Object object) {
             Money money = (Money) object;
             return this.amount == money.amount && currency.equals(money.currency());
         }
     
         public String currency() {
             return this.currency;
         }
     
         public Expression plus(Money addend) {
             return new Sum(this, addend);
         }
     
         @Override
         public Money reduce(String to) {
             return this;
         }
     
         @Override
         public String toString() {
             return "Money{" +
                     "amount=" + amount +
                     ", currency='" + currency + '\'' +
                     '}';
         }
     
     }
     </code>
  </pre>
  
 Sum.java
  <pre>
     <code>
     public class Sum implements Expression{
         Money augend;
         Money addend;
     
         public Sum(Money augend, Money addend) {
             this.augend = augend;
             this.addend = addend;
         }
     
         public Money reduce(String to) {
             int amount = this.augend.amount + this.addend.amount;
             return new Money(amount, to);
         }
     }
     </code>
  </pre>
  
 MoneyTest.java
 <pre>
    <code>
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
            Expression result = five.plus(five);
            Sum sum = (Sum) result;
            assertEquals(five, sum.augend);
            assertEquals(five, sum.addend);
        }
    
        @Test
        public void testReduceSum() {
            Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
            Bank bank = new Bank();
            Money result = bank.reduce(sum, "USD");
            assertEquals(Money.dollar(7), result);
        }
    
        @Test
        public void testReduceMoney() {
            Bank bank = new Bank();
            Money result = bank.reduce(Money.dollar(1), "USD");
            assertEquals(Money.dollar(1), result);
        }
    
    }
    </code>
 </pre>

# 14. 바꾸기
  ## TODO
   * $5 + 10CHF = $10(환율이 2:1 일 경우)  
   * $5 + $5 = 10$(중복이 있음)
   * $5 + $5 에서 Money 반환하기
   * ~~Bank.reduce(Money)~~
   * **Money 에 대한 통화 변환을 수행하는 Reduce**
   * Reduce(Bank, String)
   
 Pair.java
 <pre>
    <code>
    public class Pair {
    
        private String from;
        private String to;
    
        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(from, pair.from) &&
                    Objects.equals(to, pair.to);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
    </code>
 </pre>
 
 Expression.java
 <pre>
    <code>
    public interface Expression {
    
        Money reduce(Bank bank, String to);
    
    }

    </code>
 </pre>
 
 Bank.java
  ```
     public class Bank {
     
         private Hashtable<Pair, Integer> rates = new Hashtable();
     
         public Money reduce(Expression source, String to) {
             return source.reduce(this, to);
         }
     
         public void addRate(String from, String to, int rate) {
             rates.put(new Pair(from, to), new Integer(rate));
         }
     
         public int rate(String from, String to) {
             if(from.equals(to)) return 1;
             return rates.get(new Pair(from, to)).intValue();
         }
     }
 ```

 Money.java
  <pre>
     <code>
     public class Money implements Expression {
     
         protected int amount;
         protected String currency;
     
         public Money(int amount, String currency) {
             this.amount = amount;
             this.currency = currency;
         }
     
         public static Money dollar(int amount) {
             return new Money(amount, "USD");
         }
     
         public static Money franc(int amount) {
             return new Money(amount, "CHF");
         }
     
         public Money times(int multiplier) {
             return new Money(amount * multiplier, currency);
         }
     
         public boolean equals(Object object) {
             Money money = (Money) object;
             return this.amount == money.amount && currency.equals(money.currency());
         }
     
         public String currency() {
             return this.currency;
         }
     
         public Expression plus(Money addend) {
             return new Sum(this, addend);
         }
     
         @Override
         public Money reduce(Bank bank,String to) {
             return new Money((amount / bank.rate(this.currency, to)), to);
         }
     
         @Override
         public String toString() {
             return "Money{" +
                     "amount=" + amount +
                     ", currency='" + currency + '\'' +
                     '}';
         }
     
     }
     </code>
  </pre>
  
 Sum.java
  <pre>
     <code>
     public class Sum implements Expression {
         Money augend;
         Money addend;
     
         public Sum(Money augend, Money addend) {
             this.augend = augend;
             this.addend = addend;
         }
     
         public Money reduce(Bank bank, String to) {
             int amount = this.augend.amount + this.addend.amount;
             return new Money(amount, to);
         }
     }
     </code>
  </pre>
 

# 15. 서로 다른 통화 더하기
  ## TODO
   * **$5 + 10CHF = $10(환율이 2:1 일 경우)**  
   * ~~$5 + $5 = 10$~~
   * $5 + $5 에서 Money 반환하기
   * ~~Bank.reduce(Money)~~
   * ~~Money 에 대한 통화 변환을 수행하는 Reduce~~
   * ~~Reduce(Bank, String)~~
   
Expression.java
```
    public interface Expression {
    
        Money reduce(Bank bank, String to);
    
        Expression plus(Expression addend);
    }
```

Money.java
```
    public class Money implements Expression {
    
        protected int amount;
        protected String currency;
    
        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }
    
        public static Money dollar(int amount) {
            return new Money(amount, "USD");
        }
    
        public static Money franc(int amount) {
            return new Money(amount, "CHF");
        }
    
        public Expression times(int multiplier) {
            return new Money(amount * multiplier, currency);
        }
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && currency.equals(money.currency());
        }
    
        public String currency() {
            return this.currency;
        }
    
        public Expression plus(Expression addend) {
            return new Sum(this, addend);
        }
    
        @Override
        public Money reduce(Bank bank, String to) {
            return new Money((amount / bank.rate(this.currency, to)), to);
        }
    
        @Override
        public String toString() {
            return "Money{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    
    }
```

Sum.java
```
    public class Sum implements Expression {
        Expression augend;
        Expression addend;
    
        public Sum(Expression augend, Expression addend) {
            this.augend = augend;
            this.addend = addend;
        }
    
        public Money reduce(Bank bank, String to) {
            int amount = this.augend.reduce(bank, to).amount + this.addend.reduce(bank, to).amount;
            return new Money(amount, to);
        }
    
        @Override
        public Expression plus(Expression addend) {
            return null;
        }
    }
```

Bank.java
```
    public class Bank {
    
        private Hashtable<Pair, Integer> rates = new Hashtable();
    
        public Money reduce(Expression source, String to) {
            return source.reduce(this, to);
        }
    
        public void addRate(String from, String to, int rate) {
            rates.put(new Pair(from, to), new Integer(rate));
        }
    
        public int rate(String from, String to) {
            if(from.equals(to)) return 1;
            return rates.get(new Pair(from, to)).intValue();
        }
    }
```

Pair.java
```
    public class Pair {
    
        private String from;
        private String to;
    
        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(from, pair.from) &&
                    Objects.equals(to, pair.to);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
```

# 16. 드디어, 추상화
  ## TODO
   * ~~$5 + 10CHF = $10(환율이 2:1 일 경우)~~ 
   * ~~$5 + $5 = 10$~~
   * $5 + $5 에서 Money 반환하기
   * ~~Bank.reduce(Money)~~
   * ~~Money 에 대한 통화 변환을 수행하는 Reduce~~
   * ~~Reduce(Bank, String)~~
   * Sum.plus
   * Expression.times

Money.java
```
    public class Money implements Expression {
    
        protected int amount;
        protected String currency;
    
        public Money(int amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }
    
        public static Money dollar(int amount) {
            return new Money(amount, "USD");
        }
    
        public static Money franc(int amount) {
            return new Money(amount, "CHF");
        }
    
        public Expression times(int multiplier) {
            return new Money(amount * multiplier, currency);
        }
    
        public boolean equals(Object object) {
            Money money = (Money) object;
            return this.amount == money.amount && currency.equals(money.currency());
        }
    
        public String currency() {
            return this.currency;
        }
    
        public Expression plus(Expression addend) {
            return new Sum(this, addend);
        }
    
        @Override
        public Money reduce(Bank bank, String to) {
            return new Money((amount / bank.rate(this.currency, to)), to);
        }
    
        @Override
        public String toString() {
            return "Money{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    
    }
```

Sum.java
```
    public class Sum implements Expression {
        Expression augend;
        Expression addend;
    
        public Sum(Expression augend, Expression addend) {
            this.augend = augend;
            this.addend = addend;
        }
    
        public Money reduce(Bank bank, String to) {
            int amount = this.augend.reduce(bank, to).amount + this.addend.reduce(bank, to).amount;
            return new Money(amount, to);
        }
    
        @Override
        public Expression plus(Expression addend) {
            return new Sum(this, addend);
        }
    
        @Override
        public Expression times(int multiplier) {
            return new Sum(augend.times(multiplier), addend.times(multiplier));
        }
    }
```

Expression.java
```
    public interface Expression {
    
        Money reduce(Bank bank, String to);
    
        Expression plus(Expression addend);
    
        Expression times(int multiplier);
    }
```

Bank.java
```
    public class Bank {
    
        private Hashtable<Pair, Integer> rates = new Hashtable();
    
        public Money reduce(Expression source, String to) {
            return source.reduce(this, to);
        }
    
        public void addRate(String from, String to, int rate) {
            rates.put(new Pair(from, to), new Integer(rate));
        }
    
        public int rate(String from, String to) {
            if(from.equals(to)) return 1;
            return rates.get(new Pair(from, to)).intValue();
        }
    }
```

Pair.java
```
    public class Pair {
    
        private String from;
        private String to;
    
        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(from, pair.from) &&
                    Objects.equals(to, pair.to);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
```

# 하.. 이해가 잘되지 않는다... 다시 처음부터 봐야겠다...
  테스트 먼저 작성하고 코드를 작성해나가는 것은 이해되지만,  
  코드의 중복을 제거해나가며 테스트를 신뢰하며 나아가야하는 내용도 알겠지만...  
  리팩토링을 하는데 갑자기 Bank, Expression, Pair 등과 같은 아이디어는  
  어떻게 도출해야하는지 잘모르겠다...  
  
  어떻게 도출해내는지 모르니 각 객체의 Role 을 이해못하고,  
  그래서 어디에 어떤 메서드를 배치해야할지 감을 못잡겠다.  

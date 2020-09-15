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
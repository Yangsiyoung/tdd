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
  * ~~**Dollar Side Effect**~~
  * Money 반올림
  
  
    
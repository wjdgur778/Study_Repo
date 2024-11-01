import java.util.HashSet;
import java.util.Objects;

public class Equals_HashCode {
    /* todo
        - equals() hashcode()는 모두 객체 비교를 할때 사용되는 메소드이다.
        - equals()는 모든 클레스의 상위 클래스인 Object의 메소드
        - Overriding해서 사용한다.
        - hashcode()는 객체를 위한 고유 정수값을 반환하며 해시기반 컬렉션에서 객체를 식별할 때 사용된다.
        - equals()와 hashcode()를 적절하게 오버라이딩해서 사용해야하는 이유는 해시기반 컬렉션에서는 HashCode()로 동일성을 체크하고
          이후 equals()로 동일성을 체크하기 때문에 해시기반 컬렉션을 적절하게 사용하기 위해서는 오버라이딩을 해주어야한다.
        - 해시 충돌을 해결하기위해 hashcode()로 비교 후 equals로 한번도 비교하는 것
     */
      /*
        TODO  < HashMap 내부에서의 동일성 체크 코드 >
           if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
     */
    static class Member {
        String email;
        int password;

        Member(String email, int password) {
            this.email = email;
            this.password = password;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true; // 참조 비교
            if (obj == null || getClass() != obj.getClass()) return false; // 클래스 확인
            Member member = (Member) obj;
            return password == member.password && email.equals(member.email); // 필드 값 비교
        }
        @Override
        public int hashCode(){
            //필드값을 기반으로 hash값
            return Objects.hash(email,password);
        }
    }


    public static void main(String[] args) {
        // 객체 주소는 다르지만 필드 내용이 같은 Member 인스턴스 2개 준비
        Member member1 = new Member("abc@@google.com", 123);
        Member member2 = new Member("abc@@google.com", 123);

        HashSet<Member> set = new HashSet<Member>();
        set.add(member1);
        // equals()를 필드 비교 로직으로 오버라이딩해서 구현하면 -> true
        // 오버라이딩 하지 않는다면 member1과 member2는 서로 주소가 다르기 때문에 -> false
        System.out.println(member1.equals(member2));

        //객체 주소 비교
        //주소가 다르기 때문에 -> false
        System.out.println(member1==member2);

        //Hashcode()를 Objects.hash()를 통해 오버라이딩 구현 -> equals()까지 오버라이딩 되어야 true
        //해시기반 컬렉션 내부 코드에서는 객체비교를 hashcode로 한 이후 equals로 비교를 하기 때문에
        //객체를 필드값에 따라 비교를 하고 싶다면 오버라이딩을 적절히 하여 사용해야한다.

        System.out.println(set.contains(member2));
    }
}

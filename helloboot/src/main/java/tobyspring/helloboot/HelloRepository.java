package tobyspring.helloboot;

public interface HelloRepository {

    Hello findHello(String name);

    void increaseCount(String name);

    // interface에서 default 메서드를 활용하면 구현해야 할 메서드가 줄어드는 이점이 있다.
    // Comparator interface를 분석하면 좋은 예시가 많이 있다.
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}

package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector2 implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector2(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // Iterable : forEach에서 사용가능하다.
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);


        List<String> autoConfigs = new ArrayList<>();

        // Java5 시절의 for 문
//        for(String candidate : candidates) {
//            autoConfigs.add(candidate);
//        }

        // ForEach가 도입된 후의 반복문
        candidates.forEach(autoConfigs::add);

        // StreamSupport도 사용 가능하다.
//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

        // List의 size보다 작은 사이즈의 배열을 매개변수로 주면, 무시되고 알아서 사이즈를 설정해서 반환해준다.
//        return autoConfigs.toArray(new String[0]);

        // Stream으로도 가능하다.
        return autoConfigs.stream().toArray(String[]::new);
    }
}

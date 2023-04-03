//package tobyspring.config;
//
//import org.springframework.context.annotation.DeferredImportSelector;
//import org.springframework.core.type.AnnotationMetadata;
//
//public class MyAutoConfigImportSelector implements DeferredImportSelector {
//
//    @Override
//    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//
//        return new String[] {
//
//                // 패키지까지 명시해서 등록할 클래스들을 문자열로 반환해야한다.
//                "tobyspring.config.autoconfig.DispatcherServletConfig",
//                "tobyspring.config.autoconfig.TomcatWebServerConfig"
//        };
//
//    }
//}

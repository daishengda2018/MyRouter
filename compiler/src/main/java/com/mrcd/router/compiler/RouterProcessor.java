package com.mrcd.router.compiler;

import com.google.auto.service.AutoService;
import com.mrcd.router.annotation.Router;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 * Router 注解的处理器
 *
 * 注意所有的注解处理器必须有一个无参的构造函数，默认不写就行
 */
// @AutoService(Processor.class)
@SupportedAnnotationTypes("com.mrcd.router.annotation.Router")
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class RouterProcessor extends AbstractProcessor {

    /**
     * init()方法会被注解处理工具调用，并输入 ProcessingEnvironment 参数。
     * ProcessingEnvironment 提供很多有用的工具类 Elements, Types 和 Filer
     * @param processingEnv 提供给 processor 用来访问工具框架的环境
     */
    @Override public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    /**
     * 这相当于每个处理器的主函数 main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数 RoundEnvironment，可以让你查询出包含特定注解的被注解元素
     * @param annotations   请求处理的注解类型
     * @param roundEnv  有关当前和以前的信息环境
     * @return  如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     *          如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        // roundEnv.getElementsAnnotatedWith()返回使用给定注解类型的元素
        for (Element element : roundEnv.getElementsAnnotatedWith(Router.class)) {
            System.out.println("------------------------------");
            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;
                // 输出元素名称
                System.out.println(typeElement.getSimpleName());
                // 输出注解属性值
                System.out.println(typeElement.getAnnotation(Router.class).path());
            }
            System.out.println("------------------------------");
        }

        return false;
    }

    /**
     *  此函数表明注解处理器是注册给哪个注解的。
     *  它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称（xxx.class.getCanonicalName()）
     * @return  注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override public Set<String> getSupportedAnnotationTypes() {
        final Set<String> result = new HashSet<>();
        result.add(Router.class.getCanonicalName());
        return result;
    }
}
package cube.dynamicJava;

import java.io.IOException;  
import java.lang.reflect.Method;  
import java.net.URI;  
import java.net.URL;  
import java.net.URLClassLoader;  
import java.util.Arrays;  
import javax.tools.JavaCompiler;  
import javax.tools.JavaCompiler.CompilationTask;  
import javax.tools.JavaFileObject;  
import javax.tools.SimpleJavaFileObject;  
import javax.tools.ToolProvider;
  
public class DynamicJava {  
  

  
    @SuppressWarnings({ "resource" })
    
    public static void exec(String code,String Classname,String methodname) throws Exception {   
         
        //获取编译器.注意,运行时需要jdk,单纯的jre没有编译器  
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
        JavaFileObject fileObject = new JavaStringObject(Classname,code);  
        //编译过程  
        CompilationTask task = javaCompiler.getTask(null, null, null,  
                Arrays.asList("-d", "./bin"), null, Arrays.asList(fileObject));  
        if (!task.call()) {  
            System.out.println("动态编译失败!");  
        } else {  
            System.out.println("动态编译成功！");  
  
            //成功以后，就利用发射来执行这个类了  
            URL[] urls = new URL[] { new URL("file:/" + "./bin/") };  
            URLClassLoader classLoader = new URLClassLoader(urls);  
            Class<?> clazz = classLoader.loadClass(Classname);  
            Method method = clazz.getDeclaredMethod(methodname);  
            method.invoke(clazz.newInstance());  
        }  
  
    }  
  
    static class JavaStringObject extends SimpleJavaFileObject {  
        private String code;  
   
        public JavaStringObject(String name, String code) {  
            super(URI.create(name + ".java"), Kind.SOURCE);  
            this.code = code;  
        }  
  
        @Override  
        public CharSequence getCharContent(boolean ignoreEncodingErrors)  
                throws IOException {  
            return code;  
        }  
    }  
}  
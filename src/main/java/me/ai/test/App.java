package me.ai.test;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
	@RequestMapping("/")
	String home(String name, String password) {
		try {
			Process process=Runtime.getRuntime().exec("python C:/Users/administrator/Desktop/Survey_Doc_Demo/Survey_Doc/setup.py install");
			
			InputStreamReader isr=new InputStreamReader(process.getInputStream());
			LineNumberReader lnr=new LineNumberReader(isr);
			String s;
			while((s=lnr.readLine())!=null){
				System.out.println(s);
			}
			isr.close();
			lnr.close();
			
			process.waitFor();
			process.destroy();
			
			Process process2=Runtime.getRuntime().exec("python C:/Users/administrator/Desktop/Survey_Doc_Demo/Survey_Doc/Main/main.py");
			InputStreamReader isr2=new InputStreamReader(process2.getInputStream());
			LineNumberReader lnr2=new LineNumberReader(isr2);
			String s2;
			while((s2=lnr2.readLine())!=null){
				System.out.println(s2);
			}
			isr2.close();
			lnr2.close();
			process2.waitFor();
			process2.destroy();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+" P: "+password);
		return "Gradle test "+System.currentTimeMillis();
	}
	
	@GetMapping("/test2")
	public Long test2(){
		Map map = System.getenv();  
		System.out.println(map.entrySet().stream().isParallel());
		Iterator it = map.entrySet().iterator();  
		while(it.hasNext())  
		{  
		    Entry entry = (Entry)it.next();  
		    System.out.print(entry.getKey()+"=");  
		    System.out.println(entry.getValue());  
		}  
		return System.currentTimeMillis();
	}
	
	@GetMapping("test3")
	public Long test3(){
		List<Integer> values = Arrays.asList(10, 20, 30, 40);
		long s=System.currentTimeMillis();
		values.parallelStream().mapToInt(a -> a).sum();
		values.parallelStream().forEach(System.out::println);//ÂÒÐòÊä³ö       forEachOrdered ÓÐÐòÊä³ö
		return System.currentTimeMillis()-s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(App.class, args);
	}

}

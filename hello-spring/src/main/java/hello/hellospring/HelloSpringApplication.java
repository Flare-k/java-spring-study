package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	// welcom page인 index.html이 보여진다.
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
/*
 * 빌드 방법: root-directory -> ./gradlew build 입력
 * cd build/libs/
 * java -jar ".jar 파일"  -> intelliJ가 아니라 실제 서버에 배포할 떄는
 * 파일만 복사해서 서버에서 실행시키면 된다.
 *  */

package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @ResponseBody
    @RequestMapping("/request-body-string-v1")
    public String requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("##### message body => {}", msgBody);
        return "OK";
    }


    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("##### message body => {}", msgBody);
        writer.write("OK");
    }


    @RequestMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String msgBody = httpEntity.getBody();
        log.info("##### message body => {}", msgBody);
        return new HttpEntity<>("OK");
    }


    @ResponseBody
    @RequestMapping("/requeset-body-string-v4")
    public String requestBodyStringV4(@RequestBody String msgBody) {
        log.info("##### message body => {}", msgBody);
        return "OK";
    }

}

package com.masai.controller;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.TopLanguage;
@CrossOrigin("*")
@RestController 
@RequestMapping("/prompt")

public class AIPromptController {  
   
   private final AiClient aiClient;
   
   
   @Autowired
   public AIPromptController(AiClient aiClient) { 
      this.aiClient = aiClient;  
   }
   
   @GetMapping("/hello")
   public ResponseEntity<String> testAiPrompt() {
      String prompt = "Hello! I will ask questions related to Spring Boot?";
      String response = aiClient.generate(prompt);
      return new ResponseEntity<>(response, HttpStatus.OK);
   }
   
   @GetMapping("/learn/{topic}")
   public ResponseEntity<String> getJoke(@PathVariable("topic") String topic) {

      PromptTemplate promptTemplate = new PromptTemplate("""
        I just started learning Spring. Can you provide me 
        fundamentals of {topic} to get started?
        Please provide me short and concise details in simple language.
      """);
      promptTemplate.add("topic", topic);
      String response = this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @GetMapping("/getLanguage/{year}")
   public ResponseEntity<TopLanguage> getTopLanguage(@PathVariable("year") int year) {

      BeanOutputParser<TopLanguage> parser = new BeanOutputParser<>(TopLanguage.class);

      String prompt = """
        Which is the top programming Language in year {year}?
        {format}
      """;

      PromptTemplate template = new PromptTemplate(prompt);
      template.add("year", year);
      template.add("format", parser.getFormat());
      template.setOutputParser(parser);

      System.out.println("Format String: " + parser.getFormat());

      Prompt promt = template.create();
      AiResponse aiResponse = aiClient.generate(promt);
      String text = aiResponse.getGeneration().getText();

      TopLanguage topLanguage = parser.parse(text);

      return new ResponseEntity<>(topLanguage, HttpStatus.OK);
   }
   
}

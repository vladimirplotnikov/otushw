package com.github.vladimirplotnikov.json;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class test {

    private static ObjectMapper mapper = new ObjectMapper() {
        private static final long serialVersionUID = -174113593500315394L;

        {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
            //configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

            setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    };
    @Test
    public void testSms() throws IOException {
        File file = new File("src/test/resources/sms-256866-480df9.json");

        ChatSessions result = mapper.readValue(file,ChatSessions.class);
        OutChatSessions outChatSessions = new OutChatSessions();
        OutChatSession outChatSession = new OutChatSession();
        List<String> tmpLast = new ArrayList<String>();
        List<String> tmpBelongNumber = new ArrayList<String>();
        List<String> uniqueBelongNumber = new ArrayList<String>();
        List<OutChatSession> tmpOutChatSession = new ArrayList<>();
        for (ChatSession chatSession :result.getChatSessions()
             ) {
            outChatSession.setChatIdentifier(chatSession.getChatIdentifier());
            for (Member member:chatSession.getMembers()
                 ) {
                tmpLast.add(member.getLast());
            }
            outChatSession.setOutLast(tmpLast);
            for (Message message:chatSession.getMessages()
                 ) {
                tmpBelongNumber.add(message.getBelongNumber());
            }
            ////get unique numbers for grouping by it
            uniqueBelongNumber= tmpBelongNumber.stream().distinct().toList();

            for (String belong_number:uniqueBelongNumber
                 ) {
                outChatSession.setBelong_number(belong_number);
                OutChatSession tmpChatSession = new OutChatSession();
                tmpChatSession.setChatIdentifier(chatSession.getChatIdentifier());
                tmpChatSession.setBelong_number(belong_number);
                tmpChatSession.setOutLast(tmpLast);
                System.out.println(belong_number);
                List <OutTxt> listOutTxt = new ArrayList<>();
                OutTxt outTxt = new OutTxt();
                //search messages belonged to this number
                for (ChatSession cs :result.getChatSessions()
                     ) {  for (Message msg:chatSession.getMessages()
                ) { if (msg.getBelongNumber().toString().equals(belong_number)) {
                    outTxt.setTxt(msg.getText());
                    listOutTxt.add(outTxt);
                    System.out.println(msg.getText());}
                }
                    tmpChatSession.setTxt(listOutTxt);
                }
                tmpOutChatSession.add(tmpChatSession);
                outChatSessions.setOutChatSessions(tmpOutChatSession);
            }
        }

        String jsonString = mapper.writeValueAsString(outChatSessions);
        try (PrintWriter out = new PrintWriter("src/test/resources/sms-256866-480df9.json.out")) {
            out.println(jsonString);
        }
    }

    @Test
    void jsonFileToPojo() throws IOException {
        File file = new File("src/test/resources/employee.json");
        Employee employee = mapper.readValue(file, Employee.class);
        System.out.println(employee.getLastName().toString());
    }

    @Test
    void jsonStringToPojo() throws JsonProcessingException {
        String employeeJson = "{\n" +
                " \"firstName\" : \"Jalil\",\n" +
                " \"lastName\" : \"Jarjanazy\",\n" +
                " \"age\" : 30\n" +
                "}";

        Employee employee = mapper.readValue(employeeJson, Employee.class);
        System.out.println(employee.getLastName().toString());
    }

    @Test
    void fileToListOfPojos() throws IOException {
        File file = new File("src/test/resources/employeeList.json");
        List<Employee> employeeList = mapper.readValue(file, new TypeReference<>() {
        });
        System.out.println(employeeList.get(0).getLastName());
    }
}
